### 微服务环境

| 组件   | 版本   | 备注       |
|:----|:-----|:---------|
| nacos   | 2.2.0  |  |

### 微服务
| service   | 说明   | 备注       |
|:----|:-----|:---------|
| gateway-security   | 网关集成security  |  |
| gateway-token   | 网关集成satoken  | 实验中 |
| system   | 系统服务  |  |

##apifox邀请连接
```html
https://www.apifox.cn/web/invite?token=5KZ7pPCqVYrtx_2yhEEfB
```
### 主要特性
- 集成spring boot 常用开发组件集、公共配置、AOP日志等
- Maven多模块架构
- 集成mybatis plus快速dao操作
- 集成Swagger/Knife4j，可自动生成api文档
- 集成jwt、shiro权限控制
- 集成Redis缓存
- 集成druid连接池，JDBC性能和慢查询检测
- 集成spring boot admin，实时检测项目运行情况

## 项目结构
```text
    ├── spring-boot-demo
    ├── bootstrap           spring-boot-plus 启动模块、配置模块
    ├── framework           框架核心模块
    └── system              系统模块
```

### 项目环境

| 中间件   | 版本   | 备注       |
|:----|:-----|:---------|
| JDK   | 11   | JDK11及以上 |
| MySQL | 8    |          |
| Redis | 3.2+ |          |

### 技术选型

| 技术                   | 版本            | 备注               |
|:---------------------|:--------------|:-----------------|
| Spring Boot          | 2.7.9.RELEASE | 最新发布稳定版          |
| Mybatis              | 3.5.9         | 持久层框架            |
| Mybatis Plus         | 3.5.2         | mybatis增强框架      |
| Durid                | 1.2.8         | 数据连接池管理          |
| Fastjson             | 1.2.67        | JSON处理工具集        |
| Swagger2             | 2.9.2         | api文档生成工具        |
| Knife4j              | 2.0.2         | api文档生成工具        |
| commons-lang3        | 3.9           | 常用工具包            |
| commons-io           | 2.6           | IO工具包            |
| commons-codec        | 1.14          | 加密解密等工具包         |
| commons-collections4 | 4.4           | 集合工具包            |
| reflections          | 0.9.9         | 反射工具包            |
| hibernate-validator  | 6.0.18.Final  | 后台参数校验注解         |
| Shiro                | 1.5.1         | 权限控制             |
| jjwt                 | 0.9.1         | JSON WEB TOKEN   |
| hutool-all           | 5.2.4         | 常用工具集            |
| lombok               | 1.18.12       | 注解生成Java Bean等工具 |
| mapstruct            | 1.3.1.Final   | 对象属性复制工具         |


#### 代码结构

```text
└── src
    └── main
        ├── java
        │   └── com
        │       └── com.com.example
        │           └── foobar
        │               ├── controller
        │               │   └── FooBarController.java
        │               ├── model
        │               │   └── FooBar.java
        │               ├── mapper
        │               │   └── FooBarMapper.java
        │               ├── param
        │               │   └── FooBarPageParam.java
        │               ├── service
        │               │   ├── FooBarService.java
        │               │   └── impl
        │               │       └── FooBarServiceImpl.java
        │               ├── vo
        │               │   └── FooBarVo.java
        │               └── qo
        │                   └── FooBarQo.java
        └── resources
            └── mapper
                └── foobar
                    └── FooBarMapper.xml
```
