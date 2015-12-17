package be.ehb.restservermetdatabase.dao;


import java.sql.*;
import java.util.ArrayList;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetProvider;

public class Database {
	private static String url = "jdbc:mysql://dt5.ehb.be/IM2015GR001";
	private static String user = "IM2015GR001";
	private static String pw = "37852416";

	private static Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pw);
		} catch (SQLException ex) {
			System.out.println("Connectie kon niet worden opgebouwd. Zijn logingegevens correct?");
			throw ex;
		}
		return conn;
	}

	private static PreparedStatement createPreparedStatement(Connection conn, String sqlQuery, Object[] param) throws SQLException {
		PreparedStatement mijnPreparedStatement = conn.prepareStatement(sqlQuery);
		int parameterIndex = 1;
		for (Object parameterWaarde : param) {
			mijnPreparedStatement.setObject(parameterIndex, parameterWaarde);
			parameterIndex++;
		}
		return mijnPreparedStatement;
	}

	public static ResultSet execSqlAndReturn(String sqlQuery) throws SQLException {
		return execSqlAndReturn(sqlQuery, new Object[0]);
	}

	public static ResultSet execSqlAndReturn(String sqlQuery, Object[] param) throws SQLException {
		Connection conn = null;
		CachedRowSet cachedRowSet = null;
		try {
			conn = getConnection();
			PreparedStatement prepStatement = createPreparedStatement(conn, sqlQuery, param);

			ResultSet results = prepStatement.executeQuery();
			cachedRowSet = RowSetProvider.newFactory().createCachedRowSet();
			cachedRowSet.populate(results);

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (conn != null) {
				conn.close();
			}

		}
		return cachedRowSet;
	}

	public static int execSqlAndReturnChangedRows(String sqlQuery, Object[] param) throws SQLException {
		Connection conn = null;
		int rowAmount = 0;
		try {
			conn = getConnection();
			PreparedStatement prepStatement = createPreparedStatement(conn, sqlQuery, param);

			rowAmount = prepStatement.executeUpdate();

		} catch (SQLException ex) {
			ex.printStackTrace();
			throw ex;
		} finally {
			if (conn != null) {
				conn.close();
			}

		}

		return rowAmount;
	}

}
