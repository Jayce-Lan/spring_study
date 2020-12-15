package com.learn.dao.impl;

import org.springframework.jdbc.core.JdbcTemplate;

//抽取dao中的重复代码
public class JdbcMapperSupport {
    private JdbcTemplate jdbcTemplate;

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }
}
