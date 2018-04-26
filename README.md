# 介绍
`watchDog-framework`基于SpringBoot+Shiro+Mybatis+Mybatis-Plus+HikariCP+Redis开发的基础权限框架，欢迎使用。
# 使用方法
## 服务端
- 克隆到本地
```git
git clone git@github.com:watchdog-framework/watchdog-framework.git
```
- 导入SQL

    将项目根目录下的`wdog.sql`导入至数据库信息
- 修改数据库信息
```yml
# application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/wdog?useUnicode=yes&characterEncoding=UTF8
    password: root
    username: root
    driver-class-name: com.mysql.jdbc.Driver
```
- 启动
    
    运行`WatchDogApplication.java`，默认端口为1000
    
 ## 客户端

- [点击此处进入`watchdog-framework-web`项目部署说明](https://github.com/watchdog-framework/watchdog-framework-web)

# 演示

- 用户管理
  
  ![用户管理](dist/user.png)
  
- 角色管理
  
  ![角色管理](dist/role.png)
  
- 资源管理
  
  ![资源管理](dist/resource.png)
  
- 更多请预览线上版本
    
   即将部署
   
# 讨论

 - QQ群：30261540 
 
    [点我快捷加入QQ群讨论](https://shang.qq.com/wpa/qunwpa?idkey=c3541f1d0dbe443456228e3aebf23f6795b614a94d5df6a32f0b2b1c759bb99b)
    