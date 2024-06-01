package com.rays.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rays.dao.AttachmentDAO;
import com.rays.dto.AttachmentDTO;

@Service
@Transactional
public class AttachmentService {

	@Autowired
	public AttachmentDAO attachmentDAO;

	@Transactional(propagation = Propagation.REQUIRED)
	public long add(AttachmentDTO dto) {

		long pk = attachmentDAO.add(dto);
		return pk;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void update(AttachmentDTO dto) {
		attachmentDAO.update(dto);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public void delete(long id) {
		AttachmentDTO dto = findById(id);
		attachmentDAO.delete(dto);
	}

	@Transactional(readOnly = true)
	public AttachmentDTO findById(long pk) {
		AttachmentDTO dto = attachmentDAO.findByPk(pk);
		return dto;

	}

	@Transactional(propagation = Propagation.REQUIRED)

	public long save(AttachmentDTO dto) {
		Long id = dto.getId();
		if (id != null && id > 0) {
			update(dto);

		} else {
			id = add(dto);
		}
		return id;

	}

}
