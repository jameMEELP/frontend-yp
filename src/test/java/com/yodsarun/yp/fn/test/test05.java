package com.yodsarun.yp.fn.test;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

public class test05 {
	public static void main(String[] args) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/dev");
		dataSource.setUsername("yodsarun");
		dataSource.setPassword("1234");
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		Number number = (Number) jdbcTemplate.update("INSERT INTO public.customers (first_name, last_name) VALUES ('yodsarun', 'pongda')");
		System.out.println("number = " + number);
		System.exit(0);
	}
}
