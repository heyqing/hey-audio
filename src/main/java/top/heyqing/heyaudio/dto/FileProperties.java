package top.heyqing.heyaudio.dto;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * ClassName:FileProperties
 * Package:top.heyqing.heyaudio.dto
 * Description:
 * 文件上传下载路径
 *
 * @Date:2024/12/25
 * @Author:Heyqing
 */
@Component
public class FileProperties implements InitializingBean {

    @Value("${file.audio.path.input}")
    private String audioInputPath;

    @Value("${file.audio.path.output}")
    private String audioOutputPath;

    /**
     * 音频上传地址
     */
    public static String AUDIO_INPUT_PATH;

    /**
     * 音频输出地址
     */
    public static String AUDIO_OUTPUT_PATH;

    @Override
    public void afterPropertiesSet() throws Exception {
        AUDIO_INPUT_PATH = audioInputPath;
        AUDIO_OUTPUT_PATH = audioOutputPath;
    }
}
