package graphes.ihm;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import algorithme.IGraph;
import graphes.GrapheLA;

public class GraphImporter {
	

public static void verifierGraphes() throws FileNotFoundException {
	IGraph g;
	Arc df = new Arc(); 
	String dirStr = System.getProperty("user.dir")+ "\\graphes\\sc";
	System.out.println("Working Directory = " + dirStr);
	File dir = new File(dirStr);
	  File[] directoryListing = dir.listFiles();
	  if (directoryListing != null) {
	    for (File child : directoryListing) {
	      System.out.println(child);
	      g = importer(child, df);
	      System.out.println(g.getNbNoeuds() + " sommets");
	      System.out.println("debut et fin du chemin à trouver : "+ df.getSource()+ " ==> "+ df.getDestination()+"\n");
	    }
	  } else {
	    System.out.println("Erreur : "+ dirStr + " n'est pas un réperoire");
	  }
}

	public static Arc parse(String string) {
		String[] parts = string.split(" ");
		int source, valuation, destination;
		try {  
			source = Integer.parseInt(parts[0]);
			valuation = Integer.valueOf(parts[1]);
			destination = Integer.parseInt(parts[2]);
		} catch (Exception e) {
			throw new IllegalArgumentException(string + " n'est pas un arc");
		}
		Arc a = new Arc(source, valuation, destination);
		return a;
	}
	
	private static IGraph importer(File file, Arc df) throws FileNotFoundException {
		
		Scanner sc = new Scanner(file);
		String line;
		IGraph g;
		if (! sc.hasNextLine()) {
			sc.close();
    		throw new IllegalArgumentException("Pas de graphe dans "+ file);
		}
		line = sc.nextLine();
		int nbNodes = Integer.parseInt(line.trim());
		g = new GrapheLA(nbNodes);
		Arc a;
		while (sc.hasNextLine()) {
			line = sc.nextLine();
			a = parse(line);
			if (sc.hasNextLine())
				g.ajouterArc(a.getSource(),  a.getValuation(), a.getDestination());
			else {// la derniere ligne n'est pas un arc mais le debut et la fin du chemin à trouver
				df.set(a);
			}
		}
		sc.close();
		return g;		
	}
	public static IGraph importer(String filepath, Arc df) 
								throws  NumberFormatException, IOException, FileNotFoundException {
		File file = new File(filepath);
		return importer(file, df);
      }
	
	public static String importerReponse(String filePath) throws FileNotFoundException {
		File file = new File(filePath);
		Scanner sc = new Scanner(file);
		String line;
		line = sc.nextLine();
		if (! sc.hasNextLine()) {
			sc.close();
			return line;
		}
		line +="\n"+ sc.nextLine(); // nom de l'algo recommandé
		line +="\n"+ sc.nextLine(); // distance attendue
		sc.close();
		return line;
		}
}
