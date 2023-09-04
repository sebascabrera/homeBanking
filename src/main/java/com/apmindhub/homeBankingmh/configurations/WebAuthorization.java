package com.apmindhub.homeBankingmh.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity
@Configuration
public class WebAuthorization{
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeRequests()
                .antMatchers("/web/index.html", "/web/js/**","/web/css/**","/web/img/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/login","/api/logout","/api/clients").permitAll()
                .antMatchers("/h2-console/**","/rest/**", "/api/clients").hasAuthority("ADMIN")
                .antMatchers("/api/**","/api/clients/{id}").hasAuthority("CLIENT")
                .antMatchers("/api/clients/current", "/web/**","/api/clients/**").hasAuthority("CLIENT")
                .antMatchers(HttpMethod.POST, "/clients/current/accounts/**","/clients/current/cards").hasAuthority("CLIENT")
                .anyRequest().denyAll();

        http.formLogin()
                .usernameParameter("email") // propiedad de la clase
                .passwordParameter("password")
                .loginPage("/api/login");



        http.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");

        http.csrf().disable();

        //disabling frameOptions so h2-console can be accessed

        http.headers().frameOptions().disable();

        // if user is not authenticated, just send an authentication failure response

        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if login is successful, just clear the flags asking for authentication

        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // if login fails, just send an authentication failure response

        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // if logout is successful, just send a success response

        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

        return http.build();
    }
    private void clearAuthenticationAttributes(HttpServletRequest req) {
        HttpSession session = req.getSession(false);

        if (session != null){
            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
        }
    }
}
