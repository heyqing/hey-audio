package top.heyqing.heyaudio.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ClassName:DateUtil
 * Package:top.heyqing.heyaudio.util
 * Description:
 * 日期工具类
 *
 * @Date:2024/12/25
 * @Author:Heyqing
 */
public class DateUtil {

    /**
     * 获取时间：格式2024年12月25日17:41:45
     *
     * @return
     */
    public static String getDateForYYYY_MM_DD_HH_MM_SS() {
        // 获取当前日期和时间
        Date currentDate = new Date();
        // 设置输出格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH:mm:ss");
        // 格式化当前日期
        String formattedDate = sdf.format(currentDate);
        return formattedDate;
    }

}
