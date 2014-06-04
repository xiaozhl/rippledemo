package com.study.pojo;

public class Book implements java.io.Serializable{
	private long bookid;
	private String title;
	private String author;
	private double price;
	private int count;
	private BookType type;
	private String pic;
	private Seller seller;
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Book(long bookid, String title, String author, double price,
			int count, BookType type, String pic) {
		super();
		this.bookid = bookid;
		this.title = title;
		this.author = author;
		this.price = price;
		this.count = count;
		this.type = type;
		this.pic = pic;
	}
	public long getBookid() {
		return bookid;
	}
	public void setBookid(long bookid) {
		this.bookid = bookid;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public BookType getType() {
		return type;
	}
	public void setType(BookType type) {
		this.type = type;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public Seller getSeller() {
		return seller;
	}
	public void setSeller(Seller seller) {
		this.seller = seller;
	}
	
	
}
