package cl.empresa.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import cl.empresa.api.security.jwt.AuthEntryPointJwt;
import cl.empresa.api.security.jwt.JWTAuthorizationFilter;

@EnableWebSecurity
@Configuration
class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private AuthEntryPointJwt unauthorizedHandler;
	
	@Autowired
	private  JWTAuthorizationFilter jwtAuthorizationFilter;

	@Value("${spring.h2.console.path}")
	private String h2ConsolePath;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
			.addFilterAfter(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class)
				.authorizeRequests()
					.antMatchers(HttpMethod.POST, "/user/login").permitAll()
					.antMatchers(HttpMethod.POST, "/user/registro").permitAll()
					.antMatchers(h2ConsolePath + "/**").permitAll()
				.anyRequest().authenticated();

		// fix H2 database console: Refused to display ' in a frame because it set 'X-Frame-Options' to 'deny'
	    http.headers().frameOptions().sameOrigin();
	}
}
