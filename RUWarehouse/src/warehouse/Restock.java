package warehouse;

public class Restock {
    public static void main(String[] args) {
        String inputFileName = "/Users/sharvillimaye/Downloads/RUWarehouse/restock.in";
        String outputFileName = "/Users/sharvillimaye/Downloads/RUWarehouse/restock.out";
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
            } else {
                return;
            }
        }

        StdOut.print(warehouse);
    }
}
