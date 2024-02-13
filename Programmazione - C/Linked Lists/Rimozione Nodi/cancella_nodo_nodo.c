#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define maxNodes 5

typedef struct node {
    int data;
    struct node *next;
} Node;

void genera_lsta(Node** listHead) {
    Node* head = NULL;
    Node* temp = NULL;
    Node* tail = NULL;

    for(unsigned short int i = 0; i < maxNodes; i++) {
        if(i == 0) {
            head = malloc(sizeof(Node));

            if(head != NULL) {
                tail = head;
                head->data =  rand() % (50 - 0 + 1) + 0;
                head->next = NULL;
            } else {
                head = NULL;
            }
        } else {
            temp = malloc(sizeof(Node));

            if(temp != NULL) {
                tail->next = temp;
                tail = temp;
                tail->data = rand() % (50 - 0 + 1) + 0;
                tail->next = NULL;
            } else {
                head = NULL;
            }
        }
    }

    *listHead = head;
}

void visualizza_lista(Node* listHead) {
    printf("Lista generata: ");

    while(listHead != NULL) {
        printf("%d ", listHead->data);
        listHead = listHead->next;

        if(listHead != NULL) {
            printf("-> ");
        } else {
            printf("-> NULL");
        }
    }

    printf("\n");
}

void cancella_nodo_nodo(Node** listHead, Node* node) {
    // Ci si assicura che la lista sia effettivamente piena/non sia vuota/abbia un nodo come testa
    if (*listHead == NULL) {
        printf("Errore: impossibile eliminare il nodo nella posizione scelta\n\n");
        return;
    }

    //Si crea un nodo temporaneo al quale si associa il puntatore verso la lista
    Node* temp = *listHead;

    //Si crea un nodo "precedente"
    Node* prev = NULL;

    //Se la testa e' uguale al valore in input, allora lo si elimina e si esce dalla funzione
    if(temp != NULL && temp == node) {
        *listHead = (*listHead)->next;
        free(temp);
        return;
    }

    //Si itera all'interno della lista finche' non si trovi il valore cercato
    while(temp != NULL && temp != node) {
        //Si assegna al nodo precedente il nodo attuale
        prev = temp;

        //Si punta il puntatore al nodo successivo
        temp = temp->next;
    }

    // Ci si assicura che la lista sia effettivamente piena/non sia vuota/abbia un nodo come testa
    if(temp == NULL) {
        printf("Errore: impossibile eliminare il nodo nella posizione scelta\n\n");
        return;
    }

    //Si assegna al next del nodo precedente quello del nodo successivo a quello che deve essere eliminato, collegandoli
    prev->next = temp->next;

    //Si elimina il nodo
    free(temp);
}

int main(void) {
    Node* randList;
    Node* node;

    srand(time(NULL));

    genera_lsta(&randList);
    visualizza_lista(randList);

    int inputValue;

    printf("Inserire il numero del nodo da eliminare: ");
    scanf("%d", &inputValue);

    node = randList;

    for(unsigned short int i = 0; i < inputValue; i++) {
        node = node->next;
    }

    cancella_nodo_nodo(&randList, node);
    visualizza_lista(randList);

    return 0;
}