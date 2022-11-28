package proyecto2.pkg2;

/**
 *
 * @author Bryan Cariaco & Juan Ceballos
 */
public class NodoPila {

    private NodoArbol dato;
    private NodoPila proximo;

    public NodoPila(NodoArbol nuevo) {
        this.dato = nuevo;
        this.proximo = null;
    }

    /**
     * @return the dato
     */
    public NodoArbol getDato() {
        return dato;
    }

    /**
     * @param dato the dato to set
     */
    public void setDato(NodoArbol dato) {
        this.dato = dato;
    }

    /**
     * @return the proximo
     */
    public NodoPila getProximo() {
        return proximo;
    }

    /**
     * @param proximo the proximo to set
     */
    public void setProximo(NodoPila proximo) {
        this.proximo = proximo;
    }
}
