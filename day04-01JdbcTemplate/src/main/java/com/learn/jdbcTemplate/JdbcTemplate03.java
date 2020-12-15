package com.learn.jdbcTemplate;

import com.learn.pojo.Account;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//执行CRUD操作
public class JdbcTemplate03 {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean.xml");
        JdbcTemplate jdbcTemplate = (JdbcTemplate) context.getBean("jdbcTemplate");

//        getAccountList(jdbcTemplate);
//        getCount(jdbcTemplate);
        getAccountById(jdbcTemplate, 2);
    }

    //添加用户
    public static void saveAccount(JdbcTemplate jdbcTemplate) {
        String sql = "insert into account (name, money) values ('Toty', 2000)";
        jdbcTemplate.update(sql);
    }

    //修改用户
    public static void updateAccount(JdbcTemplate jdbcTemplate) {
        String sql = "update account set name = ?, money = ? where id = ?";
        jdbcTemplate.update(sql, "张华", 10000, 7);
    }

    //删除用户
    public static void deleteAccount(JdbcTemplate jdbcTemplate) {
        String sql = "delete from account where id = ?";
        jdbcTemplate.update(sql, 7);
    }

    //查询多个账户
    public static void getAccountList(JdbcTemplate jdbcTemplate) {
        String sql = "select * from account";

        List<Account> accountList = jdbcTemplate.query(sql, new AccountRowMapper());

        //这里也可以使用Spring封装的集合
        List<Account> accountList2 = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Account>(Account.class));

        for (Account account : accountList2) {
            System.out.println(account);
        }
    }

    //查询单个账户
    public static void getAccountById(JdbcTemplate jdbcTemplate, int id) {
        String sql = "select * from account where id = ?";

        List<Account> account = jdbcTemplate.query(sql,
                new BeanPropertyRowMapper<Account>(Account.class), id);

        System.out.println(account.isEmpty() ? "无查询结果" : account.get(0));
    }

    //返回一行一列
    public static void getCount(JdbcTemplate jdbcTemplate) {
        String sql = "select count(*) from account";
        //第二个参数表名queryForObject返回值类型
        int count = jdbcTemplate.queryForObject(sql, Integer.class);
        System.out.println(count);
    }
}

//定义Account的封装策略
class AccountRowMapper implements RowMapper<Account> {
    /**
     * 把结果集的数据封装到Account中，由spring把每个Account加到集合中
     * @param resultSet 遍历结果集
     * @param i
     * @return 返回并封装结果集
     * @throws SQLException
     */
    public Account mapRow(ResultSet resultSet, int i) throws SQLException {
        Account account = new Account();
        account.setId(resultSet.getInt("id"));
        account.setName(resultSet.getString("name"));
        account.setMoney(resultSet.getFloat("money"));
        return account;
    }
}