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
	private int mode = SphinxClient.SPH_MATCH_ANY;//BAIDUģʽ
	public void doBusiness() {
		//�����鼮id�б�
		List<Long> ids = new ArrayList<Long>();
		
		//�����ͻ���
		SphinxClient client = new SphinxClient();
		try
		{
			client.SetServer(host, port);
			client.SetMatchMode ( mode );
			client.SetLimits ( 0, 10 );//���Ϊ��ҳ
			client.SetMaxQueryTime(3000);
			
			
			//ִ����������һ�������ǹؼ��֣��ڶ�������������
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
