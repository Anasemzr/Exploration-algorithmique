package algorithme;

public interface IGraph {

	int getValuation(int a, int b);

	int getNbNoeuds();

	boolean estArcOK(int a, int b);

	boolean estNoeudOK(int n);

	void ajouterArc(int a, int valeur, int b);

	boolean aArc(int a, int b);

	int dOut(int n);

	int dIn(int n);


}