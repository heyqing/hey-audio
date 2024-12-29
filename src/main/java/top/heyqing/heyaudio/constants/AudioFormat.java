package top.heyqing.heyaudio.constants;

/**
 * ClassName:AudioFormat
 * Package:top.heyqing.heyaudio.constants
 * Description:
 * 音频格式
 *
 * @Date:2024/12/24
 * @Author:Heyqing
 */
public interface AudioFormat {

    /**
     * 音频比特率
     */
    int AUDIO_BITRATE = 128 * 1000;


    /************************************************无损音频格式*******************************************************/

    /**
     * Waveform Audio File Format：波形音频文件格式
     */
    String WAV = "wav";
    /**
     * Free Lossless Audio Codec:一种流行的开源无损音频压缩格式
     */
    String FLAC = "flac";
    /**
     * Apple Lossless Audio Codec:苹果开发的无损音频格式
     */
    String ALAC = "alac";
    /**
     * Monkey's Audio一种无损音频压缩格式
     */
    String APE = "ape";

    /************************************************有损音频格式*******************************************************/

    /**
     * MPEG-1 Audio Layer 3
     */
    String MP3 = "mp3";
    /**
     * Advanced Audio Coding:用于音频和音乐的有损压缩格式
     */
    String AAC = "aac";
    /**
     * Windows Media Audio:微软开发的有损压缩音频格式
     */
    String WMA = "wma";
    /**
     * Ogg Vorbis:一个开源的音频压缩格式
     */
    String OGG = "ogg";

    /**
     * 基于AAC编码的音频文件格式，常用于iTunes和Apple设备
     */
    String M4A = "m4a";
    /**
     * Dolby Digital:多声道音频编码格式，常用于DVD和环绕声系统
     */
    String AC3 = "ac3";
    /**
     * 多声道音频编码格式
     */
    String DTS = "dts";
    /**
     * Adaptive Multi-Rate:常用于移动通信的有损压缩格式
     */
    String AMR = "amr";
    /************************************************特殊音频格式*******************************************************/

    /**
     * QQ
     */
    String QMC = "qmc";
    /**
     * QQ
     */
    String MFLAC = "mflac";
    /**
     * QQ
     */
    String MGG = "mgg";
    /**
     * 酷狗
     */
    String KGM = "kgm";
    /**
     * 酷我
     */
    String KWM = "kwm";
    /**
     * 网易云
     */
    String NCM = "ncm";

}
