package com.study.business;

public interface Business {
	
	public void init();
	public void doBusiness();
	public void destroy();
	
	public void doit();
	
	public void setBizData(Object obj);
	public Object getResult();
}
