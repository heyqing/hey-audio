package top.heyqing.heyaudio.annotation;

import java.lang.annotation.*;

/**
 * ClassName:OptLog
 * Package:top.heyqing.heyaudio.annotation
 * Description:
 * 操作日志
 *
 * @Date:2024/12/24
 * @Author:Heyqing
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OptLog {

    /**
     * 操作类型
     *
     * @return
     */
    String optType() default "";

}
