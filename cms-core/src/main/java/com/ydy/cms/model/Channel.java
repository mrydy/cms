package com.ydy.cms.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="t_channel")
public class Channel {

	private int id;             //主键
	private String name;        //栏目名称
	private int customLink;     //栏目是否自定义链接0表示否1表示是 
	private String customLinkUrl;//自定义链接地址
	private ChannelType type;   //栏目类型，枚举类型，name属性标识中午名称 
	private int isIndex;        //是否是首页栏目
	private int isTopNav;		//是否是顶部导航栏目
	private int recommend;		//是否是推荐栏目
	private int status;			//栏目状态1启用0停止
	private int orders;			//栏目序号
	
	private Channel parent;     //父栏目

	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="custom_link")
	public int getCustomLink() {
		return customLink;
	}

	public void setCustomLink(int customLink) {
		this.customLink = customLink;
	}

	@Column(name="custom_link_url")
	public String getCustomLinkUrl() {
		return customLinkUrl;
	}

	public void setCustomLinkUrl(String customLinkUrl) {
		this.customLinkUrl = customLinkUrl;
	}

	public ChannelType getType() {
		return type;
	}

	public void setType(ChannelType type) {
		this.type = type;
	}

	@Column(name="is_index")
	public int getIsIndex() {
		return isIndex;
	}

	public void setIsIndex(int isIndex) {
		this.isIndex = isIndex;
	}

	@Column(name="is_top_nav")
	public int getIsTopNav() {
		return isTopNav;
	}

	public void setIsTopNav(int isTopNav) {
		this.isTopNav = isTopNav;
	}

	public int getRecommend() {
		return recommend;
	}

	public void setRecommend(int recommend) {
		this.recommend = recommend;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getOrders() {
		return orders;
	}

	public void setOrders(int orders) {
		this.orders = orders;
	}

	@ManyToOne
	@JoinColumn(name="pid")
	public Channel getParent() {
		return parent;
	}

	public void setParent(Channel parent) {
		this.parent = parent;
	}
	
	
	
}
