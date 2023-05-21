package pl.sdaacademy.pokemonapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import pl.sdaacademy.pokemonapi.user.UserService;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    public SecurityConfiguration(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/pokemon/details/**").authenticated()
                .antMatchers(HttpMethod.POST, "/users/**").permitAll()
                .antMatchers("/pokemon/list").permitAll()
                .and()
                .httpBasic()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable();
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userService;
    }
}
