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
	//买家修改订单状态，需要提供ripple支付事务和订单ID
	private String txid;
	private long orderid;
	//返回业务执行结果
	private ProcessResult result = new ProcessResult();
	private CountDownLatch ct = new CountDownLatch(1);
	private OrderDAO orderdao;
		
	public void doBusiness() {
	 //Ripple校验
	 //需要检验服务端的事务是否正确，还有支付金额必须重新检验，这里这是一个DEMO，不做完全
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
					//如果连接出错，调用这里
					result.setSuccessFlag(false);
					result.setErrormsg("websocket错误:"+e.getMessage());
					this.close();
					ct.countDown();
				}

				@Override
				public void onMessage(String msg) {
					//如果服务端回消息了会自动调用本方法
					//校验msg，如果成功交易，则修改订单状态,此处为简单校验，后续版本增强修改
					if(msg.indexOf("success") != -1)
					{
						//修改订单状态
						Order order = orderdao.queryOrderById(orderid);
						order.setStatus(BookstoreConstant.ORDER_PAY_NOTSENT);
						orderdao.updateOrder(order);
						result.setSuccessFlag(true);
						result.setErrormsg("交易成功");
						ct.countDown();
					
					}
					else
					{
						result.setSuccessFlag(false);
						result.setErrormsg("修改失败");
						ct.countDown();
					}
					
					
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
				result.setSuccessFlag(false);
				result.setErrormsg("连接失败");
				ct.countDown();
				
			}
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//在这里要使用多线程等待，否则结果不保证正确
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
