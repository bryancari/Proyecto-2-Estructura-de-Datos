package proyecto2.pkg2;

/**
 * Pila utilizada para evaluar los nodos que sean Operandos u Operadores de la
 * clase ArbolBinarioExpresion
 *
 * @author Bryan Cariaco & Juan Ceballos
 */
public class PilaArbol {

    private NodoPila tope;

    public PilaArbol() {
        this.tope = null;
    }

    /**
     * @return el tope de la pila
     */
    public NodoPila getTope() {
        return tope;
    }

    /**
     * @return si la pila está vacia o no
     */
    public boolean esVacia() {
        return tope == null;
    }

    public void vaciarPila() {
        this.tope = null;
    }

    /**
     * @param nuevoNodo es el nodo a agregar en la pila
     */
    public void insertarElemento(NodoArbol nuevoNodo) {
        NodoPila nodo = new NodoPila(nuevoNodo);
        nodo.setProximo(tope);
        tope = nodo;
    }

    /**
     * @return elimina el nodo del tope y a su vez lo retorna para su uso
     */
    public NodoArbol eliminarElemento() {
        NodoArbol auxNodo = null;
        if (esVacia()) {
        } else {
            NodoPila auxRaiz = tope;
            tope = auxRaiz.getProximo();
            auxNodo = auxRaiz.getDato();
        }
        return auxNodo;
    }
}
