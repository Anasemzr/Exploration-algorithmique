package algorithme;

import java.util.LinkedList;

import graphes.ArcNEégatifEx;
import graphes.NoPathEx;
import tests.algorithme.IPCC;

public class PCCDijkstra implements IPCC {
	private int[] precSommet;
	private int[] valeurChemin;
	private boolean[] sommetTraiter;
	private LinkedList<Integer> voisin;
	private LinkedList<Integer> voisinInterm;
	private IGraph graph;
	
	public PCCDijkstra(IGraph graph) {
		this.graph=graph;
		this.precSommet=new int[this.graph.getNbNoeuds()+1];
		this.valeurChemin=new int[this.graph.getNbNoeuds()+1];
		this.sommetTraiter=new boolean[this.graph.getNbNoeuds()+1];
		voisin = new LinkedList<Integer>();
		voisinInterm = new LinkedList<Integer>();
	}
	
	public static boolean estOk(IGraph graph) {
		for(int i=0;i<graph.getNbNoeuds();++i) {
			for(int j=0;j<graph.getNbNoeuds();++j) {
				if(graph.aArc(i+1, j+1)) {
					if(graph.getValuation(i+1, j+1)<0) {
						throw new ArcNEégatifEx();
					}
				}
			}
		}
		return true;
	}
	
	public void initialisation() {
		for (int i = 0; i < valeurChemin.length; i++) {
			sommetTraiter[i]=false;
			valeurChemin[i]=Integer.MAX_VALUE;
		}
	}
	
	public boolean parcouru() {
		boolean allSommetParcouru=true;
		for (boolean b:this.sommetTraiter) {
			if(!b) {
				allSommetParcouru=false;
			}
		}
		return allSommetParcouru;
	}
	
	
	public void trouveVoisin(int sommet) {
		voisinInterm.clear();
		for (int i = 1; i < this.graph.getNbNoeuds()+1; i++) {
				if(this.graph.aArc(sommet, i)) {
					
					voisinInterm.add(i);
					
				}
				
		}
		
	}
	
	
	
	
	
	private void Algodijkstra(int sommet1,int sommet2) {
		this.initialisation();
		this.valeurChemin[sommet1]=0;
		this.voisin.add(sommet1);
		
		while(!this.parcouru()) {
			if(this.voisin.isEmpty()) {
				this.voisin.add(1);
			}
		
			int s = this.voisin.getFirst();

			
			if(s==sommet2) {
				break;
			}
			
			this.sommetTraiter[s]=true;
			
			this.trouveVoisin(s);
			
			for(int v:this.voisinInterm) {
				
				if(!this.sommetTraiter[v]) {
					
					this.valeurChemin[v]=this.valeurChemin[s]+this.graph.getValuation(s, v);
					this.precSommet[v]=s;
					this.voisin.add(v);
				}
				
				
			}
			this.voisin.removeFirst();
		}
	}
	

	
	public String algo(int sommet1, int sommet2)
	{
		
		Algodijkstra(sommet1,sommet2);
		StringBuilder s= new StringBuilder("");
		
		int indice=sommet2;
		while(indice!=sommet1) {
			try 
			{
				s.insert(0,indice+" ");
				indice=this.precSommet[indice];
				
				if(valeurChemin[indice]==Integer.MAX_VALUE) {
					throw new NoPathEx();
					
				}
				
			}
			catch(NoPathEx n) {
				return "pas de chemin entre "+sommet1+" et "+sommet2;
			}
		 
		}
		s.insert(0,sommet1+" ");
		
		/*for (int i = 0; i < valeurChemin.length; i++) {
			
			System.out.println(valeurChemin[i]+"--"+precSommet[i]);
		}*/
		return "Dijkstra"+"\n"+this.valeurChemin[sommet2]+"\n"+s.toString();
	
	}
}
