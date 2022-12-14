package br.com.ifpe.Beto364.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.ifpe.Beto364.modelo.acesso.UsuarioService;
import br.com.ifpe.Beto364.security.jwt.JwtAuthenticationEntryPoint;
import br.com.ifpe.Beto364.security.jwt.JwtTokenAuthenticationFilter;
import br.com.ifpe.Beto364.security.jwt.JwtTokenProvider;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig {

    private static final String[] AUTH_WHITELIST = {
		"/swagger-resources/**",
		"/swagger-ui.html",
		"/swagger*/**",
		"/v2/api-docs",
		"/webjars/**",
		"/routes/**",
		"/favicon.ico",
		"/ws/**",
		"/beto364/**/dadosPedidoNew/**",
		"/beto364/image/**"
    };
    
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    
    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, BCryptPasswordEncoder bCryptPasswordEncoder,
	    UsuarioService userDetailService) throws Exception {
	
	return http.getSharedObject(AuthenticationManagerBuilder.class)
		.userDetailsService(userDetailService)
		.passwordEncoder(bCryptPasswordEncoder)
		.and()
		.build();
    }
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	
	 http
	 	.httpBasic().disable().csrf().disable().cors().and().sessionManagement()
	 	.sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
	 	.authenticationEntryPoint(authenticationEntryPoint).and().authorizeRequests()

	 	.antMatchers(AUTH_WHITELIST).permitAll()
	 	.antMatchers(HttpMethod.POST, "/api/login/signin").permitAll()
	 	
	 	.antMatchers(HttpMethod.POST, "/api/cliente").permitAll() //Libera o cadastro de cliente para o cadastro de usu??rio //Libera o cadastro de empresa para o cadastro de usu??rio
        
	 	//Configura????o de autoriza????es de acesso para Produto
        	.and().addFilterBefore(
        		new JwtTokenAuthenticationFilter(jwtTokenProvider),
        		UsernamePasswordAuthenticationFilter.class);
	 
	 return http.build();
    }
    
    @Bean
    public WebMvcConfigurer corsConfigurer() {

	return new WebMvcConfigurer() {
	    @Override
	    public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*");
	    }
	};
    }
    
}
