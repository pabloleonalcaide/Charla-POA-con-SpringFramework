package config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import restApi.Uris;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        // auth.inMemoryAuthentication().//
        // withUser("user").password("123456").roles("USER").and().//
        // withUser("manager").password("123456").roles("MANAGER").and().//
        // withUser("admin").password("123456").roles("ADMIN", "MANAGER", "USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().exceptionHandling().accessDeniedHandler(null).and().authorizeRequests()
                .antMatchers(Uris.SERVLET_MAP + Uris.VERSION + Uris.ADMINS + "/**").permitAll()
                .antMatchers(Uris.SERVLET_MAP + Uris.VERSION + Uris.SECURITY + Uris.USER + "/**").authenticated()
                .antMatchers(HttpMethod.GET, Uris.SERVLET_MAP + Uris.VERSION + Uris.SECURITY + Uris.ADMIN).hasRole("ADMIN").and()
                .httpBasic();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
