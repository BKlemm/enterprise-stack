package com.avondock.core.shared.infrastructure.factory;

import org.modelmapper.ModelMapper;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

abstract public class CommandFactoryImpl {

    Constructor<?> ctor;
    protected ModelMapper mapper;

    public CommandFactoryImpl() {
        this.mapper = new ModelMapper();
    }

    public Constructor<?> construct(Class<?> command, Class<?> ...args) {
        try {
            ctor = command.getDeclaredConstructor(args);
        } catch (NoSuchMethodException noSuchMethodException) {
            return null;
        }
        return ctor;
    }

    public Object createInstance(Object ...args) {
        try {
            return ctor.newInstance(args);
        } catch(InvocationTargetException | IllegalAccessException | InstantiationException invocationTargetException) {
            return null;
        }
    }
}
