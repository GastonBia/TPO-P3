package grafo;

import java.util.ArrayList;
import java.util.Collections;

public class GrafoDinamic implements GrafosTDA {
	NodoGrafo nodo;
	int cantidad;

	public void inicializarGrafo(int dim) {
		nodo = null;
		cantidad = 0;
	}
	public void eliminarVertice(int v) {
		NodoGrafo aux = encontrarNodo(v);
		
		if(aux != null) {
			aux = nodo; 
			if(aux.valor == v) {
				nodo = aux.sig;
				System.out.println("1");
			}
			while(aux != null) {
				this.eliminarArista(aux.valor, v);	
				if(aux.sig == this.encontrarNodo(v) && aux.sig != null) {
					aux.sig = aux.sig.sig;
					cantidad--;
					System.out.println("1");
				}
					aux = aux.sig;
					System.out.println("2");
			}
			
		}else {
			System.out.println("El vertice no existe");
		}

	}

	public void agregarVertice(int v) {
		if(this.encontrarNodo(v) == null) {
			
			NodoGrafo aux = new NodoGrafo();
			aux.valor = v;
			aux.lista = null;
			aux.sig = nodo;
			aux.Visitado = false;
			aux.marcado = false;
			nodo = aux;
			cantidad++;
		}	
			
	}
	
	public int[] vertices() {
		
		return null;
	}

	public void agregarArista(int v1, int v2, int peso) {
		if(this.encontrarNodo(v1) != null && this.encontrarNodo(v2) != null) {
			NodoArista aux = new NodoArista();
			aux.origen = v1;
			aux.peso = peso;
			aux.Visitado = false;
			aux.apunta = this.encontrarNodo(v2);
			aux.sig = encontrarNodo(v1).lista;
			encontrarNodo(v1).lista = aux;
		}else {
			System.out.println("No existe alguino de los nodos");
		}
	}
	
	public void eliminarArista(int v1, int v2) {
		NodoGrafo nodo = encontrarNodo(v1);
		NodoArista arista = nodo.lista;
		if(arista != null) {
			if(arista.apunta.valor == v2) {				
				nodo.lista = arista.sig;
			}
			else {
				while(arista.sig != null && arista.apunta.valor != v2) {
					arista = arista.sig;
				}
				if(arista.sig != null) {
					arista.sig = arista.sig.sig;
				}				
			}			
		}
	}

	public boolean existeArista(int v1, int v2) {
		NodoGrafo aux = this.encontrarNodo(v1);
		NodoArista arista = aux.lista ;
		while(arista != null) {
			if(arista.apunta.valor ==v2) {
				return true;
			}
			arista = arista.sig;
		}		
		return false;
	}

	public int pesoArista(int v1, int v2) {
		// TODO Auto-generated method stub
		return 0;
	}

	public void mostrarMatriz() {
		NodoGrafo aux = nodo;
		NodoArista arista;
		while(aux != null) {
		System.out.print(aux.valor + "\t" );
		arista = aux.lista;
			while(arista != null) {
				
				System.out.print(arista.peso + " "  + arista.apunta.valor +  "\t");
				arista = arista.sig;
			}
			System.out.println();
			aux = aux.sig;
		}
	}

	public boolean pertenece(int x) {
		if (this.encontrarNodo(x) != null) {
			return true;
		}
		return false;
	}

	public int mayorArista(int v) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int[] conjuntoAislados() {
		int aislados[]= new int [cantidad];
		NodoGrafo aux = nodo;
		
		while(aux != null) {
			
		}
		return null;
	}

	public void imparAristas() {
		// TODO Auto-generated method stub
		
	}
	
	public NodoGrafo encontrarNodo(int v) {
		NodoGrafo aux = nodo;
		while(aux != null) {
			if(aux.valor == v) {
				return aux;
			}
			aux = aux.sig;
		}
		return null;
	}

	public int[] dephtFirstSearch() {
		return null;
	}
	
	public void DFS(int origen) {
		NodoGrafo vertice = new NodoGrafo();
		ArrayList<NodoArista> aristas = new ArrayList<NodoArista>();
		
		vertice = encontrarNodo(origen);
		aristas = calcularAristas(vertice);
		aristas.forEach((n)->{
			DFS(n.apunta.valor);
		});
		System.out.println(vertice.valor);
	}
	
	public void BFS(int origen) {
		ArrayList<NodoGrafo> vertices = new ArrayList<NodoGrafo>();
		
		vertices.add(encontrarNodo(origen));
		while (!vertices.isEmpty()){
			NodoGrafo aux = new NodoGrafo();
			ArrayList<NodoArista> aristas = new ArrayList<NodoArista>();
			aux = vertices.get(0);
			vertices.remove(0);
			aristas = calcularAristas(aux);
			
			aristas.forEach((n)->{	
				vertices.add(n.apunta);
			});
			System.out.println(aux.valor);
		}
	}
	
