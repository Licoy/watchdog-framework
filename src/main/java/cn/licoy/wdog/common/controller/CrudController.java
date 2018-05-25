package cn.licoy.wdog.common.controller;

import cn.licoy.wdog.common.annotation.SysLogs;
import cn.licoy.wdog.common.bean.ResponseCode;
import cn.licoy.wdog.common.bean.ResponseResult;
import cn.licoy.wdog.common.service.BaseService;
import com.baomidou.mybatisplus.plugins.Page;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author Licoy
 * @version 2018/5/25/11:36
 * @param <E> 控制器对象实体
 * @param <AD> 添加DTO
 * @param <UD> 更新DTO
 * @param <UID> 对象ID
 * @param <FD> 查找DTO
 * @param <S> 服务对象
 */
public interface CrudController<E,AD,UD,UID,FD,S extends BaseService<E,AD,UD,UID,FD>> extends
        CreateController<AD,S>,
        DeleteController<UID,S>,
        QueryController<E,FD,S>,
        UpdateController<UID,UD,S>

{











}
