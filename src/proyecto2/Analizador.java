/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author kevin
 */
public class Analizador {

    // Se inicializa la lista de Variables globales
    TablaVariables TV = new TablaVariables();

    public void LeerJAVA(String fichero) {
        Validaciones validar = new Validaciones(TV, fichero.replace(".java", "_Errores.txt"));
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String linea;
            int contador_linea = 0;
            boolean DentroWhile = false, DentroIf = false, DentroElse = false;
            int contenido = 0, contenidoif = 0;
            
            while ((linea = br.readLine()) != null) {
                linea = linea.trim();
                System.out.println(contador_linea + " - " + linea);

                // Validar la estructura del while
                if (validar.ValidarEstructuraWhile(linea, contador_linea, DentroWhile, contenido)) {
                    DentroWhile = true;
                    if (!linea.isEmpty()) {
                        contenido++;
                    }

                } else {
                    DentroWhile = false;
                }

                // Validar la estructura del if
                if (validar.ValidarEstructuraIf(linea, contador_linea, DentroIf, DentroElse, contenidoif)) {
                    DentroIf = true;
                    if (!linea.isEmpty()) {
                        contenidoif++;

                    }

                } else {
                    DentroIf = false;
                }

                // Clasificar la expresión
                Clasificar_Expresion(linea, contador_linea, fichero);
                contador_linea++;
            }
            
           
            // Verificar si las llaves de apertura y cierre del ciclo while coinciden
            if (validar.CorcherteAperturaWhile != validar.CorcherteCierreWhile) {
                System.out.println("ERROR General - Verifica la estructura While, Las llaves de apertura y cierre del ciclo While no coinciden");
                System.out.println("Total llave apertura: " + validar.CorcherteAperturaWhile + " | Total llave Cierre: " + validar.CorcherteCierreWhile);
            }
            if (validar.CorcherteAperturaIf != validar.CorcherteCierreIf) {
                System.out.println("ERROR General - Verifica la estructura If - Else, Las llaves de apertura y cierre del ciclo If no coinciden");
                System.out.println("Total llave apertura: " + validar.CorcherteAperturaIf + " | Total llave Cierre: " + validar.CorcherteCierreIf);
                System.out.println("Consideraciones:\n No se puede declarar un else si el if\n La declaracion del else debe venir en la misma linea de corcherte de cierre \n No se admite la estructura vacia");
            }

            // Mostrar variables globales
            System.out.println("**************Variables***************** ");
            for (VariablesGlobales objeto : TV.obtenerVariables()) {
                System.out.println("Nombre : " + objeto.getNombre() + " | Tipo : " + objeto.getTipo() + " | Valor : " + objeto.getValor());
            }

        } catch (IOException e) {
            // Manejar errores de lectura de archivo
            System.err.println("Error al leer el archivo .java");
            System.err.println("Consejos: ");
            System.err.println("1 - Verifica que el archivo exista");
            System.err.println("2 - Verifica que el archivo esté en la misma carpeta del .jar");
            System.err.println("3 - Verifica que el nombre del archivo sea el mismo\n");
        }
    }

    public void Clasificar_Expresion(String linea, int contador_linea, String fichero) {

        Validaciones validar = new Validaciones(TV, fichero.replace(".java", "_Errores.txt"));
        if (validar.ValidarComentario(linea, contador_linea)) {
            
        } else {

            //Validacion de Scanner
            Pattern patronScanner = Pattern.compile("\\b[sS]*[cC]*[aA]*[nN]*[nN]*[eE]*[rR]\\b");
            Matcher matcherScanner = patronScanner.matcher(linea);
            if (matcherScanner.find()) {
                validar.ValidarScanner(linea, contador_linea);
            }
            // Validacion de println
            Pattern patronPrintln = Pattern.compile("\\b[pP][rR][iI][nN][tT][lL][nN]\\b");
            Matcher matcherPrintln = patronPrintln.matcher(linea);
            if (matcherPrintln.find()) {
                validar.ValidarPrintln(linea, contador_linea);
            }

            // Validacion de int
            Pattern patron = Pattern.compile("\\b[iI]*[nN]*[tT]\\b");
            Matcher matcher = patron.matcher(linea);
            if (matcher.find()) {
                validar.ValidarINT(linea, contador_linea);

            }
            //Validacion de char
            patron = Pattern.compile("\\b[cC]*[hH]*[aA]*[rR]+\\b");
            matcher = patron.matcher(linea);
            if (matcher.find()) {
                validar.ValidarChar(linea, contador_linea);

            }
            //Validacion de String
            patron = Pattern.compile("\\b[sS]*[tT]*[rR]*[iI]*[nN]*[gG]+\\b");
            matcher = patron.matcher(linea);
            if (matcher.find()) {
                validar.ValidarString(linea, contador_linea);

            }
            //Validacion de byte
            patron = Pattern.compile("\\b[bB]*[yY]*[tT]*[eE]+\\b");
            matcher = patron.matcher(linea);
            if (matcher.find()) {
                validar.ValidarByte(linea, contador_linea);
            }
            //Validacion de short
            patron = Pattern.compile("\\b[sS]*[hH]*[oO]*[rR]*[tT]+\\b");
            matcher = patron.matcher(linea);
            if (matcher.find()) {
                validar.ValidarShort(linea, contador_linea);
            }
            //Validacion de long
            patron = Pattern.compile("\\b[lL]*[oO]*[nN]*[gG]+\\b");
            matcher = patron.matcher(linea);
            if (matcher.find()) {
                validar.ValidarLong(linea, contador_linea);
            }
            //Validacion de float
            patron = Pattern.compile("\\b[fF]*[lL]*[oO]*[aA]*[tT]+\\b");
            matcher = patron.matcher(linea);
            if (matcher.find()) {
                validar.ValidarFloat(linea, contador_linea);
            }
            //Validacion de double
            patron = Pattern.compile("\\b[\\w]*[dD]+[oO]+[uU]+[bB]+[lL]+[eE]+[\\w]*\\b");
            matcher = patron.matcher(linea);
            if (matcher.find()) {
                validar.ValidarDouble(linea, contador_linea);
            }
            //Validacion de boolean
            patron = Pattern.compile("\\b[bB]+[oO]+[oO]*[lL]+[eE]+[aA]*[nN]+\\b");
            matcher = patron.matcher(linea);
            if (matcher.find()) {
                validar.ValidarBoolean(linea, contador_linea);
            }

            if (validar.ComprobarPrimeraPalabra(linea, contador_linea)) {
                validar.ValidarAsignacion(linea, contador_linea);
            }
            
        }
    }

}
