package spring30.springsecurity30.config;

import org.springframework.context.annotation.Bean; 
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity; 
import org.springframework.security.config.annotation.web.builders.HttpSecurity; 
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity; 
import org.springframework.security.core.userdetails.User; 
import org.springframework.security.core.userdetails.UserDetails; 
import org.springframework.security.core.userdetails.UserDetailsService; 
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder; 
import org.springframework.security.crypto.password.PasswordEncoder; 
import org.springframework.security.provisioning.InMemoryUserDetailsManager; 
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import spring30.springsecurity30.entity.Person;
import spring30.springsecurity30.service.UserService; 

@Configuration
//@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig { 

	// User Creation 
	@Bean
	public UserDetailsService userDetailsService() { 

//		// InMemoryUserDetailsManager 
//		UserDetails admin = User.withUserDetails(person).roles("ADMIN", "USER").build();
////				withUsername("Amiya") 
////				.password(encoder.encode("123")) 
////				.roles("ADMIN", "USER") 
////				.build(); 
//
//		UserDetails user = User.withUsername("Ejaz") 
//				.password(encoder.encode("123")) 
//				.roles("USER") 
//				.build(); 

		return new UserService(); 
	} 

//	// Configuring HttpSecurity 
//	@Bean
//	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception { 
//		return http.csrf(Customizer.withDefaults()) //.disable() 
//				.authorizeHttpRequests(Customizer.withDefaults())
////				.requestMatchers("/auth/welcome").permitAll() 
////				.and() 
//				.authorizeHttpRequests(Customizer.withDefaults()).requestMatchers("/auth/user/**").authenticated() 
//				.and() 
//				.authorizeHttpRequests().requestMatchers("/auth/admin/**").authenticated() 
//				.and().formLogin() 
//				.and().build(); 
//	} 

	
	  @Bean
	  public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		    http
		        .authorizeHttpRequests(requests -> requests
		            .requestMatchers("/auth/welcome",
		            		"/auth/user/userProfile"
		            		,"/auth/admin/adminProfile").authenticated()
		            .requestMatchers("/auth/hello").permitAll()
		            ).formLogin(Customizer.withDefaults())
		        .httpBasic(Customizer.withDefaults());
		    return http.build();
		  }
	
	// Password Encoding 
	@Bean
	public PasswordEncoder passwordEncoder() { 
		return new BCryptPasswordEncoder(); 
	} 
	
	   @Bean
	    public AuthenticationProvider authenticationProvider(){
	        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
	        authenticationProvider.setUserDetailsService(userDetailsService());
	        authenticationProvider.setPasswordEncoder(passwordEncoder());
	        return authenticationProvider;
	    }
	   
} 
