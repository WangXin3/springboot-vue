package com.wxx.springbootvue.system.util;

/**
 * @author Wang
 */
public class RespBean {
	private Integer code;
	private String msg;
	private Object data;

	public static RespBean build() {
		return new RespBean();
	}

	public static RespBean success(String msg) {
		return new RespBean(200, msg, null);
	}

	public static RespBean success(String msg, Object data) {
		return new RespBean(200, msg, data);
	}

	public static RespBean successData(Object data) {
		return new RespBean(200, null, data);
	}

	public static RespBean error(String msg) {
		return new RespBean(500, msg, null);
	}

	public static RespBean error(String msg, Object data) {
		return new RespBean(500, msg, data);
	}

	private RespBean() {
	}

	private RespBean(Integer code, String msg, Object data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public RespBean setCode(Integer code) {
		this.code = code;
		return this;
	}

	public String getMsg() {
		return msg;
	}

	public RespBean setMsg(String msg) {
		this.msg = msg;
		return this;
	}

	public Object getData() {
		return data;
	}

	public RespBean setData(Object data) {
		this.data = data;
		return this;
	}
}
