package cn.licoy.wdog.core.controller.system;

import cn.licoy.wdog.common.annotation.SysLogs;
import cn.licoy.wdog.common.bean.RequestResult;
import cn.licoy.wdog.common.bean.StatusEnum;
import cn.licoy.wdog.core.dto.system.log.FindLogDTO;
import cn.licoy.wdog.core.service.system.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Licoy
 * @version 2018/4/28/10:22
 */
@RestController
@RequestMapping(value = "/system/log")
@Api(tags = {"日志管理"})
public class LogController {

    @Resource
    private SysLogService sysLogService;

    @PostMapping("/list")
    @ApiParam("获取日志列表")
    public RequestResult list(@RequestBody @ApiParam("日志查找过滤数据") FindLogDTO findLogDTO){
        return RequestResult.e(StatusEnum.OK,sysLogService.list(findLogDTO));
    }

    @PostMapping("/remove")
    @ApiParam("批量删除")
    public RequestResult removeList(@RequestBody @ApiParam("ID集合") List<String> logList){
        sysLogService.remove(logList);
        return RequestResult.e(StatusEnum.OK);
    }

}
