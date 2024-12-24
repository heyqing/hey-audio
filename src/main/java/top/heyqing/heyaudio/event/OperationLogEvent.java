package top.heyqing.heyaudio.event;

import org.springframework.context.ApplicationEvent;
import top.heyqing.heyaudio.dto.OperationLog;

/**
 * ClassName:OperationLogEvent
 * Package:top.heyqing.heyaudio.event
 * Description:
 * 操作日志事件
 *
 * @Date:2024/12/24
 * @Author:Heyqing
 */
public class OperationLogEvent extends ApplicationEvent {

    public OperationLogEvent(OperationLog operationLog) {
        super(operationLog);
    }
}
