package com.jwt.security.learn.securityjwt.config;

import com.jwt.security.learn.securityjwt.filter.CustomAuthenticationFilter;
import com.jwt.security.learn.securityjwt.filter.CustomAuthorizationFilter;
import com.jwt.security.learn.securityjwt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserServiceImpl userDetailsService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter filter = new CustomAuthenticationFilter(authenticationManager());
        filter.setFilterProcessesUrl("/api/login");

        http.csrf().disable();// Disable this for Testing purpose

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.authorizeRequests().antMatchers("/api/login").permitAll();
        //user can only access this url
        http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/users").hasAnyAuthority("USER");

        //Manager can access these urls
        http.authorizeRequests().antMatchers( "/api/users","/api/user/save").hasAnyAuthority("MANAGER");

        //Admin can do this
        http.authorizeRequests().antMatchers( "/api/users","/api/user/save","/api/role/save")
                .hasAnyAuthority("ADMIN");

        //super admin can access all urls
        http.authorizeRequests().antMatchers( "/api/**").hasAnyAuthority("SUPER_ADMIN");


        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(filter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }
}
