package Proyecto2;

public class NodoPila {
    public NodoArbol dato;
    public NodoPila pnext;
    
    public NodoPila (NodoArbol dato){
        this.dato= dato;
        pnext= null; 
    }
    
    public Object getDato(){
        return dato;
    }
    
    public void setDato(NodoArbol dato){
        this.dato= dato;
    }
    
    public NodoPila getPnext(){
        return pnext;
    }
    
    public void setPnext(NodoPila pnext){
        this.pnext= pnext;
    }
}
