package com.study.test;

import org.junit.Test;
import org.sphx.api.SphinxClient;
import org.sphx.api.SphinxException;
import org.sphx.api.SphinxMatch;
import org.sphx.api.SphinxResult;

public class TestSearch {
	@Test
	public void search() throws SphinxException
	{
		String host ="192.168.59.101";
		int port = 9312;
		int mode = SphinxClient.SPH_MATCH_ANY;//BAIDU模式
		
		//构建客户端
		SphinxClient client = new SphinxClient();
		client.SetServer(host, port);
		client.SetMatchMode ( mode );
		client.SetLimits ( 0, 10 );//理解为分页
		client.SetMaxQueryTime(3000);
		
		
		//执行搜索，第一个参数是关键字，第二个参数是索引
		SphinxResult result = client.Query("java","*");
		SphinxMatch[] matches = result.matches;
		
		for(SphinxMatch match : matches)
		{
			System.out.println(match.docId);
		}
		
	}
}
