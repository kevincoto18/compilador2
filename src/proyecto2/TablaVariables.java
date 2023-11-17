/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

import java.util.ArrayList;

/**
 *
 * @author kevin
 */
public class TablaVariables {
        private ArrayList<VariablesGlobales> listaVariables = new ArrayList<>();
    // metodo para agregarVariables
    public void agregarVariable(String tipo, String nombre, String valor) {
        VariablesGlobales variable = new VariablesGlobales(tipo, nombre, valor);
        listaVariables.add(variable);
    }
 // Metodo para obtener variables
    public ArrayList<VariablesGlobales> obtenerVariables() {
        return listaVariables;
    }
    
     public void modificarValorVariable(String nombre, String nuevoValor) {
        // Buscar la variable por su nombre
        for (VariablesGlobales variable : listaVariables) {
            if (variable.getNombre().equals(nombre)) {
                // Actualizar el valor de la variable
                variable.setValor(nuevoValor);
                return;  // Terminar el bucle después de encontrar la variable
            }
        }

        // Si llegamos aquí, la variable no se encontró
        System.out.println("Error: No se pudo modificar el valor de la variable con nombre " + nombre);
    }

}
