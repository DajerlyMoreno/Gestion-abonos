# Gestión de Abonos

Aplicación de escritorio desarrollada en **Java + Swing** que gestiona el ciclo completo de producción de abono orgánico: desde la recepción de la materia prima (residuos orgánicos y aditivos), pasando por la producción y el empaque en bultos, hasta la venta al cliente y la generación de la factura.

Proyecto académico desarrollado para la asignatura de **Programación II** de la **Universidad Pedagógica y Tecnológica de Colombia (UPTC)**.

---

## Características

* **Materia prima:** registro de entradas de residuos orgánicos, descontando residuos despreciables, y gestión de aditivos (crear, editar, eliminar y reabastecer).
* **Producción:** cálculo de los kilos de abono que se pueden producir según el aditivo seleccionado y su relación litros/kilo, con validación de existencias antes de descontar insumos.
* **Empaque y stock:** definición de presentaciones de bulto (peso y precio unitario), cálculo de bultos posibles a partir del abono disponible y control de stock.
* **Clientes:** alta, edición, eliminación y búsqueda de clientes por número telefónico.
* **Ventas y facturación:** registro de ventas asociadas a un cliente, cálculo del total y generación de un archivo de factura por cada operación.
* **Persistencia en archivos planos:** no requiere base de datos; toda la información se guarda en la carpeta `Recursos/`.

---

## Arquitectura

El proyecto sigue una arquitectura por capas dentro del paquete `co.uptc.edu`:

```text
src/co/uptc/edu/
├── presentacion/          # Capa de presentación
│   ├── RunAbonos.java     # Clase principal (main)
│   └── GUI/               # Ventanas Swing
│       ├── Menús
│       ├── Formularios
│       ├── Tablas
│       └── Renders
│
├── logica/                # Capa de negocio
│   ├── modelo/            # Entidades
│   │   ├── MateriaPrima
│   │   ├── Aditivo
│   │   ├── ResiduosOrganicos
│   │   ├── Abono
│   │   ├── Bulto
│   │   ├── Cliente
│   │   └── Factura
│   │
│   └── control/           # Operaciones
│       ├── Abono
│       ├── Aditivo
│       ├── Bulto
│       ├── Cliente
│       ├── ResiduosOrganicos
│       └── Venta
│
└── persistencia/          # Capa de datos
    ├── DAO*.java          # Lectura/escritura de cada entidad
    └── utilidades/
        ├── Archivo.java   # Entrada/salida de texto
        └── Propiedad.java # Manejo de properties
```

`MateriaPrima` es una clase abstracta de la que heredan `Aditivo` y `ResiduosOrganicos`.

---

## Almacenamiento de datos

Los datos se almacenan en la carpeta `Recursos/`, utilizando archivos de texto separados por comas.

| Archivo                 | Contenido                     | Formato                                                                 |
| ----------------------- | ----------------------------- | ----------------------------------------------------------------------- |
| `ResiduosOrganicos.efu` | Materia orgánica disponible   | `ID,NOMBRE,CANTIDAD,VARIACION,RESIDUOSDESPRESIABLES`                    |
| `Aditivos.efu`          | Catálogo de aditivos          | `ID,NOMBRE,VARIACION,PORCENTAJECONCENTRACION,NUMLITROSPORKILO,CANTIDAD` |
| `Abono.efu`             | Abono producido en existencia | `ID,CANTIDAD`                                                           |
| `Bultos.efu`            | Presentaciones y stock        | `VARIACION,STOCK,PRECIOUNITARIO`                                        |
| `Clientes.cli`          | Clientes registrados          | `ID,NOMBRE,TELEFONO,NUMEROVENTAS`                                       |
| `facturas/`             | Una factura por venta         | `id`, `fecha`, `cliente`, `bultos`, `total`                             |

> **Nota:** Las rutas son relativas al directorio de ejecución. Por esta razón, la aplicación debe iniciarse desde la raíz del proyecto, donde se encuentra la carpeta `Recursos/`.

---

## Requisitos

