package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
   @Autowired
   DataSource dataSource;

    @Autowired
    public void configAuthentication (AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT  username, password, enabled  FROM users WHERE username=?")
                .authoritiesByUsernameQuery("SELECT username, role FROM user_roles WHERE username=?");



    }

@Override
    protected void configure (HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .antMatchers("/login").authenticated()
                .anyRequest().permitAll()
                .and().formLogin()
                .usernameParameter("username").passwordParameter("password").defaultSuccessUrl("/")




                .and().csrf().disable()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

    }


}
