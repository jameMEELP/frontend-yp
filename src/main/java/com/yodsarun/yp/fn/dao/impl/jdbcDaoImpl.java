package com.yodsarun.yp.fn.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.h2.value.CaseInsensitiveMap;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceUtils;

import com.yodsarun.yp.fn.dao.jdbcDao;

public class jdbcDaoImpl implements jdbcDao{
	private DataSource dataSource;
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
	}

	public Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}

	public Map queryForMap(String sql) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		List list = namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, new ResultSetExtractor<List>() {
			public List extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				List list = new ArrayList();
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int resultSetMetaDataColumnCount = resultSetMetaData.getColumnCount();

				while (resultSet.next()) {
					// CaseInsensitiveMap caseInsensitiveMap = new CaseInsensitiveMap();

					Map caseInsensitiveMap = new HashMap();
					for (int i = 1; i <= resultSetMetaDataColumnCount; i++) {
						String columnName = resultSetMetaData.getColumnName(i);
						Object columnValue = resultSet.getObject(columnName);

						caseInsensitiveMap.put(columnName.toUpperCase(), columnValue);
					}

					list.add(caseInsensitiveMap);
				}

				return list;
			}
		});

		Map result = new CaseInsensitiveMap();
		if (list.size() != 0) {
			result = (Map) list.get(0);
		}

		return result;
	}

	public Map queryForMap(String sql, MapSqlParameterSource mapSqlParameterSource) {
		List list = namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, new ResultSetExtractor<List>() {
			public List extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				List list = new ArrayList();
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int resultSetMetaDataColumnCount = resultSetMetaData.getColumnCount();

				while (resultSet.next()) {
					// CaseInsensitiveMap caseInsensitiveMap = new CaseInsensitiveMap();

					Map caseInsensitiveMap = new HashMap();
					for (int i = 1; i <= resultSetMetaDataColumnCount; i++) {
						String columnName = resultSetMetaData.getColumnName(i);
						Object columnValue = resultSet.getObject(columnName);

						caseInsensitiveMap.put(columnName.toUpperCase(), columnValue);
					}

					list.add(caseInsensitiveMap);
				}

				return list;
			}
		});

		Map result = new CaseInsensitiveMap();
		if (list.size() != 0) {
			result = (Map) list.get(0);
		}

		return result;
	}

	public List queryForList(String sql) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		List list = namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, new ResultSetExtractor<List>() {
			public List extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				List list = new ArrayList();
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int resultSetMetaDataColumnCount = resultSetMetaData.getColumnCount();

				while (resultSet.next()) {
					// CaseInsensitiveMap caseInsensitiveMap = new CaseInsensitiveMap();

					Map caseInsensitiveMap = new HashMap();
					for (int i = 1; i <= resultSetMetaDataColumnCount; i++) {
						String columnName = resultSetMetaData.getColumnName(i);
						Object columnValue = resultSet.getObject(columnName);

						caseInsensitiveMap.put(columnName.toUpperCase(), columnValue);
					}

					list.add(caseInsensitiveMap);
				}

				return list;
			}
		});

		return list;
	}

	public List queryForListLinkedHashMap(String sql) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		List list = namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, new ResultSetExtractor<List>() {
			public List extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				List list = new ArrayList();
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int resultSetMetaDataColumnCount = resultSetMetaData.getColumnCount();

				while (resultSet.next()) {
					// CaseInsensitiveMap caseInsensitiveMap = new CaseInsensitiveMap();

					Map caseInsensitiveMap = new LinkedHashMap();
					for (int i = 1; i <= resultSetMetaDataColumnCount; i++) {
						String columnName = resultSetMetaData.getColumnName(i);
						Object columnValue = resultSet.getObject(columnName);

						caseInsensitiveMap.put(columnName.toUpperCase(), columnValue);
					}

					list.add(caseInsensitiveMap);
				}

				return list;
			}
		});

		return list;
	}

	public int update(String sql) {
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		int result = namedParameterJdbcTemplate.update(sql.toString().trim(), mapSqlParameterSource);

		return result;
	}

	public int update(String sql, MapSqlParameterSource mapSqlParameterSource) {
		int result = namedParameterJdbcTemplate.update(sql.toString().trim(), mapSqlParameterSource);

		return result;
	}

	public int[] update(String sql, BatchPreparedStatementSetter batchPreparedStatementSetter) {
		int results[] = namedParameterJdbcTemplate.getJdbcTemplate().batchUpdate(sql, batchPreparedStatementSetter);

		return results;
	}

	public List queryForList(String sql, MapSqlParameterSource mapSqlParameterSource) {
		List list = namedParameterJdbcTemplate.query(sql, mapSqlParameterSource, new ResultSetExtractor<List>() {
			public List extractData(ResultSet resultSet) throws SQLException, DataAccessException {
				List list = new ArrayList();
				ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
				int resultSetMetaDataColumnCount = resultSetMetaData.getColumnCount();

				while (resultSet.next()) {
					// CaseInsensitiveMap caseInsensitiveMap = new CaseInsensitiveMap();

					Map caseInsensitiveMap = new HashMap();
					for (int i = 1; i <= resultSetMetaDataColumnCount; i++) {
						String columnName = resultSetMetaData.getColumnName(i);
						Object columnValue = resultSet.getObject(columnName);

						caseInsensitiveMap.put(columnName.toUpperCase(), columnValue);
					}

					list.add(caseInsensitiveMap);
				}

				return list;
			}
		});

		return list;
	}

}
