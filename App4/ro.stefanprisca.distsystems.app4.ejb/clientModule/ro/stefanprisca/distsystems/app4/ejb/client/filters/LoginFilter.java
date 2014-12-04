package ro.stefanprisca.distsystems.app4.ejb.client.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ro.stefanprisca.distsystems.app4.ejb.client.beans.ApplicationUserManagerBean;

public class LoginFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		ApplicationUserManagerBean userManager = (ApplicationUserManagerBean) ((HttpServletRequest) request)
				.getSession().getAttribute("userManager");

		if (userManager == null || !userManager.isAdminLogin()) {
			String contextPath = ((HttpServletRequest) request)
					.getContextPath();
			((HttpServletResponse) response).sendRedirect(contextPath
					+ "/home.xhtml");
		}

		chain.doFilter(request, response);

	}

	public void init(FilterConfig config) throws ServletException {
		// Nothing to do here!
	}

	public void destroy() {
		// Nothing to do here!
	}

}