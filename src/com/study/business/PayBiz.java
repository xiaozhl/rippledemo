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
 *  通过此类完成支付
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
		//通过websocket完成支付
		WebSocketClient client = null;
		try {
			client = new WebSocketClient(new URI(BookstoreConstant.RIPPLE_NETWORK)){

				@Override
				public void onClose(int arg0, String arg1, boolean arg2) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void onError(Exception e) {
					//如果连接出错，调用这里
					payresult.setSuccessFlag(false);
					payresult.setErrormsg("websocket错误:"+e.getMessage());
					this.close();
				}

				@Override
				public void onMessage(String msg) {
					//如果服务端回消息了会自动调用本方法
					//校验msg，如果成功交易，则修改订单状态,此处为简单校验，后续版本增强修改
					if(msg.indexOf("tesSUCCESS") != -1 && msg.indexOf("The transaction was applied.") != -1)
					{
					
						//修改订单状态
						Order order = orderdao.queryOrderById(orderid);
						order.setStatus(BookstoreConstant.ORDER_PAY_NOTSENT);
						orderdao.updateOrder(order);
						payresult.setSuccessFlag(true);
						payresult.setErrormsg("交易成功");
					
					}
					else
					{
						payresult.setSuccessFlag(false);
						payresult.setErrormsg("交易失败，请检查网关");
					}
					
					this.close();
				}

				@Override
				public void onOpen(ServerHandshake arg0) {
					
					
				}
				
				
			};
			
			if(client.connectBlocking())
			{
				//如果返回true，表示连接成功
				client.send(json);
			}
			else
			{
				//返回false，表示连接失败
				payresult.setSuccessFlag(false);
				payresult.setErrormsg("连接失败");
				
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//json消息要传入，订单id也得有
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
