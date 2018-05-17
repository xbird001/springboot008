package com.springboot;

import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;



/**
 * 第一个org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration可以排除，
 * 但是第二个我们自己定的com.springboot.MyConfig是不能排除的，因为excludeName是排除的那
 * 些配置spring.factories里面的，如果想排除掉com.springboot.MyConfig，可以在com.springboot.MyConfig
 * 配置在META-INF/spring.factories这个里面，具体可参见本项目META-INF/spring.factories里面的配置
 * *****注意：com.springboot.MyConfig此类上不能标有@Component注解或者是@Configuration等注解******
 * 的spring.factories里面即可，参见：
 * @author Administrator
 *
 */
@SpringBootApplication(excludeName={"org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration","com.springboot.MyConfig"})
public class App {
	
	public static void main(String[] args) {
		/**
		 * SpringApplication.run(Object source, String... args);的run方法默认把source包装成一个Configuration注解过的类，即调用
		 * 此方法时，完全可以不用在对应的类上面添加任何注解，所以此时我们可以在任何source类中添加@bean注解，并获取对象
		 */
		SpringApplication.run(App.class, args);
		SpringApplication app = new SpringApplication(App.class);
		
		Map<String, Object> defaultProperties = new HashMap<String,Object>();
		defaultProperties.put("server.ip", "127.0.0.1");
		defaultProperties.put("server.port", 8080);
		app.setDefaultProperties(defaultProperties);
		
		ConfigurableApplicationContext context = app.run(args);
		System.out.println(context.getBean(Dog.class));
		//此时会报错，将com.springboot.MyConfig从@SpringBootApplication的excludeName属性中去掉，即可不报错
		System.out.println(context.getBean(User.class));
		System.out.println(context.getBean(Person.class));
		/*System.out.println(context.getBean(Gson.class));*/
		System.out.println("server.ip:" + context.getEnvironment().getProperty("server.ip") + " server.port:" + context.getEnvironment().getProperty("server.port"));
		
		context.close();
	}

}
