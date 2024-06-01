package com.rays.form;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.DoctorDTO;

public class DoctorForm extends BaseForm {
	
	@NotEmpty(message="doctorName is required")
	private String doctorName;
	
	@NotEmpty(message="mobileNumber is required")
	private String mobileNumber;
	
	
	@NotEmpty(message="expertise is required")
	private String expertise;
	
	@NotNull(message="dob is required")
	private Date dob;

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
	
	
	@Override
	public BaseDTO getDTO() {
		System.out.println("Bansa");
		DoctorDTO dto=initDTO(new DoctorDTO());
		dto.setDoctorName(doctorName);
		dto.setMobileNumber(mobileNumber);
		dto.setExpertise(expertise);
		dto.setDob(dob);
				
		  return dto;
	}
	

}
