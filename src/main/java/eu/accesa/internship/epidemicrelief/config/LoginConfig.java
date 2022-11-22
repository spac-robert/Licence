package eu.accesa.internship.epidemicrelief.config;

import eu.accesa.internship.epidemicrelief.model.Role;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class LoginConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/products", "/products/**").hasAuthority(Role.ADMIN.name())
                .antMatchers("/profile/**").hasAuthority(Role.USER.name())
                .anyRequest().authenticated().and().formLogin().and().logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        UserDetails admin = User.withUsername("admin").password("{noop}nimda").authorities(Role.ADMIN.name()).build();
        UserDetails user = User.withUsername("user").password("{noop}resu").authorities(Role.USER.name()).build();

        userDetailsService.createUser(admin);
        userDetailsService.createUser(user);

        auth.userDetailsService(userDetailsService);
    }
}
