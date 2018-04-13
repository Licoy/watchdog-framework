package cn.licoy.sbm.common.exception;


import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

/**
 * @author licoy.cn
 * @version 2017/11/18
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequestException extends RuntimeException implements Serializable {
    private int status;
    private String msg;
    private Exception e;

    public RequestException(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public synchronized static RequestException error(String msg){
        return RequestException.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).msg(msg).build();
    }
    public synchronized static RequestException error(String msg,Exception e){
        return RequestException.builder()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value()).msg(msg).e(e).build();
    }
    public synchronized static RequestException badRequest(String msg){
        return RequestException.builder()
                .status(HttpStatus.BAD_REQUEST.value()).msg(msg).build();
    }
    public synchronized static RequestException notFound(String msg){
        return RequestException.builder()
                .status(HttpStatus.NOT_FOUND.value()).msg(msg).build();
    }
    public synchronized static RequestException unauthorized(String msg){
        return RequestException.builder()
                .status(HttpStatus.UNAUTHORIZED.value()).msg(msg).build();
    }
}