# Pubby Server

这是一个基于Spring Boot的后端项目，作为个人学习和实践Java Web开发的示例项目。

## 项目概述

- 项目名称：pubby-server
- 项目类型：Spring Boot Web应用
- 主要技术栈：Spring Boot、Spring Security、MyBatis、MySQL

## 功能特性

1. 基本的Web请求处理
2. RESTful API接口
3. 用户信息管理示例
4. 静态资源访问
5. 安全框架集成（Spring Security）

## 技术栈

- **核心框架**：Spring Boot 2.7.6
- **安全框架**：Spring Security
- **持久层框架**：MyBatis
- **数据库**：MySQL
- **开发工具**：Spring Boot DevTools
- **模板引擎**：无（目前仅提供REST API和静态页面）
- **构建工具**：Maven

## 项目结构

```
src/
├── main/
│   ├── java/
│   │   └── com/example/pubbyserver/
│   │       ├── demos/web/
│   │       │   ├── BasicController.java  # 控制器示例
│   │       │   └── User.java             # 用户实体类
│   │       └── PubbyServerApplication.java # 应用启动类
│   └── resources/
│       ├── static/
│       │   └── index.html                 # 静态首页
│       └── application.properties         # 应用配置文件
└── test/                                  # 测试代码目录
```

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

### 访问测试

应用启动后，可以通过以下URL进行测试：

- 主页：http://localhost:8080/html
- Hello接口：http://localhost:8080/hello?name=yourname
- 用户信息接口：http://localhost:8080/user
- 保存用户接口：http://localhost:8080/save_user?name=newName&age=11

## 开发说明

此项目是一个基础的Spring Boot应用程序模板，可用于快速搭建Web后端服务。

## 许可证

本项目遵循Apache License 2.0开源许可证。

