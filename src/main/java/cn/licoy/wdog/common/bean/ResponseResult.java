package cn.licoy.wdog.common.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author licoy.cn
 * @version 2017/11/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseResult<T> implements Serializable {

    private Integer status;

    private T data;

    private String msg;

    private final long timestamps = System.currentTimeMillis();

    public synchronized static ResponseResult e(ResponseCode statusEnum) {
        return ResponseResult.builder().status(statusEnum.code)
                .msg(statusEnum.msg).data(null).build();
    }

    public synchronized static ResponseResult e(ResponseCode statusEnum, Object data) {
        return ResponseResult.builder().status(statusEnum.code)
                .msg(statusEnum.msg).data(data).build();
    }
}
