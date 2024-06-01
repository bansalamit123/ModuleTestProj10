package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.dto.DoctorDTO;

@Repository
public class DoctorDAO {

	@PersistenceContext
	public EntityManager entityManager;
	
	@Autowired
	public AttachmentDAO attachmentDAO;

	public void populate(DoctorDTO dto) {

		if (dto.getId() != null && dto.getId() > 0) {

			DoctorDTO doctorData = findByPk(dto.getId());
			dto.setImageId(doctorData.getImageId());

		}

	}

	public Long add(DoctorDTO dto) {
		
		entityManager.persist(dto);
		return dto.getId();

	}

	public void update(DoctorDTO dto) {
		populate(dto);
		entityManager.merge(dto);

	}

	public void delete(DoctorDTO dto) {
		if (dto.getImageId() != null && dto.getImageId() > 0) {
			attachmentDAO.delete(attachmentDAO.findByPk(dto.getImageId()));

		}

		entityManager.remove(dto);

	}

	public DoctorDTO findByPk(Long pk) {
		DoctorDTO dto = entityManager.find(DoctorDTO.class, pk);

		return dto;
	}
	
	public List search(DoctorDTO dto, int pageNo, int pageSize) {

		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<DoctorDTO> cq = builder.createQuery(DoctorDTO.class);
		Root<DoctorDTO> qRoot = cq.from(DoctorDTO.class);
		List<Predicate> predicateList = new ArrayList<Predicate>();
		if (dto != null) {
			if (dto.getExpertise() != null && dto.getExpertise().length() > 0) {

				predicateList.add(builder.like(qRoot.get("expertise"), dto.getExpertise() + "%"));

			}
			
			
		}
		cq.where(predicateList.toArray(new Predicate[predicateList.size()]));

		TypedQuery<DoctorDTO> tq = entityManager.createQuery(cq);

		if (pageSize > 0) {
			tq.setFirstResult(pageNo * pageSize);
			tq.setMaxResults(pageSize);

		}
		List<DoctorDTO> list = tq.getResultList();

		return list;

	}

}
