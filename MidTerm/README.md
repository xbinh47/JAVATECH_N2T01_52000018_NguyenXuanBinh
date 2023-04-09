  <div align="center">
    <a href="https://github.comxbinh47/JAVATECH_N2T01_52000018_NguyenXuanBinh/MidTerm">
      <img src="images/logo.jpg" alt="Logo" width="300" height="300">
    </a>
  </div>

# TechStore

TechStore is a very simple website was built to sell products.

## Video Demo Link

https://youtu.be/5vlf2TP4mYA

## Summary

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#Summary">Summary</a></li>
    <li><a href="#Built-with">Built with</a></li>
    <li><a href="#Code-Explanation">Code Explanation</a></li>
    <li><a href="#Deployment">Deployment</a></li>
    <li><a href="#Website-snapshots">Website snapshots</a></li>
    <li><a href="#Postman-snapshots">Postman snapshots</a></li>
  </ol>
</details>

## Built with
  - A brief explanation for software development principles, patterns and practices being applied.

  > Using Maven and Spring Boot Framework

  > Lombok: It is a Java library that helps to reduce the amount of boilerplate code required in Java classes by providing annotations that generate code automatically during compilation. For example, annotations such as @Getter, @Setter, and @NoArgsConstructor can generate getters, setters, and default constructors, respectively.

  > Spring: It is a popular open-source framework for developing Java applications. Spring provides features such as inversion of control (IoC), dependency injection, and aspect-oriented programming, among others. These features help to reduce the amount of boilerplate code and enable developers to focus on building business logic rather than low-level infrastructure.

  > Thymeleaf: It is a Java-based template engine used for web and standalone applications. Thymeleaf provides a natural way to create dynamic web pages in HTML or XML using server-side Java code. Thymeleaf is particularly useful for creating HTML templates that can be reused across multiple pages.

  > JPA: It is a Java specification for object-relational mapping (ORM) that provides a framework for managing relational data in Java applications. JPA allows developers to map Java objects to relational database tables and vice versa, making it easier to work with data in a database. JPA is widely used in enterprise Java applications for data persistence and management.

  > MySQL Connector/J: It is an official JDBC driver provided by MySQL for communicating with MySQL databases. JDBC is a Java API for connecting to databases and executing SQL statements. MySQL Connector/J implements the JDBC API and is used to connect Java applications to MySQL databases.

  > Spring Security: It is a powerful and highly customizable authentication and access-control framework for securing Spring-based Java web applications. Spring Security provides various security features like authentication, authorization, and protection against common attacks like cross-site scripting and request forgery. Developers can easily secure their applications by configuring security rules, creating custom authentication mechanisms, and integrating with other security providers.

