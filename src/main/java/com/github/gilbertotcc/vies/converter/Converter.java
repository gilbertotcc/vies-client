package com.github.gilbertotcc.vies.converter;

public interface Converter<T,U> {

  U convert(T object);
}
