package io.phyokyaw.springbootsecurityjpa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	UserDetailsService userDetailsService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub

		
		auth.userDetailsService(userDetailsService);
		
		//auth.inMemoryAuthentication()
//			.withUser("bla")
//			.password("bla")
//			.roles("USER")
//			.and()
//			.withUser("foo")
//			.password("foo")
//			.roles("ADMIN");
		
//		
//		auth.jdbcAuthentication()
//			.dataSource(dataSource)
//			.usersByUsernameQuery("select username, password, enabled " 
//					+ "from users "
//					+ "where username = ? ")
//			.authoritiesByUsernameQuery("select username, authority " 
//					+ "from authorities "
//					+ "where username = ? ");	
//
//
//		auth.jdbcAuthentication()
//		.dataSource(dataSource)
//			.withDefaultSchema()
//			.withUser(
//					User.withUsername("user")
//					.password("user")
//					.roles("USER"))
//			.withUser(
//					User.withUsername("foo")
//					.password("foo")
//					.roles("ADMIN")
//					);		
		
	}
	
	@SuppressWarnings("deprecation")
	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		http.authorizeRequests()
			.antMatchers("/admin").hasRole("ADMIN")
			.antMatchers("/user").hasAnyRole("USER", "ADMIN")
			.antMatchers("/").permitAll()
			.and().formLogin();
	}
	
	

	
}
