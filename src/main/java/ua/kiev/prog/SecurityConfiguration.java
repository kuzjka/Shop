package ua.kiev.prog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource dataSource;

    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT  * FROM users WHERE login=?")
                .authoritiesByUsernameQuery("Select  * FROM users WHERE login=?");


    }

@Override
    protected void configure (HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()

                .antMatchers("/admin").hasRole("ADMIN")
                .and()
                .formLogin()
                .usernameParameter("login").passwordParameter("password")

                .and().csrf().disable()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");

    }


}
