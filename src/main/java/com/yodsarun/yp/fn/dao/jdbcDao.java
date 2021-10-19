package com.yodsarun.yp.fn.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

public interface jdbcDao {
	public DataSource getDataSource();
	public Connection getConnection();

	public Map queryForMap(String sql);
	public Map queryForMap(String sql, MapSqlParameterSource mapSqlParameterSource);

	public List queryForList(String sql);
	public List queryForListLinkedHashMap(String sql);
	public List queryForList(String sql, MapSqlParameterSource mapSqlParameterSource);

	public int update(String sql);
	public int update(String sql, MapSqlParameterSource mapSqlParameterSource);
	public int[] update(String sql, BatchPreparedStatementSetter batchPreparedStatementSetter);
}
