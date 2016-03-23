package com.ydy.cms.dto;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

import com.ydy.cms.model.User;

public class UserDto {
	private int id;
	private String username;
	private String password;
	private String nickname;
	private String email;
	private String phone;
	private int status;
	
	private Integer[] roleIds;
	private Integer[] groupIds;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@NotNull(message="登录名不能为空")
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@NotNull(message="密码不能为空")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Email(message="邮件格式不正确")
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Integer[] getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(Integer[] roleIds) {
		this.roleIds = roleIds;
	}
	public Integer[] getGroupIds() {
		return groupIds;
	}
	public void setGroupIds(Integer[] groupIds) {
		this.groupIds = groupIds;
	}

	public User getUser(){
		User u = new User();
		u.setEmail(email);
		u.setId(id);
		u.setNickname(nickname);
		u.setPassword(password);
		u.setPhone(phone);
		u.setStatus(status);
		u.setUsername(username);
		return u;
	}
	
	public UserDto(User user){
		this.setEmail(user.getEmail());
		this.setId(user.getId());
		this.setNickname(user.getNickname());
		this.setPassword(user.getPassword());
		this.setPhone(user.getPhone());
		this.setStatus(user.getStatus());
		this.setUsername(user.getUsername());
	}
	
	public UserDto(User user,Integer[] roleIds,Integer[] groupIds){
		this.setEmail(user.getEmail());
		this.setId(user.getId());
		this.setNickname(user.getNickname());
		this.setPassword(user.getPassword());
		this.setPhone(user.getPhone());
		this.setStatus(user.getStatus());
		this.setUsername(user.getUsername());
		this.setGroupIds(groupIds);
		this.setRoleIds(roleIds);
	}
	
	public UserDto(){
		
	}
	
}
