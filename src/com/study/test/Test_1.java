package com.study.test;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.business.Business;
import com.study.pojo.Book;

public class Test_1 {
	
	@Test
	public void testQueryBooks()
	{
		ApplicationContext ac = new ClassPathXmlApplicationContext("beans.xml");
		Business querybooks = (Business) ac.getBean("querybooks");
		querybooks.doit();
		
		List<Book> books = (List<Book>) querybooks.getResult();
		for(Book book : books)
		{
			System.out.println(book.getTitle());
		}
	}
	
	@Test
	public void test2()
	{
		String rnd = UUID.randomUUID().toString();
		
		String r  = rnd.replaceAll("-","");
		System.out.println(r);
	}
	
}
