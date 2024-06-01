package com.rays.ctl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rays.common.BaseCtl;
import com.rays.common.DropDownList;
import com.rays.common.ORSResponse;
import com.rays.dao.AttachmentDAO;
import com.rays.dao.DoctorDAO;
import com.rays.dto.AttachmentDTO;
import com.rays.dto.DoctorDTO;
import com.rays.form.DoctorForm;
import com.rays.service.AttachmentService;
import com.rays.service.DoctorService;

@RestController
@RequestMapping(value = "Doctor")
public class DoctorCtl extends BaseCtl {
	@Autowired
	public DoctorService doctorService;
	@Autowired
	public AttachmentService attachmentService;

	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse();
		List<DropDownList> doctorList = doctorService.search(null, 0, 0);
		res.addResult("doctorList", doctorList);

		return res;

	}

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid DoctorForm form, BindingResult bindingResult) {
		ORSResponse res = validate(bindingResult);
		if (!res.isSucess()) {

			return res;
		}
		DoctorDTO dto = (DoctorDTO) form.getDTO();

		if (dto.getId() != null && dto.getId() > 0) {
			doctorService.update(dto);
			res.addData(dto.getId());
			res.addMessage("Data Successfully Update");

		} else {
			Long pk = doctorService.add(dto);
			res.addData(pk);
			res.addMessage("Data Successfully Added");

		}

		return res;
	}

	@GetMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable Long[] ids) {
		ORSResponse res = new ORSResponse();
		for (Long id : ids) {
			doctorService.delete(id);
			res.addMessage("Data Deleted Successfully");

		}
		return res;

	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable Long id) {
		ORSResponse res = new ORSResponse();

		DoctorDTO dto = doctorService.findById(id);
		res.addData(dto);
		return res;

	}

	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody DoctorForm form, @PathVariable int pageNo) {
		ORSResponse res = new ORSResponse();
		DoctorDTO dto = new DoctorDTO();
		dto.setExpertise(form.getExpertise());
		List list = doctorService.search(dto, pageNo, 5);
		if (list.size() == 0) {
			res.addMessage("No record found");

		} else {
			res.addData(list);
		}

		return res;

	}

	@PostMapping("profilePic/{doctorId}")
	public ORSResponse uploadPic(@PathVariable Long doctorId, @RequestParam("file") MultipartFile file) {
		DoctorDTO doctorDto = doctorService.findById(doctorId);
		AttachmentDTO attachmentDTO = new AttachmentDTO(file);
		attachmentDTO.setDescription("profilePic");
		attachmentDTO.setDoctorId(doctorId);
		if (doctorDto.getImageId()!= null && doctorDto.getImageId() > 0) {
			attachmentDTO.setId(doctorDto.getImageId());

		}

		Long imageId = attachmentService.save(attachmentDTO);

		if (doctorDto.getImageId() == null) {
			doctorDto.setImageId(imageId);
			doctorService.update(doctorDto);

		}
		
		ORSResponse res=new ORSResponse();
		res.addResult("imageId", imageId);

		return res;

	}
	@GetMapping("profilePic/{doctorId}")
	public @ResponseBody void downloadPic(@PathVariable Long doctorId,HttpServletResponse response) throws IOException{
		
		  DoctorDTO doctorDTO=doctorService.findById(doctorId);
		  
		  AttachmentDTO attachmentDTO=null;
		  
		  if (doctorDTO != null) {

				attachmentDTO = attachmentService.findById(doctorDTO.getImageId());
			}
			if (attachmentDTO != null) {
				response.setContentType(attachmentDTO.getType());

				OutputStream out = response.getOutputStream();
				out.write(attachmentDTO.getDoc());
				out.close();

			} else {
				response.getWriter().write("ERROR:File not found");

			}
		
		
	}

}
