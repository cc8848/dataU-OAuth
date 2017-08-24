package com.flash.dataU.oauth.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * .
 *
 * @author sunyiming (sunyiming170619@credithc.com)
 * @version 0.0.1-SNAPSHOT
 * @since 2017年08月23日 16时13分
 */
@Controller
public class OAuthController {

    private final static String redirect_uri = "http://localhost:8081/index";

    /**
     * （A）用户访问客户端，后者将前者导向认证服务器，即跳转到登录页面
     */
    @RequestMapping("leadToLogin")
    public String leadToLogin(String client_id, String redirect_uri){
        // 去白名单里找是否有client_id
        // 看是否对应正确的redirect_uri
        return "login";
    }

    /**
     * （C）假设用户给予授权，认证服务器将用户导向客户端事先指定的"重定向URI"（redirection URI），同时附上一个授权码。
     */
    @RequestMapping("login")
    public void login(String username, String password, HttpServletResponse response) throws IOException {
        // 验证用户名密码是否正确
        response.sendRedirect(redirect_uri + "?code=shou_quan_ma");
    }

    /**
     * （E）认证服务器核对了授权码和重定向URI，确认无误后，向客户端发送访问令牌（access token）和更新令牌（refresh token）。
     */
    @RequestMapping("getTokenByCode")
    @ResponseBody
    public String getTokenByCode(String client_id, String redirect_uri, String code) throws IOException {
        // 判断client_id、redirect_uri、code是否正确
        // 返回token
        return "access_token";
    }

    /**
     * （F）资源服务器确认令牌无误，同意向客户端开放资源。。
     */
    @RequestMapping("getUserinfoByToken")
    @ResponseBody
    public String getUserinfoByToken(String token) throws IOException {
        // 判断token是否正确
        return "Tom 17岁 喜欢搞基";
    }

}
