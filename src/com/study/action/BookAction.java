package com.study.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.study.bookstore.tools.Tools;
import com.study.business.Business;
import com.study.pojo.Book;
import com.study.pojo.BookType;
import com.study.pojo.BooksResult;
import com.study.pojo.ProcessResult;
import com.study.pojo.Seller;

public class BookAction {
	// 由Spring注入具体的实例
	private Business querybooks;
	private Business addBook;
	private Business removeBooks;
	private List<Long> ids;
	// 返回结果？JSON
	private BooksResult booksresult=new BooksResult();
	// 从URL传入查询第几页，多少条记录
	private int pageNum;
	private int maxrows = 20;
	// 分类
	private BookType booktype;
	
	//按照卖家找出书籍，所需要的seller参数
	private Seller seller;
	//如果查询单本书，返回book对象
	private Book book;
	
	private ProcessResult result = new ProcessResult();
	
	private File uploadPic;
	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public BooksResult getBooksresult() {
		return booksresult;
	}

	public void setBooksresult(BooksResult booksresult) {
		this.booksresult = booksresult;
	}

	public void setQuerybooks(Business querybooks) {
		this.querybooks = querybooks;
	}

	public void setAddBook(Business addBook) {
		this.addBook = addBook;
	}

	public int getPage() {
		return pageNum;
	}

	public void setPage(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getRows() {
		return maxrows;
	}

	public void setRows(int maxrows) {
		this.maxrows = maxrows;
	}

	public BookType getBooktype() {
		return booktype;
	}

	public void setBooktype(BookType booktype) {
		this.booktype = booktype;
	}

	public File getUploadPic() {
		return uploadPic;
	}

	public void setUploadPic(File uploadPic) {
		this.uploadPic = uploadPic;
	}

	public ProcessResult getResult() {
		return result;
	}

	public void setResult(ProcessResult result) {
		this.result = result;
	}

	public Seller getSeller() {
		return seller;
	}

	public void setSeller(Seller seller) {
		this.seller = seller;
	}

	public void setRemoveBooks(Business removeBooks) {
		this.removeBooks = removeBooks;
	}

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public String queryBooks()
	{
		int startrow = pageNum * maxrows - maxrows ;
		List params = new ArrayList();
		params.add(startrow);
		params.add(maxrows);
		params.add(booktype);
		
		if(seller != null)
		{
			params.add(seller);
		}
		
		querybooks.setBizData(params);
		querybooks.doit();
		this.booksresult = (BooksResult) querybooks.getResult();
		
		
		return "JSON";
	}
	
	public String getBookById()
	{
		
		 querybooks.setBizData(book.getBookid());
		 querybooks.doit();
		 book = (Book) querybooks.getResult();
		 return "DISPLAYBOOK";
	}
	
	public String addBook() throws IOException
	{
		if(uploadPic !=null)
		{
			//获得upload的绝对路径
			String path= ServletActionContext.getServletContext().getRealPath("img");
			String fname = Tools.getRandFileName()+".jpg";
			path = path + "/"+ fname;
			//1、上传文件
			FileUtils.copyFile(uploadPic, new File(path));
			book.setPic(fname);
		}
		addBook.setBizData(book);
		addBook.doit();
		result.setSuccessFlag(true);
		
		return "JSONSUCCESS";
	}
	
	public String removeBooks()
	{
		removeBooks.setBizData(ids);
		removeBooks.doit();
		this.result = (ProcessResult) removeBooks.getResult();
		return "JSONSUCCESS";
	}
	
}
