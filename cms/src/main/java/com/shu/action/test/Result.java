package com.shu.action.test;
/** 
 * @Desc: ()
 * @author: 张瑞
 * @date: 2016年11月10日 下午6:13:32  
 * @email:2411685663@qq.com 
 */
public class Result {
	private String result;
	private String message;
	
	public Result() {
	}
	
	public Result(String result, String message) {
		super();
		this.result = result;
		this.message = message;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
