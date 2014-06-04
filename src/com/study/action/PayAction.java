package com.study.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.study.business.Business;
import com.study.pojo.BookstoreConstant;
import com.study.pojo.ProcessResult;

public class PayAction {
	private String userwallet;
	private long orderid;
	private double money;
	private File walletsecret;
	private ProcessResult result;
	private Business payBiz;
	
	public void setPayBiz(Business payBiz) {
		this.payBiz = payBiz;
	}

	public File getWalletsecret() {
		return walletsecret;
	}

	public void setWalletsecret(File walletsecret) {
		this.walletsecret = walletsecret;
	}

	public String getUserwallet() {
		return userwallet;
	}

	public void setUserwallet(String userwallet) {
		this.userwallet = userwallet;
	}

	public long getOrderid() {
		return orderid;
	}

	public void setOrderid(long orderid) {
		this.orderid = orderid;
	}

	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}

	public ProcessResult getResult() {
		return result;
	}

	public String execute() throws IOException {
		// 接收到客户端传过来的 orderid, 用户钱包，用户密码，
		// 生成一个JSON消息发送给ripple网络
		//读取walletsecret内容
		BufferedReader br =new BufferedReader(new InputStreamReader(new FileInputStream(walletsecret)));

		String secret = br.readLine();
		
		String json = genJSON(secret);
		System.out.println(json);
		List params = new ArrayList();
		params.add(json);
		params.add(orderid);
		
		payBiz.setBizData(params);
		payBiz.doit();
		result = (ProcessResult) payBiz.getResult();
		return "PAYRESULT";
	}

	public String genJSON(String secret) {
		String json = "{\"command\" : \"submit\"," +
				"\"tx_json\" : {\"TransactionType\" : " +
				"\"Payment\"," +
				"\"Account\" : \""+this.userwallet+"\"," +
				"\"Destination\" : \""+BookstoreConstant.SELLER_WALLET+"\"," +
				"\"Amount\" : " +
				"{\"currency\" : \"CNY\"," +
				"\"value\" : \""+this.money+"\"," +
				"\"issuer\" : \""+BookstoreConstant.DEFAULT_GATEWAY+"\"}}," +
				"\"secret\" : \""+secret+"\"}";

		return json;
	}
}
