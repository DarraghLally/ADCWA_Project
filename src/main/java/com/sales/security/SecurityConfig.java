package com.sales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	/*
	@Autowired
	public void configureGlobalSecurity(AuthenticationManagerBuilder amb)  throws Exception{
		amb.inMemoryAuthentication().passwordEncoder(NoOpPasswordEncoder.getInstance())
		.withUser("user").password("user").roles("USER", "ADMIN");
	}
	
	@Override
	protected void configure(HttpSecurity hs) throws Exception{
		hs.authorizeRequests().antMatchers("/login", "/h2-console/**").permitAll().antMatchers("/addCustomer.html")
	}
	*/
	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/addCustomer.html", "/addProduct.html", "/newOrder.html",  "/showCustomers.html", "/showOrders.html", "/showProducts.html")
			.authenticated()
			.and().formLogin()
			.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
	}

	@Autowired
	public void ConfigureGlobal(AuthenticationManagerBuilder amb) throws Exception{
		amb.inMemoryAuthentication().withUser("user").password("user").roles("USER");
	}
	/*
	@Bean
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("user").password("password").roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
	*/

}
