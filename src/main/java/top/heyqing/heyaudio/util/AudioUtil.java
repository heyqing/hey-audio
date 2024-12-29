package top.heyqing.heyaudio.util;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;
import org.springframework.web.multipart.MultipartFile;
import top.heyqing.heyaudio.constants.AudioFormat;
import top.heyqing.heyaudio.dto.FileProperties;
import top.heyqing.heyaudio.enums.AudioFormatEnums;
import top.heyqing.heyaudio.exception.AudioException;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Objects;

import static top.heyqing.heyaudio.constants.CommonConstant.*;

/**
 * ClassName:AudioUtil
 * Package:top.heyqing.heyaudio.util
 * Description:
 * 音频工具类
 *
 * @Date:2024/12/24
 * @Author:Heyqing
 */
@Slf4j
public class AudioUtil {

    /**
     * 转换格式
     * 1. 寻找编码器
     * 2. 配置并使用编码器
     * 打开输入音频文件并解码
     * 设置编码器参数
     * 将音频数据编码成 MP3 格式并输出
     * <p>
     * 暂时废弃
     *
     * @param targetType
     * @param inputFilename
     * @return
     */
    @SneakyThrows
    public static String audioConvert(String targetType, String inputFilename) {
        log.info("普通文件：{}开始转换文件：{}", new Date(), inputFilename);
        //寻找编码器
        AudioFormatEnums codecAndType = AudioFormatEnums.findCodecByType(targetType);
        String format = codecAndType.getFormat();
        int codec = codecAndType.getCodec();

        // 输入输出文件路径
        String inputPath = FileProperties.AUDIO_INPUT_PATH + PATH_SEPARATOR + inputFilename;
        String UUIDName = StringUtil.genUUIDName();
        String filename = StringUtil.genFileName(UUIDName, targetType);
        String outputPath = StringUtil.genFilePath(FileProperties.AUDIO_OUTPUT_PATH, filename);

        // 创建 FFmpegFrameGrabber 来读取音频文件
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputPath);
        grabber.start(); // 开始读取
        // 创建 FFmpegFrameRecorder 来写入音频文件
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputPath, grabber.getAudioChannels());
        recorder.setFormat(format); // 设置输出格式
        recorder.setAudioCodec(codec); // 设置音频编码器
        recorder.setSampleRate(grabber.getSampleRate());
        recorder.setAudioBitrate(AudioFormat.AUDIO_BITRATE); // 设置音频比特率

        recorder.start(); // 开始录制

        // 读取帧并写入
        Frame frame;
        while ((frame = grabber.grabFrame()) != null) {
            recorder.record(frame); // 录制帧
        }

        recorder.stop(); // 停止录制
        grabber.stop(); // 停止抓取

        log.info("普通文件：{}转换结束，存放路径为：{}", new Date(), outputPath);
        return outputPath;
    }

    /**
     * 转换格式-MultipartFile
     *
     * @param targetType
     * @param file
     * @return
     */
    @SneakyThrows
    public static String audioConvert(String targetType, MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        log.info("MultipartFile：{}开始转换文件：{}", DateUtil.getDateForYYYY_MM_DD_HH_MM_SS(), originalFilename);
        int index = originalFilename.lastIndexOf(FILE_SEPARATOR);
        String originalType = originalFilename.substring(index + 1);

        // 寻找编码器
        //源
        AudioFormatEnums originalCodecAndType = AudioFormatEnums.findCodecByType(originalType);
        String originalFormat = originalCodecAndType.getFormat();
        int originalCodec = originalCodecAndType.getCodec();
        //目的
        AudioFormatEnums targetCodecAndType = AudioFormatEnums.findCodecByType(targetType);
        String targetFormat = targetCodecAndType.getFormat();
        int targetCodec = targetCodecAndType.getCodec();

        // 获取文件内容
        byte[] bytes = file.getBytes();
        InputStream inputStream = new ByteArrayInputStream(bytes);

        // 创建 FFmpegFrameGrabber 来读取音频文件
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputStream);
        grabber.setFormat(originalFormat);
        grabber.setAudioCodec(originalCodec);
        grabber.start(); // 开始读取

        // 生成输出文件路径
        String UUIDName = StringUtil.genUUIDName();
        String filename = StringUtil.genFileName(UUIDName, targetType);
        String outputPath = FileProperties.AUDIO_OUTPUT_PATH + PATH_SEPARATOR + filename;

        // 创建 FFmpegFrameRecorder 来写入音频文件
        FFmpegFrameRecorder recorder = new FFmpegFrameRecorder(outputPath, grabber.getAudioChannels());
        recorder.setFormat(targetFormat); // 设置输出格式
        recorder.setAudioCodec(targetCodec); // 设置音频编码器
        recorder.setSampleRate(grabber.getSampleRate());
        recorder.setAudioBitrate(AudioFormat.AUDIO_BITRATE); // 设置音频比特率

        recorder.start(); // 开始录制

        // 读取帧并写入
        Frame frame;
        while ((frame = grabber.grabFrame()) != null) {
            recorder.record(frame); // 录制帧
        }

        recorder.stop(); // 停止录制
        grabber.stop(); // 停止抓取

        log.info("MultipartFile：{}转换结束，存放路径为：{}", DateUtil.getDateForYYYY_MM_DD_HH_MM_SS(), outputPath);
        return outputPath;
    }

    /**
     * 校验音频是否合法
     *
     * @param filename
     * @return
     */
    public static Boolean verifyAudioType(String filename) throws AudioException {
        if (filename == null || filename.length() == ZERO) {
            throw new AudioException("文件名不合法");
        }
        int lastIndex = filename.lastIndexOf(FILE_SEPARATOR);
        if (lastIndex == MINUS_ONE) {
            throw new AudioException("文件名不合法");
        }
        String inputType = filename.substring(lastIndex + 1);
        AudioFormatEnums result = AudioFormatEnums.findCodecByType(inputType);
        if (Objects.isNull(result)) {
            throw new AudioException("文件名不合法");
        }
        return true;
    }
}
