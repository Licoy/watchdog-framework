package cn.licoy.sbm.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Licoy
 * @version 2018/4/13/13:58
 */
@Data
public class User implements Serializable {

    @TableId
    private Integer id;

    private String name;

    private Integer age;

}
