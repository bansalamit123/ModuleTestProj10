package com.rays.ctl;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.rays.dto.AttachmentDTO;
import com.rays.dto.RoleDTO;
import com.rays.dto.UserDTO;
import com.rays.form.UserForm;
import com.rays.service.AttachmentService;
import com.rays.service.RoleService;
import com.rays.service.UserService;

@RestController
@RequestMapping(value = "User")
public class UserCtl extends BaseCtl {

	@Autowired
	public UserService userService;

	@Autowired
	public AttachmentService attachmentService;

	@Autowired
	public RoleService RoleService;

	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse();
		// List<DropDownList> roleList = RoleService.search(null, 0, 0);
		List<DropDownList> userList = userService.search(null, 0, 0);
		res.addResult("userList", userList);
		// res.addResult("roleList", roleList);

		return res;

	}

	@PostMapping("save")
	public ORSResponse save(@RequestBody @Valid UserForm form, BindingResult bindingResult) {
		ORSResponse res = validate(bindingResult);
		if (!res.isSucess()) {
			return res;

		}

		UserDTO dto = (UserDTO) form.getDTO();
		if (dto.getId() != null && dto.getId() > 0) {
			userService.update(dto);
			res.addData(dto.getId());
			res.addMessage("Data Successfully Updated");

		} else {
			long pk = userService.add(dto);
			res.addData(pk);
			res.addMessage("Data added Successfully");
		}

		return res;

	}

	@GetMapping("get/{id}")
	public ORSResponse get(@PathVariable long id) {
		ORSResponse res = new ORSResponse();
		UserDTO dto = userService.findById(id);
		res.addData(dto);
		return res;

	}

	@GetMapping("delete/{ids}")
	public ORSResponse delete(@PathVariable long[] ids) {
		ORSResponse res = new ORSResponse();
		for (long id : ids) {
			userService.delete(id);

		}
		res.addMessage("Data deleted Successfully");
		return res;

	}

	@PostMapping("search/{pageNo}")
	public ORSResponse search(@RequestBody UserForm form, @PathVariable int pageNo) {
		ORSResponse res = new ORSResponse();
		UserDTO dto = new UserDTO();
		dto.setFirstName(form.getFirstName());
		dto.setDob(form.getDob());
		dto.setRoleId(form.getRoleId());
		List list = userService.search(dto, pageNo, 5);
		if (list.size() == 0) {
			res.addMessage("Result Not Found");

		} else {
			res.addData(list);
		}
		return res;

	}

	@PostMapping("/profilePic/{userId}")
     
	public ORSResponse uploadPic(@PathVariable Long userId, @RequestParam("file") MultipartFile file) {
		System.out.println("Upload pic");
		UserDTO userDto = userService.findById(userId);
		AttachmentDTO attachmentDto = new AttachmentDTO(file);

		attachmentDto.setDescription("profile pic");

		attachmentDto.setUserId(userId);

		if (userDto.getImageId() != null && userDto.getImageId() > 0) {
			attachmentDto.setId(userDto.getImageId());

		}

		Long imageId = attachmentService.save(attachmentDto);

		if (userDto.getImageId() == null) {

			userDto.setImageId(imageId);

			userService.update(userDto);
		}

		ORSResponse res = new ORSResponse();

		res.addResult("imageId", imageId);

		return res;
	}

	@GetMapping("profilePic/{userId}")
	public @ResponseBody void downloadPic(@PathVariable Long userId, HttpServletResponse response) throws Exception {
      System.out.println("Dwonload Pic");
		UserDTO userDTO = userService.findById(userId);
		AttachmentDTO attachmentDTO = null;

		if (userDTO != null) {

			attachmentDTO = attachmentService.findById(userDTO.getImageId());
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
