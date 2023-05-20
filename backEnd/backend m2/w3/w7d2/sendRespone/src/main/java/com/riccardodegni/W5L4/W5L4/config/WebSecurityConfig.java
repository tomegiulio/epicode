package com.riccardodegni.W5L4.W5L4.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.riccardodegni.W5L4.W5L4.dao.DaoService;

@SuppressWarnings("deprecation")
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)		// obbligatoria per consentire le PreAuthorize per-metodo
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DaoService dao;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		/*
		 * gestisci le autorizzazioni
		 * permetti l'accesso a / e /page1 a tutti
		 * mentre a tutte le altre imponi autorizzazione e redirect sulla pagina
		 * di login
		 */
		http
			.authorizeRequests()					
				.antMatchers("/", "/page1", 
						"/auth_add_user", "/auth_update_user_pw", "/auth_update_user_cryptovalue1",
						"/getuser/**")
				.permitAll()
			.anyRequest()
				.authenticated()
			.and()
			.formLogin()
				.successForwardUrl("/login_success")
			.and()
			.logout()
			.and()
			.csrf()
				.disable();
	}
	
	// v1 hardcoded
	/*
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("student").password( passwordEncoder().encode("mela") )
			.roles("ADMIN");
	}
	*/
	
	// v2 get from db
	/*
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		Optional<User> authUserObj = dao.getUserById(1);
		User authUser = authUserObj.get();
		String role = "USER";
		Set<Role> roles = authUser.getRoles();
		
		for( Role r : roles ) {
			if( r.getType().toString().contains("ADMIN") ) {
				role = "ADMIN";
				break;
			}
		}
		
		auth.inMemoryAuthentication()
			.withUser( authUser.getUsername() ).password( passwordEncoder().encode( authUser.getPassword() ) )
			.roles(role);
	}
	*/
	
	@Autowired
	private UserDetailsImplService us;
	
	// v2 bd con userdetailsimpl
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth
			.userDetailsService(us)
			.passwordEncoder( passwordEncoder() );
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
