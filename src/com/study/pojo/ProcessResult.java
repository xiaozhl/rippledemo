package com.study.pojo;

public class ProcessResult {
	private boolean successFlag=false;
	private String errormsg="";
	public boolean isSuccess() {
		return successFlag;
	}
	public void setSuccessFlag(boolean successFlag) {
		this.successFlag = successFlag;
	}
	public String getErrormsg() {
		return errormsg;
	}
	public void setErrormsg(String errormsg) {
		this.errormsg = errormsg;
	}
	
}
