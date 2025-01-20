package config.soundsystem;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import soundsystem.CDPlayer;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations={"classpath:config/soundsystem/applicationContext.xml"}) // XML 파일에서 설정된 CDPlayer 빈을 로드
public class CDPlayerXmlConfigTest {
    @Autowired
    CDPlayer cdPlayer;

    @Test
    public void testCDPlayerNotNull() {
        assertNotNull(cdPlayer);
    }

    @Test
    public void testPlay() {
        assertEquals("Playing 붕붕 by 김하온", cdPlayer.play());
    }
}
