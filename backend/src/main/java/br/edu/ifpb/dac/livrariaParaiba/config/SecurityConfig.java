package br.edu.ifpb.dac.livrariaParaiba.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	/**
	 * Configura as permissões do usuário
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().permitAll().and().logout().permitAll();
	}

	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder().username("willian").password("willian").roles("ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
}
