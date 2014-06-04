package com.study.business;

import java.net.URI;
import java.net.URISyntaxException;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import com.study.dao.OrderDAO;
import com.study.pojo.BookstoreConstant;
import com.study.pojo.Order;
import com.study.pojo.ProcessResult;

/**
 *  ͨ���������֧��
 * @author Administrator
 *
 */
public class PayBiz extends AbstractBusiness{	
	private OrderDAO orderdao;
	private String json;
	private long orderid;
	private ProcessResult payresult = new ProcessResult();
	
	public void doBusiness()
	{
		//ͨ��websocket���֧��
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
					payresult.setSuccessFlag(false);
					payresult.setErrormsg("websocket����:"+e.getMessage());
					this.close();
				}

				@Override
				public void onMessage(String msg) {
					//�������˻���Ϣ�˻��Զ����ñ�����
					//У��msg������ɹ����ף����޸Ķ���״̬,�˴�Ϊ��У�飬�����汾��ǿ�޸�
					if(msg.indexOf("tesSUCCESS") != -1 && msg.indexOf("The transaction was applied.") != -1)
					{
					
						//�޸Ķ���״̬
						Order order = orderdao.queryOrderById(orderid);
						order.setStatus(BookstoreConstant.ORDER_PAY_NOTSENT);
						orderdao.updateOrder(order);
						payresult.setSuccessFlag(true);
						payresult.setErrormsg("���׳ɹ�");
					
					}
					else
					{
						payresult.setSuccessFlag(false);
						payresult.setErrormsg("����ʧ�ܣ���������");
					}
					
					this.close();
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
				payresult.setSuccessFlag(false);
				payresult.setErrormsg("����ʧ��");
				
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//json��ϢҪ���룬����idҲ����
	public void setBizData(Object obj)
	{
	List params = (List) obj;
	this.json = (String) params.get(0);
	this.orderid = (Long) params.get(1);
	}
	
	public Object getResult()
	{
		return payresult;
	}

	public void setOrderdao(OrderDAO orderdao) {
		this.orderdao = orderdao;
	}

}