* **JDK 17** o superior.
* Entorno de escritorio con soporte gráfico para **Swing**.
* **Eclipse IDE** (opcional), ya que el repositorio incluye los archivos `.project` y `.classpath`.

---

## Instalación y ejecución

### Desde la línea de comandos

Clonar el repositorio:

```bash
git clone https://github.com/DajerlyMoreno/Gestion-abonos.git
cd Gestion-abonos
```

#### Compilar

```bash
mkdir -p bin
javac -encoding ISO-8859-1 -d bin $(find src -name "*.java")
```

#### Ejecutar

La aplicación debe ejecutarse desde la raíz del proyecto para que pueda encontrar la carpeta `Recursos/`.

```bash
java -cp bin co.uptc.edu.presentacion.RunAbonos
```

### Windows (PowerShell)

```powershell
mkdir bin
javac -encoding ISO-8859-1 -d bin (Get-ChildItem -Recurse -Filter *.java src).FullName
java -cp bin co.uptc.edu.presentacion.RunAbonos
```

---

## Desde Eclipse

1. Seleccionar **File → Import → Existing Projects into Workspace**.
2. Seleccionar la carpeta del repositorio.
3. Verificar que el JRE del proyecto sea **Java 17**.
4. Ejecutar `RunAbonos.java` como **Java Application**.

---

## Uso

Al iniciar la aplicación se muestra el **Menú Principal**, que cuenta con tres accesos principales:

### Clientes

Permite:

* Consultar clientes.
* Crear nuevos clientes.
* Editar información.
* Eliminar clientes.
* Buscar clientes por número telefónico.

### Stock

Permite:

* Registrar entradas de residuos orgánicos.
* Gestionar los aditivos.
* Producir abono.
* Administrar las presentaciones de los bultos.
* Consultar y controlar el stock.

### Ventas

Permite:

* Seleccionar un cliente.
* Agregar bultos a la venta.
* Calcular el total.
* Generar la factura correspondiente.

---

## Flujo típico de trabajo

1. Registrar la entrada de residuos orgánicos y la cantidad de aditivo disponible.
2. Producir abono indicando el aditivo y los kilos a procesar.
3. El sistema valida que existan suficientes insumos antes de realizar el proceso.
4. Empacar el abono disponible en bultos según la presentación deseada.
5. Registrar la venta asociada a un cliente.
6. Generar la factura correspondiente a la operación.

---

## Estructura del repositorio

```text
Gestion-abonos/
├── src/                    # Código fuente Java
│
├── Recursos/               # Datos de la aplicación
│   ├── *.efu               # Archivos de datos
│   ├── *.cli               # Archivos de clientes
│   ├── facturas/           # Facturas generadas
│   └── iconos/             # Imágenes e iconos de la interfaz
│
├── .classpath              # Configuración de Eclipse
├── .project                # Configuración del proyecto Eclipse
└── README.md               # Documentación del proyecto
```

---

## Notas y limitaciones conocidas

* La persistencia se realiza mediante **archivos planos**, sin control de concurrencia ni transacciones.
* Los archivos fuente utilizan codificación **ISO-8859-1**. Se recomienda mantener esta codificación durante la compilación para evitar problemas con caracteres especiales y tildes.
* El proyecto no incluye **pruebas automatizadas**.
* El proyecto no utiliza un gestor de dependencias como **Maven** o **Gradle**.
* Las rutas de los archivos de datos son relativas al directorio desde el que se ejecuta la aplicación.

---

## Tecnologías utilizadas

* **Java 17**
* **Java Swing**
* **Programación Orientada a Objetos (POO)**
* **Arquitectura por capas**
* **Persistencia mediante archivos planos**
* **Eclipse IDE**

---

## Autores

Proyecto desarrollado por estudiantes de la **Universidad Pedagógica y Tecnológica de Colombia (UPTC)**.

### Repositorio

[DajerlyMoreno/Gestion-abonos en GitHub](https://github.com/DajerlyMoreno/Gestion-abonos?utm_source=chatgpt.com)
