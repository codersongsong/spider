package com.spider.song.spider.web.interceptor;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.gomeplus.frame.cache.GlobalApplicationCache;
import com.gome.memberCore.lang.model.UserResult;
import com.gome.sso.common.tools.SsoUserCookieTools;
import com.gome.sso.model.UserCookie;

/**
 * <Description> 登陆拦截器 </Description>
 * <ClassName> GomeUserLoginInterceptor </ClassName>
 *
 * @Author generator
 * @Date 2018年01月23日 18时:28分:06秒
 */
public class GomeUserLoginInterceptor implements HandlerInterceptor{

	private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
	
	private String[] allowUrls = null;
	
	public String[] getAllowUrls() {
		return allowUrls;
	}

	public void setAllowUrls(String[] allowUrls) {
		this.allowUrls = allowUrls;
	}
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		try {
			logger.info("校验登录信息");
			String requestURI = request.getRequestURI();
			if(allowUrls != null && allowUrls.length > 0){
				for(String temp : allowUrls){
					if(requestURI.indexOf(temp) >= 0 )
						return true;
				}
			}
			UserResult<UserCookie> userResult = SsoUserCookieTools.checkIsLoginByCookie(request, response);
			if(!userResult.isSuccess()){
				//如果没有登录，跳转到登录页面
				return false;
			}else{
				String userId = userResult.getBuessObj().getId();//用户ID
				String userLogin = userResult.getBuessObj().getLogin();
				String userNm = userLogin;//默认值为用户账户
				request.setAttribute("userId", userId);
				request.setAttribute("userLogin", userLogin);
				request.setAttribute("userNm", userNm);
				logger.info("用户登录成功:userId=" + userId + ",userLogin=" + userLogin);
				return true;
			}
		} catch (Exception e) {
			logger.error("未登录，重定向异常",e);
			try {
				response.sendRedirect((String)GlobalApplicationCache.getInstance().get("GOME_PAGE_URL"));
			} catch (IOException e1) {
				logger.error("未登录，再次重定向异常",e1);
			}
		}
		logger.info("用户未登录");
		return false;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {}

}