	private ArrayList<NodoArista> calcularAristas(NodoGrafo vertice) {	//Metodo para la obtención de todas las aristas de un vertice
		ArrayList<NodoArista> aristas = new ArrayList<NodoArista>();
		NodoArista aristaAux = new NodoArista();
		aristaAux = vertice.lista;
		while (aristaAux != null) {
			aristas.add(aristaAux);
			aristaAux = aristaAux.sig;
		}
		return aristas;
	}
	
	public GrafoDinamic Kruskal() {
		GrafoDinamic T = new GrafoDinamic();
		ArrayList<Integer> verticesL = new ArrayList<Integer>();
		ArrayList<NodoArista> aristas = new ArrayList<NodoArista>();
		ArrayList<ConjuntoTA> componentes = new ArrayList<ConjuntoTA>();
		verticesL=calcularVertices(this);
		
		for (int i = 0; i < verticesL.size(); i++) {	//Agrego todas las aristas a un array de conjuntos
			ConjuntoTA conjunto = new ConjuntoTA();
			conjunto.InicializarConjunto();
			conjunto.Agregar(verticesL.get(i));
			componentes.add(conjunto);
			
			T.agregarVertice(verticesL.get(i));			//Agrego todos los vertices al nuevo grafo T
		}
		
		aristas=calcularAristasTotales();				//Obtengo todas las aristas del grafo
		Collections.sort(aristas);						//Las ordeno crecientes por peso
		
//		aristas.forEach((n)->{							//Verifico que este ordenado
//			System.out.println("Origen: " + n.origen + " Peso: " + n.peso + " Destino: " + n.apunta.valor);
//		});

		while(componentes.size()>1) {					//Mientras exista mas de un conjunto...
			ArrayList<ConjuntoTA> componentesAux = new ArrayList<ConjuntoTA>();
			ConjuntoTA conjuntoOrigen = new ConjuntoTA();
			ConjuntoTA conjuntoDestino = new ConjuntoTA();
			NodoArista auxArista = new NodoArista();
			int verticeOrigen=0, verticeDestino=0;
			conjuntoOrigen.InicializarConjunto();
			conjuntoDestino.InicializarConjunto();
			auxArista = aristas.get(0);
			aristas.remove(0);
			for (int i = 0; i < componentes.size(); i++) {	//Obtengo las posiciones de origen y destino
				if (componentes.get(i).Pertenece(auxArista.origen)){
					verticeOrigen = i;
					conjuntoOrigen = componentes.get(i);
				}
				if (componentes.get(i).Pertenece(auxArista.apunta.valor)){
					verticeDestino = i;
					conjuntoDestino = componentes.get(i);
				}
			}
			
			if(verticeOrigen!=verticeDestino) { 		//¿Origen y destino pertenecen a conjuntos diferentes?
				componentes.remove(conjuntoOrigen);
				componentes.remove(conjuntoDestino);
				while (!conjuntoDestino.ConjuntoVacio()) {
					int elemento = conjuntoDestino.Elegir();
					conjuntoOrigen.Agregar(elemento);
					conjuntoDestino.Sacar(elemento);
				}
				componentesAux.add(conjuntoOrigen);
				componentesAux.addAll(componentes);
				componentes = componentesAux;
				T.agregarArista(auxArista.origen, auxArista.apunta.valor, auxArista.peso);
			}
		}
		return T;
	}
	
	private ArrayList<Integer> calcularVertices(GrafoDinamic g) {	//Metodo para la obtención de todos los vertices de un grafo
		ArrayList<Integer> vertices = new ArrayList<Integer>();
		NodoGrafo verticeAux = new NodoGrafo();
		verticeAux = g.nodo;
		while (verticeAux != null) {
			vertices.add(verticeAux.valor);
			verticeAux = verticeAux.sig;
		}
		return vertices;
	}
	
	private ArrayList<NodoArista> calcularAristasTotales(){			//Metodo para la obtención de todas las aristas del grafo
		ArrayList<NodoArista> aristas = new ArrayList<NodoArista>();
		ArrayList<Integer> verticesL = new ArrayList<Integer>();
		verticesL=calcularVertices(this);
		verticesL.forEach((n)->{
			NodoArista aristaAux = new NodoArista();
			aristaAux = encontrarNodo(n).lista;
			while (aristaAux != null) {
				aristas.add(aristaAux);
				aristaAux = aristaAux.sig;
			}
		});
		return aristas;
	}
}
