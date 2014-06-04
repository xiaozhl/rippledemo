package com.study.business;

import java.util.ArrayList;
import java.util.List;

import org.sphx.api.SphinxClient;
import org.sphx.api.SphinxException;
import org.sphx.api.SphinxMatch;
import org.sphx.api.SphinxResult;

import com.study.dao.BookDAO;
import com.study.pojo.Book;

public class SearchBiz extends AbstractBusiness {
	private List<Book> books;
	private String keywords;
	private BookDAO bookdao;
	private String host= "localhost";
	private int port = 9312;
	private int mode = SphinxClient.SPH_MATCH_ANY;//BAIDU模式
	public void doBusiness() {
		//构建书籍id列表
		List<Long> ids = new ArrayList<Long>();
		
		//构建客户端
		SphinxClient client = new SphinxClient();
		try
		{
			client.SetServer(host, port);
			client.SetMatchMode ( mode );
			client.SetLimits ( 0, 10 );//理解为分页
			client.SetMaxQueryTime(3000);
			
			
			//执行搜索，第一个参数是关键字，第二个参数是索引
			SphinxResult result = client.Query(keywords,"*");
			SphinxMatch[] matches = result.matches;
			
			for(SphinxMatch match : matches)
			{
				ids.add(match.docId);
			}
			
			books = bookdao.queryBooksByIds(ids);
		}
		catch(SphinxException e)
		{
			
		}

	}

	@Override
	public Object getResult() {
		
		return books;
	}

	@Override
	public void setBizData(Object obj) {
		this.keywords=(String) obj;
	}

	public void setBookdao(BookDAO bookdao) {
		this.bookdao = bookdao;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}

}
