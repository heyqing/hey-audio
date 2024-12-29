package top.heyqing.heyaudio.exception;

/**
 * ClassName:AudioException
 * Package:top.heyqing.heyaudio.exception
 * Description:
 * 自定义异常
 *
 * @Date:2024/12/24
 * @Author:Heyqing
 */
public class AudioException extends RuntimeException {
    // 构造函数，无参数
    public AudioException() {
        super();
    }

    // 构造函数，带有详细消息
    public AudioException(String message) {
        super(message);
    }

    // 构造函数，带有详细消息和原因
    public AudioException(String message, Throwable cause) {
        super(message, cause);
    }

    // 构造函数，带有原因
    public AudioException(Throwable cause) {
        super(cause);
    }
}
