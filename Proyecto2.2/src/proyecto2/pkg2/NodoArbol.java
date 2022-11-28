package proyecto2.pkg2;

/**
 * @author Bryan Cariaco & Juan Ceballos
 */
public class NodoArbol {

    private char contenido;
    private NodoArbol izquierda;
    private NodoArbol derecha;
    
    /**
     * @param dato el contenido del nodo
     */
    public NodoArbol(char dato) {
        this.contenido = dato;
        izquierda = derecha = null;
    }

    /**
     * @return the contenido
     */
    public char getContenido() {
        return contenido;
    }

    /**
     * @param contenido the contenido to set
     */
    public void setContenido(char contenido) {
        this.contenido = contenido;
    }

    /**
     * @return the izquierda
     */
    public NodoArbol getIzquierda() {
        return izquierda;
    }

    /**
     * @param izquierda the izquierda to set
     */
    public void setIzquierda(NodoArbol izquierda) {
        this.izquierda = izquierda;
    }

    /**
     * @return the derecha
     */
    public NodoArbol getDerecha() {
        return derecha;
    }

    /**
     * @param derecha the derecha to set
     */
    public void setDerecha(NodoArbol derecha) {
        this.derecha = derecha;
    }
}
