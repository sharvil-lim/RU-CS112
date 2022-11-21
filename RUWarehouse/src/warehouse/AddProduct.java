package warehouse;

/*
 * Use this class to test to addProduct method.
 */
public class AddProduct {
    public static void main(String[] args) {  
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

        Warehouse warehouse = new Warehouse();
        int numOfProducts = StdIn.readInt();
        
        for (int i = 1; i <= numOfProducts; i++) {
            int day = StdIn.readInt();
            int productID = StdIn.readInt();
            String name = StdIn.readString();
            int stock = StdIn.readInt();
            int demand = StdIn.readInt();
            warehouse.addProduct(productID, name, stock, day, demand);
        }

        StdOut.println(warehouse);
    }
}