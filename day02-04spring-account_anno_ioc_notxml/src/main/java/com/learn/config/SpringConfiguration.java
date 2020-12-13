package com.learn.config;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

/**
 * 配置类
 * 作用相当于bean.xml
 *      @ Configuration 声明当前类为一个配置类
 *      @ ComponentScan(basePackages = {"com.learn"}) 通过注解指定spring创建容器时需要扫描的包
 *          作用相当于配置了<context:component-scan base-package="com.learn"/>
 *      @ Bean 将当前方法的返回值作为bean对象存入spring的ioc容器中
 *          属性：name：用于bean的id，如果不写，默认值为当前方法的名称
 *          当方法有属性值时，会自动检测ioc容器中是否存在注册的对象
 *          与Autowired的运行机制是一样的
 *      @ PropertySource("classpath:jdbc.properties") 指定properties文件的位置
 *          classpath: 声明类路径下
 */
@Configuration
@ComponentScan("com.learn")
@PropertySource("classpath:jdbc.properties")
public class SpringConfiguration {
    //定义jdbc配置文件变量
    @Value("${driver}")
    private String driver;

    @Value("${url}")
    private String url;

    @Value("${user}")
    private String user;

    @Value("${password}")
    private String password;

    /**
     * 将原本放在配置文件中的对象使用注解进行配置
     * @ Qualifier("dataSource") 声明数据源
     * @param dataSource QueryRunner所需的DataSource对象参数
     * @return 返回一个QueryRunner
     */
    @Bean("runner")
    @Scope("prototype") //声明多例模式
    public QueryRunner createRunner(@Qualifier("dataSource") DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     * @return 返回数据源
     */
    @Bean(name = "dataSource")
    public DataSource createDateSource() {
        ComboPooledDataSource source = new ComboPooledDataSource();
        try {
            source.setDriverClass(driver);
            source.setJdbcUrl(url);
            source.setUser(user);
            source.setPassword(password);
            return source;
        } catch (PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }
}
