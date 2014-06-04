package com.study.datasource;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;


public class DatasourceAdvice implements MethodBeforeAdvice {

	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		String className = target.getClass().getName();//com.study.business.QueryOrders
		className = className.substring(className.lastIndexOf(".")+1,className.length());//QueryOrders
		
		//���className ���ַ���Query������Ϊ�ǲ�ѯ��������ָ����slave����Դ
		if(className.startsWith("Query") || className.indexOf("Login")!=-1)
		{
			System.out.println("�л����˴ӷ�����");
			DatasourceSwitch.setSlave();
		}
		else
		{
			System.out.println("�л�������������");
			DatasourceSwitch.setMaster();
		}
	}

}
