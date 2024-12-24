package top.heyqing.heyaudio;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import top.heyqing.heyaudio.config.LogoConfig;

/**
 * ClassName:MusicApplication
 * Package:top.heyqing.heymusic
 * Description:
 *
 * @Date:2024/12/24
 * @Author:Heyqing
 */
@SpringBootApplication
public class MusicApplication {
    public static void main(String[] args) {
        SpringApplication.run(MusicApplication.class,args);
        LogoConfig.v();
    }
}
