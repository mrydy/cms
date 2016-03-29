package com.ydy.cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/channel")
public class ChannelController {
	
	@RequestMapping(value="/channels")
	public String list(Model model){
		return "channel/list";
	}

}
