# PMS Admin 项目管理系统

## 项目简介

PMS Admin 是一个基于 Spring Boot 3.2.0 的项目管理系统后端，采用现代化的技术栈构建，提供完整的用户权限管理功能。

## 技术栈

- **框架**: Spring Boot 3.2.0
- **数据库**: PostgreSQL
- **ORM**: MyBatis-Plus 3.5.6 + Spring Data JPA
- **缓存**: Redis
- **安全**: Spring Security + JWT
- **文档**: OpenAPI 3.0 (Swagger)
- **构建工具**: Maven
- **Java版本**: 17

## 项目结构

```
src/main/java/com/yurongku/
├── config/          # 配置类
├── controller/      # 控制器
├── dto/            # 数据传输对象
├── entity/         # 实体类
├── exception/      # 异常处理
├── mapper/         # MyBatis映射器
├── repository/     # JPA仓库
├── security/       # 安全相关
├── service/        # 业务服务
└── utils/          # 工具类
```

## 环境要求

- JDK 17+
- Maven 3.6+
- PostgreSQL 12+
- Redis 6+

## 快速开始

### 1. 数据库配置

1. 创建 PostgreSQL 数据库
2. 执行初始化脚本：`src/main/resources/sql/init.sql`

### 2. 环境配置

项目支持多环境配置：
- `application-dev.yml` - 开发环境
- `application-prod.yml` - 生产环境

默认使用开发环境配置。

### 3. 启动项目

```bash
# 编译项目
mvn clean compile

# 启动项目
mvn spring-boot:run
```

### 4. 访问接口

- 应用地址: http://localhost:8080/api
- Swagger文档: http://localhost:8080/api/swagger-ui
- API文档: http://localhost:8080/api/api-docs

## 默认账户

- 用户名: `admin`
- 密码: `123456`

## 主要功能

### 用户管理
- 用户注册、登录、注销
- 用户信息管理
- 密码重置

### 角色权限管理
- 角色管理
- 权限管理
- 用户角色分配
- 角色权限分配

### 部门管理
- 部门树形结构管理
- 部门信息维护
- 用户部门分配

### 菜单管理
- 菜单树形结构管理
- 菜单权限配置
- 角色菜单分配

### 系统管理
- 系统配置
- 日志管理
- 数据字典

## 开发规范

### 代码规范
- 遵循阿里巴巴Java开发手册
- 使用Lombok简化代码
- 统一异常处理
- 统一API响应格式

### 数据库规范
- 表名使用下划线命名法
- 字段名使用下划线命名法
- 主键统一使用 `id`
- 审计字段：`created_time`, `updated_time`, `created_by`, `updated_by`, `is_deleted`

### API规范
- RESTful API设计
- 统一响应格式
- 参数验证
- 错误码规范

## 部署说明

### 开发环境
```bash
mvn spring-boot:run -Dspring.profiles.active=dev
```

### 生产环境
```bash
mvn clean package -Dspring.profiles.active=prod
java -jar target/pms-admin-0.0.1-SNAPSHOT.jar
```

## 环境变量配置

生产环境可通过环境变量配置：

```bash
export DB_HOST=localhost
export DB_PORT=5432
export DB_NAME=pms-admin
export DB_USERNAME=root
export DB_PASSWORD=123
export REDIS_HOST=localhost
export REDIS_PORT=6379
export JWT_SECRET=your-secret-key
```

## 许可证

MIT License
