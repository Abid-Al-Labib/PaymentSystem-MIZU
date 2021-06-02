package database;

import Service.Product;

import java.awt.image.DataBuffer;
import java.util.List;

public class ProductDatabaseController {

    DatabaseQueries dbq;

    public ProductDatabaseController(){
        dbq = new DatabaseQueries();
    }

    public String addProductToDatabase(String name, double rate, String color, String size, String rattler, String notes)
    {
        String result = dbq.addProduct(name,rate,color,size,rattler,notes);
        return result;

    }

    public List<Product> getAllProducts()
    {
        List<Product> productList;
        productList = dbq.selectAllProducts();
        return productList;
    }

    public Product getProductsByID(int ID)
    {
        Product product;
        product = dbq.selectProductByID(ID);
        return product;
    }
    public List<Product> getProductsByName(String name)
    {
        List<Product> productList;
        productList = dbq.selectProductByName(name);
        return productList;
    }


}
