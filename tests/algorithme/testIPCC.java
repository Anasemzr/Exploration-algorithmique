package tests.algorithme;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import algorithme.IGraph;
import algorithme.PCCBellman;
import algorithme.PCCDijkstra;
import graphes.ihm.Arc;
import graphes.ihm.GraphImporter;

class testIPCC {
	private static final String REPERTOIRE_ENONCE = "graphes/";
	private static final String REPERTOIRE_REPONSE = "reponses/";
	private static final String REPERTOIRE_DIJKSTRA = "ac/";
	private static final String REPERTOIRE_BELLMAN = "sc/";
	private static List<String> listeFichiers;
	
	static {
		listeFichiers = new ArrayList<>();
		listeFichiers.add("g-10-1.txt");
		listeFichiers.add("g-10-2.txt");
		
		listeFichiers.add("g-10-3.txt");
		listeFichiers.add("g-10-4.txt");
		listeFichiers.add("g-10-5.txt");
		listeFichiers.add("g-10-6.txt");
		listeFichiers.add("g-10-7.txt");
		listeFichiers.add("g-10-8.txt");
		listeFichiers.add("g-10-9.txt");
		listeFichiers.add("g-10-10.txt");
		
		listeFichiers.add("g-100-1.txt");
		listeFichiers.add("g-100-2.txt");
		listeFichiers.add("g-100-3.txt");
		listeFichiers.add("g-100-4.txt");
		listeFichiers.add("g-100-5.txt");
		
		listeFichiers.add("g-1000-1.txt");
		listeFichiers.add("g-1000-2.txt");
		
		listeFichiers.add("g-10000-1.txt");
		listeFichiers.add("g-10000-2.txt");
		
		
		listeFichiers.add("g-100000-1.txt");
		listeFichiers.add("g-100000-2.txt");
		listeFichiers.add("g-1000000-1.txt");
		listeFichiers.add("g-1000000-2.txt");
	
	}
	
	
	@Test
	void testDijkstra() throws NumberFormatException, FileNotFoundException, IOException {
		for (String fichier : listeFichiers) {
			Arc df = new Arc();
			IGraph g = GraphImporter.importer(REPERTOIRE_ENONCE+REPERTOIRE_DIJKSTRA + fichier,df);
			IPCC d = new PCCDijkstra(g);
			
			String reponse = GraphImporter.importerReponse(REPERTOIRE_REPONSE+REPERTOIRE_DIJKSTRA+fichier.replace('g', 'r'));
			String algo = d.algo(df.getSource(), df.getDestination());
			
			assertEquals(reponse,algo);
		}
	}
	
	@Test
	void testBellman() throws NumberFormatException, FileNotFoundException, IOException {
		
		
		for (String fichier : listeFichiers) {
			Arc df = new Arc();
			IGraph g = GraphImporter.importer(REPERTOIRE_ENONCE+REPERTOIRE_BELLMAN + fichier,df);
			IPCC d = new PCCBellman(g);
			
			String reponse = GraphImporter.importerReponse(REPERTOIRE_REPONSE+REPERTOIRE_BELLMAN+fichier.replace('g', 'r'));
			String algo = d.algo(df.getSource(), df.getDestination());
			
			assertEquals(reponse,algo);
		}
		
		
		
	}
}
