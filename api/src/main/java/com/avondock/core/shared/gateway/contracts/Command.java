package com.avondock.core.shared.gateway.contracts;

public interface Command<T> {
    T getIdentity();
}
