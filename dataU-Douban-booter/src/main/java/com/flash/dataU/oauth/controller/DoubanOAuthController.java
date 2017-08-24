package com.flash.dataU.oauth.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * .
 *
 * @author sunyiming (sunyiming170619@credithc.com)
 * @version 0.0.1-SNAPSHOT
 * @since 2017年08月23日 16时39分
 */
@Controller
public class DoubanOAuthController {

    @RequestMapping("leadToLogin")
    public void leadToLogin(HttpServletResponse response) throws IOException {
        response.sendRedirect("http://localhost:8080/leadToLogin?" +
            "client_id=hyd&" +
            "redirect_uri=http://localhost:8081/index");
    }

    @RequestMapping("index")
    public String index(String code, HttpServletRequest request) throws IOException {

        RestTemplate restTemplate = new RestTemplateBuilder().build();

        /**
         * （D）客户端收到授权码，附上早先的"重定向URI"，向认证服务器申请令牌。这一步是在客户端的后台的服务器上完成的，对用户不可见。
         */
        String accessToken = restTemplate.getForObject("http://localhost:8080/getTokenByCode?" +
            "code=shou_quan_ma&" +
            "redirect_uri=http://localhost:8081/getTokenByCode&" +
            "client_id=hyd", String.class);

        /**
         * 发起通过token换用户信息的请求
         */
        String username = restTemplate.getForObject("http://localhost:8080/getUserinfoByToken?" +
            "token=access_token&", String.class);

        request.getSession().setAttribute("username",username);

        return "index";
    }


    @RequestMapping("getUserInfo")
    @ResponseBody
    public String getUserInfo(HttpServletRequest request) throws IOException {
        Object username = request.getSession().getAttribute("username");
        return username.toString();
    }

}
