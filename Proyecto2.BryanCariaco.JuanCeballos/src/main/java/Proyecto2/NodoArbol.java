
package Proyecto2;


public class NodoArbol {
    public Object dato;
    public NodoArbol hijoIzq;
    public NodoArbol hijoDer;

    public NodoArbol(Object dato){
        this.dato = dato;
        hijoIzq=hijoDer=null;
    }
    
    public Object getDato(){
        return dato;
    }

    public void setDato(Object dato){
        this.dato = dato;
    }

    public NodoArbol getHijoIzq(){
        return hijoIzq;
    }

    public void setHijoIzq(NodoArbol hijoIzq){
        this.hijoIzq = hijoIzq;
    }

    public NodoArbol getHijoDer(){
        return hijoDer;
    }

    public void setHijoDer(NodoArbol hijoDer){
        this.hijoDer = hijoDer;
    }
}
