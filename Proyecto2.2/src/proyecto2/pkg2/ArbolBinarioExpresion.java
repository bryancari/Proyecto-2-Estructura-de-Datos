package proyecto2.pkg2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 * El atributo evaluado es la expresión aritmética ingresada por el usuario. El
 * atributo tipoEntradaIngresada define si la entrada anterior es infija, polaca
 * o inversa. Y el atributo mostrable es la expresión aritmética en el orden
 * solicitado por el usuario.
 *
 * @author Bryan Cariaco & Juan Ceballos
 */
public class ArbolBinarioExpresion {

    private NodoArbol raiz;
    private String evaluado;
    private int tipoEntradaIngresada;
    private String mostrable;

    public ArbolBinarioExpresion() {
        this.raiz = null;
        this.evaluado = "";
        this.mostrable = "";
    }

    /**
     * @return la raiz del arbol
     */
    public NodoArbol getRaiz() {
        return raiz;
    }

    /**
     * @return si la expresión ingresada es Infija retorna 1, si es Polaca
     * retorna 2 y si es polaca inversa retorna 3
     */
    public int getTipoEntrada() {
        return tipoEntradaIngresada;
    }

    /**
     * @param nodoRaiz iguala la raiz del arbol al valor ingresado
     */
    public void setRaiz(NodoArbol nodoRaiz) {
        raiz = nodoRaiz;
    }

    /**
     * @return evaluado que es la expresión aritmética ingresada
     */
    public String getEvaluado() {
        return evaluado;
    }

    /**
     * @param aEvaluar iguala la expresión aritmética ingresada a un atributo de
     * la clase
     */
    public void setEvaluado(String aEvaluar) {
        evaluado = aEvaluar;
    }

    /**
     * @return retorna el atributo mostrable que contiene el arbol en la
     * notación escogida por el usuario
     */
    public String getMostrable() {
        return mostrable;
    }

    /**
     * @param cadena iguala el atributo mostrable al valor ingresado
     */
    public void setMostrable(String cadena) {
        mostrable = cadena;
    }

    /**
     * @return true si el árbol está vacio
     */
    public boolean esVacio() {
        return raiz == null;
    }

    /**
     * @param x es el cáracter a evaluar
     * @return true si el cáracter evaluado es un operador
     */
    public boolean esOperando(char x) {
        return (x == '+' || x == '-' || x == '*' || x == '/' || x == '^' || x == '(' || x == ')');
    }

    /**
     * Define si la expresión ingresada está en notación infija, polaca o polaca
     * inversa. Dependiendo de la evaluación iguala el atributo
     * tipoEntradaIngresada a 1 si es infija, a 2 si es polaca o a 3 si es
     * polaca inversa
     *
     * @param entrada es la expresión aritmética
     */
    public void tipoEntrada(String entrada) {
        int aux = 0;
        for (int i = 0; i < entrada.length(); i++) {
            char caracter = entrada.charAt(i);
            if (Character.toString(caracter).equals("(") || Character.toString(caracter).equals(")")) {
                aux++;
                break;
            }
            if (i == 0 && esOperando(caracter)) {
                aux++;
            } else if (i == entrada.length() - 1 && !esOperando(caracter)) {
                aux++;
            } else if (i == entrada.length() - 1 && esOperando(caracter)) {
                aux = 3;
            }
        }
        tipoEntradaIngresada = aux;
    }

    /**
     * @param subarbol nodo desde el cual ordenará en preorden/polaca
     */
    public void Preorden(NodoArbol subarbol) {
        if (subarbol != null) {
            mostrable += subarbol.getContenido();
            Preorden(subarbol.getIzquierda());
            Preorden(subarbol.getDerecha());
        }
    }

    /**
     * @param subarbol nodo desde el cual ordenará en inorden/infija
     */
    public void Inorden(NodoArbol subarbol) {
        if (subarbol != null) {
            Inorden(subarbol.getIzquierda());
            mostrable += subarbol.getContenido();
            Inorden(subarbol.getDerecha());
        }
    }

    /**
     * @param subarbol nodo desde el cual ordenará en postorden/polaca inversa
     */
    public void Postorden(NodoArbol subarbol) {
        if (subarbol != null) {
            Postorden(subarbol.getIzquierda());
            Postorden(subarbol.getDerecha());
            mostrable += subarbol.getContenido();
        }
    }

    /**
     * @param nodoDerecho segundo operando
     * @param nodoIzquierdo primer operando
     * @param operador operador padre de ambos operando
     * @return operador con sus hijos seteados
     */
    public NodoArbol CrearSubArbol(NodoArbol nodoDerecho, NodoArbol nodoIzquierdo, NodoArbol operador) {
        operador.setIzquierda(nodoIzquierdo);
        operador.setDerecha(nodoDerecho);
        return operador;
    }

