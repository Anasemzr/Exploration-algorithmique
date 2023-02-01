package graphes;

import java.util.ArrayList;

import algorithme.IGraph;

public class GrapheLA implements IGraph {
	private ArrayList<ArrayList<Integer>> la;
	private int[][] valuation;
	
	public GrapheLA(int nbNoeuds) {
		la = new ArrayList<>();
		valuation = new int[nbNoeuds][nbNoeuds];
		for (int i = 0; i < nbNoeuds; ++i)
			la.add(new ArrayList<>());
	}

	@Override
	public int getValuation(int a,int b) {
		assert aArc(a,b);
		return valuation[a-1][b-1];
	}

	
	@Override
	public int getNbNoeuds() {
		return la.size();
	}
	
	@Override
	public boolean estArcOK(int a, int b) {
		return estNoeudOK(a) && estNoeudOK(b);
	}
	
	@Override
	public boolean estNoeudOK(int n) {
		return n >= 1 && n <= getNbNoeuds();
	}

	@Override
	public void ajouterArc(int a, int valeur,int b) {
		assert ! aArc(a,b);
		la.get(a-1).add(b);
		valuation[a-1][b-1]=valeur;
	}

	@Override
	public boolean aArc(int a, int b) {
		assert estArcOK(a,b);
		return la.get(a-1).contains(b);
	}
	
	@Override
	public String toString() {
		String s = "";
		for(int i = 0; i< la.size(); ++i) {
			s+= (i+1) + " -> ";
			for (Integer j : la.get(i)) 
				s+= j + " ";
			s+="\n";
		}
		return s;
	}

	@Override
	public int dOut(int n) {
		assert estNoeudOK(n);
		return la.get(n-1).size();
	}

	@Override
	public int dIn(int n) {
		assert estNoeudOK(n);
		int d = 0;
		for(int i = 0; i< la.size(); ++i)
			if (la.get(i).contains(n))
				++d;
		return d;
	}
}
