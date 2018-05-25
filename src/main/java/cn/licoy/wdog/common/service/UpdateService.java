package cn.licoy.wdog.common.service;

/**
 * @author Licoy
 * @version 2018/5/25/13:14
 * @see BaseService 注释配置请参见BaseService
 */
public interface UpdateService<UID,UD> {

    void update(UID id,UD updateDTO);

}
