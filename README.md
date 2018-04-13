# 介绍
`springboot-basic-model`是基于springboot+freemarker+mybatis+mybatis-plus+HikariCP的基础框架脚手架
# 使用方法
- 克隆到本地
```
git clone git@github.com:Licoy/springboot-basic-model.git
```
- 修改数据库信息
```
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
# SSM版本
<a href="https://github.com/Licoy/ssm-basic-model">https://github.com/Licoy/ssm-basic-model</a>
