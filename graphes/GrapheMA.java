package graphes;

import algorithme.IGraph;

public class GrapheMA implements IGraph {
	private boolean[][] ma;
	private int[][] valuation;

	public GrapheMA(int nbNoeuds) {
		ma = new boolean[nbNoeuds][nbNoeuds];
		valuation = new int[nbNoeuds][nbNoeuds];
	}
	
	@Override
	public int getValuation(int a,int b) {
		assert aArc(a,b);
		return valuation[a-1][b-1];
	}
	
	
	@Override
	public int getNbNoeuds() { return ma.length; }
	
	public boolean estNoeudOK(int n) {
		return n >= 1 && n <= getNbNoeuds();
	}
	
	@Override
	public boolean estArcOK(int a, int b) {
		return estNoeudOK(a) && estNoeudOK(b);
	}
	
	@Override
	public void ajouterArc(int a, int valeur,int b) {
		assert estArcOK(a,b);
		assert !ma[a-1][b-1];
		ma[a-1][b-1] = true;
		valuation[a-1][b-1]=valeur;
	}
	
	@Override
	public String toString() {
		String s = "";
		for (int i = 1; i <= getNbNoeuds(); ++i) {
			for (int j = 1; j<= getNbNoeuds(); ++j)
				s += valeur(i,j) + " ";
			s+="\n";
		}
		return s;
	}

	@Override
	public boolean aArc(int a, int b) {
		assert estArcOK(a,b);
		assert estNoeudOK(b);
		return ma[a-1][b-1];
	}
	
	private int valeur(int a, int b) {
		assert estArcOK(a,b);
		return (aArc(a,b))? 1 : 0;
	}
	
	@Override
	public int dOut(int n) {
		assert estNoeudOK(n);
		int d = 0;
		for (int i = 1; i <= getNbNoeuds(); ++i)
			if (ma[n-1][i-1])
				++d;
		return d;
	}

	@Override
	public int dIn(int n) {
		assert estNoeudOK(n);
		int d = 0;
		for (int i = 1; i <= getNbNoeuds(); ++i)
			if (ma[i-1][n-1])
				++d;
		return d;
	}
}
