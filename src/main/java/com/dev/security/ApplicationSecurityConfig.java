package com.dev.security;

import com.dev.auth.ApplicationUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;

import static com.dev.security.ApplicationUserPermission.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    @Autowired
    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()

                .antMatchers(HttpMethod.GET, "/api/v1/tire/**").hasAnyAuthority(TIRE_READ.getPermisson(), TIRE_WRITE.getPermisson(), TIRE_DELETE.getPermisson())
                .antMatchers(HttpMethod.POST, "/api/v1/tire/**").hasAnyAuthority(TIRE_WRITE.getPermisson(), TIRE_DELETE.getPermisson())
                .antMatchers(HttpMethod.PUT, "/api/v1/tire/**").hasAnyAuthority(TIRE_WRITE.getPermisson(), TIRE_DELETE.getPermisson())
                .antMatchers(HttpMethod.DELETE, "/api/v1/tire/**").hasAnyAuthority(TIRE_DELETE.getPermisson())

                .antMatchers(HttpMethod.GET, "/api/v1/race/**").hasAnyAuthority(RACE_READ.getPermisson(), RACE_WRITE.getPermisson())
                .antMatchers(HttpMethod.POST, "/api/v1/race/**").hasAnyAuthority(RACE_WRITE.getPermisson())
                .antMatchers(HttpMethod.PUT, "/api/v1/race/**").hasAnyAuthority(RACE_WRITE.getPermisson())
                .antMatchers(HttpMethod.DELETE, "/api/v1/race/**").hasAnyAuthority(RACE_WRITE.getPermisson())

                .antMatchers(HttpMethod.GET, "/api/v1/race/**").hasAnyAuthority(RACE_READ.getPermisson(), RACE_WRITE.getPermisson())
                .antMatchers(HttpMethod.POST, "/api/v1/manage/race/**").hasAnyAuthority(RACE_WRITE.getPermisson())
                .antMatchers(HttpMethod.PUT, "/api/v1/manage/race/**").hasAnyAuthority(RACE_WRITE.getPermisson())
                .antMatchers(HttpMethod.DELETE, "/api/v1/manage/race/**").hasAnyAuthority(RACE_WRITE.getPermisson())

                .antMatchers(HttpMethod.GET, "/api/v1/weather/**").hasAnyAuthority(WEATHER_READ.getPermisson(), WEATHER_WRITE.getPermisson(), WEATHER_DELETE.getPermisson())
                .antMatchers(HttpMethod.POST, "/api/v1/weather/**").hasAnyAuthority(WEATHER_WRITE.getPermisson(), WEATHER_DELETE.getPermisson())
                .antMatchers(HttpMethod.PUT, "/api/v1/weather/**").hasAnyAuthority(WEATHER_WRITE.getPermisson(), WEATHER_DELETE.getPermisson())
                .antMatchers(HttpMethod.DELETE, "/api/v1/weather/**").hasAnyAuthority(WEATHER_DELETE.getPermisson())

                .antMatchers(HttpMethod.GET, "/api/v1/user/**").hasAnyAuthority(USER_READ.getPermisson(), USER_WRITE.getPermisson())
                .antMatchers(HttpMethod.POST, "/api/v1/user/**").hasAnyAuthority(USER_WRITE.getPermisson())
                .antMatchers(HttpMethod.PUT, "/api/v1/user/**").hasAnyAuthority(USER_WRITE.getPermisson())
                .antMatchers(HttpMethod.DELETE, "/api/v1/user/**").hasAnyAuthority(USER_WRITE.getPermisson())

                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login").permitAll()
                .and()
                .rememberMe()
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(1))
                .and()
                .logout()
                .logoutUrl("/logout")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")
                .logoutSuccessUrl("/login");
    }


}
