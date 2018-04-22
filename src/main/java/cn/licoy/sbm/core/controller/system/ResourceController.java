package cn.licoy.sbm.core.controller.system;

import cn.licoy.sbm.common.bean.RequestResult;
import cn.licoy.sbm.common.bean.StatusEnum;
import cn.licoy.sbm.core.dto.system.resource.ResourceDTO;
import cn.licoy.sbm.core.service.system.ResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author licoy.cn
 * @version 2018/4/22
 */
@RestController
@RequestMapping(value = "/system/resource")
@Api(tags = {"资源管理"})
public class ResourceController {

    @Resource
    private ResourceService resourceService;

    @RequestMapping(value = {"/list"})
    @ApiOperation(value = "获取所有的资源列表")
    public RequestResult list(){
        return RequestResult.e(StatusEnum.OK,resourceService.list());
    }

    @RequestMapping(value = {"/add"})
    @ApiOperation(value = "添加资源")
    public RequestResult add(@RequestBody @ApiParam("资源数据") ResourceDTO dto){
        resourceService.add(dto);
        return RequestResult.e(StatusEnum.OK);
    }

    @RequestMapping(value = {"/update/{id}"})
    @ApiOperation(value = "添加资源")
    public RequestResult update(@PathVariable("id") @ApiParam("资源ID") String id,
                       @RequestBody @ApiParam("资源数据") ResourceDTO dto){
        resourceService.update(id,dto);
        return RequestResult.e(StatusEnum.OK);
    }

    @RequestMapping(value = {"/remove/{id}"})
    @ApiOperation(value = "删除资源")
    public RequestResult remove(@PathVariable("id") @ApiParam("资源ID") String id){
        resourceService.remove(id);
        return RequestResult.e(StatusEnum.OK);
    }


}
