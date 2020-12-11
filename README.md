# spring_study

> Spring是一个轻量级的控制反转（IOC）和面向切面（AOP）的框架

## Spring简介

- 2002年推出了Spring框架的雏形，名为 *interface 21*
- 2004年3月24日，以interface 21框架为基础，重新设计，发布了1.0版本
- spring理念：使得现有的技术更加容易使用，整合了很多现有的技术框架

### SSH 

`Struct2` + `Spring` + `Hibernate` 

### SSM 

`SpringMVC` + `Spring` + `Mybatis`



官网：[https://spring.io/projects/spring-framework](https://spring.io/projects/spring-framework ) 

官方下载地址：[https://repo.spring.io/simple/libs-release-local/org/springframework/spring/](https://repo.spring.io/simple/libs-release-local/org/springframework/spring/ ) 

GitHub网站：[https://github.com/spring-projects/spring-framework](https://github.com/spring-projects/spring-framework)

## 项目导包

使用maven导入spring-webMVC：

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-webmvc</artifactId>
    <version>5.3.1</version>
</dependency>
```

使用maven导入spring-jdbc：

```xml
<!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-jdbc</artifactId>
    <version>5.3.1</version>
</dependency>
```



## Spring优点

- Spring是一个开源的免费框架（容器）
- Spring是一个轻量级的、非入侵式的框架
- 控制反转（**IOC**），面向切面编程（**AOP**）
- 支持事务的处理，对框架整合的支持



## Spring组成

spring core：提供了spring 的核心功能，BeanFactory是spring核心容器的主要组件，它通过Ioc把程序的配置和依赖性与实际的代码分开，是整个spring的基础 spring context：通过配置文件向spring提供上下文信息，它构建在BeanFactory之上，另外增加了国际化和资源访问等功能 spring dao：提供了一个简单有效的JDBC应用 spring aop：提供了面向方面编程的功能 spring orm：spring除了有自己的JDBC以外还提供了对其他ORM框架的支持，如Hibernate，都可以和spring进行良好的结合 spring web：提供了简化的处理多部分请求以及把请求参数绑定到域的任务 spring MVC：提供了MVC2模式的实现，也可以和struts良好的集成在一起

## 了解

- `Spring Boot`：构建一切
- `Spring Cloud`：协调一切
- `Spring Cloud Data Flow`：连接一切
- `Spring Boot`
  - 一个快速开发的脚手架
  - 基于Spring Boot开源快速开发单个微服务
  - 约定大于配置
- `Spring Cloud`
  - Spring Cloud是基于Spring Boot实现的

Spring弊端：发展太久，违背了原来的理念，配置十分繁琐，人称“配置地狱”

## IOC理论推导

### 原始的实现类

- `UserDao`接口
- `UserDaoImpl`实现类
- `UserService`业务接口
- `UserServiceImpl`业务实现类

在之前的业务中，用户的需求会影响原来的代码，我们需要根据需求去修改原代码，假设程序代码量极大，那么修改量极大

### 控制反转

我们在`UserServiceImpl`中使用set方法，使得传入的Dao由最后的实现再进行定义，我们只需要提供并且实现接口，是否调用接口由用户决定

```java
package com.learn.service;

import com.learn.dao.UserDao;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    //利用set方法动态实现注入
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void getUser() {
        userDao.getUser();
    }
}
```



- 之前，程序是主动注入对象，控制权在程序员手中
- 使用了set注入之后，程序不再具有主动性，而是被动接受对象
- 这种思想，从本质上解决问题，程序架构无需改变，程序员无需管理对象创建，系统的耦合性大大降低，程序员可以更加专注于业务的实现，这是IOC的原型

### IOC本质

**控制反转IOC**（Inversion of Control）是一种设计思想，**DI（依赖注入）**是实现IOC的一种方法。没有IOC的程序中，我们使用面向对象编程，对象的创建与对象间的依赖完全硬编码在程序中，对象的创建由程序字节控制，控制反转后将对象的创建转移给第三方；而使用了IOC，**获得依赖对象的方式反转了**

采用XML的方式配置bean的时候，bean的定义信息是和实现分离的，而采用注解的方式可以把两者合为一体，bean的定义信息直接以注解的形式定义在实现类中，从而达到了零配置的目的

控制反转是一种通过描述（XML或者注解）并通过第三方去生产或获取特定对象的方式。在Spring中，实现控制反转的容器是IOC容器，其实现方法是依赖注入（Dependency Injection, DI）



## Spring项目使用过程

### hello spring

- 在`pom.xml`中导入spring-webmvc包

- 创建实体类`Hello.java`

  - ```java
    package com.learn.pojo;
    
    public class Hello {
        private String str;
    
        public String getStr() {
            return str;
        }
    
        public void setStr(String str) {
            this.str = str;
        }
    
        @Override
        public String toString() {
            return "Hello{" +
                    "str='" + str + '\'' +
                    '}';
        }
    }
    
    ```

- 配置`beans.xml`文件，注入bean

  - ```xml
    <?xml version="1.0" encoding="UTF-8"?>
    <beans xmlns="http://www.springframework.org/schema/beans"
           xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
           xsi:schemaLocation="http://www.springframework.org/schema/beans
            https://www.springframework.org/schema/beans/spring-beans.xsd">
        <!--使用Spring创建对象，这些都称为bean-->
        <!--
            在xml中，每个bean相当于一个对象
            以下语句相当于
            Hello hello = new Hello()
            @id 自定义的spring取出的名称（方便spring的getBean(id)调用），相当于变量名hello
            @class 实体类路径
        -->
        <bean id="hello" class="com.learn.pojo.Hello">
            <!--
                property 相当于给对象的属性设置一个值
                @name 实体类中的属性
                @value 给对象赋值
            -->
            <property name="str" value="Hello Spring"/>
        </bean>
    </beans>
    ```

- 在测试类中，使用`ClassPathXmlApplicationContext`引入属性

  - ```java
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    ```

  - 使用`getBean()`将对象取出



## IOC创建对象的方式

### 使用无参构造方法创建对象（默认）

#### 创建实体类对象的三种方式

```xml
<!--spring对bean的管理细节-->
<!--
        1.创建bean的三种方式
        2.bean的作用范围
        3.bean对象的生命周期
    -->
<!--
        方式一：使用默认构造函数创建
        在spring的配置文件中使用bean标签，配以id和class，而且没有其它属性时和标签时
        采用的时默认的构造函数创建bean对象，如果类中没有默认构造函数，则无法被创建
    -->
<bean id="accountService" class="com.learn.service.impl.AccountServiceImpl"/>

<!--
        方式二：使用工厂类中的方法创建对象（使用某个类中的方法创建对象，并存入spring容器）
        1.先指定工厂类名称，找到其全类名
        2.factory-bean 对应创建对象的工厂类id
        3.factory-method 对应创建对象的方法
    -->
<bean id="instanceFactory" class="com.learn.factory.InstanceFactory"/>
<bean id="accountService" factory-bean="instanceFactory" factory-method="getAccountService"/>

<!--
        方式三：使用静态方法创建对象
        factory-method 由于是静态方法，因此可以直接调用该方法创建对象
    -->
<bean id="accountService" class="com.learn.factory.StaticFactory" factory-method="getAccountService"/>
```



#### bean的作用范围（scope）

```xml
<!--bean的作用范围
        bean的 scope 属性定义作用范围
            singleton 单例模式【饿汉模式】（默认值），对象调用只会被创建一次
            prototype   多例【懒汉模式】，在对象需要被使用时才会被创建
            request     作用于web应用的请求范围
            session     作用于web应用的会话范围
            global-session      作用于集群环境的会话范围（全局会话）
    -->
<bean id="accountService" class="com.learn.service.impl.AccountServiceImpl" scope="prototype"/>
```



#### bean对象的生命周期

> 单例对象

- 当容器创建时，对象出生
- 容器销毁，对象销毁
- 单例对象生命周期与容器相同



> 多例对象

- 当使用对象时，容器创建对象
- 对象只要在使用过程就一直存在
- 容器销毁之后对象并不会跟着销毁
- 当对象长时间不被使用时，由Java的垃圾回收机制回收



### 使用有参构造方法创建对象

#### 下标赋值

```xml
<!--使用下标的方式注入有参构造方法-->
<bean id="user2" class="com.learn.pojo.User">    
    <!--        
        @index 参数下标        
        @value 传入的值    
    -->    
    <constructor-arg index="0" value="Jony"/>
</bean>
```

#### 使用属性类型创建

使用对象属性类型时，一旦对象属性有多个相同类型的值，那么将会注入失败，因此不建议使用

#### 直接通过参数名创建

```xml
<bean id="user2" class="com.learn.pojo.User">
    <!--
            直接使用参数名构造
            @name 对象属性名（参数名）
            @value 给对象赋的值
        -->
    <constructor-arg name="name" value="Tim"/>
</bean>
```

> 在配置文件加载的时候，容器中管理的对象就已经初始化了



## Spring配置

### 别名（不常用）

在bean的配置文件中，使用`<alias>`标签可以修改别名

> 实例

```xml
<bean id="user" class="com.learn.pojo.User">
    <property name="name" value="Tony"/>
</bean>
<!--
	@name 对应bean的id
	@alias 需要设置的别名
-->
<alias name="user" alias="userBean"/>
```

配置完别名后，我们在`getBean()`当中可以直接使用别名引入

```java
User user = (User) context.getBean("userBean");
```



### bean的配置

```xml
<!--
	@id bean 的唯一标识符，类似于对象名
	@class bean 对象所对应的全限定名（包名+类名）
	@name 也可以在bean标签中的name属性中创建别名 而且name可以同时取多个别名，它可以通过 ,/;分割
-->
<bean id="user" class="com.learn.pojo.User" name="userBean, userName">
    <property name="name" value="Tony"/>
</bean>
```



### import

一般用于团队开发，可以将多个xml配置文件导入合并为一个配置文件

我们在使用getBean()方法获取xml文件时，只读取一份xml文档，因此，我们可以在一份总的xml配置文件中导入多个配置文件

> 实例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <import resource="beans.xml"/>
    <import resource="beans2.xml"/>
    <import resource="beans3.xml"/>
</beans>
```



## 依赖注入（DI）



### 构造器注入

```xml
<!--构造器注入
        constructor-arg 使用标签注入类的属性
            type 用于指定要注入的数据类型，与构造函数中的属性相对应(但是当出现多个同类型的属性时，无法实现注入)
            index 用于指定要注入的数据为指定构造函数的索引位置
            name 用于指定构造函数指定名称的属性赋值

            value 引用字符串
            ref 引用关联的bean对象（在容器中配置过的对象）
    -->
    <bean id="accountService" class="com.learn.Service.impl.AccountServiceImpl">
        <constructor-arg name="name" value="泰斯特"/>
        <constructor-arg name="age" value="18"/>
        <constructor-arg name="birthday" ref="now"/>
    </bean>

    <!--配置日期对象-->
    <bean id="now" class="java.util.Date"/>
```

> 缺点：必须提供相应参数的构造方法 



### set方式注入（重点）

> 依赖注入的本质为set注入

- 依赖：bean对象的创建依赖于容器
- 注入：bean对象中的所有属性，都由容器来注入



#### 环境搭建

- 复杂类型
- 真实测试对象

> 实例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">
    <!--注入一个类，方便我们下面的引用-->
    <bean id="address" class="com.learn.pojo.Address">
        <property name="address" value="南宁"/>
    </bean>

    <bean id="student" class="com.learn.pojo.Student">
        <!--普通值注入，可以直接使用value注入-->
        <property name="name" value="Jayce"/>
        <!--bean注入，使用ref进行注入-->
        <property name="address" ref="address"/>
        <!--数组注入-->
        <property name="books">
            <array>
                <value>红楼梦</value>
                <value>水浒传</value>
                <value>西游记</value>
                <value>三国演义</value>
            </array>
        </property>
        <!--List集合注入-->
        <property name="hobbys">
            <list>
                <value>听歌</value>
                <value>看电影</value>
            </list>
        </property>
        <!--Map集合注入，使用键值对注入-->
        <property name="card">
            <map>
                <entry key="身份证" value="452216200010220022"/>
                <entry key="银行卡" value="6666888844447777"/>
            </map>
        </property>
        <!--Set集合注入-->
        <property name="games">
            <set>
                <value>DOTA2</value>
                <value>LOL</value>
                <value>CS</value>
            </set>
        </property>
        <!--null/空值注入-->
        <property name="wife">
            <!--null值注入-->
            <null/>
            <!--空值注入，如果想注入空值，那么property的value设置为空即可-->
        </property>
        <!--特殊类型注入，也是一种键值对注入，但是和map的键值对不同-->
        <property name="info">
            <props>
                <prop key="driver">1122666</prop>
                <prop key="url">男</prop>
                <prop key="username">root</prop>
                <prop key="password">123456</prop>
            </props>
        </property>
    </bean>
</beans>
```



### 拓展方式注入

> p命名和c命名空间不能直接使用，需要引入依赖



#### p命名空间注入（对应set注入）

在bean文件中导入p命名空间的依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans ...
       xmlns:p="http://www.springframework.org/schema/p"
       ...>
    <!--p命名空间依赖-->
    <!--p命名空间注入可以直接注入属性的值-->
    <bean id="user" class="com.learn.pojo.User" p:age="18"/>
</beans>
```



#### c命名空间注入（对应构造器注入）

在bean文件中导入c命名空间的依赖

```xml
<beans ...
       xmlns:c="http://www.springframework.org/schema/c"
       ...>
    <!--c命名空间注入，依赖有参构造器-->
    <bean id="user" class="com.learn.pojo.User" c:name="Jony" c:age="19"/>
</beans>
```



### bean的作用域

| 作用域      | 说明                                                         |
| ----------- | ------------------------------------------------------------ |
| singleton   | （**默认**）为每个Spring IoC容器将单个bean定义的作用域限定为单个对象实例 |
| prototype   | 将单个Bean定义范围限制为任意数量的对象实例                   |
| request     | 将单个bean定义的范围限定为单个HTTP请求的生命周期。也就是说，每个HTTP请求都有一个自己的bean实例，它是在单个bean定义的后面创建的。仅在web的Spring `ApplicationContext`上下文中有效 |
| session     | 将单个bean定义的作用域限定为HTTP会话的生命周期。仅在web的Spring `ApplicationContext`上下文中有效 |
| application | 将单个bean定义的作用域限定为`ServletContext`的生命周期。仅在web的Spring `ApplicationContext`上下文中有效 |
| websocket   | 将单个bean定义的作用域限定为`WebSocket`的生命周期。仅在web的Spring `ApplicationContext`上下文中有效。 |



#### 给bean标签添加作用域

作用域约束由*bean*标签中的`scope`属性赋值

```xml
<bean id="user" class="com.learn.pojo.User" c:age="18" c:name="Tony" scope="singleton"/>
```



#### 单例模式（Spring默认机制）

> 每次从容器中`getBean()`只会产生一个对象

一般单线程使用，因为并发的时候会产生数据延迟或者数据不一致的情况（对应懒汉模式）

```xml
<bean id="user" class="com.learn.pojo.User" c:age="18" c:name="Tony" scope="singleton"/>
```

此时创建出来的对象，user1，user2当中，`user1 == user2`为`true`



#### 原型模式

> 每次从容器`getBean()`都会自动产生一个新的对象

一般多线程会使用，会比较浪费资源（对应饿汉模式）

```xml
<bean id="user" class="com.learn.pojo.User" c:age="18" c:name="Tony" scope="prototype"/>
```

此时创建出来的对象，user1，user2当中，`user1 == user2`为`false`



### bean的自动装配（Autowired）

- 自动装配是Spring满足bean依赖的一种方式
- Spring会在上下文中自动寻找，并自动给bean装配属性
- Spring有三种装配方式
  - 在xml中显示的配置
  - 在java中显示配置
  - 隐式的自动装配bean【重要】



#### ByName自动装配

可以通过bean标签的`autowire`属性装配，会自动在容器上下文中查找和自己对象set方法后面的值所对应的id

byName可以实现多个不同属性的精准装配

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--ByName自动装配-->
    <!--
        autowire属性 可以自动装配我们已经声明好的bean
        @byName 会自动在容器上下文中查找和自己对象set方法后面的值所对应的id
    -->
    <bean id="cat" class="com.learn.pojo.Cat"/>
    <bean id="dog" class="com.learn.pojo.Dog"/>
    <bean id="person" class="com.learn.pojo.Person" autowire="byName">
        <property name="name" value="Tony"/>
    </bean>

</beans>
```



#### ByType自动装配

可以通过bean标签的`autowire`属性装配，会自动在容器上下文中查找和自己对象属性类型相同的bean

byType不可以实现多个不同属性的精准装配

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--ByName自动装配-->
    <!--
        autowire属性 可以自动装配我们已经声明好的bean
        @byType 会自动在容器上下文中查找和自己对象属性类型相同的bean(class)
    -->
    <bean id="mycat" class="com.learn.pojo.Cat"/>
    <bean id="mydog" class="com.learn.pojo.Dog"/>
    <bean id="person" class="com.learn.pojo.Person" autowire="byType">
        <property name="name" value="Tony"/>
    </bean>

</beans>
```



> 总结

- `byName`的时候，需要保证所有bean的id唯一，并且这个bean需要和自动注入的属性的set方法的值保持一致
- `byType`的时候，需要保证所有bean的class唯一，并且这个bean需要和自动注入的类型保持一致



### 使用注解自动装配

> 注解的支持

- `jdk1.5`开始支持
- `spring2.5`开始支持

> 使用注解开发

- 导入注解的约束

  - ```xml
    <beans ...
        xmlns:context="http://www.springframework.org/schema/context"
        xsi:schemaLocation="...
            http://www.springframework.org/schema/context
            https://www.springframework.org/schema/context/spring-context.xsd">
    </beans>
    ```

- 在bean标签中配置注解的支持

  - `<context:annotation-config/>`



#### @Autowired

- 可以直接在属性上使用，也可以在set方法上使用
- 使用Autowired注解可以省略set方法，前提是这个自动装配的属性在容器中存在，且符合byName

> 实例

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">
    <!--开启注解支持-->
    <context:annotation-config/>
    <!--
        开启注解支持后，我们只需要注册每个对象即可
        对象中的引用可以通过 @Autowired 实现
    -->
    <bean id="cat" class="com.learn.pojo.Cat"/>
    <bean id="dog" class="com.learn.pojo.Dog"/>
    <bean id="person" class="com.learn.pojo.Person"/>

</beans>
```



person类中声明属性(注解可以替代set方法)：

```java
//如果定义了required = false，那么说明该属性可以为null
@Autowired(required = false)
private Cat cat;
@Autowired
private Dog dog;
private String name;
```



##### @Nullable

使用@Nullable装配字段，说明该字段可为空

```java
/**
* 使用@Nullable注解说明该字段可以为null
* @name 
*/
public User(@Nullable String name) {
    
}
```





##### @Qualifier(value = "id名")

如果xml配置文件中有多个id指向同一对象，装配环境十分复杂的情况下，使用该注解可以配合@Autowired指定某个id为装配的值

Autowired注解也可以和@Qualifier(value = "id名")一起使用

```java
@Autowired
@Qualifier(value = "myDog")
private Dog dog;
```



##### Java原生的@Resource注解

> 类似于@Autowired，但是会比@Autowired高级

当我们xml配置文件中，对象属性的id/class为唯一时，使用该注解可以快速匹配对应属性

```java
//可以使用name属性去强制对应id
@Resource(name = "mydog")
 private Dog dog;
```



> 小结

`@Resource`和@Autowired`的区别`

- 都是用来自动装配，都可以放在属性字段上
- @Autowired通过`byType`的方式实现，而且必须要求这个对象存在
- @Resource默认通过`byName`的方式实现，如果找不到名字，则通过byType实现，如果两个都无法找到，则会出现报错
- 执行顺序不同
  - @Autowired通过byType方式实现
  - @Resource默认通过byName的方式实现



## 使用注解开发

- 在Spring4之后，使用注解开发必须保证aop的包导入项目中
- 使用注解要导入context约束，增加注解的支持

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/context
        https://www.springframework.org/schema/context/spring-context.xsd">

    <!--指定需要扫描的包，该包下的注解会自动生效-->
    <context:component-scan base-package="com.learn.pojo"/>
    <context:annotation-config/>

</beans>
```



### Java中的注解

#### @Scope

> 配置作用域

```java
@Component
@Scope("singleton")
public class User {
    //这里的注解给name做了一个赋值相当于<property name="name" value="Tom"/>
    @Value("Tom")
    public String name;
}
```



#### @Component

该注解置于类上，说明该类被Spring管理了，也就是bean

`@Component`有几个衍生注解，我们在web开发中会按照mvc三层架构分层

- dao【`@Repository`】
- service【`@Service`】
- controller【`@Controller`】

值得注意的是，使用多个包的注解，扫描注解要放到更大级别的包中

```xml
...
<context:component-scan base-package="com.learn"/>
...
```

以上几个注解的效用与@Component功能一致，都是代表将某个类注册到Spring中，装配bean



##### @Value

该注解置于属性上，给类的属性进行赋值，也可以置于set方法之上

```java
//这里的注解等价于<bean id="user" class="com.learn.pojo.User">
@Component
public class User {
    //这里的注解给name做了一个赋值相当于<property name="name" value="Tom"/>
    @Value("Tom")
    public String name;
}
```



### xml与注解

- xml 更加万能，适用于任何场合，维护更加简单方便
- 注解 一旦脱离类后便无法使用，维护相对复杂
- xml与注解的最佳实践
  - xml用来管理bean
  - 注解只负责完成属性的注入



### 完全使用Java的方式配置Spring

> 不再配置bean，全权交给Java完成

`JavaConfig`是Spring的一个子项目，在Spring4之后变成了核心功能

> 实体类

```java
package com.learn.pojo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class User {
    private String name;

    public String getName() {
        return name;
    }

    @Value("Tom")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
```



> 配置文件

```java
package com.learn.config;

import com.learn.pojo.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

//声明了@Configuration后，也会被Spring容器托管，它的效果与bean.xml是一样的
// 注册到容器中，因为本身也是@Component
//@ComponentScan 扫描包
//@Import 引入其他的配置文件，类似于xml中的import标签引入
@Configuration
@ComponentScan("com.learn.pojo")
@Import(Config2.class)
public class MyConfig {
    /**
     * 注册一个bean类似于之前的bean
     * 方法名getUser等价于原本bean文件中的id
     * 方法返回值类似于bean中的class属性
     * @return 返回要注入到bean中的对象
     */
    @Bean
    public User getUser() {
        return new User();
    }
}
```



> 测试类

```java
import com.learn.config.MyConfig;
import com.learn.pojo.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MyTest {
    @Test
    public void testConfig() {
        //如果使用了配置类方式，我们只能通过AnnotationConfig上下文获取容器，通过配置类的class对象加载
        ApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        User user = context.getBean("getUser", User.class);
        System.out.println(user);
    }
}
```

这种纯Java的方式，在SpringBoot中随处可见



## AOP

> 代理模式是SpringAOP的底层

代理模式的分类：

- 静态代理
- 动态代理



### 静态代理

角色分析

- **抽象角色**：一般使用接口或者抽象类解决
- **真实角色**（一般承担被代理的事件）：被代理角色
- **代理角色**：代理真实角色，代理真实角色后，我们一般会做一些附属操作
- **客户**：访问代理对象的人

> 无代理模式下的事务处理模式

真实事件：

```java
package com.learn.demo01;

//真实事件，租房
public interface Rent {
    //真实角色承担的出租房屋这个方法
    public void rent();
}
```

真实角色：

```java
package com.learn.demo01;

//房东类，一个真实角色，需要实现租房的方法
public class Landlord implements Rent {
    public void rent() {
        System.out.println("房东要出租房屋");
    }
}
```

客户：

```java
package com.learn.demo01;

//租户类需求出租房屋
public class Tenant {
    public static void main(String[] args) {
        //直接实现，不需要代理
        Landlord landlord = new Landlord();
        landlord.rent();
    }
}
```



> 代理模式下，需要添加一个代理类

```java
package com.learn.demo01;

//代理角色
//由于代理也需要租房子，因此需要实现租房接口
public class Proxy implements Rent {
    private Landlord landlord;

    public Proxy() {
    }

    public Proxy(Landlord landlord) {
        this.landlord = landlord;
    }

    //选择房东进行租房
    public void rent() {
        seeHouse();
        landlord.rent();
    }
    
    //代理可以实现自己独有的方法
    public void seeHouse() {
        System.out.println("中介带你看房");
    }
}
```

在完成代理类之后，客户类实现业务可以直接由代理完成

```java
package com.learn.demo01;

//租户类需求出租房屋
public class Tenant {
    public static void main(String[] args) {
        //代理模式下实现租房
        Landlord landlord = new Landlord();
        Proxy proxy = new Proxy(landlord);
        //由代理完成事务
        proxy.rent();
    }
}
```



> 代码实现步骤

1. 接口
2. 真实角色
3. 代理角色
4. 客户



> 代理模式的好处

- 可以使得真实角色的操作更加纯粹，无需关注公共业务
- 公共业务由代理角色完成，实现了业务分工
- 公共业务发生扩展的时候，方便集中管理

> 代理模式的缺点

- 一个真实角色会产生一个代理角色：代码量会翻倍，开发效率会降低（动态代理会解决）



### 动态代理

- 动态代理和静态代理角色一样
- 动态代理的代理时动态实现的，不是我们写好的
- 动态代理两大分类：基于接口的动态代理
  - 基于接口--JDK动态代理
  - 基于类--`cglib`
  - Java字节码实现--`javasist`



需要了解两个类：`Proxy`：代理，`InvocationHandler`：调用处理程序

> 使用反射机制完成动态代理

```java
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

//自动生成代理类
public class ProxyInvocationHandler implements InvocationHandler {
    //被代理的接口
    private Object target;

    //生成代理类
    public void setTarget(Object target) {
        this.target = target;
    }

    /**
     * 生成得到代理类
     * @return 返回一个被生成的代理类
     */
    public Object getProxy() {
        /*
            生成新的代理实例
            @参数1 获取反射对象，装载器来定义代理类
            @参数2 获取一个反射的接口，为代理类实现的接口列表
            @参数3 返回对象本身，调用处理程序调度方法调用
         */
        return Proxy.newProxyInstance(this.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
    }

    /**
     * 处理代理实例并返回结果
     * @param proxy 被新创建的代理
     * @param method 需要调用的方法名
     * @param args 成员方法的参数（如果有）
     * @return 返回方法执行结果
     * @throws Throwable 可能会抛出无法寻找方法的异常，或者参数异常
     */
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //动态代理的本质是由反射机制实现的
        Object result = method.invoke(target, args);
        return result;
    }
}
```



> 动态代理的好处

- 可以使真实角色的操作更加纯粹，不去处理公共业务
- 公共业务交由代理实现
- 公共业务发生扩展的时候，方便集中管理
- 一个动态代理类代理的是一个接口，一般对应一类业务
- 一动态代理类可以实现多个类，只要是实现了同一接口即可



### AOP实现

> 作用

提供声明事务；允许用户自定义切面

- 横切关注点：跨越应用程序多个模块的方法或功能。即是，与我们业务无关的，但是我们需要关注的部分，就是横切关注点，例如日志、安全、缓存、事务等
- 切面（ASPECT）：横切关注点，被模块化的特殊对象，即，它是一个类
- 通知（Advice）：切面必须要完成的工作，即，它是一个类中的方法
- 目标（Target）：被通知对象
- 代理（Proxy）：向目标对象应用通知之后创建的对象
- 切入点（PointCut）：切面通知执行的“地点”定义
- 连接点（JoinPoint）：与切入点匹配的执行点



### 使用spring的方式实现AOP

> 导入相应的包

```xml
<!--导入相应的包-->
<dependencies>
    <dependency>
        <groupId>org.aspectj</groupId>
        <artifactId>aspectjweaver</artifactId>
        <version>1.9.4</version>
    </dependency>
</dependencies>
```



#### 方式一【SpringAPI接口实现】

实现`MethodBeforeAdvice`接口完成代理

> 接口类

```java
package com.learn.service;

public interface UserService {
    public void add();
    public void delete();
    public void update();
    public void query();
}
```

> 实现类

```java
package com.learn.service;

public class UserServiceImpl implements UserService {

    public void add() {
        System.out.println("增加了一个用户");
    }

    public void delete() {
        System.out.println("删除了一个用户");
    }

    public void update() {
        System.out.println("修改了一个用户");
    }

    public void query() {
        System.out.println("查询了一个用户");
    }
}
```

> 不带返回值的去调用方法实现代理

```java
package com.learn.log;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class Log implements MethodBeforeAdvice {
    /**
     * 通过实现MethodBeforeAdvice接口的before方法去创建动态代理
     * @param method 要执行的目标对象方法
     * @param objects 方法参数
     * @param o 目标对象
     * @throws Throwable
     */
    public void before(Method method, Object[] objects, Object o) throws Throwable {
        System.out.println(o.getClass().getName() + "的" + method.getName() + "被执行了");
    }
}
```

> 带返回值的去实现代理

```java
package com.learn.log;

import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class AfterLog implements AfterReturningAdvice {
    /**
     * 结束之后会运行的日志类
     * @param o 返回值
     * @param method 调用的方法
     * @param objects 方法参数
     * @param o1 目标对象
     * @throws Throwable
     */
    public void afterReturning(Object o, Method method, Object[] objects, Object o1) throws Throwable {
        System.out.println("执行了" + method.getName() + "返回结果为：" + o);
    }
}
```

> xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--注册bean-->
    <bean id="userService" class="com.learn.service.UserServiceImpl"/>
    <bean id="log" class="com.learn.log.Log"/>
    <bean id="afterLog" class="com.learn.log.AfterLog"/>


    <!--方式一：使用原生的spring API接口-->
    <!--配置aop 需要导入aop的约束-->
    <aop:config>
        <!--切入点 expression:表达式execution(要执行的位置)-->
        <aop:pointcut id="pointcut" expression="execution(* com.learn.service.UserServiceImpl.*(..))"/>
        <!--
        执行环绕增强
        @advice-ref 需要切入的类 与上面注册的id相同
        @pointcut-ref 切入点 与上面注册的切入点相呼应
        -->
        <aop:advisor advice-ref="log" pointcut-ref="pointcut"/>
        <aop:advisor advice-ref="afterLog" pointcut-ref="pointcut"/>
    </aop:config>
</beans>
```

> 测试类

```java
import com.learn.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void springAPI() {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        //动态代理代理的是接口
        UserService userService = context.getBean("userService", UserService.class);

        userService.add();
    }
}
```



#### 方式二【切面定义】

使用自定义类实现动态代理

> 自定义代理类

```java
package com.learn.diy;

public class DiyPointCut {
    public void before() {
        System.out.println("======方法执行前========");
    }

    public void after() {
        System.out.println("======方法执行后=======");
    }
}
```



> 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--注册bean-->
    <bean id="userService" class="com.learn.service.UserServiceImpl"/>


    <!--方式二：使用自定义的类实现代理-->
    <bean id="diy" class="com.learn.diy.DiyPointCut" />
    <aop:config>
        <!--自定义的切面，ref要引用类-->
        <aop:aspect ref="diy">
            <!--切入点-->
            <!--
                * 表示要返回的类型，*表示所有类型
                com.learn.service.UserServiceImpl.* 代表所有方法
                (..)代表方法内的所有参数
            -->
            <aop:pointcut id="point" expression="execution(* com.learn.service.UserServiceImpl.*(..))"/>
            <!--定义代理方法执行的顺序-->
            <aop:before method="before" pointcut-ref="point"/>
            <aop:after method="after" pointcut-ref="point"/>
        </aop:aspect>
    </aop:config>
</beans>
```



> 测试类

```java
import com.learn.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void diy() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.delete();
    }
}
```



#### 方式三【注解实现】

> 注解的实现类

```java
package com.learn.diy;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;

//使用注解实现AOP
@Aspect //标记该类是一个切面
public class AnnotationPointCut {
    @Before("execution(* com.learn.service.UserServiceImpl.*(..))")
    public void before() {
        System.out.println("方法执行前");
    }

    @After("execution(* com.learn.service.UserServiceImpl.*(..))")
    public void after() {
        System.out.println("方法执行后");
    }

    /**
     * 环绕增强
     * 在环绕增强中，我们可以给定一个参数，代表我们要获取处理切入的点
     * 环绕方法执行会比before早，结束会比after晚
     */
    @Around("execution(* com.learn.service.UserServiceImpl.*(..))")
    public void around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("环绕前");
        Signature signature = point.getSignature(); //获得签名
        System.out.println(signature);  //打印出全类名下的方法名
        Object obj = point.proceed();
        System.out.println("环绕后");
    }
}
```

> 配置文件

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        https://www.springframework.org/schema/beans/spring-beans.xsd
        http://www.springframework.org/schema/aop
        https://www.springframework.org/schema/aop/spring-aop.xsd">
    <!--注册bean-->
    <bean id="userService" class="com.learn.service.UserServiceImpl"/>


    <!--方式三：使用注解实现AOP-->
    <bean id="diy" class="com.learn.diy.DiyPointCut" />
    <bean id="annotationPointCut" class="com.learn.diy.AnnotationPointCut"/>
    <!--开启注解支持-->
    <!--自动注册代理-->
    <aop:aspectj-autoproxy/>
</beans>
```

> 测试类

```java
import com.learn.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    @Test
    public void annotationPointCut() {
        ApplicationContext context = new ClassPathXmlApplicationContext("beans2.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.update();
    }
}

```



## 整合MyBatis

> 步骤

- 导入相关jar包
  - junit
  - mybatis
  - mysql
  - spring
  - aop织入
  - mybatis-spring

- 编写配置文件
- 测试

> pom.xml

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <parent>
        <artifactId>spring_study</artifactId>
        <groupId>org.example</groupId>
        <version>1.0-SNAPSHOT</version>
    </parent>
    <modelVersion>4.0.0</modelVersion>

    <artifactId>spring-10-mybatis</artifactId>

    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.12</version>
            <scope>test</scope>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.21</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis</artifactId>
            <version>3.5.2</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-webmvc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-webmvc</artifactId>
            <version>5.2.11.RELEASE</version>
        </dependency>
        <!--spring操作数据库的依赖包-->
        <!-- https://mvnrepository.com/artifact/org.springframework/spring-jdbc -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-jdbc</artifactId>
            <version>5.2.1.RELEASE</version>
        </dependency>
        <!--aop依赖-->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.8.13</version>
        </dependency>
        <!-- https://mvnrepository.com/artifact/org.mybatis/mybatis-spring -->
        <dependency>
            <groupId>org.mybatis</groupId>
            <artifactId>mybatis-spring</artifactId>
            <version>2.0.1</version>
        </dependency>

    </dependencies>

</project>
```

