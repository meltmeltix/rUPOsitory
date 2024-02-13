#include <stdio.h>
#include <stdlib.h>

#define MAX 6

void visualizza_lista(int A[], int r) {
    int i;

    printf("\n\nLista: ");

    for(i = 0; i < r; i++) {
        printf("%d ", A[i]);
    }
}

void Swap(int i, int j, int A[]) {
    int temp;

    temp = A[i];
    A[i] = A[j];
    A[j] = temp;
}

int Partition(int A[], int p, int r) {
    int i, pivot, pivotpos;

    Swap(p, (p+r)/2, A);

    pivot = A[p];
    pivotpos = p;

    for(i = p + 1; i <= r; i++) {
        if(A[i] < pivot) {
            Swap(++pivotpos, i, A);
        }
    }

    Swap(p, pivotpos, A);

    return pivotpos;
}

void QuickSort(int A[], int p, int r) {
    int q;

    /*
    A e' l'array da sistemare
    p e' l'indice iniziale dell'array
    r e' l'indice finale dell'array
    q e' l'indice che si occupa di "partizionare" l'array, indicizzando la meta'
    */

    //Si va a controllare se gli indici sono ancora diversi. 
    //Andando avanti con la divisione, si arrivera' a valori uguali
    if(p < r) {
        q = Partition(A, p, r);
        QuickSort(A, p, q-1);
        QuickSort(A, q+1, r);
    }
}

int main(void) {
    int p = 0;
    int r = MAX;
    int A[] = {2, 4, 5, 0, 3, 6};
    
    visualizza_lista(A, r);

    QuickSort(A, p, r);
    
    visualizza_lista(A, r);
}