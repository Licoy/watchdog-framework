package cn.licoy.wdog.core.controller;

import cn.licoy.wdog.common.bean.RequestResult;
import cn.licoy.wdog.common.bean.StatusEnum;
import cn.licoy.wdog.core.dto.permission.PermUpdateDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

/**
 * @author Licoy
 * @version 2018/4/19/14:40
 */
@RestController
@RequestMapping(value = {"/permission"})
@Api(tags = {"权限管理"})
public class PermissionController {

    @RequestMapping(value = "/list", method = RequestMethod.POST)
    @ApiOperation(value = "分页获取所有权限列表")
    public RequestResult get(){
        return null;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "删除指定ID的权限")
    public RequestResult remove(@PathVariable("id") @ApiParam(value = "权限标识ID") Long id){
        //
        return RequestResult.e(StatusEnum.OK);
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    @ApiOperation(value = "更新指定ID的权限信息")
    public RequestResult update(@PathVariable("id") @ApiParam(value = "权限标识ID") Long id,
                                @RequestBody @ApiParam(value = "权限更新信息") PermUpdateDTO PermUpdateDTO){
        //
        return RequestResult.e(StatusEnum.OK);
    }

}
