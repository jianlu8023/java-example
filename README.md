# Java Example Spring Boot Starters

这是一个包含多个 Spring Boot Starter 的项目，可以方便地集成到你的 Spring Boot 应用中。

## 模块介绍

### format-spring-boot-starter

提供统一响应、异常处理、请求日志等功能：

- 统一响应体封装
- 全局异常拦截
- 请求日志记录

### mock-spring-boot-starter

提供数据模拟生成能力：

- 生成中文手机号、地址、身份证、邮箱
- POJO 对象生成

## 如何使用

在你的 `pom.xml` 中添加相应的依赖：

```xml
<!-- format starter -->
<dependency>
    <groupId>com.github.jianlu8023</groupId>
    <artifactId>format-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>

<!-- mock starter -->
<dependency>
    <groupId>com.github.jianlu8023</groupId>
    <artifactId>mock-spring-boot-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

注意：需要在你的 `pom.xml` 中添加 JitPack 仓库：

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>
```

## 发布到 JitPack

这个项目已经配置好可以直接发布到 JitPack，只需将代码推送到 GitHub，然后在 JitPack 上触发构建即可。

访问 https://jitpack.io/#jianlu8023/java-example 来获取最新的依赖。

### 构建状态

确保在发布前项目能够正常构建：

```bash
# 使用 Maven Wrapper 进行构建
./mvnw clean install

# 或者使用系统安装的 Maven
mvn clean install
```

## 故障排除

如果在 JitPack 构建过程中遇到问题，请检查以下几点：

1. 确保所有 pom.xml 文件中的版本号一致
2. 确保所有依赖都有明确的版本号
3. 确保父 POM 引用正确
4. 确保项目结构正确，子模块能正确引用父模块
