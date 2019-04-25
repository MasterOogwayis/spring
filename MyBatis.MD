# MyBatis 学习笔记
## Lesson 1
### 1. resultType（属性）和resultMap（标签引用）的区别？
+ **resultType**  
    支持将结果集映射到普通java对象，也可以是基本类型
+ **resultMap**  
    如果查询结果集和java对象属性名不同，或者需要将数据第二次处理再转为 java对象就需要使用resultMap


### 2. collection 和 association 的区别？
+ **collection**  
    描述对象的一对多关系
+ **association**  
    描述对象的一对一关系

### 3. Statement 和 PreparedStatement 的区别？
+ **Statement**  
    将完整的 sql 发送到数据库，有被 sql 注入的风险
+ **PreparedStatement**  
    预编译的 sql, 以占位符替换参数，可以多次执行，效率高，防 sql 注入


***
## Lesson 2
***
## Lesson 3
***
## Lesson 4