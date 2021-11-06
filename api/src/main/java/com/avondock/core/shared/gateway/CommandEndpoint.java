package com.avondock.core.shared.gateway;

import com.avondock.core.common.Assembler;
import com.avondock.core.shared.gateway.contracts.Command;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

abstract public class CommandEndpoint {

    protected final CommandGateway commandGateway;
    protected final Assembler    assembler;

    public <T> CommandEndpoint(CommandGateway commandGateway, Assembler assembler) {
        this.commandGateway = commandGateway;
        this.assembler = assembler;
    }

    protected CompletableFuture<ResponseEntity<?>> send(Command command) {
        return commandGateway.send(command).thenApply(ResponseEntity::ok);
    }

    protected <T> ResponseEntity<?> sendCreate(Command command, String identity, RepresentationModel<?> model) {
        commandGateway.sendAndWait(command);
        return createdResponse(model, identity);
    }

    protected <T> ResponseEntity<?> sendUpdate(Command command, String identity, RepresentationModel<?> model) {
        commandGateway.sendAndWait(command);
        return acceptedResponse(model, identity);
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
        return model == null  ? ResponseEntity.notFound().build() : new ResponseEntity<>(model, status);
    }
}
