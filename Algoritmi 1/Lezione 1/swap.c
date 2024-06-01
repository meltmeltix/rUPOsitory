/* Esercizio:
    lavora con puntatori e funzioni generiche per implementare due diversi modi 
    di scambiare dati tra due posizioni di memoria. 

    L'obiettivo è completare le funzioni fornite, swap1 e swap2, 
    assicurandoti che funzionino correttamente per vari tipi di dati.
*/

/* vim: set tabstop=4 expandtab shiftwidth=4 softtabstop=4: */

#include <stddef.h>
#include <stdlib.h>
#include <stdio.h>
#include <string.h>

void swap1(void *a, void *b, size_t n);
void swap2(void *a, void *b, size_t n);
void test_swap1();
void test_swap2();

typedef struct {
    int ival;
    double dval;
    char str[10];
} item_t;

int main() {
    test_swap1();
    test_swap2();

    return 0;
}

/*
    Parte 1: Completa la Funzione swap1
        La funzione swap1 è un'implementazione di base di uno scambio
        byte per byte tra due posizioni di memoria. 
        Il tuo compito è completare la funzione riempiendo le parti mancanti.
*/

void swap1(void *a, void *b, size_t n) {
    // TODO: Assegna i puntatori generici a e b a puntatori adeguati pca e pcb
    // ...
    // ...
    unsigned char* pca = a;
    unsigned char* pcb = b;
    unsigned char temp;
    size_t i;  

    if (a == b) { return; }

    for (i = 0; i < n; ++i) {
        /* Mini-swap: swap a single byte */
        // TODO: Scambia i valori in pca e pcb in modo appropriato
        // ...
        temp = pca[i];
        pca[i] = pcb[i];
        pcb[i] = temp;
    }
}

/*
    Parte 2: Completa la Funzione swap2
        La funzione swap2 alloca dinamicamente la memoria e utilizza memmove
        per effettuare lo scambio. 
        Il tuo compito è completare la funzione riempiendo le parti mancanti.
*/
void swap2(void *a, void *b, size_t n) {
    if (a == b) { return; }

    void *tmp = malloc(n); /* Alloca n byte */
    if (tmp == NULL) {
        perror("ERRORE nella funzione di scambio");
        abort();
    }

    // TODO: Usa memmove per scambiare i valori tra a e b utilizzando la memoria allocata
    // Suggerimento: Potresti dover utilizzare tmp come memoria temporanea
    memmove(tmp, a, n);
    memmove(a, b, n);
    memmove(b, tmp, n);

    free(tmp);
}

void test_swap1() {
    int i1 = 3, i2 = 4;
    char s1[] = "Hello", s2[] = "World";
    double d1[] = {1.0, 2.3, 4.4}, d2[] = {5.5, 6.6, 7.7};
    item_t item1 = {3, 3.14, "pi"}, item2 = {2, 2.71, "e"};
    size_t k;

    printf("Test swap1...\n");

    printf("Before swap -> i1: %d, i2: %d\n", i1, i2);
    swap1(&i1, &i2, sizeof i1);
    printf("After swap -> i1: %d, i2: %d\n", i1, i2);

    printf("Before swap -> s1: %s, s2: %s\n", s1, s2);
    swap1(&s1, &s2, sizeof s1);
    printf("After swap -> s1: %s, s2: %s\n", s1, s2);

    for (k = 0; k < sizeof d1/sizeof d1[0]; ++k)
    {
        printf("Before swap -> d1[%lu]: %f, d2[%lu]: %f\n", k, d1[k], k, d2[k]);
    }
    swap1(&d1, &d2, sizeof d1);
    for (k = 0; k < sizeof d1/sizeof d1[0]; ++k)
    {
        printf("After swap -> d1[%lu]: %f, d2[%lu]: %f\n", k, d1[k], k, d2[k]);
    }

    printf("Before swap -> {%d, %f, %s}, {%d, %f, %s}\n", item1.ival, item1.dval, item1.str, item2.ival, item2.dval, item2.str);
    swap1(&item1, &item2, sizeof item1);
    printf("After swap -> {%d, %f, %s}, {%d, %f, %s}\n", item1.ival, item1.dval, item1.str, item2.ival, item2.dval, item2.str);
}

void test_swap2() {
    int i1 = 3, i2 = 4;
    char s1[] = "Hello", s2[] = "World";
    double d1[] = {1.0, 2.3, 4.4}, d2[] = {5.5, 6.6, 7.7};
    item_t item1 = {3, 3.14, "pi"}, item2 = {2, 2.71, "e"};
    size_t k;

    printf("Test swap2...\n");

    printf("Before swap -> i1: %d, i2: %d\n", i1, i2);
    swap2(&i1, &i2, sizeof i1);
    printf("After swap -> i1: %d, i2: %d\n", i1, i2);

    printf("Before swap -> s1: %s, s2: %s\n", s1, s2);
    swap2(&s1, &s2, sizeof s1);
    printf("After swap -> s1: %s, s2: %s\n", s1, s2);

    for (k = 0; k < sizeof d1/sizeof d1[0]; ++k)
    {
        printf("Before swap -> d1[%lu]: %f, d2[%lu]: %f\n", k, d1[k], k, d2[k]);
    }
    swap2(&d1, &d2, sizeof d1);
    for (k = 0; k < sizeof d1/sizeof d1[0]; ++k)
    {
        printf("After swap -> d1[%lu]: %f, d2[%lu]: %f\n", k, d1[k], k, d2[k]);
    }

    printf("Before swap -> {%d, %f, %s}, {%d, %f, %s}\n", item1.ival, item1.dval, item1.str, item2.ival, item2.dval, item2.str);
    swap1(&item1, &item2, sizeof item1);
    printf("After swap -> {%d, %f, %s}, {%d, %f, %s}\n", item1.ival, item1.dval, item1.str, item2.ival, item2.dval, item2.str);
}
