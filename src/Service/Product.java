package Service;

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


    public Product(int productID, String name, double rate, String size){
        this.productID = productID;
        this.name = name;
        this.rate = rate;
        this.color = null;
        this.size = size;
        this.rattler = null;
        this.notes = null;

    }

    public String productSummary()
    {

        String summary = "";
        summary+= "NAME: " + this.name + " SIZE: " + this.size + " RATE: " + this.rate;
        return summary;

    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getRattler() {
        return rattler;
    }

    public void setRattler(String rattler) {
        this.rattler = rattler;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }






}
