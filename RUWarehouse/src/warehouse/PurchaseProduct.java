package warehouse;

public class PurchaseProduct {
    public static void main(String[] args) {
        String inputFileName = "/Users/sharvillimaye/Downloads/RUWarehouse/purchaseproduct.in";
        String outputFileName = "/Users/sharvillimaye/Downloads/RUWarehouse/purchaseproduct.out";
        StdIn.setFile(inputFileName);
        StdOut.setFile(outputFileName);

        Warehouse warehouse = new Warehouse();
        int numberOfLines = StdIn.readInt();

        for (int i = 0; i < numberOfLines; i++) {
            String action = StdIn.readString();

            if (action.equals("add")) {
                int day = StdIn.readInt();
                int productID = StdIn.readInt();
                String name = StdIn.readString();
                int stock = StdIn.readInt();
                int demand = StdIn.readInt();
                warehouse.addProduct(productID, name, stock, day, demand);
            } else if (action.equals("restock")){
                int productID = StdIn.readInt();
                int restock = StdIn.readInt();
                warehouse.restockProduct(productID, restock);   
            } else if (action.equals("delete")) {
                int productID = StdIn.readInt();
                warehouse.deleteProduct(productID);
            } else {
                int day = StdIn.readInt();
                int productID = StdIn.readInt();
                int amountpurchased = StdIn.readInt();
                warehouse.purchaseProduct(productID, day, amountpurchased);
            }
        }

        StdOut.print(warehouse);
    }
}
