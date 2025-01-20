package config.soudsystem;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"soundsystem"}) // soundsystem이라는 패키지 내에 있는 모든 클래스에서 @Component 어노테이션이 붙은 클래스를 찾아 Spring IoC 컨테이너에 자동으로 빈으로 등록합니다.
public class CDPlayerConfig {
}
