package com.rays.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.UserDAO;
import com.rays.dto.UserDTO;

@Service
@Transactional
public class UserService {
	
	@Autowired
	public UserDAO dao;
	
	@Transactional(propagation=Propagation.REQUIRED)
	public long add(UserDTO dto) {
		long pk=dao.add(dto);
		return pk;
		
		}
	
	@Transactional(propagation=Propagation.REQUIRED)
	public void update(UserDTO dto) {
		dao.update(dto);
		
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public void delete(long id) {
		UserDTO dto = findById(id);
		dao.delete(dto);
		
		}
	@Transactional(readOnly=true)
	public UserDTO findById(long pk) {
		UserDTO dto=dao.findByPk(pk);
		return dto;
		
	}
	@Transactional(propagation=Propagation.REQUIRED)
	public long save(UserDTO dto) {
		   Long id=dto.getId();
		   if(id!=null&& id>0) 
			   update(dto);
		   else
			  id =add(dto);
		return id;
		
	}
	@Transactional(readOnly=true)
	public List search(UserDTO dto,int pageSize,int pageNo) {
		List list=dao.search(dto, pageNo, pageSize);
		return list;
		
	}

}
