/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto1estructuradedatos;

import java.awt.Dimension;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
// @autor Cristhian Fonseca H, Jeremy Ramirez Ramirez, Eduardo Ocampo Aguilar

public class Servicios {

    static Scanner input = new Scanner(System.in);

    // Declaracion e inicializacion de vectores y variables
    static int[] numeroDePago = new int[10]; // vector para guardar numero de pago
    static String[] fecha = new String[10]; // vector para fecha
    static String[] hora = new String[10]; // '' para hora
    static String[] cedula = new String[10]; // '' para cedula
    static String[] nombre = new String[10]; // '' para nombre
    static String[] apellido1 = new String[10]; // '' para registrar primer apellido
    static String[] apellido2 = new String[10]; // '' para registrar segundo apellido
    static int[] numeroDeCaja = new int[10]; // '' para numero de caja, preestablecido
    static String[] tipoDeServicio = new String[10]; // '' para tipo de servicio, preestablecido
    static int[] numeroDeFactura = new int[10]; // '' para numero de factura
    static double[] montoAPagar = new double[10]; // '' para el monto original de la factura
    static double[] montoDeComision = new double[10];// '' para monto de la comision del vendedor
    static double[] montoDeducido = new double[10];// '' para monto deducido (Monto a pagar - comision)
    static double[] montoPagaCliente = new double[10];// '' para monto con el que paga el cliente
    static double[] vuelto = new double[10];// '' para el vuelto a dar. (modoPagaCliente - montoAPagar)
    // Variable para llevar la cuenta.
    static int numPago = 0;

// Función para reiniciar los valores de las variables y vectores
    public static void inicializar() {
        for (int i = 0; i < numeroDePago.length; i++) {
            fecha[i] = "";
            hora[i] = "";
            cedula[i] = "";
            nombre[i] = "";
            apellido1[i] = "";
            apellido2[i] = "";
            numeroDeFactura[i] = 0;
            montoAPagar[i] = 0;
            montoDeComision[i] = 0;
            montoDeducido[i] = 0;
            montoPagaCliente[i] = 0;
            vuelto[i] = 0;
            numPago = 0;
        }
        JOptionPane.showMessageDialog(null, "Valores Inicializados");
    }

