public class Product {

    private int     productID;
    private String  name;
    private double  rate;
    private String  color;
    private String  size;
    private String  rattler;
    private String  notes;

    public Product(int productID,String name, double rate,String color, String size,String rattler,String notes){

        this.productID = productID;
        this.name = name;
        this.rate = rate;
        this.color = color;
        this.size = size;
        this.rattler = rattler;
        this.notes = notes;

    }

    public Product(int productID,String name, double rate, String size){
        this.productID = productID;
        this.name = name;
        this.rate = rate;
        this.color = null;
        this.size = size;
        this.rattler = null;
        this.notes = null;

    }


    public void addProductToDatabase(){

        //add this product to the database

    }



}
