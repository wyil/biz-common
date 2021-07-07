package com.aspire.workflow.exception;

/**
 * 业务逻辑异常.
 * 
 * @author xiongzy
 * 
 */
public class BusinessException extends RuntimeException {
	/**
	 * 构造.
	 * 
	 * @param msg
	 *            错误信息.
	 */
	public BusinessException(String msg) {
		super(msg);
	}

	/**
	 * 构造.
	 * 
	 * @param msg
	 *            错误信息
	 * @param t
	 *            前一异常
	 */
	public BusinessException(String msg, Throwable t) {
		super(msg, t);

		this.setStackTrace(t.getStackTrace());
	}
}
