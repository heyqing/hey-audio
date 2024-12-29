package top.heyqing.heyaudio.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import top.heyqing.heyaudio.util.DateUtil;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ClassName:FileCleanupTask
 * Package:top.heyqing.heyaudio.task
 * Description:
 * 文件定时删除任务
 *
 * @Date:2024/12/25
 * @Author:Heyqing
 */
@Slf4j
@Component
public class FileCleanupTask {

    /**
     * 存储待删除的文件路径及其删除时间
     */
    private final Map<String, Long> filesToDelete = new ConcurrentHashMap<>();


    /**
     * 添加一个文件到待删除列表
     *
     * @param filePath
     * @param deleteTime
     */
    public void scheduleFileDeletion(String filePath, long deleteTime) {
        filesToDelete.put(filePath, deleteTime);
    }

    // 定时任务，每分钟检查一次文件是否需要删除
    @Scheduled(fixedDelay = 10 * 60 * 1000)
    public void checkAndDeleteFiles() {
        log.info("**************************{}-checkAndDeleteFiles-start**************************", DateUtil.getDateForYYYY_MM_DD_HH_MM_SS());
        if (filesToDelete.isEmpty()) {
            log.info("No files to delete.");
        }
        long currentTime = System.currentTimeMillis();
        // 遍历所有待删除的文件
        Iterator<Map.Entry<String, Long>> iterator = filesToDelete.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Long> entry = iterator.next();
            String filePath = entry.getKey();
            long deleteTime = entry.getValue();

            // 如果当前时间超过删除时间，删除文件并从列表中移除
            if (currentTime >= deleteTime) {
                File file = new File(filePath);
                if (file.exists() && file.delete()) {
                    log.info("{} File deleted successfully: {}", DateUtil.getDateForYYYY_MM_DD_HH_MM_SS(), filePath);
                } else {
                    log.error("{} Failed to delete the file: {}", DateUtil.getDateForYYYY_MM_DD_HH_MM_SS(), filePath);
                }
                iterator.remove();
            }
        }
        log.info("**************************{}-checkAndDeleteFiles-end**************************\n", DateUtil.getDateForYYYY_MM_DD_HH_MM_SS());
    }
}
