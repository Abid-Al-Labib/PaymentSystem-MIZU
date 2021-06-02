package Service;

import database.ProductDatabaseController;

import java.util.List;

public class ProductServiceProvider {

    private ProductDatabaseController productDatabaseController;

    public ProductServiceProvider(){

        productDatabaseController = new ProductDatabaseController();

    }

    public String addProductToDatabase(String name, double rate, String color, String size, String rattler, String notes)
    {

        String result = productDatabaseController.addProductToDatabase(name,rate,color,size,rattler,notes);
        return  result;

    }

    public List<Product> getAllProducts()
    {
        List<Product> productList;
        productList = productDatabaseController.getAllProducts();
        return  productList;
    }

    public Product getProductsByID(int pid)
    {
        Product product;
        product = productDatabaseController.getProductsByID(pid);
        return  product;

    }

    public List<Product> getProductsByName(String name)
    {
        List<Product> productList;
        productList = productDatabaseController.getProductsByName(name);
        return  productList;

    }




}