    //Aniadir aca los metodos para las demas funciones (REPORTES, MODIFICAR PAGO, ETC)
    public static void RealizarPagos() {
        String opcion = "S";
        while (opcion.equalsIgnoreCase("S")) {
            if (numPago == 10) {
                JOptionPane.showMessageDialog(null, "Vectores llenos");
                break;
            }

            numeroDePago[numPago] = numPago + 1;
            String fechaInput = JOptionPane.showInputDialog("Ingrese la fecha (DD/MM/AAAA): ");
            fecha[numPago] = fechaInput;

            String horaInput = JOptionPane.showInputDialog("Ingrese la hora (HH:MM): ");
            hora[numPago] = horaInput;

            String cedulaInput = JOptionPane.showInputDialog("Ingrese la cedula: ");
            cedula[numPago] = cedulaInput;

            String nombreInput = JOptionPane.showInputDialog("Ingrese el nombre: ");
            nombre[numPago] = nombreInput;

            String apellido1Input = JOptionPane.showInputDialog("Ingrese el primer apellido: ");
            apellido1[numPago] = apellido1Input;

            String apellido2Input = JOptionPane.showInputDialog("Ingrese el segundo apellido: ");
            apellido2[numPago] = apellido2Input;

            numeroDeCaja[numPago] = (numPago % 3) + 1;
            String[] servicios = {"Recibo de Luz", "Recibo Telefono", "Recibo de Agua"};
            int tipoDeServicioIndex = JOptionPane.showOptionDialog(null,
                    "Seleccione el tipo de servicio:", "Tipo de Servicio",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, servicios, servicios[0]);
            tipoDeServicio[numPago] = servicios[tipoDeServicioIndex];

            String numeroDeFacturaInput = JOptionPane.showInputDialog("Ingrese el número de factura: ");
            numeroDeFactura[numPago] = Integer.parseInt(numeroDeFacturaInput);

            String montoAPagarInput = JOptionPane.showInputDialog("Ingrese el monto a pagar: ");
            montoAPagar[numPago] = Double.parseDouble(montoAPagarInput);

            if (tipoDeServicio[numPago].equals("Recibo de Luz")) {
                montoDeComision[numPago] = montoAPagar[numPago] * 0.04;
            } else if (tipoDeServicio[numPago].equals("Recibo Telefono")) {
                montoDeComision[numPago] = montoAPagar[numPago] * 0.055;
            } else {
                montoDeComision[numPago] = montoAPagar[numPago] * 0.065;
            }

            montoDeducido[numPago] = montoAPagar[numPago] - montoDeComision[numPago];
            
            String montoPagaClienteInput = JOptionPane.showInputDialog("Ingrese el monto pagado por el cliente: ");
            montoPagaCliente[numPago] = Double.parseDouble(montoPagaClienteInput);

            while (montoPagaCliente[numPago] < montoAPagar[numPago]) {
                JOptionPane.showMessageDialog(null,
                        "El monto ingresado es menor que el monto a pagar. Por favor, ingrese un monto valido.");
                montoPagaClienteInput = JOptionPane.showInputDialog("Ingrese el monto pagado por el cliente: ");
                montoPagaCliente[numPago] = Double.parseDouble(montoPagaClienteInput);
            };

            vuelto[numPago] = montoPagaCliente[numPago] - montoAPagar[numPago];
            JOptionPane.showMessageDialog(null, "Vuelto del cliente: " + String.format("%.2f", vuelto[numPago]));
            JOptionPane.showMessageDialog(null, "Pago registrado exitosamente");
            numPago++;
            opcion = JOptionPane.showInputDialog("Desea continuar? S/N ");
        }
    }
    // ------------------------------METODOS Y FUNCIONES-----------------
    // ---------------------------------Menu Principal-------------------

    public static void ConsultarPagos() {
        boolean exit = false;
        do {
            String numeroPagoString = JOptionPane.showInputDialog(null, "Ingrese el numero de pago que desea consultar:");
            int numeroPago = Integer.parseInt(numeroPagoString);
            if (numeroPago < 1 || numeroPago > numPago) {
                JOptionPane.showMessageDialog(null, "Pago no se encuentra Registrado");
                String respuesta = JOptionPane.showInputDialog(null, "¿Desea buscar otro pago? (s/n):");
                if (respuesta.equalsIgnoreCase("n")) {
                    exit = true;
                }
            } else {
                String mensaje = "=========================================\n";
                mensaje += "Datos del Pago #" + numeroPago + "\n";
                mensaje += "=========================================\n";
                mensaje += "  Fecha: " + fecha[numeroPago - 1] + " - Hora: " + hora[numeroPago - 1] + "\n";
                mensaje += "  Numero de Factura: " + numeroDeFactura[numeroPago - 1] + "\n";
                mensaje += "  Número de Pago: " + numeroDePago[numeroPago - 1] + "\n";
                mensaje += "  Número de Caja: " + numeroDeCaja[numeroPago - 1] + "\n";
                mensaje += "=========================================\n";
                mensaje += "  Cedula: " + cedula[numeroPago - 1] + "\n";
                mensaje += "  Nombre: " + nombre[numeroPago - 1] + " " + apellido1[numeroPago - 1] + " " + apellido2[numeroPago - 1] + "\n";
                mensaje += "=========================================\n";
                mensaje += "  Tipo de Servicio: " + tipoDeServicio[numeroPago - 1] + "\n";
                mensaje += "  Monto a Pagar: $" + String.format("%.2f", montoAPagar[numeroPago - 1]) + "\n";
                mensaje += "  Monto Pagado por " + nombre[numeroPago - 1] + ": $" + String.format("%.2f", montoPagaCliente[numeroPago - 1]) + "\n";
                mensaje += "  Vuelto: $" + String.format("%.2f", vuelto[numeroPago - 1]) + "\n";
                mensaje += "=========================================\n";
                JOptionPane.showMessageDialog(null, mensaje);
                String respuesta = JOptionPane.showInputDialog(null, "¿Desea buscar otro pago? (s/n):");
                if (respuesta.equalsIgnoreCase("n")) {
                    exit = true;
                }
            }
        } while (!exit);
    }

