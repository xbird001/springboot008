1、@EnableAutoConfiguration的深入分析
	1.1、@EnableAutoConfiguration的作用是从classpath中搜索 所有 META-INF/spring.factories配置文件，然后将其中的
		org.springframework.boot.autoconfigure.EnableAutoConfiguration key对应的配置加载到spring容器中
	1.2、其中的实现有两个关键点：
		1.2.1、ImportSelector的接口实现将返回的类全名Bean加载到spring容器中
		1.2.2、SpringFactoriesLoader该类可以从classpath中读取META-INF/spring.factories配置文件，并读取配置
	1.3、如果项目想从第三方Jar中管理Bean，需要开启@EnableAutoConfiguration并且在META-INF/spring.factories配置相应的属性