package top.heyqing.heyaudio.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.heyqing.heyaudio.annotation.OptLog;
import top.heyqing.heyaudio.entity.response.R;
import top.heyqing.heyaudio.service.AudioService;

import static top.heyqing.heyaudio.constants.CommonConstant.CONVERT;
import static top.heyqing.heyaudio.constants.CommonConstant.DOWNLOAD;

/**
 * ClassName:AudioController
 * Package:top.heyqing.heyaudio.controller
 * Description:
 * 音频模块
 *
 * @Date:2024/12/24
 * @Author:Heyqing
 */
@Api(tags = "音频模块")
@RestController
@RequestMapping("audio")
@RequiredArgsConstructor
@CrossOrigin
public class AudioController {

    private final AudioService audioService;

    @OptLog(optType = CONVERT)
    @ApiOperation(value = "文件格式转换-单文件", notes = "该接口实现了文件格式转换-单文件的功能")
    @PostMapping("/convert/single")
    public R audioConvertOpt(@Validated @RequestBody MultipartFile file, String targetType) {
        return audioService.audioConvertOpt(file, targetType);
    }


    @OptLog(optType = DOWNLOAD)
    @ApiOperation(value = "文件下载-单文件", notes = "该接口实现了文件下载-单文件的功能")
    @GetMapping("/download/single")
    public R audioDownloadOpt(@Validated @RequestParam String filename) {
        return audioService.audioDownloadOpt(filename);
    }


}
