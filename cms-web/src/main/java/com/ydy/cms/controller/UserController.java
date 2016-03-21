package com.ydy.cms.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ydy.cms.service.IUserService;

@Controller
@RequestMapping("admin/user")
public class UserController {

	@Inject
	private IUserService userService;

	public IUserService getUserService() {
		return userService;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}
	
	@RequestMapping("users")
	public String list(Model model){
		model.addAttribute("datas", userService.findUser());
		return "user/list";
	}
}
