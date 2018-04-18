package cn.licoy.sbm.common.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author licoy.cn
 * @version 2017/11/17
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestResult implements Serializable {

    private Integer status;

    private Object data;

    private String msg;

    private final long timestamps = System.currentTimeMillis();

    public synchronized static RequestResult e(StatusEnum statusEnum) {
        return RequestResult.builder().status(statusEnum.code)
                .msg(statusEnum.msg).data(null).build();
    }

    public synchronized static RequestResult e(StatusEnum statusEnum, Object data) {
        return RequestResult.builder().status(statusEnum.code)
                .msg(statusEnum.msg).data(data).build();
    }
}
