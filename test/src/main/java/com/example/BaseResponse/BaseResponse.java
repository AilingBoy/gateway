package com.example.BaseResponse;

import lombok.Data;
import org.springframework.util.StringUtils;



@Data
public class BaseResponse<T> {

	/**
	 * 返回码
	 */
	private Integer code;
	/**
	 * 消息
	 */
	private String msg;
	/**
	 * 返回
	 */
	private T data;

	public BaseResponse() {

	}

	public BaseResponse(Integer code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public static <U> BaseResponse<U> success(U data) {
		return new BaseResponse<>(200, "请求成功", data);
	}

	public static <U> BaseResponse<U> failure() {
		return new BaseResponse<>(500, "请求失败", null);
	}

	public static <U> BaseResponse<U> failure(String msg) {
		return new BaseResponse<>(500, msg,null);
	}

	public static <U> BaseResponse<U> failure( String msg, U data) {
		return new BaseResponse<>(500, msg ,data);
	}

	public static <U,E extends Exception> BaseResponse<U> exception(E ex) {
		return failure(ex.getMessage());
	}

}
