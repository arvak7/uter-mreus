package com.uter.commons.parser;

public interface Parser<T,S> {
    public S parse(T original);
    public T unparse(S original);
}
