package com.pocimp.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class ProductCategory implements Serializable {
	  @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private String name;
	    private String description;
	    private String createdby;
	    private String modifiedby;
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
	    
	    public ProductCategory() {
	       
	    }
	    
	    

		public ProductCategory(int id, String name, String description, String createdby, String modifiedby,
				Date creationTime, Date modificationTime) {
			super();
			this.id = id;
			this.name = name;
			this.description = description;
			this.createdby = createdby;
			this.modifiedby = modifiedby;
			this.creationTime = creationTime;
			this.modificationTime = modificationTime;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
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
