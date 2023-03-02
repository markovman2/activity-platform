package com.cong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.Resource;

//AOP
@EnableWebSecurity
public class SecurityController extends WebSecurityConfigurerAdapter {

    //链式编程
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        //首页所有人可以访问，但是功能页只有对应有权限的人才能访问。
        //请求授权的规则
        http.authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");

        //没有权限会默认到登录页；需要开启登陆页面。
        http.formLogin().loginPage("/toLogin").loginProcessingUrl("/login");

        ///http.csrf().disable();
        // 关闭csrf功能

        //注销功能
        http.logout().logoutSuccessUrl("/");

        //开启记住我功能 默认保存两周
        //自定义接收的参数
        http.rememberMe().rememberMeParameter("remember");
    }

    //认证
    //密码编码：PasswordEncode
    //spring security 5.0+ 新增了很多的加密方法。
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder());
    }
}
