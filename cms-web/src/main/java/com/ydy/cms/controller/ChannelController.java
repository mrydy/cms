package com.ydy.cms.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ydy.cms.model.ChannelTree;
import com.ydy.cms.service.IChannelService;

@Controller
@RequestMapping("/admin/channel")
public class ChannelController {
	
	private IChannelService channelService;
	
	public IChannelService getChannelService() {
		return channelService;
	}
	@Inject
	public void setChannelService(IChannelService channelService) {
		this.channelService = channelService;
	}


	@RequestMapping(value="/channels")
	public String list(Model model){
		//model.addAttribute("treeDatas", JsonUtil.getInstance().obj2json(channelService.generateTree()));
		return "channel/list";
	}
	
	@RequestMapping(value="/treeAll")
	public @ResponseBody List<ChannelTree> list(){
		return channelService.generateTree();
	}
	
	@RequestMapping(value="/channels/{pid}")
	public String listChild(@PathVariable Integer pid,Model model){
		model.addAttribute("channels", channelService.listByParentId(pid));
		return "channel/list_child";
	}

}
