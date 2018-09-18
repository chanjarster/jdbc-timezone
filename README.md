# jdbc-timezone

以下文章的源代码：

* [数据库时区那些事儿 - Oracle的时区处理](https://chanjarster.github.io/post/oracle-timezone/)
* [数据库时区那些事儿 - MySQL的时区处理](https://chanjarster.github.io/post/mysql-timezone/)

这个例子代码是Spring Boot项目，打包之后按照以下方式运行：

```bash
# 插入数据
java -Duser.timezone=Asia/Shanghai -jar app.jar --test=insert

# 查询日期时间数据
java -Duser.timezone=Asia/Shanghai -jar app.jar --test=retrieve
java -Duser.timezone=Europe/Paris -jar app.jar --test=retrieve

# 调用当前日期时间的函数
java -Duser.timezone=Asia/Shanghai -jar app.jar --test=function
java -Duser.timezone=Europe/Paris -jar app.jar --test=function
```
