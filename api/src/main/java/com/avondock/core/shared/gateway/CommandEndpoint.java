package com.avondock.core.shared.gateway;

import com.avondock.core.shared.gateway.contracts.Command;
import com.avondock.core.shared.gateway.contracts.DataTransferObject;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.CompletableFuture;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RequestMapping("/api/v1")
abstract public class CommandEndpoint {

    protected final CommandGateway commandGateway;

    public CommandEndpoint(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    protected CompletableFuture<ResponseEntity<?>> send(Command command) {
        return commandGateway.send(command).thenApply(ResponseEntity::ok);
    }

    protected <T> ResponseEntity<?> sendCreate(RepresentationModel<?> entity, String identity) {
        commandGateway.sendAndWait(entity);
        return createdResponse(entity, identity);
    }

    protected <T> ResponseEntity<?> sendUpdate(RepresentationModel<?> entity, String identity) {
        commandGateway.sendAndWait(entity);
        return acceptedResponse(entity, identity);
    }

    protected <T> ResponseEntity<?> acceptedResponse(RepresentationModel<?> entity, String identity) {
        entity.add(linkTo(this.getClass()).slash(identity).withSelfRel());
        return response(entity, HttpStatus.ACCEPTED);
    }

    protected <T> ResponseEntity<?> createdResponse(RepresentationModel<?> entity, String identity)  {
        entity.add(linkTo(this.getClass()).slash(identity).withSelfRel());
        return response(entity, HttpStatus.CREATED);
    }

    protected <T> ResponseEntity<?> response(RepresentationModel<?> entity, HttpStatus status) {
        return entity == null  ? ResponseEntity.notFound().build() : new ResponseEntity<>(entity, status);
    }
}
