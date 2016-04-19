package com.ydy.cms.dao;

import java.util.List;

import org.yandinyon.basic.dao.IBaseDao;

import com.ydy.cms.model.Channel;
import com.ydy.cms.model.ChannelTree;

public interface IChannelDao extends IBaseDao<Channel>{

	/**
	 * 根据父栏目id加载子栏目
	 * @param pid
	 * @return
	 */
	public List<Channel> listByParent(Integer pid);
	/**
	 * 获取最大的子栏目序号
	 * @param pid
	 * @return
	 */
	public int getMaxOrderByParent(Integer pid);
	
	/**
	 * 生成完整的栏目树
	 * @return
	 */
	public List<ChannelTree> generateTree();
	
	/**
	 * 异步加载树
	 * @param pid
	 * @return
	 */
	public List<ChannelTree> generateTreeByParent(Integer pid);
	
}
