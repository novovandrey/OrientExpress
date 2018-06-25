package ru.novand.orientexpress.javaconfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import ru.novand.orientexpress.domain.DAO.Impl.UserDAOImpl;
import ru.novand.orientexpress.domain.DAO.interfaces.UserDAO;
import ru.novand.orientexpress.services.impl.UserDetailsServiceImpl;
import ru.novand.orientexpress.utils.Routes;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.io.IOException;
import java.util.Collection;

/**
 * security-config.xml analogue
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Bean
    public UserDAO userDAO() {
        return new UserDAOImpl();
    };

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl(userDAO());
    };


    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(bCryptPasswordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT USERNAME, PASSWORD, ENABLED FROM USER WHERE USERNAME=?")
                .authoritiesByUsernameQuery("SELECT U.USERNAME, A.AUTHORITY\n" +
                "        \t FROM AUTHORITIES A, USER U WHERE U.USERNAME = A.USERNAME AND U.USERNAME = ?");

    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new MyCustomLoginSuccessHandler(Routes.home);
    }

    public class MyCustomLoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
        public MyCustomLoginSuccessHandler(String defaultTargetUrl) {
            setDefaultTargetUrl(defaultTargetUrl);
        }

        @Override
        public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
            HttpSession session = request.getSession();
            String roleStr =authentication.getAuthorities().toString();
            if (session != null) {
                String redirectUrl = (String) session.getAttribute("url_prior_login");
                if(!roleStr.isEmpty())
                {
                    if (roleStr.equals("[ROLE_ADMIN]")) redirectUrl = "trainRouteList";
                }
                    if (redirectUrl != null) {
                    session.removeAttribute("url_prior_login");
                    getRedirectStrategy().sendRedirect(request, response, redirectUrl);
                } else {
                    super.onAuthenticationSuccess(request, response, authentication);
                }
            } else {
                super.onAuthenticationSuccess(request, response, authentication);
            }
        }
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //TODO add and configure user permissions
        //TOFO add login success handler
        http.authorizeRequests()
//                .anyRequest().authenticated() //all requests will checked
                .and()
                .formLogin().loginPage("/login").permitAll().usernameParameter("j_username")
                .passwordParameter("j_password").loginProcessingUrl("/j_spring_security_check").failureUrl("/login.html?error=true")
                .and()
                .httpBasic()
                .and()
                .authorizeRequests()
                .antMatchers("/resources/**", "form//registration").permitAll()
                //.anyRequest().authenticated()
                .antMatchers("/addstation_emp.html/**").hasRole("ADMIN")
                .antMatchers("/addstation_emp/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/passengerList.html/**").hasRole("ADMIN")
                .antMatchers("/passengerList/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/trains.html/**").hasRole("ADMIN")
                .antMatchers("/trains/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/user/**").hasRole("USER")
                .and().formLogin().defaultSuccessUrl("/home", false)// if success auth redirect to prev page
                .successHandler(successHandler())
                .and()
                .logout().logoutUrl("/j_spring_security_logout").logoutSuccessUrl("/")
                .and()
                .rememberMe().key("myKey").tokenValiditySeconds(300)
                .and()
                .csrf().disable();

    }

}
