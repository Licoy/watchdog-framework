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

    public synchronized static RequestResult ok(){
        return RequestResult.builder().status(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase()).build();
    }
    public synchronized static RequestResult okHasDataAndMsg(Object o,String msg){
        return RequestResult.builder().status(HttpStatus.OK.value())
                .data(o).msg(msg).build();
    }
    public synchronized static RequestResult okHasData(Object o){
        return RequestResult.builder().status(HttpStatus.OK.value())
                .msg(HttpStatus.OK.getReasonPhrase()).data(o).build();
    }
    public synchronized static RequestResult error(String msg){
        return RequestResult.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).msg(msg).build();
    }
    public synchronized static RequestResult badRequest(String msg){
        return RequestResult.builder()
                .status(HttpStatus.BAD_REQUEST.value()).msg(msg).build();
    }
}
