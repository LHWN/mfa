package com.example.demo.mfa.configures;

import com.example.demo.mfa.configures.filter.CustomUsernamePasswordAuthenticationFilter;
import com.example.demo.mfa.configures.filter.PreUsernamePasswordAuthenticationFilter;
import com.example.demo.mfa.configures.handler.FailureHandler;
import com.example.demo.mfa.configures.handler.SuccessHandler;
import com.example.demo.mfa.configures.provider.CustomDaoAuthenticationProvider;
import com.example.demo.mfa.service.CustomUserDetailsService;
import com.example.demo.mfa.service.MfaService;
import com.example.demo.mfa.service.UserService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.convert.Jsr310Converters;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableJpaRepositories(basePackages = {"com.example.demo.mfa.repositories"})
@EntityScan(basePackages = {"com.example.demo.mfa.datas"}, basePackageClasses = {Jsr310Converters.class})
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsService customUserDetailsService;
    private final UserService userService;
    private final MfaService mfaService;

    public WebSecurityConfiguration(CustomUserDetailsService customUserDetailsService, UserService userService, MfaService mfaService) {
        this.customUserDetailsService = customUserDetailsService;
        this.userService = userService;
        this.mfaService = mfaService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .mvcMatchers("/i18n/**")
                .mvcMatchers("/static/**")
                .mvcMatchers("/css/**")
                .mvcMatchers("/js/**")
                .mvcMatchers("images/**");
    }

    private final String permitallUrl = "/login,/,/prelogin,/mfactor,/purelogin";

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new PreUsernamePasswordAuthenticationFilter(passwordEncoder(), userService, mfaService), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAt(customUsernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        http.authenticationProvider(customDaoAuthenticationProvider());

        http.cors().and()
                .csrf().disable();

        http.headers().frameOptions().sameOrigin()
                .and().authorizeRequests().antMatchers(permitallUrl.split(",")).permitAll()
                .and().formLogin().loginPage("/login")
                .and().logout().logoutUrl("/logout").invalidateHttpSession(false).permitAll()
                .and().authorizeRequests().anyRequest().authenticated();
    }

    private CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter() throws Exception {
        CustomUsernamePasswordAuthenticationFilter customUsernamePasswordAuthenticationFilter = new CustomUsernamePasswordAuthenticationFilter(this.authenticationManagerBean());
        customUsernamePasswordAuthenticationFilter.setAuthenticationSuccessHandler(new SuccessHandler());
        customUsernamePasswordAuthenticationFilter.setAuthenticationFailureHandler(new FailureHandler());

        return customUsernamePasswordAuthenticationFilter;
    }

    private CustomDaoAuthenticationProvider customDaoAuthenticationProvider() {
        CustomDaoAuthenticationProvider customDaoAuthenticationProvider = new CustomDaoAuthenticationProvider(customUserDetailsService);
        customDaoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

        return customDaoAuthenticationProvider;
    }
}
