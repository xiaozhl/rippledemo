package com.study.pojo;

public class BookType implements java.io.Serializable{
	
	private int typeid;
	private String typename;
	
	public BookType(int i, String string) {
		this.typeid = i;
		this.typename = string;
	}
	public BookType()
	{
		
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getTypename() {
		return typename;
	}
	public void setTypename(String typename) {
		this.typename = typename;
	}
	
}
