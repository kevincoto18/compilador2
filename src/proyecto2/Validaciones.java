/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kevin
 */
public class Validaciones {

    private TablaVariables tabla;
    public int CorcherteCierreWhile = 0;
    public int CorcherteAperturaWhile = 0;
    public int CorcherteCierreIf = 0;
    public int CorcherteAperturaIf = 0;

    String archivoErrores;

    public Validaciones(TablaVariables tabla, String archivoErrores) {
        this.tabla = tabla;
        this.archivoErrores = archivoErrores;
        //  redirigirprints(archivoErrores); // Redirigir los println() al txt errores.
    }

    public boolean ValidarComentario(String Linea, int contador) {
        if (Linea.startsWith("//") || Linea.contains("//")) {
            return true;
        } else if (Linea.startsWith("/")) {
            System.out.println("Error, en la línea " + formatearContador(contador) + " la sintaxis de comentario esta mal escrita");
            return false;
        } else {
            return false;
        }

    }

    public boolean ValidarEstructuraWhile(String Linea, int contador, boolean DentroWhile, int contenido) {

        // Verificar si la línea contiene la estructura while
        if (Linea.startsWith("while")) {
            // Validar la primera línea del while
            if (!ValidarPrimeraLineaWhile(Linea, contador)) {
                return false;
            }
           // System.out.println(" - Se detecto la primera linea del while.");
            DentroWhile = true;
            CorcherteAperturaWhile++;
        }

        // Verificar si estamos dentro del bloque while
        if (DentroWhile) {
            // Verificar si la línea contiene una llave de cierre
            if (Linea.contains("}")) {
                CorcherteCierreWhile++;
               // System.out.println(" Salida del while");
                // System.out.println(" Contenido: " + contenido);
                if (contenido == 1) {
                    System.out.println("----- ERROR -----, La estructura del While no puede estar vacia");
                    return false;
                }

                return false;
            }

            // Si no es una línea que inicie con "while",  dentro del bloque while
            if (!Linea.startsWith("while")) {
                if (!Linea.isBlank()) {
                   // System.out.println(" - Dentro del bloque while.");
                }

            }

        }

        return DentroWhile;
    }

    public boolean ValidarPrimeraLineaWhile(String Linea, int contador) {
        // Validar si la línea contiene la palabra "while"
        if (!Linea.contains("while")) {
            System.out.println("----- ERROR -----, en la línea " + formatearContador(contador) + ", falta el `while` al principio de la expresión.");
            return false;
        }

        // Validar si la línea contiene paréntesis
        if (!Linea.contains("(") || !Linea.contains(")")) {
            System.out.println("----- ERROR -----, en la línea " + formatearContador(contador) + " falta algún paréntesis en la expresión.");
            return false;
        }

        // Validar si la línea termina con una llave de apertura
        if (!Linea.endsWith("{")) {
            System.out.println("----- ERROR -----, en la línea " + formatearContador(contador) + " falta el '{' al final de la expresión.");
            return false;
        }

        return true;
    }

    public boolean ValidarEstructuraIf(String Linea, int contador, boolean DentroIf, boolean DentroElse, int contenidoIf) {

        // Verificar si la línea contiene la estructura if
        if (Linea.startsWith("if")) {
            // Validar la primera línea del if
            if (!ValidarPrimeraLineIf(Linea, contador)) {
                return false;
            }
            //System.out.println(" - Se detecto la primera linea del If.");
            DentroIf = true;
            CorcherteAperturaIf++;
        }
        if (Linea.contains("else")) {
            if (!ValidarPrimeraLineElse(Linea, contador)) {
                return false;
            }
            //System.out.println(" - Se detecto la primera linea del Else");
            DentroElse = true;
            CorcherteAperturaIf++;
        }

        // Verificar si estamos dentro del bloque If
        if (DentroIf) {
            // Verificar si la línea contiene una llave de cierre
            if (Linea.contains("}")) {
                CorcherteCierreIf++;
              //  System.out.println(" Salida del If");
                if (contenidoIf == 1) {
                    System.out.println("----- ERROR -----, La estructura del If no puede estar vacia");
                    return false;
                }
                if (!DentroElse) {
                    return false;
                }
            }

            // Si no es una línea que inicie con "if",  dentro del bloque if
            if (!Linea.startsWith("if") && !Linea.contains("else")) {
                if (!Linea.isBlank()) {
                //    System.out.println(" - Dentro del bloque If.");
                }

            }

        }

        return DentroIf;
    }

    public boolean ValidarPrimeraLineIf(String Linea, int contador) {
        // Validar si la línea contiene la palabra "if"
        if (!Linea.contains("if")) {
            System.out.println("----- ERROR -----, en la línea " + formatearContador(contador) + ", falta el `if` al principio de la expresión.");
            return false;
        }

        // Validar si la línea contiene paréntesis
        if (!Linea.contains("(") || !Linea.contains(")")) {
            System.out.println("----- ERROR -----, en la línea " + formatearContador(contador) + " falta algún paréntesis en la expresión.");
            return false;
        }

        // Validar si la línea termina con una llave de apertura
        if (!Linea.endsWith("{")) {
            System.out.println("----- ERROR -----, en la línea " + formatearContador(contador) + " falta el '{' al final de la expresión.");
            return false;
        }

        return true;
    }

    public boolean ValidarPrimeraLineElse(String Linea, int contador) {
        // Validar si la línea contiene la palabra "else"
        if (!Linea.contains("else")) {
            System.out.println("----- ERROR -----, en la línea " + formatearContador(contador) + ", falta el `else` al principio de la expresión.");
            return false;
        }
        // Validar si la línea termina con una llave de apertura
        if (!Linea.endsWith("{")) {
            System.out.println("----- ERROR -----, en la línea " + formatearContador(contador) + " falta el '{' al final de la expresión.");
            return false;
        }

        return true;
    }

