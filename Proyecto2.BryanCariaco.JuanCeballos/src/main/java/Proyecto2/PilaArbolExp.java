package Proyecto2;

public class PilaArbolExp {

    private NodoPila top;

    public PilaArbolExp() {
        top = null;
    }

    public void Insertar(NodoArbol elemento) {
        NodoPila pnew = new NodoPila(elemento);
        pnew.pnext = top;
        top = pnew;
    }

    public boolean EsVacio() {
        return top == null;
    }

    public NodoArbol TopPila() {
        return top.dato;
    }

    public void VaciarPila() {
        top = null;
    }

    public NodoArbol Eliminar() {
        NodoArbol aux = null;
        if (!EsVacio()) {
            aux = top.dato;
            top = top.pnext;
        }
        return aux;
    }
}
