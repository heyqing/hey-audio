package top.heyqing.heyaudio.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import top.heyqing.heyaudio.dto.FileProperties;
import top.heyqing.heyaudio.entity.response.R;
import top.heyqing.heyaudio.service.AudioService;
import top.heyqing.heyaudio.task.FileCleanupTask;
import top.heyqing.heyaudio.util.StringUtil;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

import static top.heyqing.heyaudio.constants.CommonConstant.RECORDS;
import static top.heyqing.heyaudio.constants.CommonConstant.THIRTY_MINUTES;
import static top.heyqing.heyaudio.util.AudioUtil.audioConvert;
import static top.heyqing.heyaudio.util.AudioUtil.verifyAudioType;

/**
 * ClassName:AudioServiceImpl
 * Package:top.heyqing.heyaudio.service.impl
 * Description:
 *
 * @Date:2024/12/24
 * @Author:Heyqing
 */
@Service
@RequiredArgsConstructor
public class AudioServiceImpl implements AudioService {

    private final FileCleanupTask fileCleanupTask;

    /**
     * 文件格式转换-单文件
     *
     * @param file
     * @param targetType
     * @return
     */
    @SneakyThrows
    @Override
    public R audioConvertOpt(MultipartFile file, String targetType) {
        String filename = file.getOriginalFilename();
        verifyAudioType(filename);
        String outputPath = audioConvert(targetType, file);
        fileCleanupTask.scheduleFileDeletion(outputPath, THIRTY_MINUTES);
        int index = outputPath.lastIndexOf('/');
        String records = outputPath.substring(index + 1);
        Map<String, Object> map = new HashMap<>();
        map.put(RECORDS, records);
        map.put("OriginalFilename", filename);
        return R.ok().data(map);
    }

    /**
     * 文件下载-单文件
     *
     * @param filename
     * @return
     */
    @Override
    public R audioDownloadOpt(String filename) {
        try {
            // 获取文件的路径
            String filePath = StringUtil.genFilePath(FileProperties.AUDIO_OUTPUT_PATH, filename);
            Path path = Paths.get(filePath);
            Resource resource = new UrlResource(path.toUri());
            if (resource.exists() || resource.isReadable()) {
                // 获取文件名
                String fileName = path.getFileName().toString();
                // 设置下载响应头
                String contentDisposition = "attachment; filename=\"" + fileName + "\"";
                return R.ok()
                        .data("filePath", filePath)   // 传递文件路径而非文件流
                        .data("contentDisposition", contentDisposition);  // 返回下载的文件名和头部信息
            } else {
                return R.error().message("该文件已经删除，请重新上传");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return R.error().message("文件下载失败，发生异常：" + e.getMessage());
        }
    }

}

