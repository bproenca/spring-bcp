package br.com.bcp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity //The @EnableWebSecurity turns on a variety of beans needed to use Spring Security.
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    /*
    You also need an LDAP server. Spring Security’s LDAP module includes an embedded server
    written in pure Java, which is being used for this guide. The ldapAuthentication() method
    configures things where the username at the login form is plugged into {0} such that it
    searches uid={0},ou=people,dc=springframework,dc=org in the LDAP server.
     */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
	auth
			.ldapAuthentication()
			.userDnPatterns("uid={0},ou=people")
			.groupSearchBase("ou=groups")
			.contextSource().ldif("classpath:test-server.ldif");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
	http
			.authorizeRequests()
			.anyRequest().fullyAuthenticated()
			.and()
			.formLogin();
    }
}
/*
LDAP servers can use LDIF (LDAP Data Interchange Format) files to exchange user data. The
ldif() method inside WebSecurityConfig pulls in an LDIF data file. This makes it easy
to pre-load demonstration data.

src/main/resources/test-server.ldif

Using an LDIF file isn’t standard configuration for a production system. However, it’s very useful for testing purposes or guide
 */