package models;

import java.sql.Date;

public class MCash {

	private int id;
	private String userName;
	private Double totalEstimated;
	private Double total;
	private Date dateToday;
	private Double totalProduct;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Double getTotalEstimated() {
		return totalEstimated;
	}
	public void setTotalEstimated(Double totalEstimated) {
		this.totalEstimated = totalEstimated;
	}
	public Double getTotal() {
		return total;
	}
	public void setTotal(Double total) {
		this.total = total;
	}
	public Date getDateToday() {
		return dateToday;
	}
	public void setDateToday(Date dateToday) {
		this.dateToday = dateToday;
	}
	public Double getTotalProduct() {
		return totalProduct;
	}
	public void setTotalProduct(Double totalProduct) {
		this.totalProduct = totalProduct;
	}
	
	
	
	
	
}
