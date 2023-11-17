/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto2;

/**
 *
 * @author kevin
 */
public class TablaSintaxis {
    enum Tokens {
        Reservada("\\b(abstract|assert|break|case|"
                + "catch|class|const|continue|default|do|else|enum|extends|"
                + "final|finally|println|String|for|if|implements|import|instanceof|interface|"
                + "native|new|null|package|private|protected|public|return|"
                + "synchronized|this|throw|throws|transient|try|void|volatile|while|static|Scanner | new| System.in)\\b"),
        TipoDato("\\b(char|byte|short|int|long|float|double|boolean)\\b"),
        CorcheteApetura("\\{\\]*"),
        CorcheteCierre("}"),
        ParentesisApertura("\\("),
        ParentesisCierre("\\)"),
        CuadradosApertura("\\["),
        CuadradosCierre("\\]");

        public final String patron;

        Tokens(String t) {
            this.patron = t;
        }
    }
}
