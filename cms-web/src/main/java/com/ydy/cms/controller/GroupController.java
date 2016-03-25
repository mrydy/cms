package com.ydy.cms.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ydy.cms.model.Group;
import com.ydy.cms.service.IGroupService;
import com.ydy.cms.service.IUserService;

@Controller
@RequestMapping("/admin/group")
public class GroupController {

	private IGroupService groupService;
	
	private IUserService userService;
	
	public IGroupService getGroupService() {
		return groupService;
	}

	@Inject
	public void setGroupService(IGroupService groupService) {
		this.groupService = groupService;
	}
	
	
	public IUserService getUserService() {
		return userService;
	}

	@Inject
	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value="/groups",method=RequestMethod.GET)
	public String list(Model model){
		model.addAttribute("datas", groupService.findGroup());
		return "group/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String add(Model model){
		model.addAttribute(new Group());
		return "group/add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String add(@Validated Group group,BindingResult br){
		if(br.hasErrors()){
			return "group/add";
		}
		groupService.add(group);
		return "redirect:/admin/group/groups";
	}
	
	@RequestMapping(value="/update/{id}",method=RequestMethod.GET)
	public String update(@PathVariable int id,Model model){
		model.addAttribute(groupService.load(id));
		return "group/update";
	}
	@RequestMapping(value="/update/{id}",method=RequestMethod.POST)
	public String update(@Validated Group group,@PathVariable int id,BindingResult br){
		if(br.hasErrors()){
			return "group/update";
		}
		Group oldGroup = groupService.load(id);
		oldGroup.setDescription(group.getDescription());
		oldGroup.setName(group.getName());
		groupService.update(oldGroup);
		return "redirect:/admin/group/groups";
	}
	
	@RequestMapping(value="/delete/{id}",method=RequestMethod.GET)
	public String delete(@PathVariable int id){
		groupService.delete(id);
		return "redirect:/admin/group/groups";
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.GET)
	public String show(@PathVariable int id,Model model){
		model.addAttribute(groupService.load(id));
		model.addAttribute("us",userService.listGroupUsers(id));
		return "group/show";
	}
	
	@RequestMapping(value="/cleanUsers/{id}",method=RequestMethod.GET)
	public String cleanGroupUsers(@PathVariable int id){
		groupService.deleteGroupUsers(id);
		return "redirect:/admin/group/groups";
	}
	
}
