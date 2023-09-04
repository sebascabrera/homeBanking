package com.apmindhub.homeBankingmh.configurations;

import com.apmindhub.homeBankingmh.models.Client;
import com.apmindhub.homeBankingmh.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public abstract class WebAuthentication extends GlobalAuthenticationConfigurerAdapter {
    @Autowired
    ClientRepository clientRepository;
    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(inputName-> {

            Client client = clientRepository.findByEmail(inputName);

            if (client != null) {
                // persona registrada en DB, se crea una sesion,
                if (client.getEmail().toLowerCase().contains("@admin.com")){
                    return new User(client.getEmail(), client.getPassword(),
                            AuthorityUtils.createAuthorityList("ADMIN"));
                }
                return new User(client.getEmail(), client.getPassword(),
                        // asignación de rol -ca tegoría de autoridad
                        AuthorityUtils.createAuthorityList("CLIENT"));
            } else {
                throw new UsernameNotFoundException("Unknown user: " + inputName);
            }
        });
    }
    @Bean
    public PasswordEncoder passwordEncoder() {

        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    protected abstract void configure(HttpSecurity http) throws Exception;
}
