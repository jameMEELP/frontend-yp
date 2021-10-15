package com.yodsarun.yp.fn.test;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class test03 {
	public static void main(String[] args) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/dev");
		dataSource.setUsername("yodsarun");
		dataSource.setPassword("1234");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		String sql = "select * from customers";
		List list = jdbcTemplate.queryForList(sql);
		System.out.println(list);
	}
}
