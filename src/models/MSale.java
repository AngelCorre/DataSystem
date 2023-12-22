package models;

import java.sql.Date;

public class MSale {
	
	private int id;
	private String sellerName;
	private Double total;
	private Date dateT;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSellerName() {
		return sellerName;
	}
	public void setSellerName(String sellerName) {
		this.sellerName = sellerName;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getDateT() {
		return dateT;
	}
	public void setDateT(Date dateT) {
		this.dateT = dateT;
	}
	
	
	

}
