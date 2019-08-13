package com.cn.domain.produ;

import java.util.Date;

public class Product {

	private int id;
	
	private String proName;
	
	private Integer proQty;
	
	private String proType;
	
	private String proState;
	
	private Double price;
	
	private String ioc;
	
	private Date crDate;
	
	private Date upDate;
	
	private String descs;
	
	private Double discount;

	public Product(){}
	
	
	public Product(int id, String proName, Integer proQty, String proType, String proState, Double price, String ioc,
			Date crDate, Date upDate, String descs, Double discount) {
		super();
		this.id = id;
		this.proName = proName;
		this.proQty = proQty;
		this.proType = proType;
		this.proState = proState;
		this.price = price;
		this.ioc = ioc;
		this.crDate = crDate;
		this.upDate = upDate;
		this.descs = descs;
		this.discount = discount;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Integer getProQty() {
		return proQty;
	}

	public void setProQty(Integer proQty) {
		this.proQty = proQty;
	}

	public String getProType() {
		return proType;
	}

	public void setProType(String proType) {
		this.proType = proType;
	}

	public String getProState() {
		return proState;
	}

	public void setProState(String proState) {
		this.proState = proState;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getIoc() {
		return ioc;
	}

	public void setIoc(String ioc) {
		this.ioc = ioc;
	}

	public Date getCreDate() {
		return crDate;
	}

	public void setCreDate(Date creDate) {
		this.crDate = creDate;
	}

	public Date getUpDate() {
		return upDate;
	}

	public void setUpDate(Date upDate) {
		this.upDate = upDate;
	}

	public String getDescs() {
		return descs;
	}

	public void setDesc(String descs) {
		this.descs = descs;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}
}
