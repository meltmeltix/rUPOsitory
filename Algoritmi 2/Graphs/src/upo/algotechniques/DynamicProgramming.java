package upo.algotechniques;

import upo.graph.base.Graph;
import upo.graph.base.Vertex;

import java.util.*;

import static java.lang.Integer.MIN_VALUE;
import static java.lang.Integer.numberOfLeadingZeros;

public class DynamicProgramming {
	
	/** Calcola la LCS tra <code>s1</code> e <code>s2</code> utilizzando l'algoritmo visto a lezione.
	 * </br>CONSIGLIO: potete usare i metodi di String per accedere alle posizioni di s1 ed s2.
	 * </br>CONSIGLIO2: potete costruire l'output come un array di caratteri, e poi trasformarlo in stringa,
	 * oppure usare le concatenazioni di stringhe nelle chiamate ricorsive (vedi slide).
	 * 
	 * @param s1 una sequenza di caratteri
	 * @param s2 una sequenza di caratteri
	 * @return una LCS di <code>s1</code> e <code>s2</code>
	 */
	public static String LongestCommonSubsequence(String s1, String s2) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/** Risolve il problema dello zaino 0-1 con l'algoritmo di programmazione dinamica visto a lezione.
	 * 
	 * @param weights un vettore contenente in posizione i-esima, per ogni oggetto oi, il suo peso. 
	 * @param values un vettore contenente in posizione i-esima, per ogni oggetto oi, il suo valore. 
	 * @param maxWeight la capienza dello zaino.
	 * @return un vettore di boolean che contiene, in posizione i-esima, true se l'oggetto i-esimo è
	 * incluso nella soluzione, false altrimenti.
	 */
	public static boolean[] getKnapsack01(int[] weights, int[] values, int maxWeight) {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/** Calcola il Massimo Sottoinsieme Indipendente con l'algoritmo di programmazione dinamica
	 * visto a lezione.
	 * </br>NOTA: nonostante l'algoritmo possa essere applicato solo a grafi non orientati, qui per
	 * semplicità vi viene chiesto di applicarlo anche a grafi orientati. Nel caso in cui il vostro grafo
	 * sia orientato, considerate <u,v> equivalente ad (u,v). Prima di applicare l'algoritmo, preprocessate
	 * il vostro grafo: se sono presenti sia l'arco <u,v> che l'arco <v,u>, eliminate uno dei 2.
	 *
	 * @param graph il grafo sul quale applicare l'algoritmo
	 * @param vertexWeights mappa ogni vertice del grafo con il suo peso
	 * @return una collezione di vertici che rappresenta il MSI del grafo dato
	 * @throws IllegalArgumentException se il grafo dato non è lineare (eventualmente non considerando la direzione
	 * degli archi) o se c'è discrepanza tra i vertici di graph e vertexWeights.
	 */
	public static Collection<Vertex> getMSI(Graph graph, Map<Vertex, Integer> vertexWeights) throws IllegalArgumentException {
        int n = vertexWeights.size();
		List<Set<Vertex>> A = new ArrayList<>(Collections.nCopies(n + 1, null));
		Set<Vertex> vertices = graph.getVertices();
		Iterator<Vertex> it = vertices.iterator();

		A.set(0, new HashSet<>());

		if (it.hasNext()) {
			Set<Vertex> firstSet = new HashSet<>();
			firstSet.add(it.next());
			A.set(1, firstSet);
		}

		for (int i = 2; i <= n; i++)
            A.set(i, compareMaxWeight(A.get(i - 1), A.get(i - 2), it.next(), vertexWeights));

		return A.get(n);
	}

	private static Set<Vertex> compareMaxWeight(
			Set<Vertex> sub1,
			Set<Vertex> sub2,
			Vertex vertexToAdd,
			Map<Vertex, Integer> weights
	) {
		Set<Vertex> auxSub2 = new HashSet<>(sub2);
		auxSub2.add(vertexToAdd);

		int w1 = calculateSetWeight(sub1, weights);
		int w2 = calculateSetWeight(auxSub2, weights);

		if (w1 >= w2) return sub1;
		else return auxSub2;
	}

	private static int calculateSetWeight(Set<Vertex> set, Map<Vertex, Integer> weights) {
		if (set.isEmpty()) return 0;

		int result = MIN_VALUE;
		for (Vertex v:set) {
			int curWeight = weights.get(v);
			if (curWeight > result) result = curWeight;
		}

		return result;
	}
}
