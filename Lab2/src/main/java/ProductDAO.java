import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements Repository<Product,Integer>{
    private MySQLConnUtils mySQLConnUtils;

    public ProductDAO(DatabaseInfo databaseInfo) {
        this.mySQLConnUtils = new MySQLConnUtils(databaseInfo);
    }

    public boolean isIdExist(int id){
        try{
            Connection conn = mySQLConnUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select id From product Where id =" + id);
            if(rs.next()){
                return true;
            }
            return false;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Integer add(Product item) {
        try{
            Connection conn = mySQLConnUtils.getConnection();
            Statement stmt = conn.createStatement();
            int id = 0;
            ResultSet rs = stmt.executeQuery("SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'ProductManagement' AND   TABLE_NAME   = 'product'");
            if(rs.next()){
                id = rs.getInt("AUTO_INCREMENT");
            }else{
                return -1;
            }
            PreparedStatement preparedStatement = conn.prepareStatement("Insert into product(name,price) Values(?,?)");
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2,item.getPrice());
            preparedStatement.executeUpdate();
            return id;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Product> readAll() {
        List<Product> productList = new ArrayList<>();
        try{
            Connection conn = mySQLConnUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from product");
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                productList.add(new Product(id,name,price));
            }
            mySQLConnUtils.closeConnection(conn);
        }catch (SQLException e){
            e.printStackTrace();
        }

        return productList;
    }

    @Override
    public Product read(Integer id) {
        Product product = null;
        try{
            Connection conn = mySQLConnUtils.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("Select * from product where id =" + id);
            if (rs.next()){
                return new Product(rs.getInt("id"),rs.getString("name"),rs.getDouble("price"));
            }
            mySQLConnUtils.closeConnection(conn);
            return null;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean update(Product item) {
        try{
            Connection conn = mySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("Update product set name = ?, price = ? Where id = ?");
            preparedStatement.setString(1, item.getName());
            preparedStatement.setDouble(2,item.getPrice());
            preparedStatement.setInt(3,item.getId());
            preparedStatement.executeUpdate();
            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Integer id) {
        try{
            Connection conn = mySQLConnUtils.getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement("Delete from product Where id = ?");
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            return true;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public void printList(List<Product> students){
        for (Product product:
                students) {
            System.out.println(product.toString());
        }
    }
}
