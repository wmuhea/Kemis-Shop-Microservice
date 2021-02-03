package com.kemishshop.config;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.UUID;

/*
    wontgn created on 1/21/21 inside the package - com.kemishshop.config
*/

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AdminServerProperties adminProperties;

    public WebSecurityConfig(AdminServerProperties adminProperties) {
        this.adminProperties = adminProperties;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler =
                new SavedRequestAwareAuthenticationSuccessHandler();

        successHandler.setTargetUrlParameter("redirectTo");
        successHandler.setDefaultTargetUrl(this.adminProperties.getContextPath() + "/");

        http
            .authorizeRequests()
                .antMatchers(this.adminProperties.getContextPath() +
                        "/assets/**").permitAll()
                .antMatchers(this.adminProperties.getContextPath() + "/login").permitAll()
                .anyRequest().authenticated()
                .and()
            .formLogin()
                .loginPage(adminProperties.getContextPath() + "/login")
                .successHandler(successHandler)
                .and()
            .logout()
                .logoutUrl(this.adminProperties.getContextPath() + "/logout")
                .and()
            .httpBasic()
                .and()
            .csrf()
                .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                .ignoringRequestMatchers(
                        new AntPathRequestMatcher(this.adminProperties.getContextPath() +
                                "/instances", HttpMethod.POST.toString()),
                        new AntPathRequestMatcher(this.adminProperties.getContextPath() +
                                "/instances", HttpMethod.DELETE.toString()),
                        new AntPathRequestMatcher(this.adminProperties.getContextPath() +
                             "/actuator/**")
                )
                .and()
                .rememberMe()
                    .key(UUID.randomUUID().toString())
                    .tokenValiditySeconds(1209600);


    }
}
