package com.study.business;

import java.util.List;

import com.study.dao.BookDAO;
import com.study.pojo.ProcessResult;

public class RemoveBooksBiz extends AbstractBusiness {
	private List<Long> ids;
	private BookDAO bookdao;
	//返回处理结果
	private ProcessResult result = new ProcessResult();
	public void doBusiness() {
		try {
			bookdao.removeBooks(ids);
			result.setSuccessFlag(true);
		} catch (RuntimeException e) {
			result.setErrormsg(e.getMessage());
			result.setSuccessFlag(false);
		}

	}
	
	public void setBookdao(BookDAO bookdao) {
		this.bookdao = bookdao;
	}

	@Override
	public void setBizData(Object obj) {
		// TODO Auto-generated method stub
		this.ids = (List<Long>) obj;
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return this.result;
	}
	
	
}
