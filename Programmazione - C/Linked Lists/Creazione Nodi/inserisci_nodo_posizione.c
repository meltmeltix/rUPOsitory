#include <stdio.h>
#include <stdlib.h>
#include <time.h>

#define maxNodes 5

typedef struct node {
    int data;
    struct node* next;
} Node;

void genera_lsta(Node** listHead) {
    Node* head = NULL;
    Node* temp = NULL;
    Node* tail = NULL;

    for (unsigned short int i = 0; i < maxNodes; i++) {
        if (i == 0) {
            head = malloc(sizeof(Node));

            if (head != NULL) {
                tail = head;
                head->data = rand() % (50 - 0 + 1) + 0;
                head->next = NULL;
            } else {
                head = NULL;
            }
        } else {
            temp = malloc(sizeof(Node));

            if (temp != NULL) {
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

    while (listHead != NULL) {
        printf("%d ", listHead->data);
        listHead = listHead->next;

        if (listHead != NULL) {
            printf("-> ");
        } else {
            printf("-> NULL");
        }
    }

    printf("\n");
}

void inserisci_nodo_posizione(Node* listHead, int inputValue, int inputPosition) {
    //Partiamo da inizio lista
    int i = 0;

    //Creiamo il nodo precedente alla posizione in input
    //Creiamo il nodo che dovra essere inserito
    Node* prev = NULL;
    Node* newNode = malloc(sizeof(Node));

    //Ci si assicura che il nuovo nodo esista
    if(newNode != NULL) {
        //Si assegna il valore al nuovo nodo
        newNode->data = inputValue;

        //Lo scopo e' quello di andare a prendere il nodo precedente a quello nuovo
        //Si cicla, assegnando i valori al nodo precedente, arrivando prima del nuovo nodo
        while(listHead != NULL && i != inputPosition) {
            i++;
            prev = listHead;
            listHead = listHead->next;
        }

        //Si collega il nodo precedente a quello nuovo
        prev->next = newNode;

        //Si collega il nuovo nodo a quello successivo
        newNode->next = listHead;
    } else {
        printf("Impossibile aggiungere il nodo\n\n");
    }

}

int main(void) {
    Node* randList;

    srand(time(NULL));

    genera_lsta(&randList);
    visualizza_lista(randList);

    int inputValue;
    int inputPosition;

    printf("Inserire la posizione del nuovo nodo da aggiungere: ");
    scanf("%d", &inputPosition);

    printf("Inserire il valore da inserire nel nuovo creato: ");
    scanf("%d", &inputValue);

    inserisci_nodo_posizione(randList, inputValue, inputPosition);
    visualizza_lista(randList);

    return 0;
}