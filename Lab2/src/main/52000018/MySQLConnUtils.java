//Su dung chung chung

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnUtils {

	private DatabaseInfo databaseInfo;

	public MySQLConnUtils(DatabaseInfo databaseInfo) {
		this.databaseInfo = databaseInfo;
	}

	public Connection getConnection() {
		Connection con=null;
		try {
			//Đăng ký MySQL Driver với DriverManager
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			
			//Các thông số
			String url="jdbc:mySQL://localhost:3306/ProductManagement";
			String username="root";
			String password="";
			
			//Tạo kết nối
			con=DriverManager.getConnection(this.databaseInfo.getUrl(), this.databaseInfo.getUser(), this.databaseInfo.getPassword());
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return con;
		
	}
	public void closeConnection(Connection con) {
		try {
			if (con!=null) {
				con.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public void printInfo(Connection con) {
		try {
			if(con!=null) {
				System.out.println(con.getMetaData().getDatabaseProductName().toString());
				System.out.println(con.getMetaData().getDatabaseProductVersion().toString());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
