package algorithme;

import java.util.ArrayList;
import java.util.Arrays;

import graphes.CircuitAbsorbantEx;
import graphes.NoPathEx;
import tests.algorithme.IPCC;

public class PCCBellman implements IPCC {
		private int[] precSommet;
		private int[] valeurChemin;
		private IGraph graph;
		
		
		public PCCBellman(IGraph graph) {
			this.graph=graph;
			this.precSommet=new int[this.graph.getNbNoeuds()+1];
			this.valeurChemin=new int[this.graph.getNbNoeuds()+1];
		}
		
		
		private static boolean existeChemin(IGraph graph,int sommet1, int sommet2) {
			ArrayList<Integer> file = new ArrayList<Integer>();
		    boolean[] visites = new boolean[graph.getNbNoeuds()];
		    Arrays.fill(visites,false);
		    file.add(sommet1);
		    int courant;
		    
		    while (!file.isEmpty())
		    {
		        courant = file.remove(0);
		        visites[courant] = true;
		        for (int j = 0; j < graph.getNbNoeuds(); j++) {
					if (graph.getValuation(courant+1, j+1) > 0 && visites[j] == false)
					{
						file.add(j);
		                visites[j] = true;
					}
		              
		            //Si i est un noeud adjacent et égal à v (destination)
		            //donc il existe un chemin de u à i
		            
					else if ( graph.getValuation(courant+1, j+1) > 0 && j == sommet2)
					{
						return true;
					}
		                
				}
		            
		    }
		    //pas de chemin entre u et v
		    return false;
		}
		
		public static boolean estOk(IGraph graph) {
		    for (int j = 0; j < graph.getNbNoeuds(); j++) {
				try {
					if (existeChemin(graph, j, j) == true) {
						return false;
					}
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					throw new CircuitAbsorbantEx();
				}
			}
			return true;
		    
			
		}
			
		
		    
		
		
		
		public void initialisation() {
			
			for (int i = 0; i < valeurChemin.length; i++) {
				valeurChemin[i]=Integer.MAX_VALUE;
			}
		
			
		}
		
	
		
		private void Algobellman(int sommet1,int sommet2) {
			this.initialisation();
			
			this.valeurChemin[sommet1]=0;
			boolean flag;
			
			for(int i=1;i<graph.getNbNoeuds()+1;++i) {
				flag=false;
				for(int a=1;a<graph.getNbNoeuds()+1;++a) {
					for(int b=1;b<graph.getNbNoeuds()+1;++b) {
						if(this.graph.aArc(a, b))
						{
							
							if (valeurChemin[a]!=Integer.MAX_VALUE && (valeurChemin[a] + this.graph.getValuation(a,b)) < valeurChemin[b])
							{
								valeurChemin[b] = valeurChemin[a] + this.graph.getValuation(a,b);
								precSommet[b]=a;
								flag=true;
							}
							
							
				        }
					}
			            
				 }
				if(flag==false) {
					break;
				}
			}
		}
		
	
		
		
		@Override
		public String algo(int sommet1, int sommet2)
		{
			
			
			
			Algobellman(sommet1,sommet2);
			
			
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
			
			
			return "Bellman sans circuit"+"\n"+this.valeurChemin[sommet2]+"\n"+s.toString();
		
		}
}
