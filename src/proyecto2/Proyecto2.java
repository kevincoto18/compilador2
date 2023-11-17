/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proyecto2;

import java.io.File;

/**
 *
 * @author kevin
 */
public class Proyecto2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String nombretxt =  "calculadora.java";
//        if (args.length > 0) {
//            nombretxt = args[0];
//        }
//        if (args.length < 1) {
//            System.err.println("Error general, no se ingreso el archivo de entrada");
//            return;
//        }
        BorrarTemporarl(nombretxt);
        if (!nombretxt.endsWith(".java")) {
            System.err.println("Error general, el archivo tiene que tener extension .java");
            return;
        }
       Analizador analizar = new Analizador();
       analizar.LeerJAVA(nombretxt);    
    }
    
     public static void BorrarTemporarl(String fichero) {
        File archivo = new File(fichero.replace(".java", "_Errores.txt"));

        if (archivo.exists()) {
            if (archivo.delete()) {
            // System.err.println("Errores.txt limpiado");

            } else {
                System.err.println("Error al eliminar el archivo temporal");
            }
        }

    }

}
