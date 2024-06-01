package com.rays.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.rays.common.BaseDTO;

@Entity
@Table(name = "ST_DOCTOR")
public class DoctorDTO extends BaseDTO {
	@Column(name="DOCTOR_NAME" ,length=50)
	private String doctorName;
	
	@Column(name="MOBILE_NUMBER",length=50)
	private String mobileNumber;
	
	@Column(name="Expertise",length=50)
	private String expertise;
	
	@Column(name="DOB")
	private Date dob;
	
	
	@Column(name="IMAGE_ID")
	private Long  imageId;
	
	

	public String getDoctorName() {
		return doctorName;
	}



	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}



	public String getMobileNumber() {
		return mobileNumber;
	}



	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}



	public String getExpertise() {
		return expertise;
	}



	public void setExpertise(String expertise) {
		this.expertise = expertise;
	}



	public Date getDob() {
		return dob;
	}



	public void setDob(Date dob) {
		this.dob = dob;
	}



	public Long getImageId() {
		return imageId;
	}



	public void setImageId(Long imageId) {
		this.imageId = imageId;
	}



	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return expertise;
	}
	
	

}
