package top.heyqing.heyaudio.util;

import org.bytedeco.ffmpeg.global.avcodec;
import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.bytedeco.javacv.FFmpegFrameRecorder;
import org.bytedeco.javacv.Frame;

public class demo {

    public static void main(String[] args) {
        String inputPath = "E:\\music\\md\\张玮玮 _ 郭龙 - 米店.mp3"; // 输入文件路径
        String outputPath = "H:\\dev\\code\\github\\hey-music\\src\\main\\java\\top\\heyqing\\output\\output.wav"; // 输出文件路径

        // 创建 FFmpegFrameGrabber 来读取音频文件
        FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(inputPath);
        FFmpegFrameRecorder recorder = null;
        try {
            grabber.start(); // 开始读取

            // 创建 FFmpegFrameRecorder 来写入音频文件
            recorder = new FFmpegFrameRecorder(outputPath, grabber.getAudioChannels());
            recorder.setAudioBitrate(128000); // 设置音频比特率
            recorder.setSampleRate(grabber.getSampleRate());
            recorder.setFormat("wav"); // 设置输出格式为 WAV
            recorder.setAudioCodec(avcodec.AV_CODEC_ID_PCM_S16LE); // 设置音频编码器为 PCM_S16LE（无损音频）
            recorder.setAudioQuality(0); // 设置为无损质量
            recorder.start(); // 开始录制

            // 读取帧并写入
            Frame frame;
            while ((frame = grabber.grabFrame()) != null) {
                if (frame.samples != null) {
                    try {
                        // 录制音频帧
                        recorder.record(frame);
                    } catch (Exception e) {
                        System.err.println("Error recording frame at timestamp: " + frame.timestamp);
                        e.printStackTrace();
                    }
                } else {
                    // 如果当前帧没有样本数据，输出丢帧信息
                    System.out.println("Skipped frame at timestamp: " + frame.timestamp);
                }
            }

            recorder.stop(); // 停止录制
            grabber.stop(); // 停止抓取
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (grabber != null) {
                    grabber.release(); // 释放资源
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                if (recorder != null) {
                    recorder.release(); // 释放资源
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
