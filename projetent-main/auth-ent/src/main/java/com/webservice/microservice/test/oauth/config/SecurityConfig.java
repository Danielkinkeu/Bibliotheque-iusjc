package com.webservice.microservice.test.oauth.config;

import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
class SecurityConfig extends WebSecurityConfigurerAdapter {
/*
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Bean(name = "passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public AuthenticationSuccessHandler successHandler() {
        return new RefererRedirectionAuthenticationSuccessHandler("/yourdefaultsuccessurl");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        *//*
        DefaultRedirectStrategy authenticationSuccessHandlerRedirectStrategy = new DefaultRedirectStrategy();
        authenticationSuccessHandlerRedirectStrategy.setContextRelative(true);

        SavedRequestAwareAuthenticationSuccessHandler authenticationSuccessHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        authenticationSuccessHandler.setDefaultTargetUrl("/");
        authenticationSuccessHandler.setAlwaysUseDefaultTargetUrl(true);
        authenticationSuccessHandler.setRedirectStrategy(authenticationSuccessHandlerRedirectStrategy);
        //*//*


        http.anonymous().and()
                .authorizeRequests()
                .antMatchers("/", "/public/**", "/oauth/authorize", "/authorize", "/oauth/login", "/login", "/oauth/login2", "/login2").permitAll()
                .and()
                .formLogin()
                .successHandler(new RefererRedirectionAuthenticationSuccessHandler("/"))
                .permitAll()
                .and()
                .logout()
                .permitAll().and().anonymous();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }*/
}