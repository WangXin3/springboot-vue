package com.wxx.springbootvue.util;

/**
 * @author Wang
 */
public class RespBean {
	private Integer status;
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

	public static RespBean success(Object data) {
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

	private RespBean(Integer status, String msg, Object data) {
		this.status = status;
		this.msg = msg;
		this.data = data;
	}

	public Integer getStatus() {
		return status;
	}

	public RespBean setStatus(Integer status) {
		this.status = status;
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
