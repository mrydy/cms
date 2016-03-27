package com.ydy.cms.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.yandinyon.basic.util.EnumUtils;

import com.ydy.cms.model.Role;
import com.ydy.cms.model.RoleType;
import com.ydy.cms.service.IRoleService;
import com.ydy.cms.service.IUserService;

@Controller
@RequestMapping("/admin/role")
public class RoleController {

	private IRoleService roleService;
	private IUserService userService;
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
	
	@RequestMapping(value="/roles",method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("roles", roleService.listRole());
		return "role/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new Role());
		model.addAttribute("types", EnumUtils.enum2Name(RoleType.class));
		return "role/add";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(Role role,BindingResult br){
		if(br.hasErrors()){
			return "role/add";
		}
		roleService.add(role);
		return "redirect:/admin/role/roles";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model){
		model.addAttribute(roleService.load(id));
		model.addAttribute("types", EnumUtils.enum2Name(RoleType.class));
		return "role/update";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@PathVariable int id,Role role,BindingResult br){
		if(br.hasErrors()){
			return "role/update";
		}
		Role oldRole = roleService.load(id);
		oldRole.setName(role.getName());
		oldRole.setRoleType(role.getRoleType());
		roleService.update(oldRole);
		return "redirect:/admin/role/roles";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id){
		roleService.delete(id);
		return "redirect:/admin/role/roles";
	}
	
	@RequestMapping(value="/cleanUsers/{id}",method=RequestMethod.GET)
	public String cleanUsers(@PathVariable int id){
		roleService.deleteRoleUsers(id);
		return "redirect:/admin/role/roles";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int id,Model model){
		model.addAttribute(roleService.load(id));
		model.addAttribute("us", userService.listRoleUsers(id));
		return "role/show";
	}
	
	
}









