package com.sales.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
/*
 * This class is taking care of the security req's for the project. 
 * Changed from the template as that code didn't work for my version of Spring.
 * References: 
 * https://stackoverflow.com/questions/20848312/how-to-correctly-logout-user-in-spring-security
 * https://stackoverflow.com/questions/35218354/difference-between-registerglobal-configure-configureglobal-configureglo
 */
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

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
}
