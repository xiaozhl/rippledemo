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
		int mode = SphinxClient.SPH_MATCH_ANY;//BAIDUģʽ
		
		//�����ͻ���
		SphinxClient client = new SphinxClient();
		client.SetServer(host, port);
		client.SetMatchMode ( mode );
		client.SetLimits ( 0, 10 );//���Ϊ��ҳ
		client.SetMaxQueryTime(3000);
		
		
		//ִ����������һ�������ǹؼ��֣��ڶ�������������
		SphinxResult result = client.Query("java","*");
		SphinxMatch[] matches = result.matches;
		
		for(SphinxMatch match : matches)
		{
			System.out.println(match.docId);
		}
		
	}
}
