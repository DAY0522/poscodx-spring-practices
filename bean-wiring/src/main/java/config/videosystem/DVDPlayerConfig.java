package config.videosystem;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import videosystem.Avengers;
import videosystem.DVDPlayer;
import videosystem.DigitalVideoDisc;
import videosystem.IronMan;

// 빈(bean)을 설정하고 의존성 주입을 수행하는 예제
@Configuration
public class DVDPlayerConfig {
    @Bean // 메소드가 반환하는 객체를 Spring IoC 컨테이너의 빈으로 등록
    public DigitalVideoDisc avengers() {
        return new Avengers();
    }

    @Bean
    public DigitalVideoDisc ironMan() {
        return new IronMan(); // IronMan 객체를 빈으로 등록
    }

    // 의존성 주입(Dependency Injection) 하기1
    // Bean 생성 메소드(avengers())를 직접 호출하는 방법
    // 생성자 주입
    @Bean
    public DVDPlayer dvdPlayer1() {
        return new DVDPlayer(avengers()); // DVDPlayer 빈을 생성
    }

    // 의존성 주입(Dependency Injection) 하기2
    // Parameter로 Bean을 전달하는 방법
    // 생성자 주입
    @Bean("DVDPlayer2nd") // DVDPlayer2nd라는 이름을 가진 빈을 등록
    public DVDPlayer dvdPlayer2(Avengers dvd) {
        // Spring이 자동으로 Avengers 빈을 찾아서 DVDPlayer 생성자에 주입
        return new DVDPlayer(dvd);
    }

    // 의존성 주입(Dependency Injection) 하기3
    // Parameter로 Bean을 전달하는 방법
    // setter 주입
    @Bean
    public DVDPlayer dvdPlayer3(@Qualifier("ironMan") DigitalVideoDisc dvd) {
        // @Qualifier("ironMan") 어노테이션은 ironMan 빈을 주입하도록 지정
        DVDPlayer dvdPlayer = new DVDPlayer(dvd);
        dvdPlayer.setDvd(dvd);

        return dvdPlayer;
    }

}
