package gdu.kr.chap2.javaconfig;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import gdu.kr.chap2.BuildRunner;
import gdu.kr.chap2.Camera;
import gdu.kr.chap2.DisplayMode;
import gdu.kr.chap2.InfraredRaySensor;
import gdu.kr.chap2.Project;

@Configuration //컨테이너(bean) 설정 클래스 라는 뜻
@ComponentScan(basePackages= {"gdu.kr.chap2"}) 
//gdu.kr.chap2의 패키지를 스캔하면서 @Component가 있는 내용들을 객체화 시켜라
//xml의 <context:component-scan>이랑 같은 내용
public class AppCtx {
	@Bean // 객체화.메서드의 이름으로 객체가 저장됨
	public Camera camera1() {// xml의 <bean id="camera1">이랑 같은 내용
		Camera c = new Camera();
		c.setNumber(1);
		return c;
	}

	@Bean
	public Camera camera2() {
		Camera c = new Camera();
		c.setNumber(2);
		return c;
	}

	@Bean
	public Camera camera3() {
		Camera c = new Camera();
		c.setNumber(3);
		return c;
	}

	@Bean
	public Camera camera4(){
		Camera c = new Camera();
		c.setNumber(4);
		return c;
    }
	@Bean
	@Qualifier("intrusionDetection")
	public InfraredRaySensor windowSensor() {
		return new InfraredRaySensor("창센서");
	}
	@Bean
	@Qualifier("intrusionDetection") //doorSensor의 별명으로 넣음
	public InfraredRaySensor doorSensor() {
		return new InfraredRaySensor("현관센서");
	}
	@Bean
	public InfraredRaySensor lampSensor() { //@Qualifier("intrusionDetection")를 안 써서 이걸로는 가져올 수 없다.
		return new InfraredRaySensor("전등센서");
	}
	@Bean
	public DisplayMode displatMode() {
		DisplayMode d = new DisplayMode();
		d.setType("GRID");
		return d;
	}
	@Bean
	public Project project() {
		Project p = new Project();
		p.setBindir("bin");
		p.setClasspath("src/");
		List<String> list = Arrays.asList("src/","srcResources/");
		p.setSrcdirs(list);
		p.setBuild(runner());
		return p;
}
	@Bean
	public BuildRunner runner() {
		BuildRunner b = new BuildRunner();
		b.setPath("c:setup/");
		return b;
		
	}
	
}
/*
 ====일반 자바 클래스에서 사용되는 어노테이션 ====
    @Component : 객체 생성, 객체 주입이 완료
    @Autowired : 자료형 기준으로 객체 주입.  
                 주입 되는 객체가 없는 경우 : 오류 발생
                 객체가 없어도 허용 : @Autowired(required=false)
                 @Resource(name=이름) : 해당 이름을 가진 객체를 주입
              
    @Scope    : 객체 생성 시 일회성으로 객체 생성
    
 ====환경 설정을 위한 자바 클래스에서 사용 되는 어노테이선====
    @Configuration : 환경 설정 자바 클래스. xml 대체 클래스
    @ComponentScan : 객체 생성을 위한 패키지 scan 설정
    @Bean   : xml의 <bean id = "".../> 와 같다. 환경설정 자바 클래스에서 메서드에 사용 됨
              리턴 타입에 해당하는 객체 생성. 메서드의 이름으로 객체를 저장            
             
                 
    --DI를 해줌--
    
*/