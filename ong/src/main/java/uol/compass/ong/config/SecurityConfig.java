package uol.compass.ong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import uol.compass.ong.security.JwtAuthFilter;
import uol.compass.ong.security.JwtService;
import uol.compass.ong.services.UsuarioService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private JwtService jwtService;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    public OncePerRequestFilter jwtFilter(){
        return new JwtAuthFilter(jwtService, usuarioService);
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
        	.userDetailsService(usuarioService)
        	.passwordEncoder(passwordEncoder());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
	      .csrf().disable()
	      .authorizeRequests()
//	      	.antMatchers("/**").permitAll()
//	      	.antMatchers(HttpMethod.POST, "/usuarios/**").permitAll()
//	      	.antMatchers(HttpMethod.POST, "/usuarios/**").hasAnyRole("ADMIN", "USER")
	      	.antMatchers(HttpMethod.GET, "/**").hasAnyRole("ADMIN", "USER")
	      	.antMatchers(HttpMethod.POST, "/**").hasRole("ADMIN")
	      	.antMatchers(HttpMethod.PUT, "/**").hasRole("ADMIN")
	      	.antMatchers(HttpMethod.DELETE, "/**").hasRole("ADMIN")
	        .anyRequest().authenticated()
	      .and()
	      	.httpBasic();
//	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//		  .and()
//		  	.addFilterBefore(jwtFilter(), UsernamePasswordAuthenticationFilter.class);
	}
}