    public boolean ValidarScanner(String Linea, int contador) {

        if (!Linea.startsWith("static")) {
            System.out.println("----- ERROR -----, en la linea " + formatearContador(contador) + " falta el `static` al principio de la expresion.");
            return false;
        }
        if (!Linea.contains("new") || !Linea.contains("=")) {
            System.out.println("----- ERROR -----, en la linea " + formatearContador(contador) + " falta `= new` en la expresion.");
            return false;
        }

        // Contar el número de veces que aparece "Scanner" en la línea
        long conteoScanner = Linea.toLowerCase().replaceAll("[^a-z]", "").split("scanner", -1).length - 1;

        if (conteoScanner != 2) {
            System.out.println("----- ERROR -----, en la línea " + formatearContador(contador) + " falta la declaración exacta de dos instancias de `Scanner`.");
            return false;
        }
        Pattern pattern = Pattern.compile("new\\s+Scanner");

        // Crea un objeto Matcher para buscar coincidencias
        Matcher matcher = pattern.matcher(Linea);

        // Comprueba si se encuentra la coincidencia "new Scanner"
        if (!matcher.find()) {
            System.out.println("----- ERROR -----, en la linea " + formatearContador(contador) + " falta Scanner en la expresion.");
            return false;
        }
        if (!Linea.contains("(") || !Linea.contains(")")) {
            System.out.println("----- ERROR -----, en la linea " + formatearContador(contador) + " falta parentesis en la expresion.");
            return false;
        }
        if (!Linea.contains("System.in")) {
            System.out.println("----- ERROR -----, en la linea " + formatearContador(contador) + " no contiene 'System.in' como parametro de inicializacion");
            return false;
        }
        // Verificar si la línea contiene un punto y coma al final
        if (!Linea.endsWith(";")) {
            System.out.println("----- ERROR -----, en la linea " + formatearContador(contador) + " falta el ';' al final de la expresion.");
            return false;
        }

        Pattern patron = Pattern.compile("Scanner\\s+(\\w+)\\s*=\\s*.*");
        Matcher variable = patron.matcher(Linea);
        String nombreVariable = "N/A";
        if (variable.find()) {
            nombreVariable = variable.group(1);
        } else {
            System.out.println("----- ERROR -----, en la linea " + formatearContador(contador) + " no se inicializo el Scanner");
            return false;
        }

        ValidarIdentificador(nombreVariable, contador);
        // Agregar la variable a la tabla de símbolos
        tabla.agregarVariable("Scanner", nombreVariable, "Scanner");

        return true;
    }

    public boolean ValidarPrintln(String Linea, int contador) {

        if (!Linea.toLowerCase().startsWith("println")) {
            System.out.println("----- ERROR -----, en la línea " + formatearContador(contador) + " falta el `println` al principio de la expresión.");
            return false;
        }

        // Verificar si la línea contiene paréntesis
        if (!Linea.contains("(") || !Linea.contains(")")) {
            System.out.println("----- ERROR -----, en la línea " + formatearContador(contador) + " falta algún paréntesis en la expresión.");
            return false;
        }

        // Extraer el contenido entre paréntesis
        String contenido = Linea.substring(Linea.indexOf("(") + 1, Linea.lastIndexOf(")")).trim();

        // Contar las comillas dobles dentro del contenido
        long conteoComillasDobles = contenido.chars().filter(ch -> ch == '"').count();

        // Verificar que haya al menos dos comillas dobles
        if (conteoComillasDobles < 2) {
            System.out.println("----- ERROR -----, en la línea " + formatearContador(contador) + " falta al menos una comilla doble dentro de los paréntesis.");
            return false;
        }

        // Verificar si la línea termina con punto y coma
        if (!Linea.endsWith(";")) {
            System.out.println("----- ERROR -----, en la línea " + formatearContador(contador) + " falta el ';' al final de la expresión.");
            return false;
        }
        return true;
    }

