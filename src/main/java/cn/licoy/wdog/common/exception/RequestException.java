package cn.licoy.wdog.common.exception;


import cn.licoy.wdog.common.bean.StatusEnum;
import lombok.*;

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
    private Integer status;
    private String msg;
    private Exception e;

    public RequestException(Integer status,String msg) {
        this.status = status;
        this.msg = msg;
    }


    public RequestException(StatusEnum statusEnum) {
        this.status = statusEnum.code;
        this.msg = statusEnum.msg;
    }




}