package top.heyqing.heyaudio.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bytedeco.ffmpeg.global.avcodec;
import top.heyqing.heyaudio.constants.AudioFormat;
import top.heyqing.heyaudio.exception.AudioException;

/**
 * ClassName:AudioFormatEnums
 * Package:top.heyqing.heyaudio.enums
 * Description:
 * 枚举音频格式及其对应的编码器
 *
 * @Date:2024/12/24
 * @Author:Heyqing
 */
@Getter
@AllArgsConstructor
public enum AudioFormatEnums {
    /************************************************无损音频格式*******************************************************/

    /**
     * Waveform Audio File Format：波形音频文件格式
     */
    WAV("wav", avcodec.AV_CODEC_ID_PCM_S16LE),

    /**
     * Free Lossless Audio Codec:一种流行的开源无损音频压缩格式
     */
    FLAC("flac", avcodec.AV_CODEC_ID_FLAC),

    /**
     * Apple Lossless Audio Codec:苹果开发的无损音频格式
     */
    ALAC("alac", avcodec.AV_CODEC_ID_ALAC),

    /**
     * Monkey's Audio一种无损音频压缩格式
     */
    APE("ape", avcodec.AV_CODEC_ID_APE),

    /************************************************有损音频格式*******************************************************/

    /**
     * MPEG-1 Audio Layer 3
     */
    MP3("mp3", avcodec.AV_CODEC_ID_MP3),

    /**
     * Advanced Audio Coding:用于音频和音乐的有损压缩格式
     */
    AAC("aac", avcodec.AV_CODEC_ID_AAC),

    /**
     * Windows Media Audio:微软开发的有损压缩音频格式
     */
    WMA("wma", avcodec.AV_CODEC_ID_WMAV1),

    /**
     * Ogg Vorbis:一个开源的音频压缩格式
     */
    OGG("ogg", avcodec.AV_CODEC_ID_VORBIS),

    /**
     * 基于AAC编码的音频文件格式，常用于iTunes和Apple设备
     */
    M4A("m4a", avcodec.AV_CODEC_ID_MP4ALS),

    /**
     * Dolby Digital:多声道音频编码格式，常用于DVD和环绕声系统
     */
    AC3("ac3", avcodec.AV_CODEC_ID_AC3),

    /**
     * 多声道音频编码格式
     */
    DTS("dts", avcodec.AV_CODEC_ID_DTS),

    /**
     * Adaptive Multi-Rate:常用于移动通信的有损压缩格式
     */
    AMR("amr", avcodec.AV_CODEC_ID_AMR_NB),

    /************************************************特殊音频格式*******************************************************/

    /**
     * QQ Music
     */
    QMC("qmc", -1),

    /**
     * QQ Music
     */
    MFLAC("mflac", -1),

    /**
     * QQ Music
     */
    MGG("mgg", -1),

    /**
     * 酷狗音乐
     */
    KGM("kgm", -1),

    /**
     * 酷我音乐
     */
    KWM("kwm", -1),

    /**
     * 网易
     */
    NCM("ncm", -1);

    private final String format;
    private final int codec;


    /**
     * 仅限于有限类型
     *
     * @param type
     * @return
     */
    public static AudioFormatEnums findCodecByType(String type) throws AudioException {
        switch (type) {
            case AudioFormat.WAV:
                return WAV;
            case AudioFormat.FLAC:
                return FLAC;
            case AudioFormat.ALAC:
                return ALAC;
            case AudioFormat.APE:
                return APE;
            case AudioFormat.MP3:
                return MP3;
            case AudioFormat.AAC:
                return AAC;
            case AudioFormat.WMA:
                return WMA;
            case AudioFormat.OGG:
                return OGG;
            case AudioFormat.M4A:
                return M4A;
            case AudioFormat.AC3:
                return AC3;
            case AudioFormat.DTS:
                return DTS;
            case AudioFormat.AMR:
                return AMR;
            case AudioFormat.QMC:
                return QMC;
            case AudioFormat.MFLAC:
                return MFLAC;
            case AudioFormat.MGG:
                return MGG;
            case AudioFormat.KGM:
                return KGM;
            case AudioFormat.KWM:
                return KWM;
            case AudioFormat.NCM:
                return NCM;
            default:
                throw new AudioException("暂时不支持" + type + "音频格式");
        }
    }
}
