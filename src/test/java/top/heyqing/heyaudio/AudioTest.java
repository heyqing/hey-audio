package top.heyqing.heyaudio;

import org.testng.annotations.Test;
import top.heyqing.heyaudio.constants.AudioFormat;
import top.heyqing.heyaudio.util.AudioUtil;

/**
 * ClassName:AudioTest
 * Package:top.heyqing.heyaudio
 * Description:
 * 音频测试类
 *
 * @Date:2024/12/25
 * @Author:Heyqing
 */
public class AudioTest {

    /**
     * 音频格式转换测试
     */
    @Test
    public void AudioConvertTest() {
        String output = AudioUtil.audioConvert(AudioFormat.WAV, "md.mp3");
        System.out.println(output);
    }

}
