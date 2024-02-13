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

void inserisci_nodo_testa(Node** listHead, int inputValue) {
    //Crea un nuovo nodo
    Node* newHead = malloc(sizeof(Node));

    //Si controlla che si astato creato con successo
    if(newHead != NULL) {
        //Gli si assegna il valore
        newHead->data = inputValue;

        //Si punta il next all'inizio della lista
        newHead->next = *listHead;

        //Si rende quindi la testa della lista il nuovo nodo
        *listHead = newHead;
    } else {
        printf("Errore: impossibile aggiungere nuovo nodo in testa\n\n");
    }
}

int main(void) {
    Node* randList = NULL;

    srand(time(NULL));

    genera_lsta(&randList);
    visualizza_lista(randList);

    int inputValue;
    printf("\nInserire un valore da aggiungere in testa: ");
    scanf("%d", &inputValue);

    inserisci_nodo_testa(&randList, inputValue);
    visualizza_lista(randList);

    return 0;
}