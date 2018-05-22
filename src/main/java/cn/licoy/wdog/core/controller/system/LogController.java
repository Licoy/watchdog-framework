package cn.licoy.wdog.core.controller.system;

import cn.licoy.wdog.common.bean.ResponseResult;
import cn.licoy.wdog.common.bean.ResponseCode;
import cn.licoy.wdog.core.dto.system.log.FindLogDTO;
import cn.licoy.wdog.core.service.system.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/28/10:22
 */
@RestController
@RequestMapping(value = "/system/log")
@Api(tags = {"日志管理"})
public class LogController {

    @Autowired
    private SysLogService sysLogService;

    @PostMapping("/list")
    @ApiParam("获取日志列表")
    public ResponseResult list(@RequestBody @ApiParam("日志查找过滤数据") FindLogDTO findLogDTO){
        return ResponseResult.e(ResponseCode.OK,sysLogService.list(findLogDTO));
    }

    @PostMapping("/remove")
    @ApiParam("批量删除")
    public ResponseResult removeList(@RequestBody @ApiParam("ID集合") List<String> logList){
        sysLogService.remove(logList);
        return ResponseResult.e(ResponseCode.OK);
    }

}
