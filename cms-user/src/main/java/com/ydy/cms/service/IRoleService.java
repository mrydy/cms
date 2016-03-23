package com.ydy.cms.service;

import java.util.List;

import com.ydy.cms.model.Role;

public interface IRoleService {
	
	public void add(Role role);
	public void delete(int id);
	public Role load(int id);
	public void update(Role role);
	
	public List<Role> listRole();
	public void deleteRoleUsers(int rid);
	
}
