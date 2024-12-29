package top.heyqing.heyaudio.service;

import org.springframework.web.multipart.MultipartFile;
import top.heyqing.heyaudio.entity.response.R;

/**
 * ClassName:AudioService
 * Package:top.heyqing.heyaudio.service
 * Description:
 *
 * @Date:2024/12/24
 * @Author:Heyqing
 */
public interface AudioService {

    /**
     * 文件格式转换-单文件
     *
     * @param file
     * @param targetType
     * @return
     */
    R audioConvertOpt(MultipartFile file, String targetType);

    /**
     * 文件下载-单文件
     *
     * @param filename
     * @return
     */
    R audioDownloadOpt(String filename);

}
