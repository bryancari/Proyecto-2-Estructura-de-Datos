package proyecto2.pkg2;

import javax.swing.*;
import java.awt.*;

/**
 * Tiene como atributos el arbol ya construido, de modo que solo sea necesario
 * recorrer y dibujar
 *
 * @author Bryan Cariaco & Juan Ceballos
 */
public class ArbolBinarioGrafico extends JFrame {

    private JPanel ventana;

    private ArbolBinarioExpresion arbol;

    /**
     * Se llama al momento de ya haber construido y modificado correctamente el
     * arbol de expresión basado en la expresión ingresada por el usuario
     *
     * @param nuevoArbol es el árbol ya definido
     */
    public ArbolBinarioGrafico(ArbolBinarioExpresion nuevoArbol) {
        arbol = nuevoArbol;
    }

    /**
     * Recursivamente dibuja en un panel los nodos pertenecientes al arbol de
     * expresion cambiando la posición de los nodos según su altura
     *
     * @param g clase Graphics para poder graficar
     * @param nodo nodo a mostrar en la llamada de la función
     * @param x0 posición del nodo anterior
     * @param x1 posición del nodo evaluando
     * @param y posición en y
     * @return un int que será el valor de la posición a evaluar
     */
    public int drawTree(Graphics g, NodoArbol nodo, int x0, int x1, int y) {

        int m = (x0 + x1) / 2;
        g.setColor(Color.LIGHT_GRAY);
        g.fillOval(m, y, 50, 40);
        g.setColor(Color.magenta);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        String t = String.valueOf(nodo.getContenido());
        g.drawString(t, m + 20, y + 30);
        if (nodo.getIzquierda() != null) {
            int x2 = drawTree(g, nodo.getIzquierda(), x0, m, y + 50);
            g.drawLine(m + 25, y + 40, x2 + 25, y + 50);
        }
        if (nodo.getDerecha() != null) {
            int x2 = drawTree(g, nodo.getDerecha(), m, x1, y + 50);
            g.drawLine(m + 25, y + 40, x2 + 25, y + 50);
        }
        return m;
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        drawTree(g, arbol.getRaiz(), 0, this.getWidth() - 25, 100);
    }
}
