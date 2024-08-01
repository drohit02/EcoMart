## Ecomart : Product-Category Management

This Project is capable of the storing the Product ans we can perform creat,update,delete and edit the data releated to products
We have mapped one to many relations between product and category i.e One category can have multiple products.
Server side Pagination help to load the data into chuck which improves the performance of the Appliction

## Installation

Install "Ecomart" using Git Clone

```bash
  1. Unzip the Folder ecommart or clone using Git
  2. Open the Eclipse IDE
  3. Import the Project into Eclipse
  4. Use Maven-> Update Project by left clicking on project 
```
    
## Deployment

To deploy this project run

```bash
  1. Finish the import and maven update
  2. open com.task.ecomart package
  3. Before running make sure you have mysql schema named as "task"
  4. Once all above are avaliable with you then run the project
  5. Now your SpringBoot application is running on localhost:8080
```
## Documentation

For more details, you can view the complete JSON documentation(Your Browser Should Have JSON Formatter):
- [Open API Documentation](/ecomart/src/main/resources/static/docs/api-doc.json)


## API Reference

#### Product Crud API

| Method | Endpoint                          | Description                              | Parameters               |
| ------ | --------------------------------- | ---------------------------------------- | ------------------------ |
| GET    | `/api/categories?page=3`          | Get all categories                       | `page`: number           |
| POST   | `/api/categories`                 | Create a new category                    |  Product Object          |                        
| GET    | `/api/categories/{id}`            | Get category by Id                       | `id`: number (required)  |
| PUT    | `/api/categories/{id}`            | Update category by id                    | `id`: number (required)  |
| DELETE | `/api/categories/{id}`            | Delete category by id                    | `id`: number (required)  |

#### Category Crud API

| Method | Endpoint                          | Description                              | Parameters               |
| ------ | --------------------------------- | ---------------------------------------- | ------------------------ |
| GET    | `/api/products?page=2`            | Get all products                         | `page`: number           |
| POST   | `/api/products`                   | Create a new product                     |   Category Object        |
| GET    | `/api/products/{id}`              | Get product by Id                        | `id`: number (required)  |
| PUT    | `/api/products/{id}`              | Update product by id                     | `id`: number (required)  |
| DELETE | `/api/products/{id}`              | Delete product by id                     | `id`: number (required)  |

#### Images
**1 . API Created**
![API Created](/ecomart/src/main/resources/static/images/API.png)

**2. Project Folder Structure**

![Folder Structure](/ecomart/src/main/resources/static/images/folderView.png)

