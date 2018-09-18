# jdbc-timezone


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