    /**
     * Define la prioridad de los caracteres evaluados, el más importante es la
     * potencia, le siguen la multiplicación y la división, luego la suma y la
     * resta y por último los operandos
     *
     * @param c cáracter evaluado
     * @return el valor númerico que representa el cáracter evaluado
     */
    public int definirPrioridad(char c) {
        switch (c) {
            case '^':
                return 30;
            case '*':
            case '/':
                return 20;
            case '+':
            case '-':
                return 10;
            default:
                return 0;
        }
    }

    /**
     * Es llamada si la expresión aritmética es infija. Crea un árbol en base a
     * dicha expresión. Crea dos pilas auxiliares una para operadores y otra
     * para operandos, luego de procesar ambas pilas iguala la raiz del árbol al
     * tope de la pila de operandos
     *
     * @param cadena es la expresión aritmética
     */
    public void crearArbolInfijo(String cadena) {
        PilaArbol pilaOperadores = new PilaArbol();
        PilaArbol pilaExpresiones = new PilaArbol();
        NodoArbol token;
        NodoArbol operando1;
        NodoArbol operando2;
        NodoArbol operador;
        char caracterEvaluado;
        for (int i = 0; i < cadena.length(); i++) {
            caracterEvaluado = cadena.charAt(i);
            token = new NodoArbol(caracterEvaluado);
            if (esOperando(caracterEvaluado)) {
                switch (caracterEvaluado) {
                    case '(':
                        pilaOperadores.insertarElemento(token);
                        break;
                    case ')':
                        while (!pilaOperadores.esVacia() && !Character.toString(pilaOperadores.getTope().getDato().getContenido()).equals("(")) {
                            operando2 = pilaExpresiones.eliminarElemento();
                            operando1 = pilaExpresiones.eliminarElemento();
                            operador = pilaOperadores.eliminarElemento();
                            operador = CrearSubArbol(operando2, operando1, operador);
                            pilaExpresiones.insertarElemento(operador);
                        }
                        pilaOperadores.eliminarElemento();
                        break;
                    default:
                        while (!pilaOperadores.esVacia() && definirPrioridad(caracterEvaluado) <= definirPrioridad(pilaOperadores.getTope().getDato().getContenido())) {
                            operando2 = pilaExpresiones.eliminarElemento();
                            operando1 = pilaExpresiones.eliminarElemento();
                            operador = pilaOperadores.eliminarElemento();
                            operador = CrearSubArbol(operando2, operando1, operador);
                            pilaExpresiones.insertarElemento(operador);
                        }
                        pilaOperadores.insertarElemento(token);
                }
            } else {
                pilaExpresiones.insertarElemento(token);
            }
        }
        while (!pilaOperadores.esVacia()) {
            operando2 = pilaExpresiones.eliminarElemento();
            operando1 = pilaExpresiones.eliminarElemento();
            operador = pilaOperadores.eliminarElemento();
            operador = CrearSubArbol(operando2, operando1, operador);
            pilaExpresiones.insertarElemento(operador);
        }
        operador = pilaExpresiones.eliminarElemento();
        raiz = operador;
    }

    /**
     * Es llamada si la expresión aritmética ingresada es polaca o polaca
     * inversa. Crea un árbol según la expresión. Funciona parecido a
     * crearArbolInfijo pero al no contener parentésis y tener otro orden la
     * expresión se evalua de manera distinta dando un árbol analizando la
     * notación polaca o polaca inversa ingresada
     *
     * @param cadena es la expresión aritmética
     */
    public void crearArbolPolacaTradicionalInversa(String cadena) {
        PilaArbol pilaOperadores = new PilaArbol();
        PilaArbol pilaExpresiones = new PilaArbol();
        NodoArbol token;
        NodoArbol operando1;
        NodoArbol operando2;
        NodoArbol operador;
        char caracterEvaluado;
        for (int i = 0; i < cadena.length(); i++) {
            caracterEvaluado = cadena.charAt(i);
            token = new NodoArbol(caracterEvaluado);
            if (esOperando(caracterEvaluado)) {
                operando2 = pilaExpresiones.eliminarElemento();
                operando1 = pilaExpresiones.eliminarElemento();
                operador = token;
                operador = CrearSubArbol(operando2, operando1, operador);
                pilaExpresiones.insertarElemento(operador);

                pilaOperadores.insertarElemento(token);
            } else {
                pilaExpresiones.insertarElemento(token);
            }
        }
        raiz = pilaExpresiones.eliminarElemento();
    }

