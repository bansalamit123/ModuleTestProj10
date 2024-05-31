package com.rays.common;

public class BaseForm {
	public Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BaseDTO getDTO() {
		System.out.println("Ami");
		return null;
	}
	
	

	public <T extends BaseDTO> T initDTO(T dto) {
		System.out.println(id);
		if (id != null && id > 0) {

			dto.setId(id);
			
		} else {

			dto.setId(null);
		}
		return dto;

	}
}
