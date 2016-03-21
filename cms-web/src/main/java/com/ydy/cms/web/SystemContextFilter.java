package com.ydy.cms.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.yandinyon.basic.model.SystemContext;

public class SystemContextFilter implements Filter {
	
	private Integer pageSize;

	@Override
	public void init(FilterConfig cfg) throws ServletException {
		try {
			pageSize = Integer.parseInt(cfg.getInitParameter("pager.pageSize"));
		} catch (Exception e) {
			pageSize =15;
		}
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		Integer offset = 0;
		try {
			offset = Integer.parseInt(request.getParameter("pager.offset"));
		} catch (Exception e) {}
		try{
			SystemContext.setOrder(request.getParameter("order"));
			SystemContext.setSort(request.getParameter("sort"));
			SystemContext.setPageOffset(offset);
			SystemContext.setPageSize(pageSize);
			chain.doFilter(request, response);
		}finally{
			SystemContext.removeOrder();
			SystemContext.removeSort();
			SystemContext.removePageOffset();
			SystemContext.removePageSize();
		}
	}

	@Override
	public void destroy() {
		
	}

}
