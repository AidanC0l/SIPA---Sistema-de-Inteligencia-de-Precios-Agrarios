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

void registrarCosecha() {
    System.out.println("\n--- SELECCIÓN DE PRODUCTO ---");
    System.out.println("1. Papa capira");
    System.out.println("2. Café Pergamino");
    System.out.println("3. Aguacate Hass");

    var opcionProducto = leerOpcion("Seleccione el producto: ", 1, 3);

    var producto = switch (opcionProducto) {
        case 1 -> "Papa capira";
        case 2 -> "Café Pergamino";
        case 3 -> "Aguacate Hass";
        default -> throw new IllegalStateException("Producto inválido");

    };

    var precio = switch (opcionProducto) {
        case 1 -> 2500.0;
        case 2 -> 15000.0;
        case 3 -> 8000.0;
        default -> 0.0;
    };

    var ivaPorcentaje = switch (opcionProducto) {
        case 1, 3 -> 0.0;
        case 2 -> 0.05;
        default -> 0.0; 
    };

    System.out.println("\n--- VOLUMEN DE PRODUCCIÓN ---");
    var toneladas = leerDecimalNoNegativo("Ingrese las toneladas cosechadas: ");

    var kilos = toneladas * 1000;
    var subtotal = kilos * precio;
    var iva = subtotal * ivaPorcentaje;

    System.out.println("\n--- ORIGEN DE LA COSECHA ---");
    System.out.println("1. Boyacá");
    System.out.println("2. Huila");
    System.out.println("3. Antioquia");

    var opcionOrigen = leerOpcion("Seleccione el origen: ", 1, 3);

    var fletePorTonelada = switch (opcionOrigen) {
        case 1 -> 120000.0;
        case 2 -> 180000.0;
        case 3 -> 150000.0;
        default -> 0.0;
    };

    var flete = toneladas * fletePorTonelada;

    System.out.println("\n--- DESTINO DE LA COSECHA ---");
    System.out.println("1. Mercado local / Corabastos");
    System.out.println("2. Exportación (puerto)");

    var destino = leerOpcion("Seleccione el destino: ", 1, 2);

    if (destino == 2) {
        flete = flete * 1.15;
    }

    var retencionPorcentaje = subtotal > 4000000 ? 0.025 : 0.015;

     var retencion = subtotal * retencionPorcentaje;
    var impuestos = iva + retencion;
    var utilidad = subtotal - impuestos - flete;

    // Guardar los resultados para el reporte final
    ultimoProducto = producto;
    ultimoSubtotal = subtotal;
    ultimoIVA = iva;
    ultimaRetencion = retencion;
    ultimoFlete = flete;
    ultimaUtilidad = utilidad;
    hayCosecha = true;

    mostrarResultadoCosecha(
        producto, origen, toneladas, kilos, precio,
        subtotal, iva, retencion, flete, utilidad
    );
}

void mostrarResultadoCosecha( String producto, String origen, double toneladas, double kilos, double precio, double subtotal, double iva, double retencion, double flete, double utilidad) {

    var reporte = """
    =======================================
            RESULTADO DE LA COSECHA        
    =======================================
    Producto: %s
    Origen: %s
    Toneladas: %.2f
    Kilogramos: %.2f
    Precio por kg: %s
    ---------------------------------------
    Subtotal bruto: %s
    IVA: %s
    Retención en la fuente: %s
    Flete final: %s
    ---------------------------------------
    UTILIDAD NETA: %s
    =======================================
    """.formatted(producto, origen, toneladas, kilos, formatoCOP(precio), formatoCOP(subtotal), formatoCOP(iva), formatoCOP(retencion), formatoCOP(flete), formatoCOP(utilidad));

    System.out.println(reporte);
}

