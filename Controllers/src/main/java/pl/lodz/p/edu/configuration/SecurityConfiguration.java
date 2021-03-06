package pl.lodz.p.edu.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import pl.lodz.p.edu.mapping.UserMappingService;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserMappingService userMappingService;

    public SecurityConfiguration(UserMappingService userPrincipalDetailsService) {
        this.userMappingService = userPrincipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/**").permitAll()
//                .antMatchers("/api/v1/**").permitAll()
//                .antMatchers("/trains").permitAll()
//                .antMatchers("/trains/sort").permitAll()
//                .antMatchers("/trains/train/**").authenticated()
//                .antMatchers("/trains/**").hasAnyRole("ResourcesManager", "Admin")
//                .antMatchers("/users/registration").permitAll()
//                .antMatchers("/tickets/add").hasAnyRole("Client", "ResourcesManager", "Admin")
//                .antMatchers("/tickets/show").hasAnyRole("Client", "ResourcesManager", "Admin")
//                .antMatchers("/tickets/**").hasAnyRole("ResourcesManager", "Admin")
//                //.antMatchers("/users/**").permitAll()
//                .antMatchers("/users/**").hasRole("Admin")
                .and().csrf().disable()
                .formLogin()
                .loginPage("/login").defaultSuccessUrl("/home").permitAll()
                .usernameParameter("txtUsername")
                .passwordParameter("txtPassword")
                .and()
                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).logoutSuccessUrl("/login")
                .and()
                .rememberMe();
    }

    @Bean
    DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(this.userMappingService);

        return daoAuthenticationProvider;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
