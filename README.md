
# SIPA - Sistema de Inteligencia de Precios Agrarios

Aplicación de consola desarrollada en **Java 25** para apoyar a los agricultores en la proyección financiera de sus cosechas, mediante el cálculo de ingresos, impuestos, costos de transporte, utilidad neta y punto de equilibrio.

## Descripción del proyecto

SIPA es un proyecto académico de DevSenior que busca aplicar conceptos fundamentales y modernos de Java en el desarrollo de una herramienta orientada al sector agrícola colombiano.

El sistema permite registrar cosechas de diferentes productos, calcular sus proyecciones financieras según el volumen producido y el destino comercial, consultar conceptos financieros, simular el punto de equilibrio y generar un reporte con los resultados de la última cosecha registrada.

## Objetivo general

Desarrollar una aplicación de consola en Java 25 que permita calcular y consultar indicadores financieros relacionados con la producción y comercialización agrícola, mediante el uso de estructuras de control, métodos, validación de entradas y características modernas del lenguaje.

## Funcionalidades

### 1. Registrar cosecha

Permite seleccionar un producto agrícola, ingresar el volumen de producción, establecer el origen y definir el destino comercial.

**Productos disponibles:**

| Producto | Precio base por kilogramo | IVA |
|---|---:|---:|
| Papa Capira | $2.500 COP | 0 % |
| Café Pergamino | $15.000 COP | 5 % |
| Aguacate Hass | $8.000 COP | 0 % |

**Tarifas de transporte por tonelada:**

| Departamento de origen | Costo base |
|---|---:|
| Boyacá | $120.000 COP |
| Huila | $180.000 COP |
| Antioquia | $150.000 COP |

El sistema calcula el subtotal bruto, el IVA, la retención en la fuente, el costo del transporte y la utilidad neta estimada. Cuando el destino es la exportación, se aplica un recargo logístico del 15 % sobre el flete.

### 2. Glosario de términos

Permite consultar las definiciones de los siguientes conceptos:

- Punto de equilibrio.
- Retención en la fuente.
- Arancel logístico.

Las definiciones se presentan mediante **Text Blocks** de Java.

### 3. Simulador de punto de equilibrio

Calcula la cantidad mínima de unidades que deben venderse para cubrir los costos fijos, considerando el precio de venta y el costo variable por unidad.

### 4. Configuración

Permite registrar el nombre del agricultor y el nombre de la finca. Esta información se conserva durante la ejecución y se utiliza en el reporte final.

### 5. Generar reporte y salir

Presenta un resumen con los datos del agricultor, la finca y los resultados de la última cosecha registrada. Después, muestra el mensaje de despedida y finaliza la aplicación.

## Fórmulas utilizadas

### Conversión de toneladas a kilogramos

```text
Kilogramos = Toneladas × 1000
```

### Subtotal bruto

```text
Subtotal = Kilogramos × Precio por kilogramo
```

### Impuesto sobre las ventas (IVA)

```text
IVA = Subtotal × Tasa de IVA
```

### Retención en la fuente

- Si el subtotal es superior a $4.000.000 COP, se aplica una tasa del 2,5 %.
- Si el subtotal es igual o inferior a $4.000.000 COP, se aplica una tasa del 1,5 %.

```text
Retención = Subtotal × Tasa de retención
```

### Costo de transporte

```text
Flete base = Toneladas × Tarifa por tonelada
```

Para exportación:

```text
Flete final = Flete base × 1,15
```

### Utilidad neta estimada

```text
Utilidad = Subtotal - IVA - Retención - Flete final
```

### Punto de equilibrio

```text
Punto de equilibrio = Costos fijos / (Precio de venta - Costo variable)
```

El resultado se redondea hacia arriba para determinar el número entero de unidades necesarias para cubrir los costos.

> **Nota:** Las tarifas y tasas tributarias corresponden a los parámetros definidos en el enunciado académico. No constituyen asesoría tributaria ni una verificación de las tarifas vigentes.

