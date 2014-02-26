package net.madvirus.spring4.chap07.config;

import net.madvirus.spring4.chap07.ac.ACLController;
import net.madvirus.spring4.chap07.ac.AclService;
import net.madvirus.spring4.chap07.auth.Authenticator;
import net.madvirus.spring4.chap07.auth.LoginController;
import net.madvirus.spring4.chap07.calculator.CalculationController;
import net.madvirus.spring4.chap07.etc.SimpleHeaderController;
import net.madvirus.spring4.chap07.event.EventController;
import net.madvirus.spring4.chap07.event.EventCreationController;
import net.madvirus.spring4.chap07.exhandler.CommonExceptionHandler;
import net.madvirus.spring4.chap07.file.FileController;
import net.madvirus.spring4.chap07.member.MemberController;
import net.madvirus.spring4.chap07.member.MemberModificationController;
import net.madvirus.spring4.chap07.member.MemberService;
import net.madvirus.spring4.chap07.member.RegistrationController;
import net.madvirus.spring4.chap07.search.SearchController;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
public class SampleConfig extends WebMvcConfigurerAdapter {

	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("index");
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver result = new InternalResourceViewResolver();
		result.setPrefix("/WEB-INF/view/");
		result.setSuffix(".jsp");
		return result;
	}

	@Bean
	public MemberService memberService() {
		return new MemberService();
	}

	@Bean
	public EventController eventController() {
		return new EventController();
	}

	@Bean
	public EventCreationController eventCreationController() {
		return new EventCreationController();
	}

	@Bean
	public RegistrationController registrationController() {
		RegistrationController result = new RegistrationController();
		result.setMemberService(memberService());
		return result;
	}

	@Bean
	public MemberController memberController() {
		MemberController result = new MemberController();
		result.setMemberService(memberService());
		return result;
	}

	@Bean
	public MemberModificationController memberModController() {
		MemberModificationController result = new MemberModificationController();
		result.setMemberService(memberService());
		return result;
	}

	@Bean
	public FileController fileController() {
		return new FileController();
	}

	@Bean
	public SearchController searchController() {
		return new SearchController();
	}

	@Bean
	public SimpleHeaderController simpleHeaderController() {
		return new SimpleHeaderController();
	}

	@Bean
	public AclService aclService() {
		return new AclService();
	}

	@Bean
	public ACLController aclController() {
		ACLController aclController = new ACLController();
		aclController.setAclService(aclService());
		return aclController;
	}

	@Bean
	public Authenticator authenticator() {
		return new Authenticator(memberService());
	}

	@Bean
	public LoginController loginController() {
		LoginController result = new LoginController();
		result.setAuthenticator(authenticator());
		return result;
	}

	@Bean
	public CalculationController calculationController() {
		return new CalculationController();
	}

	@Bean
	public CommonExceptionHandler commonExceptionHandler() {
		return new CommonExceptionHandler();
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource ms = new ResourceBundleMessageSource();
		ms.setBasenames("message.error");
		ms.setDefaultEncoding("UTF-8");
		return ms;
	}

}
