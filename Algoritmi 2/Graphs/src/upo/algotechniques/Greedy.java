package upo.algotechniques;

import java.util.*;
import java.util.stream.IntStream;

public class Greedy {
	
	/** Calcola una codifica di Huffman per i caratteri contenuti nel vettore characters, date le frequenze 
	 * contenute in f. f[i] contiene la frequenza (in percentuale, 0<=f[i]<=100) del carattere characters[i] 
	 * nel testo per il quale si vuole calcolare la codifica.
	 * </br>CONSIGLIO: potete estendere o usare un vostro grafo non pesato non orientato per rappresentare la 
	 * foresta di Huffman.
	 * </br>CONSIGLIO2: potete implementate una PriorityQueue dall'interfaccia in upo.additionalstructures,
	 * oppure aggiungere al grafo del primo consiglio delle priorità.
	 * 
	 * @param frequencies mappa i caratteri dell'alfabeto per i quali calcolare la codifica nelle loro frequenze nel testo.
	 * @return una Map che mappa ciascun carattere in una stringa (di bit) che rappresenta la sua codifica secondo
	 * l'algoritmo visto a lezione.
	 */
	public static Map<Character,String> getHuffmanCodes(Map<Character,Integer> frequencies) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/** Trova il massimo insieme di intervalli disgiunti, tra tutti quelli identificati da [starting[i], ending[i]],
	 * (0<=i<=starting.length) utilizzando l'algoritmo Greedy. Il risultato contiene gli indici degli intervalli selezionati.
	 * Ad esempio, con starting=[2,5,6] ed ending=[5,7,8] il risultato sar� uguale a [0,2] perch� il massimo insieme
	 * di intervalli disgiunti � {[2,5],[6,8]}.
	 * 
	 * @param starting il vettore dei tempi di inizio degli intervalli
	 * @param ending il vettore dei tempi di fine degli intervalli
	 * @return un vettore contenente gli indici del massimo insieme di intervalli disgiunti
	 */
	public static Integer[] getMaxDisjointIntervals(Integer[] starting, Integer[] ending) {
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	/** Trova lo scheduling massimale, utilizzando l'algoritmo di Moore, tra i job identificati dai vettori duration e deadline
	 * (duration[i] e deadline[i] sono, rispettivamente, la durata e la scadenza del job L_i). Il risultato contiene, nell'ordine
	 * selezionato dall'algoritmo, gli indici dei job nello scheduling massimale.
	 * 
	 * @param duration il vettore delle durate
	 * @param deadline il vettore delle scadenze
	 * @return un vettore contenente gli indici dei job in uno scheduling massimale
	 */
	public static Integer[] getMooreMaxJobs(Integer[] duration, Integer[] deadline) {
		if (duration.length != deadline.length) throw new IllegalArgumentException();

		int n = duration.length;
		int t = 0;
		Integer[] jobIndices = sortByDeadline(duration, deadline);
		List<Integer> sol = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			sol.add(jobIndices[i]);
			t += duration[jobIndices[i]];

			if (t > deadline[jobIndices[i]]) {
				int indexOfLargest = 0;

                for (Integer integer : sol) {
                    if (duration[integer] > duration[jobIndices[indexOfLargest]])
                        indexOfLargest = jobIndices[integer];
                }

				t -= duration[jobIndices[indexOfLargest]];
				sol.remove(jobIndices[indexOfLargest]);
			}
		}

		return sol.toArray(new Integer[0]);
	}

	private static Integer[] sortByDeadline(Integer[] duration, Integer[] deadline) {
		int jobs = duration.length;

		Integer[] jobIndices = IntStream.range(0, jobs).boxed().toArray(Integer[]::new);
		Arrays.sort(jobIndices, Comparator.comparingInt(index -> deadline[index]));

		return jobIndices;
	}
}
