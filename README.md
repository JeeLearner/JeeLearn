# JeeLearn

#### 简介
 * JeeLearn是

#### 项目选型
* 开发工具eclipse
* 版本管理Maven
* 基础框架 

#### 整体结构
Maven依赖关系：https://github.com/JeeLearner/JeeLearn/tree/master/doc/JeeLearn-Maven依赖关系.jpg

#### 技术分析
##### 后台
* 基础框架
    *  IOC容器 Spring 4.3.8.RELEASE
    *  WEB框架 SpringMVC
    *  ORM框架 Hibernate 4.3.0.Final + JPA 1.4.1.RELEASE
    *  模板视图 jsp
    *  servlet 3.0.1
* 权限控制
    *  Shiro 1.2.2
* 数据服务
    *  数据库 MYSQL 5.1.25
    *  数据源 druid 1.1.4
* 验证框架
    *  Hibernate validator 5.0.2.Final
* 缓存服务
    *  ehcache 2.6.6
* 日志框架
    *  slf4j 1.7.5
    *  logback 1.0.13
* 验证码
    *  Jcaptcha 2.0-alpha-1
* 其它
    *  日期美化 prettytime 3.2.3.Final
 
##### 前端
* 界面框架 &nbsp;&nbsp;&nbsp;&nbsp;  jquery-ui-bootstrap
* 日期时间选择器 &nbsp;&nbsp;&nbsp;&nbsp; bootstrap-datetimepicker
* 日历插件 &nbsp;&nbsp;&nbsp;&nbsp; fullcalendar
* 表单验证插件 &nbsp;&nbsp;&nbsp;&nbsp; jquery-validation-engine
* 编辑器 &nbsp;&nbsp;&nbsp;&nbsp; kindeditor
* 滚动条美化插件 &nbsp;&nbsp;&nbsp;&nbsp; nicescroll
* 树插件 &nbsp;&nbsp;&nbsp;&nbsp; zTree
* 弹出框/遮罩框架 &nbsp;&nbsp;&nbsp;&nbsp; jquery blockUI
* 字体/图标框架 &nbsp;&nbsp;&nbsp;&nbsp; font-wesome
* 文件上传 &nbsp;&nbsp;&nbsp;&nbsp; jquery-fileupload

#### 核心功能
##### 基础建设
* 系统登录

##### 已完成
* 系统登录
* 系统监控
* 缓存实现
* 通知功能

##### 进行中
* 系统管理
    *  资源管理
    
##### 未完成
* 系统监控-拦截器：限制ip
* 组织机构-移动节点、查看子节点时新增子节点



#### 项目分析
优点：

缺点：
* 授权是在sys_auth表进行的，这样处理不太好，后续最好改为OAuth2请求授权。
* 缓存的清空时机可优化

