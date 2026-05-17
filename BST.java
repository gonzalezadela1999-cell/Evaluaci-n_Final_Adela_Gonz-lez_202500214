// Clase que representa el nodo del árbol
class Nodo {
    int valor;
    Nodo izquierdo, derecho;

    public Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}

// Clase principal del Árbol Binario de Búsqueda (BST)
class ArbolBinarioBusqueda {
    Nodo raiz;

    public ArbolBinarioBusqueda() {
        this.raiz = null;
    }

    // 1. INSERCIÓN
    public void insert(int valor) {
        raiz = insertarRec(raiz, valor);
    }

    private Nodo insertarRec(Nodo actual, int valor) {
        if (actual == null) {
            return new Nodo(valor);
        }
        if (valor < actual.valor) {
            actual.izquierdo = insertarRec(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = insertarRec(actual.derecho, valor);
        }
        return actual;
    }

    // 2. BÚSQUEDA
    public boolean search(int valor) {
        return buscarRec(raiz, valor);
    }

    private boolean buscarRec(Nodo actual, int valor) {
        if (actual == null) {
            return false;
        }
        if (valor == actual.valor) {
            return true;
        }
        return valor < actual.valor 
            ? buscarRec(actual.izquierdo, valor) 
            : buscarRec(actual.derecho, valor);
    }

    // 3. ELIMINACIÓN
    public void delete(int valor) {
        raiz = eliminarRec(raiz, valor);
    }

    private Nodo eliminarRec(Nodo actual, int valor) {
        if (actual == null) return null;

        // Buscar el nodo a eliminar
        if (valor < actual.valor) {
            actual.izquierdo = eliminarRec(actual.izquierdo, valor);
        } else if (valor > actual.valor) {
            actual.derecho = eliminarRec(actual.derecho, valor);
        } else {
            // ¡Nodo encontrado! Aquí se manejan los 3 casos críticos:

            // Caso 1 y 2: Sin hijos o con un solo hijo
            if (actual.izquierdo == null) {
                return actual.derecho;
            } else if (actual.derecho == null) {
                return actual.izquierdo;
            }

            // Caso 3: Con dos hijos
            // Se busca el sucesor en in-order (el menor del subárbol derecho)
            actual.valor = encontrarMinimo(actual.derecho);
            // Se elimina el sucesor
            actual.derecho = eliminarRec(actual.derecho, actual.valor);
        }
        return actual;
    }

    private int encontrarMinimo(Nodo actual) {
        int minValor = actual.valor;
        while (actual.izquierdo != null) {
            minValor = actual.izquierdo.valor;
            actual = actual.izquierdo;
        }
        return minValor;
    }

    // 4. RECORRIDOS
    public void inOrder() {
        inOrderRec(raiz);
        System.out.println();
    }

    private void inOrderRec(Nodo actual) {
        if (actual != null) {
            inOrderRec(actual.izquierdo);
            System.out.print(actual.valor + " ");
            inOrderRec(actual.derecho);
        }
    }

    public void preOrder() {
        preOrderRec(raiz);
        System.out.println();
    }

    private void preOrderRec(Nodo actual) {
        if (actual != null) {
            System.out.print(actual.valor + " ");
            preOrderRec(actual.izquierdo);
            preOrderRec(actual.derecho);
        }
    }

    public void postOrder() {
        postOrderRec(raiz);
        System.out.println();
    }

    private void postOrderRec(Nodo actual) {
        if (actual != null) {
            postOrderRec(actual.izquierdo);
            postOrderRec(actual.derecho);
            System.out.print(actual.valor + " ");
        }
    }
}

// Clase para ejecutar
public class BST {
    public static void main(String[] args) {
        ArbolBinarioBusqueda arbol = new ArbolBinarioBusqueda();

        System.out.println("--- Insertando 5 valores iniciales ---");
        int[] valores = {50, 30, 70, 20, 40};
        for (int v : valores) {
            arbol.insert(v);
            System.out.println("Insertado: " + v);
        }

        System.out.print("\nRecorrido In-Order (Debe salir ordenado): ");
        arbol.inOrder();

        System.out.println("\nBuscando el valor 40: " + (arbol.search(40) ? "Encontrado" : "No encontrado"));
        System.out.println("Buscando el valor 90: " + (arbol.search(90) ? "Encontrado" : "No encontrado"));

        System.out.println("\n--- Eliminando el nodo 30 (Caso con dos hijos) ---");
        arbol.delete(30);

        System.out.print("Recorrido In-Order después de eliminar: ");
        arbol.inOrder();
        
        System.out.print("Recorrido Pre-Order: ");
        arbol.preOrder();
        
        System.out.print("Recorrido Post-Order: ");
        arbol.postOrder();
    }
}