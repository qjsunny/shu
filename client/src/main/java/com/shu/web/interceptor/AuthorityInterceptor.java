package com.shu.web.interceptor;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.shu.db.model.user.TUser;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2017/2/28.
 */
public class AuthorityInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession(true);
        TUser user = (TUser) session.getAttribute("user");
        if(user == null){
            response.sendRedirect(request.getContextPath() + "/login/loginhtml");
            return false;
        }else{

        }
        return true;
        /**
         * 下面是sessionid加密，可能不会使用
         */
//        if (!StringUtils.isEmpty(request.getParameter("sessionId"))) {
//            HttpSession session = request.getSession(true);
//            TFlowDistributor user = (TFlowDistributor) session.getAttribute(request.getParameter("sessionId").replaceAll(" ", "+"));
//            if (user == null) {
//                response.sendRedirect(request.getContextPath() + "/login");
//                return false;
//            } else {
//                // sessionId 解密
//                DES des = new DES(SystemConfig.p.getProperty("oudot.app.session.key"));
//                String signStr = des.decryptDES(request.getParameter("sessionId").replaceAll(" ", "+"));
//                JSONObject obj = JSON.parseObject(signStr);
//                if (obj != null) {
//                    if (!user.getDisaccount().equals(obj.getString("account"))) {
//                        response.sendRedirect(request.getContextPath() + "/login");
//                        return false;
//                    }
//                } else {
//                    response.sendRedirect(request.getContextPath() + "/login");
//                    return false;
//                }
//            }
//        } else {
//            response.sendRedirect(request.getContextPath() + "/login");
//            return false;
//        }
//
//        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
