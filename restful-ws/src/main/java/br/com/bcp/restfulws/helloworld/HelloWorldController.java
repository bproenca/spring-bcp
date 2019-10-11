package br.com.bcp.restfulws.helloworld;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Autowired
    private MessageSource messageSource;

	@GetMapping(path = "/hello-world")
	public String helloWorld() {
		return "Hello World";
	}

	@GetMapping(path = "/hello-world-bean")
	public HelloWorldBean helloWorldBean() {
		return new HelloWorldBean("Hello World");
    }
    
    @GetMapping(path = "/hello-world/internacionalized")
	//public HelloWorldBean helloWorldInternacionalized(@RequestHeader(name="Accept-Language", required=false) Locale locale) {
	public HelloWorldBean helloWorldInternacionalized() {
		return new HelloWorldBean(messageSource.getMessage("good.morning.message", null, LocaleContextHolder.getLocale()));
	}
	
	//http://localhost:8080/hello-world/path-variable/Bruno
	@GetMapping(path = "/hello-world/path-variable/{name}")
	public HelloWorldBean helloWorldPathVariable(@PathVariable String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
    }
    
    //http://localhost:8080/hello-world/request-param?name=Bruno
    @GetMapping(path = "/hello-world/request-param")
	public HelloWorldBean helloWorldRequestParam(@RequestParam(defaultValue="Fulano") String name) {
		return new HelloWorldBean(String.format("Hello World, %s", name));
	}
	
}