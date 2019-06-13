package run;

import grafo.GrafoDinamic;

public class Main {
	
	public static void main(String[] args) {
		GrafoDinamic grafo = new GrafoDinamic();
		GrafoDinamic grafoK = new GrafoDinamic();
		
		//Cargo grafo
		grafo.agregarVertice(1);
		grafo.agregarVertice(2);
		grafo.agregarVertice(3);
		grafo.agregarVertice(4);
		grafo.agregarVertice(5);
		grafo.agregarVertice(6);
		grafo.agregarVertice(7);
		grafo.agregarVertice(8);
		
		grafo.agregarArista(1, 2, 1);
		grafo.agregarArista(2, 3, 2);
		grafo.agregarArista(2, 4, 5);
		grafo.agregarArista(1, 5, 5);
		grafo.agregarArista(5, 6, 3);
		grafo.agregarArista(5, 7, 8);
		grafo.agregarArista(7, 8, 8);
		
		//Cargo grafo Kruskal
		grafoK.agregarVertice(1);
		grafoK.agregarVertice(2);
		grafoK.agregarVertice(3);
		grafoK.agregarVertice(4);
		grafoK.agregarVertice(5);
		grafoK.agregarVertice(6);
		
		grafoK.agregarArista(1, 2, 6);
		grafoK.agregarArista(2, 1, 6);
		grafoK.agregarArista(1, 3, 1);
		grafoK.agregarArista(3, 1, 1);
		grafoK.agregarArista(1, 4, 5);
		grafoK.agregarArista(4, 1, 5);
		grafoK.agregarArista(2, 3, 5);
		grafoK.agregarArista(3, 2, 5);
		grafoK.agregarArista(2, 5, 3);
		grafoK.agregarArista(5, 2, 3);
		grafoK.agregarArista(3, 4, 5);
		grafoK.agregarArista(4, 3, 5);
		grafoK.agregarArista(3, 5, 6);
		grafoK.agregarArista(5, 3, 6);
		grafoK.agregarArista(3, 6, 4);
		grafoK.agregarArista(6, 3, 4);
		grafoK.agregarArista(4, 6, 2);
		grafoK.agregarArista(6, 4, 2);
		grafoK.agregarArista(5, 6, 6);
		grafoK.agregarArista(6, 5, 6);
		
		
		System.out.println("Depth-First-Search");
		grafo.DFS(1);		
		System.out.println("Breath-First-Search");
		grafo.BFS(1);
		System.out.println("Kruskal");
		grafoK.Kruskal().mostrarMatriz();
		System.out.println("Prim");
		grafoK.Prim().mostrarMatriz();
	}

}
