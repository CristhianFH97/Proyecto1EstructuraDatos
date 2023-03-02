/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto1estructuradedatos;

import java.util.Scanner;
import javax.swing.JOptionPane;


public class Menu {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        int opcion;

        do {
            String menu = "\n======== Menu Principal ========\n"
                    + "1. Inicializar Vectores\n"
                    + "2. Realizar Pagos\n"
                    + "3. Consultar Pagos\n"
                    + "4. Modificar Pagos\n"
                    + "5. Eliminar Pagos\n"
                    + "6. Submenu Reportes\n"
                    + "7. Salir\n\n";

            opcion = Integer.parseInt(JOptionPane.showInputDialog(null, menu + "Ingrese una opcion:"));

            switch (opcion) {
                case 1:
                    // Inicializar Vectores
                    Servicios.inicializar();
                    break;
                case 2:
                    // Realizar Pagos
                    Servicios.RealizarPagos();
                    break;
                case 3:
                    // Consultar Pagos
                    Servicios.ConsultarPagos();
                    break;
                case 4:
                    // Modificar Pagos
                    Servicios.ModificarPagos();
                    break;
                case 5:
                    // Eliminar Pagos
                    Servicios.eliminarPago();
                    break;
                case 6:
                    // submenu para reportes
                    int reportOption;

                    do {
                        String submenu = "\n======== Submenu Reportes ========\n"
                                + "1. Ver todos los Pagos\n"
                                + "2. Ver Pagos por tipo de Servicio\n"
                                + "3. Ver Pagos por codigo de caja\n"
                                + "4. Ver Dinero Comisionado por servicios\n"
                                + "5. Regresar Menu Principal\n\n";

                        reportOption = Integer.parseInt(JOptionPane.showInputDialog(null, submenu + "Ingrese una opcion:"));

                        switch (reportOption) {
                            case 1:
                                // Ver todos los Pagos
                                Servicios.verTodosLosPagos();
                                break;
                            case 2:
                                // Ver Pagos por tipo de Servicio
                                Servicios.pagosPorTipoDeServicio();
                                break;
                            case 3:
                                // Ver Pagos por código de caja
                                Servicios.pagosPorCodigoCaja();
                                break;
                            case 4:
                                // Monto Comisionado
                                Servicios.dineroComisionado();
                                
                                break;
                            case 5:
                                JOptionPane.showMessageDialog(null, "Regresando al Menu Principal...");
                                break;
                            default:
                                JOptionPane.showMessageDialog(null, "Opcion invalida. Por favor intente de nuevo.");
                        }

                    } while (reportOption != 5);

                    break;
                case 7:
                    // Salir
                    JOptionPane.showMessageDialog(null, "Saliendo...");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion invalida. Por favor intente de nuevo.");
            }

        } while (opcion != 7);

        input.close();
    }

}
