package com.yzd.jutils.db;

import sun.misc.BASE64Encoder;

import java.sql.*;

public class OpSqliteDB {

	private static final String Class_Name = "org.sqlite.JDBC";
	private static final String DB_URL = "jdbc:sqlite:E:\\FaceDatabase.db";

	public static void main(String args[]) {
		// load the sqlite-JDBC driver using the current class loader
		Connection connection = null;
		try {
			connection = createConnection();
			func1(connection);
			System.out.println("Success!");
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null)
					connection.close();
			} catch (SQLException e) {
				// connection close failed.
				System.err.println(e);
			}
		}
	}

	// 创建Sqlite数据库连接
	public static Connection createConnection() throws SQLException, ClassNotFoundException {
		Class.forName(Class_Name);
		return DriverManager.getConnection(DB_URL);
	}

	public static void func1(Connection connection) throws Exception {
		OpMysqlDB mysqlDB = new OpMysqlDB();
        Statement statement = connection.createStatement();
        statement.setQueryTimeout(30); // set timeout to 30 sec.
        // 执行查询语句

        ResultSet rs = statement.executeQuery("select * from t_FacialFeature");
        while (rs.next()) {
			String col1 = rs.getString("id");
			byte[] bytes = rs.getBytes(4);
			System.out.println(bytes.length);
			String feature = new BASE64Encoder().encode(bytes).replace("\r\n", "");
			mysqlDB.add(Integer.parseInt(col1), feature);
        }
		// true表示在文件末尾追加
    }
}