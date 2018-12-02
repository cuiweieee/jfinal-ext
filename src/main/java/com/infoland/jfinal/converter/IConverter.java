package com.infoland.jfinal.converter;

public interface IConverter<T> {

	T convert(String s) throws Exception;
}
