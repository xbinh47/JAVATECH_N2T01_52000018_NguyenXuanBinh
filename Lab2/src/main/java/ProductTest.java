import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ProductTest {
    public static void main(String[] args) {
        if(args.length != 2 && args.length !=3){
            System.out.println("Invalid input information");
            return;
        }

        DatabaseInfo databaseInfo;
        if(args.length == 2 ){
            databaseInfo = new DatabaseInfo(args[0],args[1],"");
        }else{
            databaseInfo = new DatabaseInfo(args[0],args[1],args[2]);
        }

        ProductDAO productDAO = new ProductDAO(databaseInfo);

        Scanner input = new Scanner(System.in);
        int choice;
        do {
            System.out.println("****************Product Management****************");
            System.out.println("1. Read product list");
            System.out.println("2. Read a product by input id");
            System.out.println("3. Add a new product, the result is the product id (auto increment)");
            System.out.println("4. Update a product");
            System.out.println("5. Delete a product");
            System.out.println("6. Exit");
            System.out.println("**************************************************");
            System.out.print("Choose: ");
            choice = input.nextInt();
            int id = -1;
            String name = "";
            double price = 0.0;
            switch (choice) {
                case 1:
                    System.out.println("Products List");
                    List<Product> productList = productDAO.readAll();
                    productDAO.printList(productList);
                    break;
                case 2:
                    System.out.println("Enter product Id: ");
                    id = input.nextInt();
                    Product product = productDAO.read(id);
                    if(!Objects.isNull(product)) {
                        System.out.println(product.toString());
                    }else {
                        System.out.println("No product is found");
                    }
                    break;
                case 3:
                    System.out.println("Add a product with auto increment id");
                    input.nextLine();
                    System.out.print("Enter name of product: ");
                    name = input.nextLine();
                    System.out.print("Enter price of product: ");
                    price = input.nextDouble();
                    int resultId = productDAO.add(new Product(name,price));
                    System.out.println("Successfully add product with ID: " +resultId);
                    break;
                case 4:
                    System.out.println("Update a product");
                    productList = productDAO.readAll();
                    productDAO.printList(productList);
                    int idCheck;
                    do{
                        System.out.print("Enter valid ID: ");
                        idCheck = input.nextInt();
                    }while(!productDAO.isIdExist(idCheck));
                    input.nextLine();
                    System.out.print("Enter name of product: ");
                    name = input.nextLine();
                    System.out.print("Enter price of product: ");
                    price = input.nextDouble();
                    if(productDAO.update(new Product(idCheck,name,price))){
                        System.out.println("Update product successfully");
                    }else {
                        System.out.println("Update product unsuccessfully");
                    }
                    break;
                case 5:
                    System.out.println("Delete a Product");
                    productList = productDAO.readAll();
                    productDAO.printList(productList);
                    do{
                        System.out.print("Enter valid ID: ");
                        idCheck = input.nextInt();
                    }while(!productDAO.isIdExist(idCheck));
                    if(productDAO.delete(idCheck))    {
                        System.out.println("Delete product successfully");
                    }else {
                        System.out.println("Delete product unsuccessfully");
                    }
                    break;
                case 6:
                    System.out.println("Exit the program");
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a correct one");
                    break;
            }
        } while (choice != 6);
    }
}
