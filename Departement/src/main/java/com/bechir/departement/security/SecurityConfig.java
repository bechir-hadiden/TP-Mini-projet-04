package com.bechir.departement.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity

	public class SecurityConfig {
//	@Bean
//	public InMemoryUserDetailsManager userDetailsService() {
////        PasswordEncoder passwordEncoder = passwordEncoder();
//
//        UserDetails admin = User.withDefaultPasswordEncoder()
//                .username("admin")
//                .password("123")
//                .authorities("ADMIN")
//                .build();
//
//        UserDetails userBechir = User.withDefaultPasswordEncoder()
//                .username("bechir")
//                .password("123")
//                .authorities("AGENT","USER")
//                .build();
//
//        UserDetails user1 = User.withDefaultPasswordEncoder()
//                .username("user1")
//                .password("12345")
//                .authorities("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, userBechir, user1);
//    }
//	 
	 
	 @Bean
	 SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception 
	 {
	  http.authorizeHttpRequests((requests)->requests
	  
	 .requestMatchers("/showCreate","/saveDepartement","/modifierDepartement","/showCreatecollege","/saveCollege" ,"/supprimerDepartement","/supprimerCollege","/modifierCollege").hasAnyAuthority("ADMIN")
	 
	 .requestMatchers("/showCreate","/saveDepartement","/showCreatecollege" ,"/saveCollege").hasAnyAuthority("ADMIN","AGENT")

	  
	 .requestMatchers("/listeDepartement","/listCollege").hasAnyAuthority("ADMIN","AGENT","USER")
	 .requestMatchers("/login","s/webjars/**").permitAll() 
	 .anyRequest().authenticated())
	                  
      .formLogin((formLogin) ->   formLogin 
              .loginPage("/login") 
              .defaultSuccessUrl("/") ) 
      
	  .httpBasic(Customizer.withDefaults())
	  .exceptionHandling((exception)-> 
	  exception.accessDeniedPage("/accessDenied"));
	  return http.build();
	 }
	 
		@Bean
		 public BCryptPasswordEncoder passwordEncoder () {
		 return new BCryptPasswordEncoder();
		 }


		
//		@Bean 
//		public UserDetailsService userDetailsService(DataSource dataSource) {    
//		JdbcUserDetailsManager jdbcUserDetailsManager =new 
//		JdbcUserDetailsManager(dataSource); 
//		       
//		jdbcUserDetailsManager.setUsersByUsernameQuery("select username , password,   enabled from user where username =?"); 
//		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("SELECT   u.username, r.role as authority   " +  "FROM user_role ur, user u , role r " + 
//		"WHERE u.user_id = ur.user_id AND ur.role_id = r.role_id AND u.username = ?"); 
//		       
//		      return jdbcUserDetailsManager; 
//		     } 
		
		
		
	}



