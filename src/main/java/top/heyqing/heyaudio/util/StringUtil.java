package top.heyqing.heyaudio.util;

import lombok.SneakyThrows;
import top.heyqing.heyaudio.constants.CommonConstant;

import java.util.UUID;

/**
 * ClassName:StringUtil
 * Package:top.heyqing.heyaudio.util
 * Description:
 * 字符串工具
 *
 * @Date:2024/12/25
 * @Author:Heyqing
 */
public class StringUtil {

    /**
     * 生成UUID文件名
     *
     * @return
     */
    public static String genUUIDName() {
        return UUID.randomUUID().toString();
    }


    /**
     * 生成文件名
     *
     * @param prefix
     * @param type
     * @return
     */
    @SneakyThrows
    public static String genFileName(String prefix, String type) {
        String filename = prefix + CommonConstant.FILE_SEPARATOR + type;
        AudioUtil.verifyAudioType(filename);
        return filename;
    }

    /**
     * 生成文件路径
     *
     * @param basePath
     * @param filename
     * @return
     */
    public static String genFilePath(String basePath, String filename) {
        return basePath + CommonConstant.PATH_SEPARATOR + filename;
    }


}
