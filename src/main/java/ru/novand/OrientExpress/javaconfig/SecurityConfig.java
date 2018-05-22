package ru.novand.OrientExpress.javaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * security-config.xml analogue
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT USERNAME, PASSWORD, ENABLED FROM USER WHERE USERNAME=?")
                .authoritiesByUsernameQuery("SELECT U.USERNAME, A.AUTHORITY\n" +
                "        \t FROM AUTHORITIES A, USER U WHERE U.USERNAME = A.USERNAME AND U.USERNAME = ?");;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO add and configure user permissions
        //TOFO add login success handler
        http.authorizeRequests()
//                .anyRequest().authenticated() //all requests will checked
                .and()
                .formLogin().loginPage("/login.html").permitAll().usernameParameter("j_username")
                .passwordParameter("j_password").loginProcessingUrl("/j_spring_security_check").failureUrl("/login.html?error=true")
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/addstation_emp.html/**").hasRole("ADMIN")
                .antMatchers("/addstation_emp/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/passengerList.html/**").hasRole("ADMIN")
                .antMatchers("/passengerList/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/trains.html/**").hasRole("ADMIN")
                .antMatchers("/trains/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/**").hasRole("USER")
                .and().formLogin().defaultSuccessUrl("/default", false)// if success auth redirect to prev page
                .and()
                .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/")
                .and()
                .rememberMe().key("myKey").tokenValiditySeconds(300)
                .and()
                .csrf().disable();

    }
}
