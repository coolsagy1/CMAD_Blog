package org.cmad.blog.rest;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.cmad.blog.Blog;
import org.cmad.blog.api.User;
import org.cmad.blog.biz.SimpleBlog;


/**
 * Authentication filter that will be invoked before every request to an API
 * resource
 *
 */
@WebFilter("/*")
public class AuthFilter implements Filter {

	Blog blog;

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("AuthFilter.init()");
		blog = new SimpleBlog();
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain)
					throws IOException, ServletException {
		HttpServletRequest hreq = (HttpServletRequest) req;
		HttpServletResponse hres = (HttpServletResponse) res;
		System.out.println("AuthFilter.doFilter() uri: "+hreq.getRequestURI()+" method: "+hreq.getMethod());
		String basicAuthHeader = hreq.getHeader("Authorization");
		System.out.println("AuthFilter.doFilter() basicAuthHeader = "+basicAuthHeader);
		/*if (basicAuthHeader == null) {
			hres.sendError(401, "Unauthenticated");
			return;
		}*/

		if (basicAuthHeader != null && basicAuthHeader.startsWith("Basic")) {
			String base64Credentials = basicAuthHeader.substring("Basic".length()).trim();
			String credentials = new String(Base64.decodeBase64(base64Credentials));
			String username = credentials.split(":")[0];
			String password = credentials.split(":")[1];
			System.out.println("AuthFilter.doFilter() credentials:"+credentials+" username:"+username+" password:"+password);
			User user = blog.getUser(new User(username));
			if(user!=null && user.getPassword().equals(password)){
				System.out.println("AuthFilter.doFilter() Correct Credentials, Successfully Login! for "+username);
				hreq.getSession().setAttribute("username", username);
			}
			else {
				hres.sendError(401, "Incorrect Credentials!");
				return;
			}

		}
		chain.doFilter(req, res);
	}

	@Override
	public void destroy() {
		System.out.println("AuthFilter.destroy()");
	}}
