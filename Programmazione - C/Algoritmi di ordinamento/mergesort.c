#include <stdio.h>
#include <stdlib.h>

#define MAX 5

void visualizza_dati(int A[], int p, int r) {
    printf("Lista: ");

    for (unsigned short int i = 0; i <= r; i++) {
        printf("%d ", A[i]);
    }

    printf("\np = %d\nr = %d\n\n", p, r);
}

void Merge(int A[], int p, int q, int r) {
    /*
    B e' un array temporaneo che si occupera' di salvare l'ordine delle operazioni
    i sara' l'indice scorrevole della prima meta'
    j sara' l'indice scorrevole della seconda meta'
    k e' l'indice scorrevole "globale" dell'array
    */

    int B[MAX], i, j, k;

    i = p;
    j = (q + 1);
    k = p;


    //Si va a iterare tra le due meta', confrontando le celle di ognuna, 
    //e quindi salvando i dati ad ogni posizione
    while (i <= q && j <= r) {
        if (A[i] < A[j]) {
            B[k++] = A[i++];
        } else {
            B[k++] = A[j++];
        }
    }

    //Puo' capitare che l'iterazione termini prima in base ai valori dell'array

    //Si itera quindi solo nella prima meta' per salvare i dati mancanti
    while (i <= q) {
        B[k++] = A[i++];
    }

    //Poi si itera nella seconda meta' per salvare i dati mancanti
    while (j <= r) {
        B[k++] = A[j++];
    }


    //Poi si itera copiando i nuovi dati nell'array
    for (k = p; k < r; k++) {
        A[k] = B[k];
    }
}

void MergeSort(int A[], int p, int r) {
    int q;

    /*
    A e' l'array da sistemare
    p e' l'indice iniziale dell'array
    r e' l'indice finale dell'array
    q e' l'indice che si occupa di "partizionare" l'array, indicizzando la meta'
    */

    //Si va a controllare se gli indici sono ancora diversi. 
    //Andando avanti con la divisione, si arrivera' a valori uguali
    if (p < r) {
        //Si trova la meta' dell'array
        q = (p + r) / 2;

        //Si va ad effettuare il mergesort della prima meta...
        MergeSort(A, p, q);

        //E poi della seconda
        MergeSort(A, (q + 1), r);

        //Si va quindi a formattare i dati
        Merge(A, p, q, r);
    }
}

int main(void) {
    int p = 0;
    int r = MAX;
    int A[] = {2, 4, 5, 0, 3, 6};

    MergeSort(A, p, r);
}