    /**
     * @param cadena es la expresión aritmética
     * @return pone en reversa la expresión aritmética ingresada
     */
    public String voltearCadena(String cadena) {
        String nuevaCadena = "";
        for (int i = cadena.length() - 1; i >= 0; i--) {
            nuevaCadena += cadena.charAt(i);
        }
        return nuevaCadena;
    }

    /**
     * Función para invertir el hijo izquierdo con el derecho de un nodo
     *
     * @param subArbol es el nodo al cual se le aplicará el cambio
     */
    public void invertirSubArbol(NodoArbol subArbol) {
        if (subArbol != null) {
            NodoArbol auxNodo = subArbol.getIzquierda();
            subArbol.setIzquierda(subArbol.getDerecha());
            subArbol.setDerecha(auxNodo);
        }
    }

    /**
     * Función que invierte el árbol completo dado una raiz, se usa para
     * invertir el árbol generado al evaluar la expresión aritmética en notación
     * polaca, ya que esta es volteada para evaluarse como si fuese polaca
     * inversa y como resultado su árbol también se encuentra inverso, por lo
     * que se usa esta función para generar el árbol correspondiente
     *
     * @param subArbol nodo raiz para empezar a invertir el árbol
     */
    public void invertirArbol(NodoArbol subArbol) {
        if (subArbol != null) {
            invertirSubArbol(subArbol);
            invertirArbol(subArbol.getIzquierda());
            invertirArbol(subArbol.getDerecha());
        }
    }

    /**
     * Si el parámetro ingresado es 1 la expresión ingresada fue infija por lo
     * que llama a crearArbolInfijo. Si el parámetro ingresado es 2 la expresión
     * ingresada fue polaca por lo que voltea la expresión y la evalua como si
     * fuese polaca inversa, como el árbol resultante también está invertido se
     * llama a la función invertirArbol para generar el árbol correspondiente.
     * Si el parámetro ingresado es 3 la expresión ingresada fue polaca inversa
     * por lo que crea el árbol llamando a crearArbolPolacaTradicionalInversa.
     * Luego se ordenan según la notación que tenga para ser verificada la
     * entrada del usuario con la función verificarExpresion
     *
     * @param num se le ingresa el atributo tipoEntradaIngresada en base al
     * valor realiza una acción u otra
     * @
     */
    public void crearArbol(int num) {
        switch (num) {
            case 1:
                this.crearArbolInfijo(evaluado);
                this.Inorden(raiz);
                break;
            case 2:
                evaluado = voltearCadena(evaluado);
                this.crearArbolPolacaTradicionalInversa(evaluado);
                this.invertirArbol(raiz);
                this.Preorden(raiz);
                break;
            case 3:
                this.crearArbolPolacaTradicionalInversa(evaluado);
                this.Postorden(raiz);
                break;
        }
    }

    /**
     * @param datos es la expresión aritmética a guardar en un archivo TXT
     */
    public void crearArchivo(String datos) {
        if ("".equals(datos)) {
            JOptionPane.showMessageDialog(null, "No se ingresaron datos");
        } else {
            try {
                PrintWriter escritura = new PrintWriter("test\\datos.txt");
                escritura.print(datos);
                escritura.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }

    }

    /**
     * lee los datos guardados en el archivo TXT y los iguala a los atributos
     * (evaluado)
     */
    public void leerDatos() {
        String ruta = "test\\datos.txt";
        File archivo = new File(ruta);
        String auxLinea = "";
        try {
            if (!archivo.exists()) {
                archivo.createNewFile();
            } else {
                FileReader lector1 = new FileReader(archivo);
                BufferedReader lector2 = new BufferedReader(lector1);
                if ((auxLinea = lector2.readLine()) != null) {
                    evaluado = auxLinea;
                }
                lector2.close();
                JOptionPane.showMessageDialog(null, "Contenido Registrado");
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Sucedió un error");
        }
    }

    /**
     * La función verifica si la expresión aritmética ingresada es correcta esto
     * se hace creando el árbol de expresión según el input del usuario y
     * ordenandolo según la notación que tenga, si el input es correcto entonces
     * este debería ser igual al ordenamiento del árbol en ese tipo de notación,
     * por lo tanto si el input y el ordenamiento son iguales la expresión
     * ingresada está planteada correctamente
     *
     * @param cadena verifica si la expresión aritmética (cadena) ingresada está
     * correctamente escrita
     * @return en caso de estar bien planteada retorna true
     */
    public boolean verificarExpresion(String cadena) {
        if (tipoEntradaIngresada == 1) {
            String auxCadena1 = cadena.replace("(", "");
            String auxCadena2 = auxCadena1.replace(")", "");
            return (mostrable.equals(auxCadena2));
        }
        return (mostrable.equals(cadena));
    }
}
