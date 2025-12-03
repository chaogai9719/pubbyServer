# Pubby Server

这是一个基于Spring Boot的后端项目，作为个人学习和实践Java Web开发的示例项目。

## 项目概述

- 项目名称：pubby-server
- 项目类型：Spring Boot Web应用
- 主要技术栈：Spring Boot、Spring Security、MyBatis、MySQL

## 功能特性

1. 基本的Web请求处理
2. RESTful API接口
3. 用户认证与授权（JWT）
4. 操作日志记录
5. 数据自动填充（创建人、创建时间等）
6. 文件上传下载功能
7. 异常统一处理

## 技术栈

- **核心框架**：Spring Boot 2.7.6
- **安全框架**：Spring Security + JWT
- **持久层框架**：MyBatis
- **数据库**：MySQL
- **开发工具**：Spring Boot DevTools、Lombok
- **构建工具**：Maven
- **其他**：AOP（操作日志切面）、MyBatis Interceptor（自动填充）

## 项目结构

```
src/
├── main/
│   ├── java/
│   │   └── com/example/pubbyserver/
│   │       ├── annotation/           # 自定义注解
│   │       ├── aspect/               # AOP切面
│   │       ├── config/               # 配置类
│   │       ├── controller/           # 控制器
│   │       ├── dao/                  # 数据访问对象
│   │       ├── entity/               # 实体类
│   │       ├── exception/            # 异常处理
│   │       ├── filter/               # 过滤器
│   │       ├── interceptor/          # 拦截器
│   │       ├── service/              # 业务逻辑层
│   │       │   └── impl/             # 业务逻辑实现
│   │       ├── util/                 # 工具类
│   │       └── PubbyServerApplication.java # 应用启动类
│   └── resources/
│       ├── mappers/                  # MyBatis映射文件
│       ├── static/                   # 静态资源
│       └── application.properties    # 应用配置文件
└── test/                             # 测试代码目录
```

## 核心功能模块

### 用户管理
- 用户注册、登录、登出
- JWT Token认证机制
- 用户信息增删改查

### 操作日志
- 使用AOP记录用户操作
- 记录操作模块、类型、执行时间等信息

### 数据实体管理
1. **小狗记录** ([Dog](src/main/java/com/example/pubbyserver/entity/Dog.java))
2. **美食评价** ([FoodReview](src/main/java/com/example/pubbyserver/entity/FoodReview.java))
3. **吵架记录** ([QuarrelRecord](src/main/java/com/example/pubbyserver/entity/QuarrelRecord.java))
4. **用户信息** ([User](src/main/java/com/example/pubbyserver/entity/User.java))

每个实体均支持完整的CRUD操作，并具有文件上传下载功能。

### 安全控制
- 基于JWT的无状态认证
- 登录接口免认证，其他接口需认证访问
- 密码加密存储（BCrypt）

### 自动填充
- 通过MyBatis拦截器自动填充创建人、创建时间、更新人、更新时间字段

## 快速开始

### 环境要求

- JDK 1.8 或更高版本
- Maven 3.2+
- MySQL 5.7+

### 构建和运行

1. 克隆项目到本地
2. 在 `application.properties` 中配置数据库连接
3. 使用Maven构建项目：

```bash
mvn clean install
```

4. 运行应用：

```bash
mvn spring-boot:run
```

或者打包后运行：

```bash
java -jar target/pubby-server-0.0.1-SNAPSHOT.jar
```

### Docker部署

项目提供了Dockerfile，可以直接构建Docker镜像：

```bash
docker build -t pubby-server .
docker run -p 8080:80 pubby-server
```

注意：需要确保容器能访问到配置的MySQL数据库。

### 访问测试

应用启动后，可以通过以下URL进行测试：

- 主页：http://localhost:8080/
- 登录接口：POST http://localhost:8080/api/auth/login
- 用户管理接口：http://localhost:8080/api/users
- 小狗记录接口：http://localhost:8080/api/dogs
- 美食评价接口：http://localhost:8080/api/food-reviews
- 吵架记录接口：http://localhost:8080/api/quarrels
- 操作日志接口：http://localhost:8080/api/logs

## 配置说明

主要配置项在 `application.properties` 文件中：

- `server.port`: 应用端口，默认80
- `spring.datasource.*`: 数据库连接配置
- `food.picture.path`: 美食图片存储路径
- `dog.picture.path`: 狗狗图片存储路径
- `spring.servlet.multipart.*`: 文件上传大小限制

## 开发说明

此项目是一个基础的Spring Boot应用程序模板，可用于快速搭建Web后端服务。集成了常见的企业级开发组件和模式。

## 许可证

本项目遵循Apache License 2.0开源许可证。