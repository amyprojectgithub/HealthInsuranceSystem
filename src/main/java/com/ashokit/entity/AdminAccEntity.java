package com.ashokit.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "ADMIN_MASTER")
public class AdminAccEntity implements Serializable{
	
	private static final long serialVersionUID = 2599070690769438597L;

	@Id
	@GeneratedValue
	@Column(name = "ACC_ID")
	private Integer accountId;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "EMAIL_ID")
	private String emailId;

	@Column(name = "PWD")
	private String pazzword;

	@Column(name = "GENDER")
	private String gender;

	@Column(name = "ROLE")
	private String role;

	@Column(name = "LOCKED_STATUS")
	private String lockedStatus;

	@Column(name = "DELETE_SWITCH")
	private String deleteSwitch;

	@CreationTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "CREATED_DATE",updatable = false)
	private Date createdDate;

	@UpdateTimestamp
	@Temporal(TemporalType.DATE)
	@Column(name = "UPDATED_DATE",insertable = false)
	private Date updatedDate;

}
