package com.study.pojo;

public interface BookstoreConstant {
	/**
	 * 订单状态
	 */
	public static final int ORDER_NOT_PAY =0;
	public static final int ORDER_PAY_NOTSENT =1;
	public static final int ORDER_SENT =2;
	public static final int ORDER_OK =3;
	
	/**
	 * 书籍类别
	 */
	public static final int BOOKTYPE_HISTORY =1;
	public static final int BOOKTYPE_SCIENCE =2;
	public static final int BOOKTYPE_NOTSET  =0;
	
	/**
	 * 钱包的配置
	 */
	public static final String SELLER_WALLET="raRG8NeAACcXBLdeucoribLp7AHk2dWg97";
	public static final String RIPPLE_NETWORK="ws://s1.ripple.com:443";
	public static final String DEFAULT_GATEWAY="rLWt35JxFDdjhcVp3uJk1caxncUfeCCFLL";
	
}
