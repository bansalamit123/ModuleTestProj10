package com.rays.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.DoctorDAO;
import com.rays.dto.DoctorDTO;

@Service
@Transactional
public class DoctorService {
	
	@Autowired
	public DoctorDAO dao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public long add(DoctorDTO dto) {
		long pk=dao.add(dto);
		return pk;
		
		}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(DoctorDTO dto) {
		dao.update(dto);
		
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(long id) {
		DoctorDTO dto = findById(id);
		dao.delete(dto);
		
		}
	@Transactional(readOnly=true)
	public DoctorDTO findById(long pk) {
		DoctorDTO dto=dao.findByPk(pk);
		return dto;
		
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public long save(DoctorDTO dto) {
		   Long id=dto.getId();
		   if(id!=null&& id>0) 
			   update(dto);
		   else
			  id =add(dto);
		return id;
		
	}
	@Transactional(readOnly=true)
	public List search(DoctorDTO dto,int pageSize,int pageNo) {
		List list=dao.search(dto, pageNo, pageSize);
		return list;
		
	}

}