    //---------------------------------------Validaciones de tipos de datos --------------------------------------------------------------------------------------------------
    public boolean ValidarINT(String linea, int contador) {
        try {
            String valorAsignado = "N/A"; 

          
            linea = linea.trim();

            // Verificar si la línea comienza con "int" 
            if (!linea.startsWith("int")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Java es sensible a mayúsculas y minúsculas. ¿Quisiste decir 'int'?");
                return false;
            }

            // Dividir la línea en partes usando espacios en blanco como separadores
            String[] partes = linea.substring(0, linea.length() - 1).split(" ");
            
            // Validar el identificador
            if (!ValidarIdentificador(partes[1], contador)) {
                return false;
            }

            // Verificar si hay suficientes partes (deben ser al menos 2: "int" y el nombre de la variable)
            if (partes.length < 2 && partes.length > 4) {
                System.out.println("Error en línea " + formatearContador(contador) + ": La declaración de variables debe contener un nombre válido.");
                return false;
            }

            // Verificar si la línea contiene un punto y coma al final
            if (!linea.endsWith(";")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Falta el ';' al final de la expresión.");
                return false;
            }

            // Verificar si hay asignación de valor
            if (partes.length >= 3) {
                // Utilizar una expresión regular para encontrar la asignación
                Pattern pattern = Pattern.compile("=(.*?);");
                Matcher matcher = pattern.matcher(linea);
                if (matcher.find()) {
                    valorAsignado = matcher.group(1).trim();

                    // Verificar si valorAsignado contiene solo números
                    if (!valorAsignado.matches("^-?[0-9 ]+$")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Despues del signo '=' se requieren solo numeros.");
                        return false;
                    }
                }
            }

            // Agregar la variable a la tabla de símbolos
            tabla.agregarVariable("int", partes[1], valorAsignado);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean ValidarChar(String linea, int contador) {
        try {
            String valorAsignado = "N/A"; 

            // Limpiar y estandarizar la línea de código
            linea = linea.trim();

            // Verificar si la línea comienza con "int" (case-sensitive)
            if (!linea.startsWith("char")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Java es sensible a mayúsculas y minúsculas. ¿Quisiste decir 'char'?");
                return false;
            }

            // Dividir la línea en partes usando espacios en blanco como separadores
            String[] partes = linea.substring(0, linea.length() - 1).split(" ");

            // Validar el identificador
            if (!ValidarIdentificador(partes[1], contador)) {
                return false;
            }

            // Verificar si hay suficientes partes (deben ser al menos 2: "int" y el nombre de la variable)
            if (partes.length < 2) {
                System.out.println("Error en línea " + formatearContador(contador) + ": La declaración de variables debe contener un nombre válido.");
                return false;
            }

            // Verificar si la línea contiene un punto y coma al final
            if (!linea.endsWith(";")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Falta el ';' al final de la expresión.");
                return false;
            }
            if (linea.contains("=")) {
                // Verificar si hay asignación de valor
                if (partes.length >= 3) {
                    // Utilizar una expresión regular para encontrar la asignación
                    Pattern pattern = Pattern.compile("'\\s*(\\S)\\s*'");
                    Matcher matcher = pattern.matcher(linea);
                    if (matcher.find()) {
                        valorAsignado = matcher.group(1).trim();

                        // Verificar si valorAsignado contiene solo un carácter
                        if (valorAsignado.length() != 1) {
                            System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' se requiere un solo carácter.");
                            return false;
                        }
                    } else {
                        System.out.println("Error en línea " + formatearContador(contador) + ": verifica la asignacion de valor \n Verifica comillas simples o que sea un solo caracter");
                        return false;
                    }
                }
            }

            // Agregar la variable a la tabla de símbolos
            tabla.agregarVariable("char", partes[1], valorAsignado);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean ValidarString(String linea, int contador) {
        try {
            String valorAsignado = "N/A"; 

            // Limpiar y estandarizar la línea de código
            linea = linea.trim();
            if (linea.contains("static") || linea.contains("String args[]") || linea.contains("main")) {
                return true;
            }
            // Verificar si la línea comienza con "int" (case-sensitive)
            if (!linea.startsWith("String")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Java es sensible a mayúsculas y minúsculas. ¿Quisiste decir 'String'?");
                return false;
            }

            // Dividir la línea en partes usando espacios en blanco como separadores
            String[] partes = linea.substring(0, linea.length() - 1).split(" ");

            // Validar el identificador
            if (!ValidarIdentificador(partes[1], contador)) {
                return false;
            }

            // Verificar si hay suficientes partes (deben ser al menos 2: "int" y el nombre de la variable)
            if (partes.length < 2) {
                System.out.println("Error en línea " + formatearContador(contador) + ": La declaración de variables debe contener un nombre válido.");
                return false;
            }

            // Verificar si la línea contiene un punto y coma al final
            if (!linea.endsWith(";")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Falta el ';' al final de la expresión.");
                return false;
            }
            if (linea.contains("=")) {
                // Verificar si hay asignación de valor
                if (partes.length >= 3) {
                    // Utilizar una expresión regular para encontrar la asignación
                    Pattern pattern = Pattern.compile("'\\s*(\\S)\\s*'");
                    Matcher matcher = pattern.matcher(linea);
                    if (matcher.find()) {
                        valorAsignado = matcher.group(1).trim();

                    } else {
                        System.out.println("Error en línea " + formatearContador(contador) + ": verifica la asignacion de valor \n Verifica comillas simples o que sea un solo caracter");
                        return false;
                    }
                }
            }

            // Agregar la variable a la tabla de símbolos
            tabla.agregarVariable("String", partes[1], valorAsignado);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Validacion del Byte
    public boolean ValidarByte(String linea, int contador) {
        try {
            String valorAsignado = "N/A"; 

            // Limpiar y estandarizar la línea de código
            linea = linea.trim();

            // Verificar si la línea comienza con "int" (case-sensitive)
            if (!linea.startsWith("byte")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Java es sensible a mayúsculas y minúsculas. ¿Quisiste decir 'byte'?");
                return false;
            }

            // Dividir la línea en partes usando espacios en blanco como separadores
            String[] partes = linea.substring(0, linea.length() - 1).split(" ");

            // Validar el identificador
            if (!ValidarIdentificador(partes[1], contador)) {
                return false;
            }

            // Verificar si hay suficientes partes (deben ser al menos 2: "int" y el nombre de la variable)
            if (partes.length < 2) {
                System.out.println("Error en línea " + formatearContador(contador) + ": La declaración de variables debe contener un nombre válido.");
                return false;
            }

            // Verificar si la línea contiene un punto y coma al final
            if (!linea.endsWith(";")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Falta el ';' al final de la expresión.");
                return false;
            }

            // Verificar si hay asignación de valor
            if (partes.length >= 3) {
                // Utilizar una expresión regular para encontrar la asignación
                Pattern pattern = Pattern.compile("=(.*?);");
                Matcher matcher = pattern.matcher(linea);
                if (matcher.find()) {
                    valorAsignado = matcher.group(1).trim();

                    // Verificar si valorAsignado contiene solo números
                    if (!valorAsignado.matches("^-?[0-9 ]+$")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Despues del signo '=' se requieren solo numeros.");
                        return false;
                    }
                    int numero = Integer.parseInt(valorAsignado);
                    if (numero > 127 || numero < -128) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": por desbordamiento, (minimo: -128 , maximo: 127)");
                        return false;
                    }

                }
            }

            // Agregar la variable a la tabla de símbolos
            tabla.agregarVariable("byte", partes[1], valorAsignado);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Validacion del Short
    public boolean ValidarShort(String linea, int contador) {
        try {
            String valorAsignado = "N/A"; 

            // Limpiar y estandarizar la línea de código
            linea = linea.trim();

            // Verificar si la línea comienza con "short" (case-sensitive)
            if (!linea.startsWith("short")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Java es sensible a mayúsculas y minúsculas. ¿Quisiste decir 'short'?");
                return false;
            }

            // Dividir la línea en partes usando espacios en blanco como separadores
            String[] partes = linea.substring(0, linea.length() - 1).split(" ");

            // Validar el identificador
            if (!ValidarIdentificador(partes[1], contador)) {
                return false;
            }

            // Verificar si hay suficientes partes (deben ser al menos 2: "int" y el nombre de la variable)
            if (partes.length < 2) {
                System.out.println("Error en línea " + formatearContador(contador) + ": La declaración de variables debe contener un nombre válido.");
                return false;
            }

            // Verificar si la línea contiene un punto y coma al final
            if (!linea.endsWith(";")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Falta el ';' al final de la expresión.");
                return false;
            }

            // Verificar si hay asignación de valor
            if (partes.length >= 3) {
                // Utilizar una expresión regular para encontrar la asignación
                Pattern pattern = Pattern.compile("=(.*?);");
                Matcher matcher = pattern.matcher(linea);
                if (matcher.find()) {
                    valorAsignado = matcher.group(1).trim();

                    // Verificar si valorAsignado contiene solo números
                    if (!valorAsignado.matches("^-?[0-9 ]+$")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Despues del signo '=' se requieren solo numeros.");
                        return false;
                    }
//                    int numero = Integer.parseInt(valorAsignado);
//                    if(numero > 127 || numero < -128)
//                    {
//                       System.out.println("Error en línea " + formatearContador(contador) + ": por desbordamiento, (minimo: -128 , maximo: 127)");
//                        return false; 
//                    }

                }
            }

            // Agregar la variable a la tabla de símbolos
            tabla.agregarVariable("short", partes[1], valorAsignado);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Validacion del Long
    public boolean ValidarLong(String linea, int contador) {
        try {
            String valorAsignado = "N/A"; 

            // Limpiar y estandarizar la línea de código
            linea = linea.trim();

            // Verificar si la línea comienza con "short" (case-sensitive)
            if (!linea.startsWith("long")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Java es sensible a mayúsculas y minúsculas. ¿Quisiste decir 'long'?");
                return false;
            }

            // Dividir la línea en partes usando espacios en blanco como separadores
            String[] partes = linea.substring(0, linea.length() - 1).split(" ");

            // Validar el identificador
            if (!ValidarIdentificador(partes[1], contador)) {
                return false;
            }

            // Verificar si hay suficientes partes (deben ser al menos 2: "int" y el nombre de la variable)
            if (partes.length < 2) {
                System.out.println("Error en línea " + formatearContador(contador) + ": La declaración de variables debe contener un nombre válido.");
                return false;
            }

            // Verificar si la línea contiene un punto y coma al final
            if (!linea.endsWith(";")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Falta el ';' al final de la expresión.");
                return false;
            }

            // Verificar si hay asignación de valor
            if (partes.length >= 3) {
                // Utilizar una expresión regular para encontrar la asignación
                Pattern pattern = Pattern.compile("=(.*?);");
                Matcher matcher = pattern.matcher(linea);
                if (matcher.find()) {
                    valorAsignado = matcher.group(1).trim();

                    // Verificar si valorAsignado contiene solo números
                    if (!valorAsignado.matches("-?\\d+[lL]$")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Despues del signo '=' se requieren solo numeros y terminar con L");
                        return false;
                    }
                    if (!valorAsignado.endsWith("L") && !valorAsignado.endsWith("l")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Se requiere que al final termine con L o l para indicar que es un Long");
                        return false;
                    }
//                    int numero = Integer.parseInt(valorAsignado);
//                    if(numero > 127 || numero < -128)
//                    {
//                       System.out.println("Error en línea " + formatearContador(contador) + ": por desbordamiento, (minimo: -128 , maximo: 127)");
//                        return false; 
//                    }

                }
            }

            // Agregar la variable a la tabla de símbolos
            tabla.agregarVariable("long", partes[1], valorAsignado);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Validacion del Float
    public boolean ValidarFloat(String linea, int contador) {
        try {
            String valorAsignado = "N/A"; 

            // Limpiar y estandarizar la línea de código
            linea = linea.trim();

            // Verificar si la línea comienza con "short" (case-sensitive)
            if (!linea.startsWith("float")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Java es sensible a mayúsculas y minúsculas. ¿Quisiste decir 'float'?");
                return false;
            }

            // Dividir la línea en partes usando espacios en blanco como separadores
            String[] partes = linea.substring(0, linea.length() - 1).split(" ");

            // Validar el identificador
            if (!ValidarIdentificador(partes[1], contador)) {
                return false;
            }

            // Verificar si hay suficientes partes (deben ser al menos 2: "int" y el nombre de la variable)
            if (partes.length < 2) {
                System.out.println("Error en línea " + formatearContador(contador) + ": La declaración de variables debe contener un nombre válido.");
                return false;
            }

            // Verificar si la línea contiene un punto y coma al final
            if (!linea.endsWith(";")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Falta el ';' al final de la expresión.");
                return false;
            }

            // Verificar si hay asignación de valor
            if (partes.length >= 3) {
                // Utilizar una expresión regular para encontrar la asignación
                Pattern pattern = Pattern.compile("=(.*?);");
                Matcher matcher = pattern.matcher(linea);
                if (matcher.find()) {
                    valorAsignado = matcher.group(1).trim();

                    // Verificar si valorAsignado contiene solo números
                    if (!valorAsignado.matches("^-?\\d+(\\.\\d+)[f]?$")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Despues del signo '=' se requieren solo numeros y terminar con f");
                        return false;
                    }
                    if (!valorAsignado.endsWith("f")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Se requiere que al final termine con f para indicar que es un float");
                        return false;
                    }

                }
            }
            // Agregar la variable a la tabla de símbolos
            tabla.agregarVariable("float", partes[1], valorAsignado);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //Validacion del Double
    public boolean ValidarDouble(String linea, int contador) {
        try {
            String valorAsignado = "N/A"; 

            // Limpiar y estandarizar la línea de código
            linea = linea.trim();

            // Verificar si la línea comienza con "short" (case-sensitive)
            if (!linea.startsWith("double")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Java es sensible a mayúsculas y minúsculas. ¿Quisiste decir 'double'?");
                return false;
            }

            // Dividir la línea en partes usando espacios en blanco como separadores
            String[] partes = linea.substring(0, linea.length() - 1).split(" ");

            // Validar el identificador
            if (!ValidarIdentificador(partes[1], contador)) {
                return false;
            }

            // Verificar si hay suficientes partes (deben ser al menos 2: "int" y el nombre de la variable)
            if (partes.length < 2) {
                System.out.println("Error en línea " + formatearContador(contador) + ": La declaración de variables debe contener un nombre válido.");
                return false;
            }

            // Verificar si la línea contiene un punto y coma al final
            if (!linea.endsWith(";")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Falta el ';' al final de la expresión.");
                return false;
            }

            // Verificar si hay asignación de valor
            if (partes.length >= 3) {
                // Utilizar una expresión regular para encontrar la asignación
                Pattern pattern = Pattern.compile("=(.*?);");
                Matcher matcher = pattern.matcher(linea);
                if (matcher.find()) {
                    valorAsignado = matcher.group(1).trim();

                    // Verificar si valorAsignado contiene solo números
                    if (!valorAsignado.matches("^-?\\d+(\\.\\d+)?$")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Despues del signo '=' se requieren solo numeros.");
                        return false;
                    }

                }
            }
            // Agregar la variable a la tabla de símbolos
            tabla.agregarVariable("double", partes[1], valorAsignado);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    //---------------------------------------Validaciones de tipos de datos --------------------------------------------------------------------------------------------------
    public boolean ValidarBoolean(String linea, int contador) {
        try {
            String valorAsignado = "N/A"; 

            // Limpiar y estandarizar la línea de código
            linea = linea.trim();

            // Verificar si la línea comienza con "int" (case-sensitive)
            if (!linea.startsWith("boolean")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Java es sensible a mayúsculas y minúsculas. ¿Quisiste decir 'boolean'?");
                return false;
            }

            // Dividir la línea en partes usando espacios en blanco como separadores
            String[] partes = linea.substring(0, linea.length() - 1).split(" ");

            // Validar el identificador
            if (!ValidarIdentificador(partes[1], contador)) {
                return false;
            }

            // Verificar si hay suficientes partes (deben ser al menos 2: "int" y el nombre de la variable)
            if (partes.length < 2) {
                System.out.println("Error en línea " + formatearContador(contador) + ": La declaración de variables debe contener un nombre válido.");
                return false;
            }

            // Verificar si la línea contiene un punto y coma al final
            if (!linea.endsWith(";")) {
                System.out.println("Error en línea " + formatearContador(contador) + ": Falta el ';' al final de la expresión.");
                return false;
            }

            // Verificar si hay asignación de valor
            if (partes.length >= 3) {
                // Utilizar una expresión regular para encontrar la asignación
                Pattern pattern = Pattern.compile("=(.*?);");
                Matcher matcher = pattern.matcher(linea);
                if (matcher.find()) {
                    valorAsignado = matcher.group(1).trim();

                    if (!valorAsignado.matches("^(true|false)$")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Despues del signo '=' es permitido solo true o false");
                        return false;
                    }
                }
            }

            // Agregar la variable a la tabla de símbolos
            tabla.agregarVariable("boolean", partes[1], valorAsignado);

            return true;
        } catch (Exception e) {
            return false;
        }
    }

// Metodo para validar la asignacion de variables, solo para comprobar si empieza con una variable ya definida
    public boolean ComprobarPrimeraPalabra(String Linea, int contador) {

        String[] palabras = Linea.split(" ");
        boolean Entra = false;
        boolean conocida = false;
        // Verificar si hay al menos una palabra
        if (palabras.length > 0) {
            String PrimeraPalabra = palabras[0];

            for (VariablesGlobales obj : tabla.obtenerVariables()) {
                if (obj.getNombre().equals(PrimeraPalabra)) {
                    Entra = true;
                    conocida = true;
                }
            }

            if (!Entra) {

                if (Linea.contains("=")) {
                    Entra = true;
                }
                for (TablaSintaxis.Tokens validacion : TablaSintaxis.Tokens.values()) {
                    if (PrimeraPalabra.matches(validacion.patron)) {
                        if (validacion == TablaSintaxis.Tokens.Reservada) {
                            Entra = false;  // Es una palabra reservada pero no ha sido definida, no hay error
                            conocida = true;
                        }
                        if (validacion == TablaSintaxis.Tokens.TipoDato || validacion == TablaSintaxis.Tokens.CorcheteApetura
                                || validacion == TablaSintaxis.Tokens.CorcheteCierre || validacion == TablaSintaxis.Tokens.ParentesisApertura
                                || validacion == TablaSintaxis.Tokens.ParentesisCierre) {
                            Entra = false;  // Es una palabra reservada pero no ha sido definida, no hay error
                            conocida = true;
                        }

                    }
                }
                if (!conocida) {
                    System.out.println("----- ERROR -----, en la linea " + formatearContador(contador) + ", comando desconocido");
                    return false;
                }
            }

        }
        return Entra;
    }

    // Metodo para validar la asignacion de variables
    public boolean ValidarAsignacion(String linea, int contador) {

        String[] palabras = linea.split("\\s+");
        VariablesGlobales VariableEncontrada = null;
        // Verificar si hay al menos una palabra
        if (palabras.length > 0) {
            String tipoDato = palabras[0];  // La primera palabra
            // Validar si existe el identificador definido anteriormente
            boolean identificadorEncontrado = false;
            for (VariablesGlobales obj : tabla.obtenerVariables()) {
                if (obj.getNombre().equals(tipoDato)) {

                    VariableEncontrada = obj;
                    identificadorEncontrado = true;
                }
            }
            if (!identificadorEncontrado) {
                System.out.println("----- ERROR -----, en la linea " + formatearContador(contador) + ", el identificador `" + tipoDato + "` no existe, comprueba si esta definido anteriormente.");
                return false;
            }

            if (!linea.contains("=")) {
                System.out.println("----- ERROR -----, en la linea " + formatearContador(contador) + ", falta el `=` para la asignacion de valores.");
                return false;
            }
        }
        // Verificar si la línea contiene un punto y coma al final
        if (!linea.endsWith(";")) {
            System.out.println("Error en línea " + formatearContador(contador) + ": Falta el ';' al final de la expresión.");
            return false;
        }

        // Utilizar expresión regular para encontrar el contenido entre '=' y ';'
        Pattern pattern = Pattern.compile("=(.*?);");
        Matcher matcher = pattern.matcher(linea);

        if (matcher.find()) {
            String valorAsignado = matcher.group(1).trim();
            ValidarTipoDato(valorAsignado, VariableEncontrada, contador);

        } else {
            System.out.println("----- ERROR -----, no se pudo encontrar el valor asignado en la línea " + formatearContador(contador) + ".");
            return false;
        }

        return false;
    }

    public boolean ValidarTipoDato(String valorAsignado, VariablesGlobales variable, int contador) {
        String tipoDato = variable.getTipo();
        String Scanner = "N/A";

        for (VariablesGlobales obj : tabla.obtenerVariables()) {
            if (obj.getTipo().equals("Scanner")) {
                Scanner = obj.getNombre();
            }
        }

        boolean EsScanner;
        switch (tipoDato) {
            case "char":
                EsScanner = false;
                if (valorAsignado.contains(Scanner)) {
                    EsScanner = true;
                    if (!valorAsignado.contains(".nextLine()")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' para el método de entrada del char se requiere .nextLine()");
                        return false;
                    }
                }
                if (!EsScanner) {
                    // Utilizar una expresión regular para encontrar la asignación
                    Pattern pattern = Pattern.compile("'\\s*(\\S)\\s*'");
                    Matcher matcher = pattern.matcher(valorAsignado);
                    if (matcher.find()) {
                        valorAsignado = matcher.group(1).trim();

                        // Verificar si valorAsignado contiene solo un carácter
                        if (valorAsignado.length() != 1) {
                            System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' se requiere un solo carácter.");
                            return false;
                        }
                    } else {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Verifica la asignación de valor. Verifica comillas simples o que sea un solo carácter.");
                        return false;
                    }
                    tabla.modificarValorVariable(variable.getNombre(), valorAsignado);

                }
                break;

            case "String":
                EsScanner = false;
                if (valorAsignado.contains(Scanner)) {
                    EsScanner = true;
                    if (!valorAsignado.contains(".nextLine()")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' para el método de entrada del String se requiere .nextLine()");
                        return false;
                    }

                    if (EsScanner) {
                        // Verificar si valorAsignado contiene operadores y/o variables
                        if (valorAsignado.matches(".*[+\\-*/].*")) {
                            String[] partes = valorAsignado.split("[+\\-*/]");

                            // Iterar sobre las partes y verificar si son Strings o variables previamente declaradas
                            for (String parte : partes) {
                                String parteLimpia = parte.trim();
                                // Utilizar una expresión regular para encontrar la asignación de String
                                Pattern pattern = Pattern.compile("\"([^\"]*)\"");
                                Matcher matcher = pattern.matcher(parteLimpia);
                                if (!matcher.find()) {
                                    // La parte no es un String, por lo que se considera una variable
                                    boolean variableEncontrada = false;
                                    for (VariablesGlobales obj : tabla.obtenerVariables()) {
                                        if (obj.getNombre().equals(parteLimpia) && obj.getTipo().equals("String")) {
                                            variableEncontrada = true;
                                            break;
                                        }
                                    }
                                    if (!variableEncontrada) {
                                        System.out.println("Error en línea " + formatearContador(contador) + ": Variable '" + parteLimpia + "' no declarada o no es de tipo String.");
                                        return false;
                                    }
                                }
                            }
                        }

                        tabla.modificarValorVariable(variable.getNombre(), valorAsignado);
                    }
                }
                break;

            case "byte":
                EsScanner = false;
                if (valorAsignado.contains(Scanner)) {
                    EsScanner = true;
                    if (!valorAsignado.contains(".nextByte()")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' para el método de entrada del byte se requiere .nextByte() ");
                        return false;
                    }
                }

                if (!EsScanner) {
                    // Verificar si valorAsignado contiene operadores y/o variables
                    if (valorAsignado.matches(".*[+\\-*/].*")) {
                        String[] partes = valorAsignado.split("[+\\-*/]");

                        // Iterar sobre las partes y verificar si son números o variables previamente declaradas
                        for (String parte : partes) {
                            String parteLimpia = parte.trim();
                            if (!parteLimpia.matches("^-?[0-9 ]+$")) {
                                // La parte no es un número, por lo que se considera una variable
                                boolean variableEncontrada = false;
                                for (VariablesGlobales obj : tabla.obtenerVariables()) {
                                    if (obj.getNombre().equals(parteLimpia) && obj.getTipo().equals("byte")) {
                                        variableEncontrada = true;
                                        break;
                                    }
                                }
                                if (!variableEncontrada) {
                                    System.out.println("Error en línea " + formatearContador(contador) + ": Variable '" + parteLimpia + "' no declarada o no es de tipo byte.");
                                    return false;
                                }
                            }
                        }
                    } else {
                        // Verificar si valorAsignado contiene solo números enteros para byte
                        if (!valorAsignado.matches("^-?[0-9 ]+$")) {
                            System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' se requieren solo números.");
                            return false;
                        }
                    }

                    tabla.modificarValorVariable(variable.getNombre(), valorAsignado);

                    return EsScanner;
                }
                break;

            case "short":
                EsScanner = false;
                if (valorAsignado.contains(Scanner)) {
                    EsScanner = true;
                    if (!valorAsignado.contains(".nextShort()")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' para el método de entrada del short se requiere .nextShort() ");
                        return false;
                    }
                }

                if (!EsScanner) {
                    // Verificar si valorAsignado contiene operadores y/o variables
                    if (valorAsignado.matches(".*[+\\-*/].*")) {
                        String[] partes = valorAsignado.split("[+\\-*/]");

                        // Iterar sobre las partes y verificar si son números o variables previamente declaradas
                        for (String parte : partes) {
                            String parteLimpia = parte.trim();
                            if (!parteLimpia.matches("^-?[0-9 ]+$")) {
                                // La parte no es un número, por lo que se considera una variable
                                boolean variableEncontrada = false;
                                for (VariablesGlobales obj : tabla.obtenerVariables()) {
                                    if (obj.getNombre().equals(parteLimpia) && obj.getTipo().equals("short")) {
                                        variableEncontrada = true;
                                        break;
                                    }
                                }
                                if (!variableEncontrada) {
                                    System.out.println("Error en línea " + formatearContador(contador) + ": Variable '" + parteLimpia + "' no declarada o no es de tipo short.");
                                    return false;
                                }
                            }
                        }
                    } else {
                        // Verificar si valorAsignado contiene solo números enteros para short
                        if (!valorAsignado.matches("^-?[0-9 ]+$")) {
                            System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' se requieren solo números.");
                            return false;
                        }
                    }

                    tabla.modificarValorVariable(variable.getNombre(), valorAsignado);

                    return EsScanner;

                }
                break;

            case "boolean":
                EsScanner = false;
                if (valorAsignado.contains(Scanner)) {
                    EsScanner = true;
                    if (!valorAsignado.contains(".nextBoolean()")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' para el método de entrada del boolean se requiere .nextBoolean ");
                        return false;
                    }
                }

                if (!EsScanner) {
                    // Verificar si valorAsignado contiene operadores y/o variables
                    if (valorAsignado.matches(".*[+\\-*/].*")) {
                        String[] partes = valorAsignado.split("[+\\-*/]");

                        // Iterar sobre las partes y verificar si son números o variables previamente declaradas
                        for (String parte : partes) {
                            String parteLimpia = parte.trim();
                            if (!parteLimpia.matches("^(true|false)$")) {
                                // La parte no es un valor booleano, por lo que se considera una variable
                                boolean variableEncontrada = false;
                                for (VariablesGlobales obj : tabla.obtenerVariables()) {
                                    if (obj.getNombre().equals(parteLimpia) && obj.getTipo().equals("boolean")) {
                                        variableEncontrada = true;
                                        break;
                                    }
                                }
                                if (!variableEncontrada) {
                                    System.out.println("Error en línea " + formatearContador(contador) + ": Variable '" + parteLimpia + "' no declarada o no es de tipo boolean.");
                                    return false;
                                }
                            }
                        }
                    } else {
                        // Verificar si valorAsignado contiene solo valores booleanos
                        if (!valorAsignado.matches("^(true|false)$")) {
                            System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' solo se permite true o false");
                            return false;
                        }
                    }
                }

                if (!EsScanner) {
                    tabla.modificarValorVariable(variable.getNombre(), valorAsignado);
                }
                break;
            case "int":
                EsScanner = false;
                if (valorAsignado.contains(Scanner)) {
                    EsScanner = true;
                    if (!valorAsignado.contains(".nextInt()")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' para el método de entrada del int se requiere .nextInt() ");
                        return false;
                    }
                }

                if (!EsScanner) {
                    // Verificar si valorAsignado contiene operadores y/o variables
                    if (valorAsignado.matches(".*[+\\-*/].*")) {
                        String[] partes = valorAsignado.split("[+\\-*/]");

                        // Iterar sobre las partes y verificar si son números o variables previamente declaradas
                        for (String parte : partes) {
                            String parteLimpia = parte.trim();
                            if (!parteLimpia.matches("^-?\\d+$")) {
                                // La parte no es un número, por lo que se considera una variable
                                boolean variableEncontrada = false;
                                for (VariablesGlobales obj : tabla.obtenerVariables()) {
                                    if (obj.getNombre().equals(parteLimpia) && obj.getTipo().equals("int")) {
                                        variableEncontrada = true;
                                        break;
                                    }
                                }
                                if (!variableEncontrada) {
                                    System.out.println("Error en línea " + formatearContador(contador) + ": Variable '" + parteLimpia + "' no declarada o no es de tipo int.");
                                    return false;
                                }
                            }
                        }
                    } else {
                        // Verificar si valorAsignado contiene solo números
                        if (!valorAsignado.matches("^-?\\s*\\d+\\s*$")) {
                            System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' se requieren solo números.");
                            return false;
                        }
                    }

                }

                if (!EsScanner) {
                    tabla.modificarValorVariable(variable.getNombre(), valorAsignado);
                }
                break;

            case "double":
                EsScanner = false;
                if (valorAsignado.contains(Scanner)) {
                    EsScanner = true;
                    if (!valorAsignado.contains(".nextDouble()")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' para el método de entrada del double se requiere .nextDouble() ");
                        return false;
                    }
                }

                if (!EsScanner) {
                    // Verificar si valorAsignado contiene operadores y/o variables
                    if (valorAsignado.matches(".*[+\\-*/].*")) {
                        String[] partes = valorAsignado.split("[+\\-*/]");

                        // Iterar sobre las partes y verificar si son números o variables previamente declaradas
                        for (String parte : partes) {
                            String parteLimpia = parte.trim();
                            if (!parteLimpia.matches("^-?\\d+(\\.\\d+)?$")) {
                                // La parte no es un número, por lo que se considera una variable
                                boolean variableEncontrada = false;
                                for (VariablesGlobales obj : tabla.obtenerVariables()) {
                                    if (obj.getNombre().equals(parteLimpia) && obj.getTipo().equals("double")) {
                                        variableEncontrada = true;
                                        break;
                                    }
                                }
                                if (!variableEncontrada) {
                                    System.out.println("Error en línea " + formatearContador(contador) + ": Variable '" + parteLimpia + "' no declarada o no es de tipo double.");
                                    return false;
                                }
                            }
                        }
                    } else {
                        // Verificar si valorAsignado contiene solo números decimales
                        if (!valorAsignado.matches("^-?\\d+(\\.\\d+)?$")) {
                            System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' se requieren solo números decimales.");
                            return false;
                        }
                    }

                    tabla.modificarValorVariable(variable.getNombre(), valorAsignado);
                }

                break;

            case "float":
                EsScanner = false;
                if (valorAsignado.contains(Scanner)) {
                    EsScanner = true;
                    if (!valorAsignado.contains(".nextFloat()")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' para el método de entrada del float se requiere .nextFloat() ");
                        return false;
                    }
                }
                if (!EsScanner) {
                    // Verificar si valorAsignado contiene solo números decimales para float y termina con 'f'
                    if (!valorAsignado.matches("^-?\\d+(\\.\\d+)[f]?$")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' se requieren solo números y terminar con 'f'");
                        return false;
                    }
                    if (!valorAsignado.endsWith("f")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Se requiere que al final termine con 'f' para indicar que es un float");
                        return false;
                    }

                    tabla.modificarValorVariable(variable.getNombre(), valorAsignado);
                }

                break;

            case "long":
                EsScanner = false;
                if (valorAsignado.contains(Scanner)) {
                    EsScanner = true;
                    if (!valorAsignado.contains(".nextLong()")) {
                        System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' para el método de entrada del long se requiere .nextLong() ");
                        return false;
                    }
                }

                if (!EsScanner) {
                    // Verificar si valorAsignado contiene operadores y/o variables
                    if (valorAsignado.matches(".*[+\\-*/].*")) {
                        String[] partes = valorAsignado.split("[+\\-*/]");

                        // Iterar sobre las partes y verificar si son números o variables previamente declaradas
                        for (String parte : partes) {
                            String parteLimpia = parte.trim();
                            if (!parteLimpia.matches("^-?\\d+[lL]?$")) {
                                // La parte no es un número, por lo que se considera una variable
                                boolean variableEncontrada = false;
                                for (VariablesGlobales obj : tabla.obtenerVariables()) {
                                    if (obj.getNombre().equals(parteLimpia) && obj.getTipo().equals("long")) {
                                        variableEncontrada = true;
                                        break;
                                    }
                                }
                                if (!variableEncontrada) {
                                    System.out.println("Error en línea " + formatearContador(contador) + ": Variable '" + parteLimpia + "' no declarada o no es de tipo long.");
                                    return false;
                                }
                            }
                        }
                    } else {
                        // Verificar si valorAsignado contiene solo números enteros para long y termina con 'L' o 'l'
                        if (!valorAsignado.matches("-?\\d+[lL]?$")) {
                            System.out.println("Error en línea " + formatearContador(contador) + ": Después del signo '=' se requieren solo números y terminar con 'L' o 'l'");
                            return false;
                        }
                        if (!valorAsignado.endsWith("L") && !valorAsignado.endsWith("l")) {
                            System.out.println("Error en línea " + formatearContador(contador) + ": Se requiere que al final termine con 'L' o 'l' para indicar que es un Long");
                            return false;
                        }
                    }

                    tabla.modificarValorVariable(variable.getNombre(), valorAsignado);
                }

                break;

            default:

        }
        return false;
    }

    //metodo para validar el identificador de las variables
    public boolean ValidarIdentificador(String identificador, int contador) {
        //Validar si existe el identificador definido anteriormente
        for (VariablesGlobales obj : tabla.obtenerVariables()) {
            if (obj.getNombre().equals(identificador)) {
                System.out.println("----- ERROR -----, en la linea " + formatearContador(contador) + ", el identificador `" + identificador + "` ya esta en uso.");
                return false;
            }
        }

        // Validar si es una palabra reservada
        for (TablaSintaxis.Tokens validacion : TablaSintaxis.Tokens.values()) {
            if (identificador.matches(validacion.patron)) {
                if (validacion == TablaSintaxis.Tokens.Reservada) {
                    System.out.println("----- ERROR -----, en la linea " + formatearContador(contador) + ", el identificador `" + identificador + "` no puede ser una palabra reservada.");
                    return false;
                }
            }
        }
        // Validar si es una palabra reservada
        for (TablaSintaxis.Tokens validacion : TablaSintaxis.Tokens.values()) {
            if (identificador.matches(validacion.patron)) {
                if (validacion == TablaSintaxis.Tokens.TipoDato) {
                    System.out.println("----- ERROR -----, en la linea " + formatearContador(contador) + ", el identificador `" + identificador + "` no puede ser una palabra reservada.");
                    return false;
                }
            }
        }
        identificador = identificador.trim();
        if (identificador.matches("^[a-zA-Z_$][a-zA-Z_$0-9]*$")) {
            return true; // El identificador es válido
        } else {
            System.out.println("----- ERROR -----, en la línea " + formatearContador(contador) + ", el identificador no cumple con las reglas \n Verifica que no empiece con un número y que no contenga espacios en blanco.");
            return false; // El identificador no cumple con las reglas
        }

    }

    // Método auxiliar para formatear el contador como "0001", "0002", etc.
    private String formatearContador(int contador) {
        return String.format("%04d", contador);
    }

    // Metodo para redirigir los println al archivo txt
    private void redirigirprints(String archivoErrores) {
        try {
            // Crear un nuevo flujo de salida hacia el archivo especificado en modo de apertura (append)
            PrintStream fileOut = new PrintStream(new FileOutputStream(archivoErrores, true));

            // Establecer el flujo de salida estándar (System.out) para que apunte al archivo
            System.setOut(fileOut);
        } catch (FileNotFoundException e) {

        }
    }
}
