package com.study.business;

public abstract class AbstractBusiness implements Business {

	public void destroy() {
		System.out.println("destroy...");

	}

	public void doit() {
	 init();
	 doBusiness();
	 destroy();

	}

	public Object getResult() {
		// TODO Auto-generated method stub
		return null;
	}

	public void init() {
		System.out.println("init...");

	}

	public void setBizData(Object obj) {
		// TODO Auto-generated method stub

	}

}
