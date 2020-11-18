# 学习笔记

## 作业1

> 写代码实现 Spring Bean 的装配，方式越多越好（XML、Annotation 都可以）, 提交到 Github。

解答：

主要使用三种方式实现：

* xml 文件配置：详见 SpringBeans 项目 `io.guokaide.xml` 包
* Java Config 方式：详见 SpringBeans 项目 `io.guokaide.javaconfig` 包
* ComponentScan 方式：详见 SpringBeans 项目 `io.guokaide.componentscan` 包

## 作业2

> 给前面课程提供的 Student/Klass/School 实现自动配置和 Starter。

解答：

实现自动配置 和 Starter，详见 custom-spring-boot-starter 项目

测试项目：springboot-practice 项目

## 作业3

> 研究一下 JDBC 接口和数据库连接池，掌握它们的设计和用法：
> 1）使用 JDBC 原生接口，实现数据库的增删改查操作。
> 2）使用事务，PrepareStatement 方式，批处理方式，改进上述操作。
> 3）配置 Hikari 连接池，改进上述操作。提交代码到 Github。

解答：

* 1）详见 springboot-practice 项目：`io.guokaide.springbootpractice.jdbc.BasicJDBCOperations.java`
* 2）详见 springboot-practice 项目：`io.guokaide.springbootpractice.jdbc.TransactionOperations.java`
* 3）详见 springboot-practice 项目：`io.guokaide.springbootpractice.hikaricp.HikaricpService.java`