package top.heyqing.heyaudio.constants;

/**
 * ClassName:CommonConstant
 * Package:top.heyqing.heyaudio.constants
 * Description:
 * 通用常量
 *
 * @Date:2024/12/24
 * @Author:Heyqing
 */
public interface CommonConstant {
    Integer MINUS_ONE = -1;
    Integer ZERO = 0;
    String UNKNOWN = "未知";
    String FILE_SEPARATOR = ".";
    String PATH_SEPARATOR = "/";
    String CONVERT = "音频转换";
    String DOWNLOAD = "音频下载";
    String RECORDS = "records";
    Long ONE_MINUTE = System.currentTimeMillis() + 60 * 1000L;
    Long TEN_MINUTES = System.currentTimeMillis() + 10 * 60 * 1000L;
    Long FIFTEEN_MINUTES = System.currentTimeMillis() + 15 * 60 * 1000L;
    Long THIRTY_MINUTES = System.currentTimeMillis() + 30 * 60 * 1000L;
}
