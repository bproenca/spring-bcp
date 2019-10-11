package br.com.bcp.restfulws;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class RestfulWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestfulWsApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		//SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
		AcceptHeaderLocaleResolver sessionLocaleResolver = new AcceptHeaderLocaleResolver();
		sessionLocaleResolver.setDefaultLocale(Locale.US);
		return sessionLocaleResolver;
	}

	// BCP definido no application.properties "spring.messages.basename=messages"
	// **************************************************************************
	// @Bean
	// public ResourceBundleMessageSource messageSource() {
	// 	  ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
	// 	  messageSource.setBasename("messages");
	// 	  return messageSource;
	// }
}