## Tecnologías y conceptos aplicados

- **Java 25:** lenguaje de programación utilizado.
- **Scanner:** lectura de datos ingresados por el usuario.
- **Inferencia de tipos (`var`):** declaración de variables locales.
- **Switch expressions:** selección de productos, precios, tasas y tarifas.
- **Bucles `while` y `do-while`:** control del menú y validación de entradas.
- **Condicionales:** aplicación de reglas de negocio.
- **Métodos:** modularización y reutilización del código.
- **Text Blocks (`"""`):** presentación de reportes y definiciones.
- **NumberFormat y Locale:** formato de valores monetarios.
- **Git y GitHub:** control de versiones y publicación del proyecto.

##  Requisitos previos

Para ejecutar el proyecto se necesita:

- Tener instalado el **JDK 25**.
- Disponer de una terminal o un entorno de desarrollo compatible con Java 25.
- Tener Git instalado si se desea administrar el proyecto mediante control de versiones.

El proyecto utiliza la sintaxis de archivo fuente compacto de Java, que permite declarar `void main()` sin la estructura tradicional de una clase explícita.

##  Instalación y ejecución

### 1. Clonar el repositorio

```bash
git clone URL_DE_TU_REPOSITORIO
```

Reemplaza `URL_DE_TU_REPOSITORIO` por la dirección real del repositorio en GitHub.

### 2. Ingresar al directorio del proyecto

```bash
cd SIPA
```

### 3. Verificar la versión de Java

```bash
java --version
```

Comprueba que el entorno utilice Java 25.

### 4. Ejecutar el programa

Si el archivo principal se llama `SIPA.java`, ejecuta:

```bash
java SIPA.java
```

La aplicación mostrará el menú principal y permitirá interactuar con las diferentes funcionalidades.

##  Estructura del proyecto

```text
SIPA/
├── SIPA.java
├── README.md
└── .gitignore
```

- **`SIPA.java`:** contiene la lógica del sistema, los cálculos financieros, las validaciones y la presentación de resultados.
- **`README.md`:** documentación general del proyecto.
- **`.gitignore`:** define los archivos y directorios que Git debe excluir del seguimiento.

##  Validaciones implementadas

El programa contempla las siguientes validaciones:

- Las opciones del menú deben ser números enteros dentro del rango permitido.
- Las entradas numéricas deben ser válidas y finitas.
- No se aceptan cantidades negativas.
- Las entradas inválidas vuelven a solicitarse al usuario.
- El precio de venta debe ser superior al costo variable para calcular el punto de equilibrio.
- Los campos del nombre del agricultor y de la finca no pueden quedar vacíos.

##  Limitaciones actuales

- Los datos se conservan únicamente durante la ejecución del programa.
- El reporte final presenta los resultados de la última cosecha registrada.
- Los cálculos se basan en los productos, precios, tarifas y tasas definidos para el ejercicio.
- No existe conexión con bases de datos ni servicios externos.
- La utilidad calculada es una estimación académica y no contempla otros posibles costos de producción o comercialización.

##  Mejoras futuras

- Incorporar almacenamiento persistente de cosechas.
- Permitir consultar el historial de registros.
- Agregar más productos agrícolas y regiones.
- Incorporar reportes exportables a CSV o PDF.
- Implementar pruebas automatizadas.
- Evaluar el uso de `BigDecimal` para operaciones monetarias.
- Desarrollar una interfaz gráfica o una aplicación web.

##  Autor

**Brayan Steven Pérez Hernández**

Proyecto académico DevSeniorCode - Unidad 1: SIPA, Sistema de Inteligencia de Precios Agrarios.

##  Licencia

Este proyecto se desarrolla con fines académicos. La licencia de distribución deberá definirse explícitamente si se desea autorizar su reutilización o modificación por terceros.
