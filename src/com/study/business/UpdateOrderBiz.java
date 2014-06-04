package com.study.business;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.study.dao.OrderDAO;
import com.study.pojo.BookstoreConstant;
import com.study.pojo.Order;
import com.study.pojo.ProcessResult;

public class UpdateOrderBiz extends AbstractBusiness {
	//����޸Ķ���״̬����Ҫ�ṩripple֧������Ͷ���ID
	private String txid;
	private long orderid;
	//����ҵ��ִ�н��
	private ProcessResult result = new ProcessResult();
	private CountDownLatch ct = new CountDownLatch(1);
	private OrderDAO orderdao;
		
	public void doBusiness() {
	 //RippleУ��
	 //��Ҫ�������˵������Ƿ���ȷ������֧�����������¼��飬��������һ��DEMO��������ȫ
		String json = checkRippleTx();
		WebSocketClient client = null;
		try {
			client = new WebSocketClient(new URI(BookstoreConstant.RIPPLE_NETWORK)){

				@Override
				public void onClose(int arg0, String arg1, boolean arg2) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onError(Exception e) {
					//������ӳ�����������
					result.setSuccessFlag(false);
					result.setErrormsg("websocket����:"+e.getMessage());
					this.close();
					ct.countDown();
				}

				@Override
				public void onMessage(String msg) {
					//�������˻���Ϣ�˻��Զ����ñ�����
					//У��msg������ɹ����ף����޸Ķ���״̬,�˴�Ϊ��У�飬�����汾��ǿ�޸�
					if(msg.indexOf("success") != -1)
					{
						//�޸Ķ���״̬
						Order order = orderdao.queryOrderById(orderid);
						order.setStatus(BookstoreConstant.ORDER_PAY_NOTSENT);
						orderdao.updateOrder(order);
						result.setSuccessFlag(true);
						result.setErrormsg("���׳ɹ�");
						ct.countDown();
					
					}
					else
					{
						result.setSuccessFlag(false);
						result.setErrormsg("�޸�ʧ��");
						ct.countDown();
					}
					
					
				}

				@Override
				public void onOpen(ServerHandshake arg0) {
					
					
				}
				
				
			};
			
			if(client.connectBlocking())
			{
				//�������true����ʾ���ӳɹ�
				client.send(json);
				
			}
			else
			{
				//����false����ʾ����ʧ��
				result.setSuccessFlag(false);
				result.setErrormsg("����ʧ��");
				ct.countDown();
				
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//������Ҫʹ�ö��̵߳ȴ�������������֤��ȷ
		try {
			ct.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setOrderdao(OrderDAO orderdao) {
		this.orderdao = orderdao;
	}
	
	@Override
	public void setBizData(Object obj) {
		List params = (List) obj;
		this.txid = (String) params.get(0);
		this.orderid = (Long) params.get(1);
	}

	@Override
	public Object getResult() {
		return this.result;
	}

	private String checkRippleTx()
	{
		String json = "{\"id\": 1," +
				"\"command\": \"tx\"," +
				"\"transaction\": \""+txid+"\"}";
		
		return json;
	}

}
