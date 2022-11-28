
package Proyecto2;

public class ArbolBinarioExp {
    private NodoArbol Root;

    public NodoArbol getRoot(){
        return Root;
    }
    
    public ArbolBinarioExp(){
        this.Root = null;
    }
    
    public ArbolBinarioExp(String cadena){
        Root = CrearArbolBE(cadena);
    }
    
   public void Vaciar(){
        Root=null;
    }
   
   public void CrearNodo(Object dato){
       Root= new NodoArbol(dato);
   }
   
   public NodoArbol CrearSubArbol(NodoArbol dato2, NodoArbol dato1, NodoArbol operador){
       operador.hijoIzq= dato1;
       operador.hijoDer= dato2;
       return operador;
   }
   
   public boolean ArbolVacio(){
       return Root== null;
   }
   
   public String ToString(int a){
       String cadena= "";
       switch(a){
           case 0:
               cadena= Preorden(Root, cadena);
               break;
           case 1:
               cadena= Inorden(Root, cadena);
               break;
           case 2:
               cadena= Postorden(Root, cadena);
               break;
       }
       return cadena;
   }
   
   private String Preorden(NodoArbol subarbol, String c){
       String cadena;
       cadena= "";
       if (subarbol != null){
           cadena= c+ subarbol.dato.toString()+ "\n" + Preorden(subarbol.hijoIzq, c) + Preorden(subarbol.hijoDer, c);
       }
       return cadena;
   }
   
   private String Inorden(NodoArbol subarbol, String c){
       String cadena;
       cadena= "";
       if (subarbol != null){
           cadena= c+ Inorden(subarbol.hijoIzq, c)+ subarbol.dato.toString()+ "\n"+ Inorden(subarbol.hijoDer, c);
       }
       return cadena;
       
   }
   
   private String Postorden(NodoArbol subarbol, String c){
       String cadena;
       cadena= "";
       if (subarbol != null){
           cadena= c + Postorden(subarbol.hijoIzq, c) + Postorden(subarbol.hijoDer, c)+ subarbol.dato.toString()+ "\n";
       }
       return cadena;
   }
   
   private int Prioridad(char c){
       int p=100;
       switch(c){
           case '^':
               p= 30;
               break;
           case '*':
           case '/':
               p= 20;
               break;
           case '+':
           case '-':
               p= 10;
               break;
           default:
               p=0;
       }
       return p;
   }
   
   private boolean EsOperador(char c){
       boolean resultado;
       switch(c){
           case '(':
           case ')':
           case '^':
           case '*':
           case '/':
           case '+':
           case '-':
               resultado= true;
               break;
           default:
               resultado= false;
       }
       return resultado;
   }
   
   private NodoArbol CrearArbolBE(String cadena){
       PilaArbolExp PilaOperadores;
       PilaArbolExp PilaExpresiones;
       NodoArbol token;
       NodoArbol op1;
       NodoArbol op2;
       NodoArbol op;
       PilaOperadores= new PilaArbolExp();
       PilaExpresiones= new PilaArbolExp();
       char caracterEvaluado;
       for (int i=0; i<cadena.length(); i++){
           caracterEvaluado= cadena.charAt(i);
           token= new NodoArbol(caracterEvaluado);
           if (!EsOperador(caracterEvaluado)){
               PilaExpresiones.Insertar(token);
           }
           else{
               switch(caracterEvaluado){
                   case '(':
                       PilaOperadores.Insertar(token);
                       break;
                   case ')':
                       while (!PilaOperadores.EsVacio() && !PilaOperadores.TopPila().dato.equals('(')){
                           op2= PilaExpresiones.Eliminar();
                           op1= PilaExpresiones.Eliminar();
                           op= PilaOperadores.Eliminar();
                           op= CrearSubArbol(op2, op1, op);
                           PilaExpresiones.Insertar(op);
                       }
                       PilaOperadores.Eliminar();
                       break;
                   default:
               }      while (!PilaOperadores.EsVacio() && Prioridad(caracterEvaluado)<= Prioridad(PilaOperadores.TopPila().dato.toString().charAt(0))){
                           op2= PilaExpresiones.Eliminar();
                           op1= PilaExpresiones.Eliminar();
                           op= PilaOperadores.Eliminar();
                           op= CrearSubArbol(op2, op1, op);
                           PilaExpresiones.Insertar(op);  
                      }  
                      PilaOperadores.Insertar(token);
           }
       }
       
   
   while (!PilaOperadores.EsVacio()){
       op2= PilaExpresiones.Eliminar();
       op1= PilaExpresiones.Eliminar();
       op= PilaOperadores.Eliminar();
       op= CrearSubArbol(op2, op1, op);
       PilaExpresiones.Insertar(op);
   }
   op= PilaExpresiones.Eliminar();
   return op;
}
   public double EvaluarExpresion(){
       return Evaluar(Root);
   }
   
   private double Evaluar(NodoArbol subarbol){
       double acum= 0;
       if (!EsOperador(subarbol.dato.toString().charAt(0))){
           return Double.parseDouble(subarbol.dato.toString());
           
       }else{
           switch(subarbol.dato.toString().charAt(0)){
               case '^':
                   acum= acum+ Math.pow(Evaluar(subarbol.hijoIzq), Evaluar(subarbol.hijoDer));
                   break;
               case '*':
                   acum= acum+ Evaluar(subarbol.hijoIzq) * Evaluar(subarbol.hijoDer);
                   break;
               case '/':
                   acum= acum+ Evaluar(subarbol.hijoIzq) / Evaluar(subarbol.hijoDer);
                   break;
               case '+':
                   acum= acum+ Evaluar(subarbol.hijoIzq) + Evaluar(subarbol.hijoDer);
                   break;
               case '-':
                   acum= acum+ Evaluar(subarbol.hijoIzq) - Evaluar(subarbol.hijoDer);
                   break;
           }
       }
       return acum;
       
   }
       /*
    public double evaluar(NodoArbol subarbol) {
        double acum = 0;
        char auxChar = subarbol.getContenido();
        if (esOperando(auxChar)) {
            switch (auxChar) {
                case '^':
                    acum = acum + Math.pow(evaluar(subarbol.getIzquierda()), evaluar(subarbol.getDerecha()));
                    break;
                case '*':
                    acum = acum + evaluar(subarbol.getIzquierda()) * evaluar(subarbol.getDerecha());
                    break;
                case '/':
                    acum = acum + evaluar(subarbol.getIzquierda()) / evaluar(subarbol.getDerecha());
                    break;
                case '+':
                    acum = acum + evaluar(subarbol.getIzquierda()) + evaluar(subarbol.getDerecha());
                    break;
                case '-':
                    acum = acum + evaluar(subarbol.getIzquierda()) - evaluar(subarbol.getDerecha());
                    break;
            }
        } else {
            return Double.parseDouble(String.valueOf(auxChar));
        }
        return acum;
    }
     */
    
}
