package main;

import model.Product;
import model.Sale;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.util.ArrayList;
import model.Amount;
import model.Employee;
import static model.Product.EXPIRATION_RATE;

public class Shop {

    private double cash = 100.00;
    private ArrayList<Product> inventory;
    private int numberProducts;
    private ArrayList<Sale> sales;

    final static double TAX_RATE = 1.04;
    
    
    
    public Shop() {
        inventory = new ArrayList();
        sales = new ArrayList();
    }
    
    public static void main (String[] args) {
        Shop shop = new Shop();
        
        shop.loadInventory();
        initSession();
        
        Scanner scanner = new Scanner(System.in);
        int opcion = 0;
        boolean exit = false;
        
        do {
            System.out.println("\n");
            System.out.println("===========================");
            System.out.println("Menu principal miTienda.com");
            System.out.println("===========================");
            System.out.println("1) Contar caja");
            System.out.println("2) Añadir producto");
            System.out.println("3) Añadir stock");
            System.out.println("4) Marcar producto proxima caducidad");
            System.out.println("5) Ver inventario");
            System.out.println("6) Venta");
            System.out.println("7) Ver ventas");
            System.out.println("8) Ver monto total de ventas");
            System.out.println("9) Eliminar producto del inventario");
            System.out.println("10) Salir programa");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    shop.showCash();
                    break;

                case 2:
                    shop.addProduct();
                    break;

                case 3:
                    shop.addStock();
                    break;

                case 4:
                    shop.setExpired();
                    break;

                case 5:
                    shop.showInventory();
                    break;

                case 6:
                    shop.sale();
                    break;

                case 7:
                    shop.showSales();
                    break;
                    
                case 8:
                    shop.amountSales();
                    break;
                
                case 9:
                    shop.removeProduct();
                    break;

                case 10:
                    exit = true;
                    break;
            }
        } while (!exit);
    }

    /**
     * load initial inventory to shop
     */
    public void loadInventory() {
        
        addProduct(new Product("Manzana", 10.00, true, 10));
        addProduct(new Product("Pera", 20.00, true, 20));
        addProduct(new Product("Hamburguesa", 30.00, true, 30));
        addProduct(new Product("Fresa", 5.00, true, 20));

    }
    public static void initSession() {
        Scanner sc = new Scanner(System.in);
        boolean loged = false;
        
        do{
      
        
        System.out.print("Ingrese el id del empleado: ");
        int id = sc.nextInt();
        sc.nextLine();
        
        System.out.print("Ingrese la password del empleado: ");
        String password = sc.nextLine();
        
        Employee employee = new Employee(id, password);
        
        
            loged = employee.login(id, password);
            
        }while(!loged);
    }

    /**
     * show current total cash
     */
    private void showCash() {
        System.out.println("Dinero actual: " + cash );
    }

    /**
     * add a new product to inventory getting data from console
     */
    public void addProduct() {
        boolean doit = true;
        
            Scanner scanner = new Scanner(System.in);
            
            System.out.print("Nombre: ");
            String name = scanner.nextLine();
            
            for (Product product : inventory) {
                if (product!=null&&name.equalsIgnoreCase(product.getName())) {
                    System.out.println("Este ya esta en el inventario!");
                    doit = false;
                    break;
                }
            }
            if(doit == true){
                System.out.print("Precio mayorista: ");
                double wholesalerPrice = scanner.nextDouble();
                System.out.print("Stock: ");
                int stock = scanner.nextInt();

                addProduct(new Product(name, wholesalerPrice, true, stock));
            }
        }
    

    /**
     * add stock for a specific product
     */
    public void addStock() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.next();
        Product product = findProduct(name);

        if (product != null) {
            // ask for stock
            System.out.print("Seleccione la cantidad a añadir: ");
            int stock = scanner.nextInt();
            // update stock product
            stock = stock + product.getStock();
            product.setStock(stock);
            System.out.println("El stock del producto " + name + " ha sido actualizado a " + product.getStock());

        } else {
            System.out.println("No se ha encontrado el producto con nombre " + name);
        }
    }

    /**
     * set a product as expired
     */
    private void setExpired() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Seleccione un nombre de producto: ");
        String name = scanner.next();

        Product product = findProduct(name);
        
        double descuento = product.getPublicPrice()*EXPIRATION_RATE;
        product.setPublicPrice(descuento);
        
        if (product != null) {
            System.out.println("El stock del producto " + name + " ha sido actualizado a " + product.getPublicPrice());

        }
    }

    /**
     * show all inventory
     */
    public void showInventory() {
        System.out.println("Contenido actual de la tienda:");
        for (Product product : inventory) {
            if (product != null) {
                System.out.println(product);
            }
        }
    }

    /**
     * make a sale of products to a client
     * 
     * 1. Pedir nombre cliente-
     * 2. Identificar un producto (rellenar los datos del mismo).-
     * 3. Almacenar el producto en un listado.-
     * 4. Comprobar si quiero a�adir m�s productos-
     * 5. Pasar por caja
     */
    public void sale() {
        // ask for client name
        Scanner sc = new Scanner(System.in);
        System.out.println("Realizar venta, escribir nombre cliente");
        String client = sc.nextLine();

        // sale product until input name is not 0
        double totalAmount = 0.0;
        String name = "";
        ArrayList<Product> cart = new ArrayList();
        while (!name.equals("0")) {
            boolean inventoryFull = false;
            System.out.println("Introduce el nombre del producto, escribir 0 para terminar:");
            name = sc.nextLine();

            if (name.equals("0")) {
                break;
            }
            Product product = findProduct(name);
            boolean productAvailable = false;

            if (product != null && product.isAvailable()) {
                productAvailable = true;
                totalAmount += product.getPublicPrice();
                product.setStock(product.getStock() - 1);
                // if no more stock, set as not available to sale
                if (product.getStock() == 0) {
                    product.setAvailable(false);
                }
                cart.add(product);
                System.out.println("Producto añadido con éxito");                  
                           
            if (!productAvailable) {
                System.out.println("Producto no encontrado o sin stock");
            }
        }
    }
    // show cost total
    totalAmount = totalAmount * TAX_RATE;
    cash += totalAmount;
    
    //create new sell an add to the arraylist
    Sale sell = new Sale(client, cart, totalAmount );
    Amount amount = new Amount(totalAmount);
    sales.add(sell);  
    System.out.println("Venta realizada con éxito, total: " + totalAmount);
    
}

    /**
     * show all sales
     */
    private void showSales() {
        System.out.println("Lista de ventas:");
        for (Sale sale : sales) {
            if (sale != null) {
                System.out.println(sale.toString());
            }
        }
    }

    /**
     * add a product to inventory
     *
     * @param product
     */
    public void addProduct(Product product) {
        
        inventory.add(numberProducts, product);
        numberProducts++;
    }

    /**
     * check if inventory is full or not
     *
     * @return true if inventory is full
     */

    /**
     * find product by name
     *
     * @param name
     * @return product found by name
     */
    public Product findProduct(String name) {
        for (Product product : inventory) {
            if (product != null && product.getName().equals(name)) {
                return product;
            }
        }
        return null;
    }
    
    public void amountSales() {
        
        double totalamount = 0;
        
        for (Sale sale : sales) {
            if (sale != null){
                totalamount += sale.getAmount();
            }else{
                break;
            }
        }
        
        DecimalFormat df = new DecimalFormat("#.00");
        System.out.println("El monto total de las ventas es: "+df.format(totalamount));    
    }

    public void removeProduct() {
        Scanner scanner = new Scanner(System.in);
        boolean doit = false; //It will help us know if we have found it or not
        
        System.out.print("Nombre del producto que desea eliminar: ");
        String name = scanner.nextLine();  
        
        for (Product product : inventory) {
            if (name.equalsIgnoreCase(product.getName())) { //search for the same name that the user entered
                inventory.remove(product); //remove the current product
                System.out.println("Fue borrado correctamente!");
                doit = true; //found it
                break;
            }
        }
        if(doit==false){ //not found it
            System.out.println("Este no existe en el inventario!");
        }
    }
    
}
