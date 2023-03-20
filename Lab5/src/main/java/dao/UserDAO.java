package dao;

import database.MySQLConnUtils;

import java.sql.*;

public class UserDAO {
    public static UserDAO getInstance(){
        return new UserDAO();
    }
    public boolean login(String email, String password){
        Boolean flag = false;
        try{
            Connection conn = MySQLConnUtils.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("Select * from User where email = ? and password = ?");
            statement.setString(1,email);
            statement.setString(2,password);
            if(statement.execute()){
                flag = true;
            }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            return flag;
        }
    }

    public boolean register(String name,String email, String password){
        Boolean flag = false;
        try {
            if(isEmailExist(email)){
                return false;
            }
            Connection conn = MySQLConnUtils.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("Insert into user (name,email,password) values(?,?,?)");
            statement.setString(1,name);
            statement.setString(2,email);
            statement.setString(3,password);
            if(statement.executeUpdate() > 0){
                flag = true;
            }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            return flag;
        }
    }

    public boolean isEmailExist(String email){
        Boolean flag = false;
        try{
            Connection conn = MySQLConnUtils.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("Select * from user where email = ?");
            statement.setString(1,email);
            if(statement.execute()){
                flag = true;
            }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            return flag;
        }
    }

    public String getUsername(String email){
        String name = null;
        try{
            Connection conn = MySQLConnUtils.getInstance().getConnection();
            PreparedStatement statement = conn.prepareStatement("Select * from User where email = ?");
            statement.setString(1,email);
            ResultSet rs = statement.executeQuery();
            if(rs.next()){
                name = rs.getString("name");
            }
            conn.close();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            return name;
        }
    }
}
