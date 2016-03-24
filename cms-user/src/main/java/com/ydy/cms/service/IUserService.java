package com.ydy.cms.service;

import java.util.List;

import org.yandinyon.basic.model.Pager;

import com.ydy.cms.model.Group;
import com.ydy.cms.model.Role;
import com.ydy.cms.model.User;

public interface IUserService {
	
	/**
	 * 增加用户
	 * @param user
	 * @param rids 角色ids
	 * @param gids 组ids
	 */
	public void add(User user,Integer[] rids,Integer[] gids);
	
	/**
	 * 先删除关联关系
	 * 如果有文章只能禁用不能删除
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 更新用户
	 * @param user
	 * @param rids
	 * @param gids
	 */
	public void update(User user,Integer[] rids,Integer[] gids);
	
	/**
	 * 更新状态
	 * @param id
	 */
	public void updateStatus(int id);
	
	public Pager<User> findUser();
	
	/**
	 * 获取用户信息
	 * @param id
	 * @return
	 */
	public User load(int id);
	
	/**
	 * 获取用户的所有角色信息
	 * @param id
	 * @return
	 */
	public List<Role> listUserRoles(int id);
	
	/**
	 * 获取用户的所有组信息
	 * @param id
	 * @return
	 */
	public List<Group> listUserGroups(int id);

	public List<Integer> listUserRoleIds(int id);
	public List<Integer> listUserGroupIds(int id);
	
	
}
