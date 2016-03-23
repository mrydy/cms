package com.ydy.cms.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ydy.cms.model.User;
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
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("user", new User());
		model.addAttribute("groups", groupService.listGroup());
		model.addAttribute("roles", roleService.listRole());
		return "user/add";
	}
}
