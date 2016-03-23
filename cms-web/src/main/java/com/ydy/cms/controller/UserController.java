package com.ydy.cms.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ydy.cms.dto.UserDto;
import com.ydy.cms.service.IGroupService;
import com.ydy.cms.service.IRoleService;
import com.ydy.cms.service.IUserService;

@Controller
@RequestMapping("/admin/user")
public class UserController {

	private IUserService userService;
	public IGroupService groupService;
	public IRoleService roleService;
	
	public IGroupService getGroupService() {
		return groupService;
	}
	@Inject
	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}
	public IRoleService getRoleService() {
		return roleService;
	}
	@Inject
	public void setRoleService(IRoleService roleService) {
		this.roleService = roleService;
	}
	public IUserService getUserService() {
		return userService;
	}
	@Inject
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("/users")
	public String list(Model model){
		model.addAttribute("datas", userService.findUser());
		return "user/list";
	}
	
	private void initAddUser(Model model){
		model.addAttribute("groups", groupService.listGroup());
		model.addAttribute("roles", roleService.listRole());
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("user", new UserDto());
		initAddUser(model);
		return "user/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Valid UserDto userDto,BindingResult br,Model model){
		if(br.hasErrors()){
			initAddUser(model);
			return "user/add";
		}
		userService.add(userDto.getUser(), userDto.getRoleIds(), userDto.getGroupIds());
		return "redirect:/admin/user/users";
	}
	
	
	
}







