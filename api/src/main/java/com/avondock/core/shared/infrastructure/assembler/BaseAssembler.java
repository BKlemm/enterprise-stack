package com.avondock.core.shared.infrastructure.assembler;

import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public abstract class BaseAssembler<T, D extends RepresentationModel<?>> extends RepresentationModelAssemblerSupport<T, D> {

    protected List<String> expand = new ArrayList<>();

    public BaseAssembler(Class<?> controllerClass, Class<D> resourceType) {
        super(controllerClass, resourceType);
    }

    public BaseAssembler<T, D> setExpand(Optional<String> expand) {
        this.expand = expand.map(s -> Arrays.asList(s.split(",", -1))).orElse(null);
        return this;
    }
}
