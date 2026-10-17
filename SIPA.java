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

void mostrarGlosario() {

    var continuar = true;

    while (continuar) {
        System.out.println("""
                --- GLOSARIO SIPA ---
                1. Punto de equilibrio
                2. Retención en la fuente
                3. Arancel logístico
                4. Volver al menú principal
                """);

        var opcion = leerOpcion("Seleccione un concepto: ", 1, 4);

        var definicion = switch (opcion) {
            case 1 -> """
                    PUNTO DE EQUILIBRIO

                    Es el nivel de ventas donde los ingresos
                    igualan a los costos totales; es decir,
                    donde no hay pérdida ni ganancia.
                    """;
            case 2 -> """
                    RETENCIÓN EN LA FUENTE

                    Es un mecanismo de recaudo anticipado
                    de impuestos que el comprador descuenta
                    del pago total al agricultor.
                    """;
            case 3 -> """
                    ARANCEL LOGÍSTICO

                    Costo adicional aplicado a mercancías
                    destinadas a la exportación para cubrir
                    trámites portuarios.
                    """;
            default -> "";
        };

        if (opcion == 4) {
            continuar = false;
        } else {
            System.out.println(definicion);
        }
    }
}

void simularPuntoEquilibrio() {

    System.out.println("\n--- SIMULADOR DE PUNTO DE EQUILIBRIO ---");

    var costosFijos = leerDecimalNoNegativo("Ingrese los costos fijos totales: ");

    double precioVenta;
    double costoVariable;
    double margen;

    do {
        precioVenta = leerDecimalNoNegativo(
            "Ingrese el precio de venta por unidad: "
        );

        costoVariable = leerDecimalNoNegativo(
            "Ingrese el costo variable por unidad: "
        );

        margen = precioVenta - costoVariable;

        if (margen <= 0) {
            System.out.println(
                "Error: el precio de venta debe ser mayor "
                + "que el costo variable. Intente nuevamente."
            );
        }
    } while (margen <= 0);

    var unidades = Math.ceil(costosFijos / margen);

    var resultado = """
            ==========================================
                SIMULACIÓN DE PUNTO DE EQUILIBRIO
            ==========================================
            Costos fijos: %s
            Precio de venta por unidad: %s
            Costo variable por unidad: %s
            Margen por unidad: %s
            Unidades mínimas necesarias: %.0f
            ==========================================
            """.formatted(
                formatoCOP(costosFijos),
                formatoCOP(precioVenta),
                formatoCOP(costoVariable),
                formatoCOP(margen),
                unidades
            );

    System.out.println(resultado);
}

