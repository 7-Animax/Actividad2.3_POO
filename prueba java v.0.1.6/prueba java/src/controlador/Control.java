package controlador;

import java.util.ArrayList;
import java.util.Scanner;

public class Control {

    private ArrayList<String> registroProductos;
    private ArrayList<Integer> registroStock;
    private Scanner scanner;

    public Control() {
        registroProductos = new ArrayList<>();
        registroStock = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void registrarProducto() {
        System.out.print("Ingrese nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese cantidad inicial: ");
        try {
            int stock = scanner.nextInt();
            scanner.nextLine();
            if (stock > 0) {
                int indice = registroProductos.indexOf(nombre);

                if (indice != -1) {
                    int stockActual = registroStock.get(indice);
                    registroStock.set(indice, stockActual + stock);
                    System.out.println("Producto '" + nombre + "' ya existe. Stock actualizado a: " + (stockActual + stock));
                    verificarStockCritico(nombre, stockActual + stock);
                } else {
                    registroProductos.add(nombre);
                    registroStock.add(stock);
                    System.out.println("Producto '" + nombre + "' registrado con stock: " + stock);
                    verificarStockCritico(nombre, stock);
                }
            } else {
                System.out.println("Error: El stock debe ser mayor a 0");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Cantidad inválida");
            if (scanner.hasNextLine()) scanner.nextLine();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Error: Cantidad inválida");
        }
    }

    public void venderProducto() {
        System.out.print("Ingrese el nombre del producto a vender: ");
        String nombre = scanner.nextLine();

        System.out.print("Ingrese cantidad a vender: ");
        try {
            int cantidad = scanner.nextInt();
            scanner.nextLine();
            if (cantidad > 0) {
                int indice = registroProductos.indexOf(nombre);

                if (indice == -1) {
                    System.out.println("Error: El producto '" + nombre + "' no está en la lista");
                    return;
                }

                if (registroStock.get(indice) < cantidad) {
                    System.out.println("Error: Stock insuficiente. Disponible: " + registroStock.get(indice));
                    return;
                }

                int nuevoStock = registroStock.get(indice) - cantidad;
                registroStock.set(indice, nuevoStock);
                System.out.println("Venta realizada exitosamente de " + cantidad + " unidades de " + nombre);
                verificarStockCritico(nombre, nuevoStock);
            } else {
                System.out.println("Error: La cantidad debe ser mayor a 0");
            }
        } catch (java.util.InputMismatchException e) {
            System.out.println("Error: Cantidad inválida");
            if (scanner.hasNextLine()) scanner.nextLine();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("Error: Cantidad inválida");
        }
    }

    private void verificarStockCritico(String nombre, int stock) {
        if (stock <= 5) {
            System.out.println("⚠️  AVISO: El producto '" + nombre + "' está en estado CRÍTICO. Stock actual: " + stock + " unidades");
        }
    }

    public void mostrarInventario() {
        System.out.println("\n========== LISTADO DE INVENTARIO ==========");
        System.out.println("\n1.Mostrar productos \n2.Mostrar productos con cantidad");
        String inventario = scanner.nextLine();
        int numeroInventario = Integer.parseInt(inventario);

        if (registroProductos.isEmpty()) {
            System.out.println("Error: No hay productos registrados en el inventario");
        } else {
            if (numeroInventario == 1) {
                for (String mercancia : registroProductos ) {
                    System.out.println(mercancia);
                }
            } else if (numeroInventario == 2) {
                for (int i = 0; i < registroProductos.size(); i++) {
                    System.out.println(registroProductos.get(i) + ": " + registroStock.get(i) + " unidades");
                }
            } else {
                System.out.println("Ingrese un numero correcto");
            }
        }
        System.out.println("==========================================\n");
    }

    public void mostrarStockCritico() {
        System.out.println("\n========== PRODUCTOS CON STOCK CRÍTICO ==========");
        boolean hayProductos = false;

        for (int i = 0; i < registroProductos.size(); i++) {
            if (registroStock.get(i) <= 5) {
                System.out.println(registroProductos.get(i) + ": " + registroStock.get(i) + " unidades");
                hayProductos = true;
            }
        }

        if (!hayProductos) {
            System.out.println("Error: No hay productos con stock crítico");
        }
        System.out.println("=================================================\n");
    }

    public ArrayList<String> obtenerRegistro() {
        return registroProductos;
    }

    public ArrayList<Integer> obtenerStock() {
        return registroStock;
    }

    // Método auxiliar para pruebas y uso programático: crea un producto con stock inicial
    public void crearProducto(String nombre, int stock) {
        registroProductos.add(nombre);
        registroStock.add(stock);
    }

    // Agrega stock al producto existente. Retorna true si se actualizó correctamente.
    public boolean agregarStock(String nombre, int cantidad) {
        if (cantidad <= 0) return false;
        int indice = registroProductos.indexOf(nombre);
        if (indice == -1) return false;
        int nuevo = registroStock.get(indice) + cantidad;
        registroStock.set(indice, nuevo);
        System.out.println("Producto '" + nombre + "' ya existe. Stock actualizado a: " + nuevo);
        verificarStockCritico(nombre, nuevo);
        return true;
    }

    // Resta stock del producto existente. No permite dejar stock negativo.
    // Retorna true si la operación se realizó correctamente, false en caso contrario.
    public boolean restarStock(String nombre, int cantidad) {
        if (cantidad <= 0) return false;
        int indice = registroProductos.indexOf(nombre);
        if (indice == -1) return false;
        int actual = registroStock.get(indice);
        if (actual < cantidad) {
            System.out.println("Error: Stock insuficiente. Disponible: " + actual);
            return false;
        }
        int nuevo = actual - cantidad;
        registroStock.set(indice, nuevo);
        System.out.println("Venta realizada exitosamente de " + cantidad + " unidades de " + nombre);
        verificarStockCritico(nombre, nuevo);
        return true;
    }
}