    public static void ModificarPagos() {
        boolean exit = false;
        do {
            String numeroPagoString = JOptionPane.showInputDialog(null, "Ingrese el numero  que desea modificar:");
            int numeroPago = Integer.parseInt(numeroPagoString);
            if (numeroPago < 1 || numeroPago > numPago) {
                JOptionPane.showMessageDialog(null, "Pago no se encuentra Registrado");
                String respuesta = JOptionPane.showInputDialog(null, "¿Desea modificar otro elemento? (s/n):");
                if (respuesta.equalsIgnoreCase("n")) {
                    exit = true;
                }
            } else {
                String mensaje = "=========================================\n";
                mensaje += "Modificar elemento " + "\n";
                mensaje += "=========================================\n";
                mensaje += " (1) Fecha: " + fecha[numeroPago - 1] + " - (2) Hora: " + hora[numeroPago - 1] + "\n";
                mensaje += " (3) Numero de Factura: " + numeroDeFactura[numeroPago - 1] + "\n";
                mensaje += "=========================================\n";
                mensaje += " (4) Cedula: " + cedula[numeroPago - 1] + "\n";
                mensaje += " (5) Nombre: " + nombre[numeroPago - 1] + "\n";
                mensaje += " (6) Primer Apellido: " + apellido1[numeroPago - 1] + "\n";
                mensaje += " (7) Segundo Apellido: " + apellido1[numeroPago - 1] + "\n";
                mensaje += "=========================================\n";
                mensaje += " (8) Tipo de Servicio: " + tipoDeServicio[numeroPago - 1] + "\n";
                mensaje += " (9) Monto a Pagar: $" + String.format("%.2f", montoAPagar[numeroPago - 1]) + "\n";
                mensaje += "(10) Monto Pagado por " + nombre[numeroPago - 1] + ": $" + String.format("%.2f", montoPagaCliente[numeroPago - 1]) + "\n";
                mensaje += "=========================================\n";
                mensaje += "Selecione la opcion que desea modificar";
                int numModificar = Integer.parseInt(JOptionPane.showInputDialog(null, mensaje));
                boolean encontrado = false;
                switch (numModificar) {
                    case 1:
                        fecha[numeroPago - 1] = JOptionPane.showInputDialog(null, "Ingrese la fecha (DD/MM/AAAA):", fecha[numeroPago - 1]);
                        encontrado = true; // se encontró el pago
                        break;
                    case 2:
                        hora[numeroPago - 1] = JOptionPane.showInputDialog(null, "Ingrese la hora (HH:MM):", hora[numeroPago - 1]);
                        encontrado = true; // se encontró el pago
                        break;
                    case 3:
                        numeroDeFactura[numeroPago - 1] = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el numero de factura:", numeroDeFactura[numeroPago - 1]));
                        encontrado = true;
                        break;
                    case 4:
                        cedula[numeroPago - 1] = JOptionPane.showInputDialog(null, "Ingrese el numero de cédula:", cedula[numeroPago - 1]);
                        encontrado = true; // se encontró el pago
                        break;
                    case 5:
                        nombre[numeroPago - 1] = JOptionPane.showInputDialog(null, "Ingrese el nombre:", nombre[numeroPago - 1]);
                        encontrado = true; // se encontró el pago
                        break;
                    case 6:
                        apellido1[numeroPago - 1] = JOptionPane.showInputDialog(null, "Ingrese el primer apellido:", apellido1[numeroPago - 1]);
                        encontrado = true; // se encontró el pago
                        break;
                    case 7:
                        apellido2[numeroPago - 1] = JOptionPane.showInputDialog(null, "Ingrese el segundo apellido:", apellido2[numeroPago - 1]);
                        encontrado = true; // se encontró el pago
                        break;
                    case 8:
                        String[] servicios = {"Recibo de Luz", "Recibo Telefono", "Recibo de Agua"};
                        int tipoDeServicioIndex = JOptionPane.showOptionDialog(null, "Seleccione el tipo de servicio:", "Tipo de Servicio", JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, servicios, servicios[0]);
                        tipoDeServicio[numeroPago - 1] = servicios[tipoDeServicioIndex];
                        encontrado = true; // se encontró el pago
                        break;
                    case 9:
                        montoAPagar[numeroPago - 1] = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el monto a pagar:", montoAPagar[numeroPago - 1]));
                        encontrado = true; // se encontró el pago
                        break;
                    case 10:
                        montoPagaCliente[numeroPago - 1] = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingrese el nuevo monto pagado por el cliente:", montoPagaCliente[numeroPago - 1]));
                        while (montoPagaCliente[numeroPago - 1] < montoAPagar[numeroPago - 1]) {
                            JOptionPane.showMessageDialog(null, "El monto ingresado es menor que el monto a pagar. Por favor, ingrese un monto valido.",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                            montoPagaCliente[numeroPago - 1] = Double.parseDouble(JOptionPane.showInputDialog(null,
                                    "Ingrese el nuevo monto pagado por el cliente:",
                                    "Modificar pago", JOptionPane.PLAIN_MESSAGE));
                        }
                        encontrado = true; // se encontró el pago

                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Opción inválida.");
                        break;
                }
                if (encontrado) {
                    JOptionPane.showMessageDialog(null, "Pago modificado exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Pago no encontrado");

                }

                String respuesta = JOptionPane.showInputDialog(null, "¿Desea modificar otro pago? (s/n):");
                if (respuesta.equalsIgnoreCase("n")) {
                    exit = true;
                }
            }
        } while (!exit);
    }

    // verificar si hay pagos
    public static boolean hayPagos() {
        return numPago > 0;
    }

    // eliminar pagos
    public static void eliminarPago() {
        if (!hayPagos()) {
            JOptionPane.showMessageDialog(null, "No hay pagos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Pedir número de pago a eliminar
        int numPagoInt = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el número de pago que desea eliminar:"));
        if (numPagoInt == 0) {
            // si usuario cancela la selección        }
            return;
        }
        // buscamos el indice del vector
        for (int i = 0; i < numPago; i++) {
            if (numeroDePago[i] == numPagoInt) {
                numPagoInt = i;
                break;
            }
        }
        // Si no se encontro el pago a eliminar, mostrar error y salir.
        if (numPagoInt == -1) {
            JOptionPane.showMessageDialog(null, "El pago con numero de pago " + numPagoInt + " no se ha encontrado.");
            return;
        }
        // Si solo hay un pago, eliminarlo y actualizar el contador (numPago)
        if (numPago == 1) {
            numeroDePago[0] = 0;
            fecha[0] = "";
            hora[0] = "";
            cedula[0] = "";
            nombre[0] = "";
            apellido1[0] = "";
            apellido2[0] = "";
            numeroDeCaja[0] = 0;
            tipoDeServicio[0] = "";
            numeroDeFactura[0] = 0;
            montoAPagar[0] = 0.0;
            montoDeComision[0] = 0.0;
            vuelto[0] = 0.0;
            montoPagaCliente[0] = 0.0;
            montoDeducido[0] = 0.0;
            numPago = 0;
            JOptionPane.showMessageDialog(null, "Pago eliminado exitosamente.");
        } else {
            for (int i = numPagoInt; i < numPago - 1; i++) {
                // Si hay mas de un pago, se elimina mediante la asignasion de un valor "arriba"
                numeroDePago[i] = numeroDePago[i + 1];
                fecha[i] = fecha[i + 1];
                hora[i] = hora[i + 1];
                cedula[i] = cedula[i + 1];
                nombre[i] = nombre[i + 1];
                apellido1[i] = apellido1[i + 1];
                apellido2[i] = apellido2[i + 1];
                numeroDeCaja[i] = numeroDeCaja[i + 1];
                tipoDeServicio[i] = tipoDeServicio[i + 1];
                numeroDeFactura[i] = numeroDeFactura[i + 1];
                montoAPagar[i] = montoAPagar[i + 1];
                montoDeComision[i] = montoDeComision[i + 1];
                vuelto[i] = vuelto[i + 1];
                montoPagaCliente[i] = montoPagaCliente[i + 1];
                montoDeducido[i] = montoDeducido[i + 1];

            }
            JOptionPane.showMessageDialog(null, "Pago eliminado exitosamente.");
            numPago = numPago - 1;
        }

    }

    // ---------------------------------Menu Secundario-------------------
    // Funcion para ver todos los pagos
    public static void verTodosLosPagos() {
        if (!hayPagos()) {
            JOptionPane.showMessageDialog(null, "No hay pagos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String[] columnas = {"Num. de Pago", "Fecha", "Hora", "Cedula", "Nombre", "Apellido1", "Apellido2", "Caja", "Tipo de Serv.", "Num. de Fact.", "Monto a Pagar"};

        String[][] datos = new String[numPago][11];
        for (int i = 0; i < numPago; i++) {
            datos[i][0] = String.valueOf(numeroDePago[i]);
            datos[i][1] = fecha[i];
            datos[i][2] = hora[i];
            datos[i][3] = cedula[i];
            datos[i][4] = nombre[i];
            datos[i][5] = apellido1[i];
            datos[i][6] = apellido2[i];
            datos[i][7] = String.valueOf(numeroDeCaja[0]);
            datos[i][8] = tipoDeServicio[0];
            datos[i][9] = String.valueOf(numeroDeFactura[i]);
            datos[i][10] = String.format("%.2f", montoAPagar[i]);
        }

        JTable tabla = new JTable(datos, columnas);
        tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setPreferredSize(new Dimension(950, 300));

        JOptionPane.showMessageDialog(null, scroll, "Registros de todos los pagos", JOptionPane.PLAIN_MESSAGE);
    }

    //  Pago por tipo de servicios
    public static void pagosPorTipoDeServicio() {
        if (!hayPagos()) {
            JOptionPane.showMessageDialog(null, "No hay pagos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Solicitar al usuario que seleccione el tipo de servicio
        String[] opciones = {"Recibo de Luz", "Recibo Telefono", "Recibo de Agua"};
        String opcionSeleccionada = (String) JOptionPane.showInputDialog(null, "Seleccione el tipo de servicio:", "Ver Pagos por Tipo de Servicio", JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[0]);
        if (opcionSeleccionada == null) {
            // si usuario cancela la selección
            return;
        }

        // Buscar registros con el tipo de servicio seleccionado
        int contador = 0;
        for (int i = 0; i < numPago; i++) {
            if (tipoDeServicio[i].equals(opcionSeleccionada)) {
                contador++;
            }
        }

        // Si no hay registros para el tipo de servicio seleccionado, 
        // mostrar un mensaje y salir de la función
        if (contador == 0) {
            JOptionPane.showMessageDialog(null, "No hay registros para el tipo de servicio seleccionado.", "Ver Pagos por Tipo de Servicio", JOptionPane.INFORMATION_MESSAGE);
            return;
        }

        // Mostrar los registros encontrados
        String[] columnas = {"Num. de Pago", "Fecha", "Hora", "Cedula", "Nombre", "Apellido1", "Apellido2", "Caja", "Num. de Fact.", "Monto a Pagar"};
        Object[][] datos = new Object[contador][columnas.length];
        contador = 0;
        for (int i = 0; i < numPago; i++) {
            if (tipoDeServicio[i].equals(opcionSeleccionada)) {
                // matriz con la información a mostrar
                datos[contador][0] = numeroDePago[i];
                datos[contador][1] = fecha[i];
                datos[contador][2] = hora[i];
                datos[contador][3] = cedula[i];
                datos[contador][4] = nombre[i];
                datos[contador][5] = apellido1[i];
                datos[contador][6] = apellido2[i];
                datos[contador][7] = numeroDeCaja[0];
                datos[contador][8] = numeroDeFactura[i];
                datos[contador][9] = montoAPagar[i];
                contador++;
            }
        }
        JOptionPane.showMessageDialog(null, new JTable(datos, columnas), "Ver Pagos por Tipo de Servicio", JOptionPane.PLAIN_MESSAGE);
    }

    //  Pagos por codigo de caja caja
    public static void pagosPorCodigoCaja() {
        if (!hayPagos()) {
            JOptionPane.showMessageDialog(null, "No hay pagos registrados.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Obtener la caja a buscar
        int codigoCaja;
        try {
            codigoCaja = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese el código de caja (1, 2 o 3):"));
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "El código de caja ingresado no es válido.");
            return;
        }

        // Buscar pagos por caja
        boolean seEncontraronPagos = false;
        Object[][] datos = new Object[numPago][11];
        for (int i = 0; i < numPago; i++) {
            if (numeroDeCaja[i] == codigoCaja) {
                // matriz con la información a mostrar
                datos[i][0] = numeroDePago[i];
                datos[i][1] = fecha[i];
                datos[i][2] = hora[i];
                datos[i][3] = cedula[i];
                datos[i][4] = nombre[i];
                datos[i][5] = apellido1[i];
                datos[i][6] = apellido2[i];
                datos[i][7] = numeroDeCaja[i];
                datos[i][8] = tipoDeServicio[i];
                datos[i][9] = numeroDeFactura[i];
                datos[i][10] = montoAPagar[i];
                seEncontraronPagos = true;
            }
        }

        // Mostrar resultados
        if (seEncontraronPagos) {
            String[] columnas = {"Num. de Pago", "Fecha", "Hora", "Cedula", "Nombre", "Apellido1", "Apellido2", "Caja", "Tipo de Serv.", "Num. de Fact.", "Monto a Pagar"};
            JTable tabla = new JTable(datos, columnas);
            JScrollPane scrollPane = new JScrollPane(tabla);
            JOptionPane.showMessageDialog(null, scrollPane, "Pagos por código de caja " + codigoCaja, JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "No se encontraron pagos para la caja ingresada.");
        }
    }

    //  Dinero comisionado por todos los servicios.
    public static void dineroComisionado() {
    // matriz con la información a mostrar
        String[][] datos = new String[numPago + 1][6];
        double totalComision = 0.0;
        for (int i = 0; i < numPago; i++) {
            datos[i][0] = Integer.toString(numeroDePago[i]);
            datos[i][1] = fecha[i];
            datos[i][2] = tipoDeServicio[i];
            datos[i][3] = Double.toString(montoAPagar[i]);
            datos[i][4] = Double.toString(montoDeComision[i]);
            datos[i][5] = Double.toString(montoAPagar[i] - montoDeComision[i]);
            totalComision += montoDeComision[i];
        }
        datos[numPago][0] = "";
        datos[numPago][1] = "";
        datos[numPago][2] = "Total:";
        datos[numPago][3] = "";
        datos[numPago][4] = Double.toString(totalComision);
        datos[numPago][5] = "";

        // Crear tabla con los nombres de las columnas
        String[] columnNames = {"Número de pago", "Fecha", "Tipo de servicio", "Monto", "Comisión", "Monto deducido"};
        DefaultTableModel model = new DefaultTableModel(datos, columnNames);

        // Crear y mostrar la tabla
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        JOptionPane.showMessageDialog(null, scrollPane, "Dinero comisionado (total: " + totalComision + ")", JOptionPane.PLAIN_MESSAGE);
    }
}