## Diagram
 - ERD:
 
 ![ERD](https://user-images.githubusercontent.com/91370679/230735594-76d979c3-c71c-4041-b105-6e14b643225f.png)
 
 - Database Diagram

![Database Diagram](https://user-images.githubusercontent.com/91370679/230737449-e6bee4db-b2f2-4683-beef-243d0cbe205e.png)

## Code Explanation
  - Config Security
  Spring Security configuration class for securing a web application.
  
  > The class defines three beans, namely:

  > PasswordEncoder: A bean that returns an instance of BCryptPasswordEncoder, which is a password encoder implementation that uses the bcrypt hashing algorithm to hash passwords.

  > SecurityFilterChain: A bean that configures the HttpSecurity object to define the security rules for the application. It defines rules that permit access to static resources and require authentication for all API requests.

  > AuthenticationProvider: A bean that returns an instance of DaoAuthenticationProvider, which is an implementation of the AuthenticationProvider interface. This bean sets the userDetailsService and passwordEncoder for the authentication provider. The userDetailsService is used to retrieve user details from a data source and the passwordEncoder is used to encode the password before authentication.
  
  AccountServiceImpl implements the UserDetailsService interface.

  > The loadUserByUsername method is implemented from the UserDetailsService interface, which retrieves an account by username from the database and converts it to a UserDetails object. If the account is not found, it throws a UsernameNotFoundException.

  > The CustomAccountDetails object is returned as a UserDetails object, which extends the User object from Spring Security to include additional custom details of the account, such as the account ID.
  
  - Entity
  > Account: Represents a user account with a username, password, and a list of order histories associated with it.
  
  > Category: Represents a product category with a name and a list of products associated with it.
  
  > CustomAccountDetails: Extends the Spring Security User class to provide a custom implementation for user account details.
  
  > OrderDetail: Represents a detail of an order with a product and a quantity associated with it.
  
  > OrderHistory: Represents an order with a total price, status, creation date, and a list of order details associated with it.
  
  > Product: Represents a product with a name, price, description, color, brand, image, and a category associated with it.
  
  > All entity classes have annotations for defining their properties, relationships, and database mappings. The annotations include @Entity, @Table, @Column, @Id, @GeneratedValue, @ManyToOne, @OneToMany, and others. Some classes also have annotations for handling serialization and deserialization with Jackson.
  
  > @JsonIgnoreProperties: This annotation is used to ignore specific properties during serialization and deserialization of JSON data. In the provided code, it is used to ignore Hibernate-specific properties in the entities.

  > @JsonManagedReference and @JsonBackReference: These annotations are used to handle bidirectional relationships between entities. @JsonManagedReference is used on the parent side of the relationship, while @JsonBackReference is used on the child side. They help to prevent infinite recursion during JSON serialization.

  > @JsonFormat: This annotation is used to specify the format of a date or time field during serialization and deserialization of JSON data. In the provided code, it is used to format the createdAt field in OrderHistory entity.

  > @JsonProperty: This annotation is used to rename a property during serialization and deserialization of JSON data. It is not used in the provided code.
  
  - Repository
  > AccountRepository provides CRUD operations and additional methods to find an account by ID or username.
  
  > CategoryRepository provides CRUD operations and additional methods to find a category by ID or retrieve all categories.
  
  > OrderDetailRepository provides CRUD operations and additional methods to find an order detail by ID, retrieve all order details for an order history, or retrieve an order detail for a specific order history and product.
  
  > OrderHistoryRepository provides CRUD operations and additional methods to retrieve all order histories for an account and a specific status or retrieve an order history by ID.
  
  > ProductRepository provides CRUD operations and additional methods to retrieve all products, find a product by ID, retrieve all products for a category, search for products by name or price range, and filter products by price range, category, brand, and name, sorted by price in ascending or descending order.
  
  - Service
  > The AccountService interface has methods to find an account by its ID and username.

  > The OrderService interface has methods for adding, updating, confirming, and deleting orders, as well as getting order details and the number of order details for a given order ID.

  > The ProductService interface has methods for getting all products, getting a product by its ID, deleting a product by its ID, saving a product, getting products by category or name, and filtering products by a map of parameters. It also has a method for getting all categories.
  
  - Service Implement
  > These are implementation class for three service interface implementations in a Spring application. Each of these classes is annotated with @Service indicating that it is a Spring service component and has a repository injected through the @Autowired annotation.
  
  Account Service Implement
  
  > findAccountById: finds an account by its ID.

  > findAccountByUsername: finds an account by its username.
  
  > loadUserByUsername: loads the user details for the given username by retrieving the corresponding account from the repository and returning a custom implementation of UserDetails called CustomAccountDetails.

  Order Service Implement
  
  > The getOrderHistory method returns a list of OrderHistory for a specific Account and status.

  > The getAllOrder method returns all orders for a given Account and status.

  > The addOrder method adds a new Order or updates an existing Order if there is already an open Order for the specified Account.

  > The addNewOrder method creates a new Order with a OrderHistory and OrderDetail.

  > The updateOldOrder method updates an existing Order with new OrderDetail.

  Product Service Implement

  > getAllProducts: returns a list of all products.
  
  > getProduct: returns a product by ID.
  
  > deleteProduct: deletes a product by ID.
  
  > save: saves a new product.
  
  > getProductsByCategory: returns a list of products by category.

  > getProductsByName: returns a list of products by name, with the option to search for all products if name is null.
  
  > filterProducts: returns a list of products based on filters, including category, price range, brand, and name.
  
  > getAllCategories: returns a list of all categories.
  
  - Controller

  The ProductController handles requests related to products, and has the following methods:

  > renderMain: a GET method that retrieves the current user's account ID, orders history, and all available products and categories, and renders the main page of the application.
  
  > getProduct: a GET method that retrieves a specific product's information by its ID and renders its details page.
  
  > getProductsByName: a GET method that retrieves a list of products that match a certain name, sent as a request parameter.
  
  > getProducts: a GET method that retrieves a list of products that match certain filtering criteria, sent as request parameters.
  
  The OrderController handles requests related to orders, and has the following methods:

  > cart: a GET method that retrieves the current user's account ID and current order history, and renders the cart page of the application.
  
  > getOrderHistory: a GET method that retrieves a list of orders for the current user, sent as request parameters.
   
  > addOrder: a POST method that adds a new order for the current user, sent as request body.
   
  > confirmOrder: a POST method that confirms a previously added order for the current user, sent as request body.
   
  > deleteOrderDetail: a POST method that deletes a specific order detail for the current user, sent as request body.
   
  > getOrderHistories: a GET method that retrieves a list of order histories for the current user.
   
  > getOrderHistoryDetail: a GET method that retrieves the details of a specific order from the order history, sent as request parameters.
  
  CustomErrorController implements the ErrorController interface and handles HTTP error requests by mapping them to the "/error" endpoint. 
  
  > The HandleError method retrieves the status code and error message from the HttpServletRequest object and adds them as attributes to the Model object. It then returns the name of the view to be rendered, which is "error". This class allows for customization of error handling and display of error messages in the application's UI.
## Deployment

  > To deploy this project run

  > To run the application, you need to have Java Runtime 17 or later installed on your computer, but we recommend using Java 19. Once you have Java installed, you can run the following command in your terminal (PowerShell, Command Prompt, etc.):

  > java -jar your-app-name.jar
  
  > Before running the application, you need to create a MySQL database. You can use XAMPP or any other program that supports MySQL. Make sure the database name is specified in the application.properties file in the source code folder, and the port is set to 3306. Once you have created the database, you can import the data.sql file.

  > Alternatively, you can open the project in IntelliJ IDEA and create a MySQL database with the same specifications. Then, you can run the project through the main class and import the data.sql file.

## Website snapshots
- Login: Login to use web (have two account(username, password): (account,123456), (xbinh47, 123456)

![Login](https://user-images.githubusercontent.com/91370679/230649931-17c0ca90-9be0-493e-a003-0d7cf4fb9acf.png)

- Home Page: contain all products

![HomePage](https://user-images.githubusercontent.com/91370679/230649687-d9d2eacd-c3c1-4aae-ab1d-7f9af5391e52.png)

- Product Detail: contain product information.

![ProductDetail](https://user-images.githubusercontent.com/91370679/230650373-cbd8b340-3721-4bbc-b047-3158c8a8dd1a.png)

- Cart: contain all product that customers have selected

![Cart](https://user-images.githubusercontent.com/91370679/230649994-de81dc93-a4e2-4b0a-a14c-6e5e77990813.png)

- History: contain all products were purchased by customer.

![History](https://user-images.githubusercontent.com/91370679/230650057-7003497b-bea0-46c3-93ef-efa4814ef664.png)

- Error: Error Page
![image](https://user-images.githubusercontent.com/91370679/230759972-9df8b7bc-5b76-48d2-8c1c-7013382b9e3e.png)

## Postman snapshots
- Setup JSESSIONID in Postman
![image](https://user-images.githubusercontent.com/91370679/230707709-9d992cef-9812-40d8-80b1-c82e71e5b70f.png)
![image](https://user-images.githubusercontent.com/91370679/230707753-23c9258b-9ada-4d64-911c-fef75b556967.png)


- Authorization Postman for my API:
![image](https://user-images.githubusercontent.com/91370679/230651356-52387c67-c6f2-4210-93e1-d72db303a421.png)

- FilterProductByName: return product by name
![image](https://user-images.githubusercontent.com/91370679/230707842-41292b5a-8de5-4f79-b891-db04340f5a82.png)

- AddOrder: add new order. Return success or error message
![image](https://user-images.githubusercontent.com/91370679/230707855-3fc6a51f-5c6d-49a7-a0c3-38dc156219d4.png)

- GetOrder: get all orders by status
![image](https://user-images.githubusercontent.com/91370679/230707886-8830b39c-4ef6-4003-8366-125a71e903de.png)

- DeleteOrderDetail: delete order of a product in current cart
- ![image](https://user-images.githubusercontent.com/91370679/230707993-f4b0cab7-b60b-46b3-a65c-482b669897dd.png)

- Get Order Detail: get all the details product by order id relate to order history
![image](https://user-images.githubusercontent.com/91370679/230708149-67d22545-af8d-4594-8809-3a1ea098aa91.png)

- Confirm Order 
![image](https://user-images.githubusercontent.com/91370679/230757928-b9c93345-f508-4009-9777-3e741d7aba0d.png)

