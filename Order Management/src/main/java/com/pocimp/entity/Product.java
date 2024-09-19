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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Product {
	
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  
	    private int id;
	    private String productName;
	    private String productCode;
	    private int  totalStock;
	    private int cost;
	    private String expireDate;
	    private String manufacturingDate;
	    private String description;
	    private String createdby;
	    private String modifiedby;
	    @ManyToOne
	    @JoinColumn(name = "category_name",referencedColumnName="name")
	    private ProductCategory category;

	    @ManyToOne
	    @JoinColumn(name = "company_name",referencedColumnName="name")
	    private Company company;
	    
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
	    public Product() {
	    	
	    }
	    

	    
	    
		public Product(int id, String productName, String productCode, int totalStock, int cost, String expireDate,
				String manufacturingDate, String description, String createdby, String modifiedby,
				ProductCategory category, Company company, Date creationTime, Date modificationTime) {
			super();
			this.id = id;
			this.productName = productName;
			this.productCode = productCode;
			this.totalStock = totalStock;
			this.cost = cost;
			this.expireDate = expireDate;
			this.manufacturingDate = manufacturingDate;
			this.description = description;
			this.createdby = createdby;
			this.modifiedby = modifiedby;
			this.category = category;
			this.company = company;
			this.creationTime = creationTime;
			this.modificationTime = modificationTime;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getProductName() {
			return productName;
		}

		public void setProductName(String productName) {
			this.productName = productName;
		}

		public String getProductCode() {
			return productCode;
		}

		public void setProductCode(String productCode) {
			this.productCode = productCode;
		}

		public int getTotalStock() {
			return totalStock;
		}

		public void setTotalStock(int totalStock) {
			this.totalStock = totalStock;
		}

		public int getCost() {
			return cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

		public String getExpireDate() {
			return expireDate;
		}

		public void setExpireDate(String expireDate) {
			this.expireDate = expireDate;
		}

		public String getManufacturingDate() {
			return manufacturingDate;
		}

		public void setManufacturingDate(String manufacturingDate) {
			this.manufacturingDate = manufacturingDate;
		}

		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
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

		public ProductCategory getCategory() {
			return category;
		}

		public void setCategory(ProductCategory category) {
			this.category = category;
		}

		public Company getCompany() {
			return company;
		}

		public void setCompany(Company company) {
			this.company = company;
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
