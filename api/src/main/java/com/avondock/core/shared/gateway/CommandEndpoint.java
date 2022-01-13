package com.avondock.core.shared.gateway;

import com.avondock.core.shared.gateway.contracts.Command;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

abstract public class CommandEndpoint {

    protected final CommandGateway commandGateway;

    public <T> CommandEndpoint(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    protected <T> CompletableFuture<ResponseEntity<?>> send(Command<T> command) {
        return commandGateway.send(command).thenApply(ResponseEntity::ok);
    }

    protected <T> ResponseEntity<?> sendCreate(Command<T> command, RepresentationModel<?> model) {
        Object identity = commandGateway.sendAndWait(command);
        return createdResponse(model, identity.toString());
    }

    protected <T> ResponseEntity<?> sendUpdate(Command<T> command, RepresentationModel<?> model) {
        commandGateway.sendAndWait(command);
        return acceptedResponse(model, command.getIdentity().toString());
    }

    protected <T> ResponseEntity<?> acceptedResponse(RepresentationModel<?> model, String identity) {
        model.add(linkTo(this.getClass()).slash(identity).withSelfRel());
        return response(model, HttpStatus.ACCEPTED);
    }

    protected <T> ResponseEntity<?> createdResponse(RepresentationModel<?> model, String identity)  {
        model.add(linkTo(this.getClass()).slash(identity).withSelfRel());
        return response(model, HttpStatus.CREATED);
    }

    protected <T> ResponseEntity<?> response(RepresentationModel<?> model, HttpStatus status) {
        MultiValueMap<String, String> header = new HttpHeaders();
        header.add("Request-Id", UUID.randomUUID().toString());
        return model == null  ? ResponseEntity.notFound().build() : new ResponseEntity<>(
                model, header, status
        );
    }
}
