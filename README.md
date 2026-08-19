Gestión de Abonos

Aplicación de escritorio en Java + Swing que gestiona el ciclo completo de producción de abono orgánico: desde la recepción de la materia prima (residuos orgánicos y aditivos), pasando por la producción y el empaque en bultos, hasta la venta al cliente y la generación de la factura.

Proyecto académico desarrollado para la asignatura de Programación II (UPTC).

Características
Materia prima: registro de entradas de residuos orgánicos (descontando residuos despreciables) y gestión de aditivos (crear, editar, eliminar y reabastecer).
Producción: cálculo de los kilos de abono que se pueden producir según el aditivo seleccionado y su relación litros/kilo, con validación de existencias antes de descontar insumos.
Empaque y stock: definición de presentaciones de bulto (peso y precio unitario), cálculo de bultos posibles a partir del abono disponible y control de stock.
Clientes: alta, edición, eliminación y búsqueda de clientes por número telefónico.
Ventas y facturación: registro de ventas asociadas a un cliente, cálculo del total y generación de un archivo de factura por cada operación.
Persistencia en archivos planos: no requiere base de datos; toda la información se guarda en la carpeta Recursos/.
Arquitectura

El proyecto sigue una arquitectura por capas dentro del paquete co.uptc.edu:

src/co/uptc/edu/
├── presentacion/          # Capa de presentación
│   ├── RunAbonos.java     # Clase principal (main)
│   └── GUI/               # Ventanas Swing (menús, formularios, tablas, renders)
├── logica/                # Capa de negocio
│   ├── modelo/            # Entidades: MateriaPrima, Aditivo, ResiduosOrganicos,
│   │                      #            Abono, Bulto, Cliente, Factura
│   └── control/           # Operaciones: Abono, Aditivo, Bulto, Cliente,
│                          #              ResiduosOrganicos, Venta
└── persistencia/          # Capa de datos
    ├── DAO*.java          # Lectura/escritura de cada entidad
    └── utilidades/        # Archivo (E/S de texto) y Propiedad (properties)

MateriaPrima es una clase abstracta de la que heredan Aditivo y ResiduosOrganicos.

Almacenamiento de datos

Los datos viven en la carpeta Recursos/, en archivos de texto separados por comas:

Archivo	Contenido	Formato
ResiduosOrganicos.efu	Materia orgánica disponible	ID,NOMBRE,CANTIDAD,VARIACION,RESIDUOSDESPRESIABLES
Aditivos.efu	Catálogo de aditivos	ID,NOMBRE,VARIACION,PORCENTAJECONCENTRACION,NUMLITROSPORKILO,CANTIDAD
Abono.efu	Abono producido en existencia	ID,CANTIDAD
Bultos.efu	Presentaciones y stock	VARIACION,STOCK,PRECIOUNITARIO
Clientes.cli	Clientes registrados	ID,NOMBRE,TELEFONO,NUMEROVENTAS
facturas/	Una factura por venta	id, fecha, cliente, bultos, total

Las rutas son relativas al directorio de ejecución, por lo que la aplicación debe iniciarse desde la raíz del proyecto (donde se encuentra la carpeta Recursos/).

Requisitos
JDK 17 o superior (el proyecto está configurado para JavaSE-17).
Entorno de escritorio con soporte gráfico (Swing).
Opcional: Eclipse IDE, ya que el repositorio incluye los archivos .project y .classpath.
Instalación y ejecución
Desde la línea de comandos
bash
git clone https://github.com/DajerlyMoreno/Gestion-abonos.git
cd Gestion-abonos

# Compilar
mkdir -p bin
javac -encoding ISO-8859-1 -d bin $(find src -name "*.java")

# Ejecutar (desde la raíz del proyecto, para que encuentre Recursos/)
java -cp bin co.uptc.edu.presentacion.RunAbonos

En Windows (PowerShell):

powershell
mkdir bin
javac -encoding ISO-8859-1 -d bin (Get-ChildItem -Recurse -Filter *.java src).FullName
java -cp bin co.uptc.edu.presentacion.RunAbonos
Desde Eclipse
File → Import → Existing Projects into Workspace.
Selecciona la carpeta del repositorio.
Verifica que el JRE del proyecto sea Java 17.
Ejecuta RunAbonos.java como Java Application.
Uso

Al iniciar se abre el Menú Principal con tres accesos:

Clientes — consultar, crear, editar y eliminar clientes.
Stock — registrar entradas de residuos orgánicos y aditivos, producir abono y administrar los bultos.
Ventas — seleccionar un cliente, agregar bultos a la venta y generar la factura.

Flujo típico de trabajo:

Registrar la entrada de residuos orgánicos y la cantidad de aditivo disponible.
Producir abono indicando el aditivo y los kilos a procesar (el sistema valida que alcancen los insumos).
Empacar el abono en bultos según la presentación deseada.
Registrar la venta a un cliente y emitir la factura correspondiente.
Estructura del repositorio
Gestion-abonos/
├── src/                # Código fuente Java
├── Recursos/           # Datos de la aplicación
│   ├── *.efu, *.cli    # Archivos de datos
│   ├── facturas/       # Facturas generadas
│   └── iconos/         # Imágenes e iconos de la interfaz
├── .classpath          # Configuración de Eclipse
├── .project
└── README.md
Notas y limitaciones conocidas
La persistencia se realiza sobre archivos planos, sin control de concurrencia ni transacciones.
Los archivos fuente utilizan codificación ISO-8859-1; conviene mantenerla al compilar para evitar problemas con las tildes.
El proyecto no incluye pruebas automatizadas ni gestor de dependencias (Maven/Gradle).
Autores

Proyecto desarrollado por estudiantes de la Universidad Pedagógica y Tecnológica de Colombia (UPTC).

Repositorio: DajerlyMoreno/Gestion-abonos
