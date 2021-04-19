package com.eb.property.management.api.utils;

import com.eb.property.management.api.persistence.dto.GenericRes;

public interface ResponseUtils {

	public static <T> GenericRes<T> success(T t) {
		return GenericRes.<T>builder().data(t).message("success").build();
	}

	public static <T> GenericRes<T> success(T t, String message) {
		return GenericRes.<T>builder().data(t).message(message).build();
	}
	
	public static <T> GenericRes<T> error(T t) {
		return GenericRes.<T>builder().data(t).status(false).build();
	}
	
	public static <T> GenericRes<T> error(String msg) {
		return GenericRes.<T>builder().message(msg).status(false).build();
	}

	public static <T> GenericRes<T> error(String msg, String type) {
		return GenericRes.<T>builder().message(msg).type(type).status(false).build();
	}

	public static <T> GenericRes<T> error(T t, String type) {
		return GenericRes.<T>builder().data(t).type(type).status(false).build();
	}

	public static <T> GenericRes<T> error(String msg, String type, String statusCode) {
		return GenericRes.<T>builder().message(msg).type(type).status(false).code(statusCode).build();
	}

}
