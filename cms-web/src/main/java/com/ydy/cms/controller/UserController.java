package com.ydy.cms.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ydy.cms.dto.UserDto;
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
	
	private void initAddUser(Model model){
		model.addAttribute("groups", groupService.listGroup());
		model.addAttribute("roles", roleService.listRole());
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute("userDto", new UserDto());
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
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id){
		userService.delete(id);
		return "redirect:/admin/user/users";
	}
	
	@RequestMapping(value="/updateStatus/{id}",method=RequestMethod.GET)
	public String updateStatus(@PathVariable int id){
		userService.updateStatus(id);
		return "redirect:/admin/user/users";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model){
		User u = userService.load(id);
		model.addAttribute("userDto", new UserDto(u, userService.listUserRoleIds(id), userService.listUserGroupIds(id)));
		initAddUser(model);
		return "user/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,@Valid UserDto userDto,BindingResult br,Model model){
		if(br.hasErrors()){
			initAddUser(model);
			return "user/update";
		}
		User oldUser = userService.load(id);
		oldUser.setNickname(userDto.getNickname());
		oldUser.setEmail(userDto.getEmail());
		oldUser.setPhone(userDto.getPhone());
		oldUser.setStatus(userDto.getStatus());
		userService.update(oldUser, userDto.getRoleIds(), userDto.getGroupIds());
		return "redirect:/admin/user/users";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int id,Model model){
		model.addAttribute(userService.load(id));
		model.addAttribute("gs", userService.listUserGroups(id));
		model.addAttribute("rs", userService.listUserRoles(id));
		return "user/show";
	}
}







