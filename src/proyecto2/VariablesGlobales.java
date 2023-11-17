/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author kevin
 */
public class VariablesGlobales {

    private String TipoVariable;
    private String NombreVariable;
    private String ValorVariable;

    public VariablesGlobales(String Tipo, String Nombre, String Valor) {
        this.TipoVariable = Tipo;
        this.NombreVariable = Nombre;
        this.ValorVariable = Valor;
    }

    public String getTipo() {
        return TipoVariable;
    }

    public String getNombre() {
        return NombreVariable;
    }

    public String getValor() {
        return ValorVariable;
    }

    public void setValor(String nuevoValor) {
        this.ValorVariable = nuevoValor;
    }

    public void setTipo(String nuevoValor) {
        this.TipoVariable = nuevoValor;
    }

    public void setNombre(String nuevoValor) {
        this.NombreVariable = nuevoValor;
    }
}
