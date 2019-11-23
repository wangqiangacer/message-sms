package com.jacken.messagesms.result;


import java.io.Serializable;

public class ResultModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	 
	public static final int CODE_SUCCESS = 0;
	public static final int CODE_FAILURE = 1;
	public static final String RESULT_SUCCESS = "success";
	public static final String RESULT_FAIL = "fail";

	private int code = CODE_SUCCESS;
	private T data;
	private String message;
	private String result = RESULT_SUCCESS;
	private int totalCount;

	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	private ResultModel() {
	}
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

	public static <T extends Object> ResultModel<T> createSuccess() {
		ResultModel<T> result = new ResultModel<T>();
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static <T extends Object> ResultModel<T> createSuccess(T data) {
		ResultModel<T> result = ResultModel.createSuccess();
		if (data instanceof PagerList) {
			result.setTotalCount(((PagerList)data).getCount());
		}
		result.setData(data);
		return result;
	}
	
	public static <T extends Object> ResultModel<T> createSuccess(T data, int totalCount) {
		ResultModel<T> result = ResultModel.createSuccess();
		result.setTotalCount(totalCount);
		result.setData(data);
		return result;
	}

	public static <T extends Object> ResultModel<T> createSuccess(T data, Long totalCount) {
		ResultModel<T> result = ResultModel.createSuccess();
		if (totalCount == null) {
			result.setTotalCount(0);
		}else{
			result.setTotalCount(Long.valueOf(totalCount).intValue());
		}
		result.setData(data);
		return result;
	}
	
	public static <T extends Object> ResultModel<T> createFail(int code) {
		return createFail(code, null);
	}

	public static <T extends Object> ResultModel<T> createFail(int code, String message) {
		ResultModel<T> result =  new ResultModel<T>();
		result.setMessage(message);
		result.setResult(RESULT_FAIL);
		result.setCode(code);
		return result;
	}

}
