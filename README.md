# 介绍
`springboot-basic-model`是基于springboot+freemarker+shiro+mybatis+mybatis-plus+HikariCP的基础权限脚手架框架整合，其包括数据库的CRUD、身份认证以及访问角色/权限的控制；欢迎使用。
# 使用方法
- 克隆到本地
```git
git clone git@github.com:Licoy/springboot-basic-model.git
```
- 修改数据库信息
```yml
# application.yml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/sbm?useUnicode=yes&characterEncoding=UTF8
    password: root
    username: root
    driver-class-name: com.mysql.jdbc.Driver
```
- 导入数据库
    
    将sbm.sql导入到数据库
- 启动

    访问地址<a href="http://localhost:8080/account/sign-in">http://localhost:8080/account/sign-in</a>
    
    默认用户：user ，管理员用户：admin
    
    密码均为：123456
# 推荐
此项目配合`mybatisPlus-codeGenerate`食用效果更佳，地址：<a href="https://github.com/Licoy/javas/tree/master/code-generate">https://github.com/Licoy/javas/tree/master/code-generate</a>
# SSM版本
<a href="https://github.com/Licoy/ssm-basic-model">https://github.com/Licoy/ssm-basic-model</a>
