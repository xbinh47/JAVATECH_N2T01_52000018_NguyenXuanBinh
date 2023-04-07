# LapTop4U

LapTop4U is a very simple website was built to sell laptop.

## Summary

<details>
  <summary>Table of Contents</summary>
  <ol>
    <li><a href="#Summary">Summary</a></li>
    <li><a href="#Built-with">Built with</a></li>
    <li><a href="#Code-Explanation">Code Explanation</a></li>
    <li><a href="#Deployment">Deployment</a></li>
    <li><a href="#Website-screenshots">Website screenshots</a></li>
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
  
  > findAccountById: finds an account by its ID.

  > findAccountByUsername: finds an account by its username.
  
  > loadUserByUsername: loads the user details for the given username by retrieving the corresponding account from the repository and returning a custom implementation of UserDetails called CustomAccountDetails.
  
  > The getOrderHistory method returns a list of OrderHistory for a specific Account and status.

  > The getAllOrder method returns all orders for a given Account and status.

  > The addOrder method adds a new Order or updates an existing Order if there is already an open Order for the specified Account.

  > The addNewOrder method creates a new Order with a OrderHistory and OrderDetail.

  > The updateOldOrder method updates an existing Order with new OrderDetail.

  > I use these repositories to access the application's database, including OrderHistoryRepository, OrderDetailRepository, AccountRepository, and ProductRepository.
  
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
  
  > CustomErrorController implements the ErrorController interface and handles HTTP error requests by mapping them to the "/error" endpoint. The HandleError method retrieves the status code and error message from the HttpServletRequest object and adds them as attributes to the Model object. It then returns the name of the view to be rendered, which is "error". This class allows for customization of error handling and display of error messages in the application's UI.
## Deployment

  > To deploy this project run

  > To run the application, you need to have Java Runtime 17 or later installed on your computer, but we recommend using Java 19. Once you have Java installed, you can run the following command in your terminal (PowerShell, Command Prompt, etc.):

  > java -jar your-app-name.jar
  
  > Before running the application, you need to create a MySQL database. You can use XAMPP or any other program that supports MySQL. Make sure the database name is specified in the application.properties file in the source code folder, and the port is set to 3306. Once you have created the database, you can import the data.sql file.

  > Alternatively, you can open the project in IntelliJ IDEA and create a MySQL database with the same specifications. Then, you can run the project through the main class and import the data.sql file.

## Website screenshots
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

## Postman snapshots
- Authorization Postman for my API:
![image](https://user-images.githubusercontent.com/91370679/230651356-52387c67-c6f2-4210-93e1-d72db303a421.png)


- FilterProductByName: return product by name
![image](https://user-images.githubusercontent.com/91370679/230651624-e366f27b-32c7-4a2f-923e-4e8829ca8d36.png)


- AddOrder: add new order. Return success or error message
![image](https://user-images.githubusercontent.com/91370679/230652009-0b980625-70fe-4a3e-83f0-d822763bc6a5.png)

- GetOrder: get all orders by status
![GetAllLaptop](https://user-images.githubusercontent.com/89438952/230541919-7b6d7f18-d6e3-4d56-bb4a-fbefc3ea0fbf.png)

- getCart: get laptop list depending on status. Return list laptop
![GetCart](https://user-images.githubusercontent.com/89438952/230541931-774b5661-ce54-4820-a346-e81573e1759c.png)

- laptop: get laptop infomation by id. Return laptop infomation page
![GetLaptop](https://user-images.githubusercontent.com/89438952/230541940-44c63396-2df3-456a-80a7-8d8e638da8a4.png)

- search: search laptop by keywords. Return list laptop
![GetSearchLaptop](https://user-images.githubusercontent.com/89438952/230541955-a9cca064-24d6-4232-8f2d-71a70935452d.png)

- purchaseCart: mark cart as purchased. Return success or error message
![Purchase](https://user-images.githubusercontent.com/89438952/230541966-a9467719-b0f3-482f-9d40-14a71e2ebcdb.png)
