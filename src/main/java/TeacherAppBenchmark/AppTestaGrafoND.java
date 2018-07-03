package TeacherAppBenchmark;

import java.util.LinkedList;

import TeacherAppBenchmark.graphs.Edge;
import TeacherAppBenchmark.graphs.GraphLad.TADGrafoLadjND;
import TeacherAppBenchmark.graphs.Vertice;

public class AppTestaGrafoND {		
	public static void main(String[] args){
		//TADGrafoMadjND gnd = new TADGrafoMadjND();
		TADGrafoLadjND gnd = new TADGrafoLadjND();
		
		// Povoando o grafo gnd.
		Vertice v = gnd.insertVertex(null);
		v.setLabel("V");
		
		Vertice u = gnd.insertVertex(null);
		u.setLabel("U");
		
		Vertice x = gnd.insertVertex(null);
		x.setLabel("X");
		
		Vertice z = gnd.insertVertex(null);
		z.setLabel("Z");
		
		Vertice w = gnd.insertVertex(null);
		w.setLabel("W");
		
		Vertice y = gnd.insertVertex(null);
		y.setLabel("Y");
		
		Edge a = gnd.insertEdge(v,u,null);
		a.setLabel("a");
		
		Edge b = gnd.insertEdge(v,x, null);
		b.setLabel("b");
		
		Edge c = gnd.insertEdge(u,w, null);
		c.setLabel("c");
		
		Edge d = gnd.insertEdge(v,w, null);
		d.setLabel("d");
		
		Edge e = gnd.insertEdge(x,w,null);
		e.setLabel("e");
		
		Edge f = gnd.insertEdge(w,y, null);
		f.setLabel("f");
		
		Edge g = gnd.insertEdge(x,y,null);
		g.setLabel("g");
		
		Edge h = gnd.insertEdge(x,z,null);
		h.setLabel("h");
		
		//gnd.printmatriz();
		
		System.out.println();
		
		// Testando interface do grafo
		LinkedList<Vertice> lvs = gnd.vertices();
		LinkedList<Vertice> ladjs = gnd.vertices();
		int i, j;
		
		System.out.println("Adjacentes dos vértices:");
		for(i=0; i < lvs.size(); i++){
			ladjs = gnd.adjacenteVertices(lvs.get(i));
			System.out.print("Adjacentes de " + lvs.get(i).getLabel() + ": ");
			for(j=0; j < ladjs.size()-1; j++)
				System.out.print(ladjs.get(j).getLabel() + ", ");
			System.out.println(ladjs.get(j).getLabel());
		} // for(i..
		
		System.out.println();
		
		System.out.println("Graus dos vértices:");
		for(i=0; i < lvs.size(); i++)
			System.out.println("Vértice " + lvs.get(i).getLabel() + " grau " + gnd.degree(lvs.get(i)));
		
		gnd.areAdjacent(v,u);
		gnd.areAdjacent(x,z);
		gnd.areAdjacent(w,y);
		gnd.areAdjacent(v,z);
		gnd.areAdjacent(w,z);
		gnd.areAdjacent(u,y);
		
		System.out.println();
		
		System.out.println("Total de vertices: " + gnd.numVertices());
		System.out.println("Total de arestas: " + gnd.numEdges());
		
		// Construa a classe TPA2GS (Grafo TPA para grafo GraphStream). Nesta classe
		// construa o método exibeGrafo(grafo TPA). O método exibe o visual do grafo TPA
		// passado como parâmetro.
		//TPA2GS.exibeGrafo(gnd);
		
		System.out.println();
		
		System.out.println("Removendo todos os vértices:");
		for(i = 0; i < lvs.size(); i++)
			gnd.removeVertex(lvs.get(i));
		
		System.out.println();
		
		System.out.println("Total de vertices: " + gnd.numVertices());
		System.out.println("Total de arestas: " + gnd.numEdges());
		
		System.out.println();	
	} // fim main

} // fim de AppTestaGrafoND