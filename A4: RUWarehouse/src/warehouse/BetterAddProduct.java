package warehouse;

/*
 * Use this class to test the betterAddProduct method.
 */ 
public class BetterAddProduct {
    public static void main(String[] args) {
        String inputFileName = "/Users/sharvillimaye/Downloads/RUWarehouse/betteraddproduct.in";
        String outputFileName = "/Users/sharvillimaye/Downloads/RUWarehouse/betteraddproduct.out";
        StdIn.setFile(inputFileName);
        StdOut.setFile(outputFileName);

        Warehouse warehouse = new Warehouse();
        int numOfProducts = StdIn.readInt();
        
        for (int i = 1; i <= numOfProducts; i++) {
            int day = StdIn.readInt();
            int productID = StdIn.readInt();
            String name = StdIn.readString();
            int stock = StdIn.readInt();
            int demand = StdIn.readInt();
            warehouse.betterAddProduct(productID, name, stock, day, demand);
        }

        StdOut.println(warehouse);
    }
}
