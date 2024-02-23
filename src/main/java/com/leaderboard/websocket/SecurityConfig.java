package com.leaderboard.websocket;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests()
//                    .mvcMatchers("/","/ws/**")
//                    .permitAll()
//                .and()
//                .authorizeHttpRequests()
//                    .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .and()
//                .logout( logout -> logout.logoutSuccessUrl("/"));
//        return http.build();
//    }
//
//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("test")
//                .password("test")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}