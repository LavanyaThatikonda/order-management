package com.pocimp.entity;

import java.util.Date;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
@Table(name = "orders")

public class Order {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String status;
    private int totalUnit;
    private double totalCost;
    private double totalAmount;
    private String createdby;
    private String modifiedby;
    @ManyToOne
    
    @JoinColumn(name = "product_id")
    private Product product;
    
    @ManyToOne
    
    @JoinColumn(name = "customer_id")
    private Customer customer;
      
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;
 
   
    @Temporal(TemporalType.TIMESTAMP)
    private Date modificationTime;
 
    @PrePersist
    protected void onCreate() {
        creationTime = new Date();
        modificationTime = creationTime;
    }
 
    @PreUpdate
    protected void onUpdate() {
        modificationTime = new Date();   
    }
    
    public Order() {
    	
    }
    
    

	public Order(int id, String status, int totalUnit, double totalCost, double totalAmount, String createdby,
			String modifiedby, Product product, Customer customer, Date creationTime, Date modificationTime) {
		super();
		this.id = id;
		this.status = status;
		this.totalUnit = totalUnit;
		this.totalCost = totalCost;
		this.totalAmount = totalAmount;
		this.createdby = createdby;
		this.modifiedby = modifiedby;
		this.product = product;
		this.customer = customer;
		this.creationTime = creationTime;
		this.modificationTime = modificationTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getTotalUnit() {
		return totalUnit;
	}

	public void setTotalUnit(int totalUnit) {
		this.totalUnit = totalUnit;
	}

	public double getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedby() {
		return modifiedby;
	}

	public void setModifiedby(String modifiedby) {
		this.modifiedby = modifiedby;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

    
	
}
