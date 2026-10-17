import java.util.Scanner;
import java.util.Locale;
import java.text.NumberFormat;

Scanner scanner = new Scanner(System.in);

String nombreAgricultor = "No configurado";
String nombreFinca = "No configurada";

boolean hayCosecha = false;
String ultimoProducto = "";
double ultimoSubtotal = 0;
double ultimoIVA = 0;
double ultimaRetencion = 0;
double ultimoFlete = 0;
double ultimaUtilidad = 0;


void main() {
    var opcion = 0;

    while (opcion != 5) {
        mostrarMenu();
        opcion = leerOpcion("Sleccione una opción: ", 1, 5);

        switch (opcion) {
            case 1 -> registrarCosecha();
            case 2 -> mostrarGlosario();
            case 3 ->simularPuntoEquilibrio();
            case 4 -> configurarAgricultor();
            case 5 -> generarReporte();
        }
    }
    scanner.close();
}

void mostrarMenu() {
    var menu = """
    ==========================================
        SIPA - SISTEMA DE PRECIOS AGRARIOS    
    ==========================================
    1. Registrar cosecha
    2. Glosario de términos
    3. Simulador de punto de equilibrio
    4. Configuración
    5. Generar reporte y salir
    ==========================================
    """;

    System.out.print(menu);
}