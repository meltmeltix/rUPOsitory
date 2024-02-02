#include <stdio.h>
#include <stdlib.h>
#include <time.h>

typedef struct node {
    int data;
    struct node *next;
} Node;

void genera_lista(Node** list, int numNodes) {
    Node* head = NULL;
    Node* temp = NULL;
    Node* tail = NULL;

    for(unsigned short int i = 0; i < numNodes; i++) {
        if(i == 0) {
            head = malloc(sizeof(Node));

            if(head != NULL) {
                tail = head;
                head->data = rand() % (50 - 0 + 1) + 0;
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

    *list = head;
}

void visualizza_lista(Node* list) {
    printf("Lista generata: ");

    while(list != NULL) {
        printf("%d ", list->data);
        list = list->next;

        if(list != NULL) {
            printf("-> ");
        } else {
            printf("-> NULL");
        }
    }

    printf("\n\n");
}

void cancella_nodo_testa(Node** list) {
    Node* new_head = NULL;

    if(*list == NULL) {
        printf("Impossibile eliminare il primo nodo\n\n");
        return;
    }

    new_head = *list;
    *list = (*list)->next;
    free(new_head);
}

void cancella_nodo_posizione(Node** list, int inputPosition) {
    if(*list == NULL) {
        printf("Impossibile eliminare il nodo\n\n");
        return;
    }

    Node* temp = NULL;

    temp = *list;

    if(inputPosition == 0) {
        *list = temp->next;
        free(temp);
        return;
    }

    for(unsigned short int i = 0; temp != NULL && i < inputPosition; i++) {
        temp = temp->next;
    }

    if(temp == NULL || temp->next == NULL) {
        printf("Impossibile eliminare il nodo\n\n");
        return;
    }

    Node* next = temp->next->next;
    free(temp->next);
    temp->next = next;
}

void cancella_nodo_valore(Node** list, int inputValue){
    Node* temp = *list;
    Node* prev = NULL;

    if(temp != NULL && temp->data == inputValue) {
        *list = temp->next;
        free(temp);
        return;
    }

    while(temp != NULL && temp->data != inputValue) {
        prev = temp;
        temp = temp->next;
    }

    if(temp == NULL) {
        printf("Impossibile eliminare il nodo\n\n");
        return;
    }

    prev->next = temp->next;
    free(temp);
}

void cancella_nodo_nodo(Node** list, Node* node){
    Node* temp = *list;
    Node* prev = NULL;

    if (temp != NULL && temp == node) {
        *list = temp->next;
        free(temp);
        return;
    }

    while (temp != NULL && temp != node) {
        prev = temp;
        temp = temp->next;
    }

    if (temp == NULL) {
        return;
    }

    prev->next = temp->next;
    free(temp);
}

void cancella_lista(Node** list){
    Node* current = *list;
    Node* next;

    while(current != NULL) {
        next = current->next;
        free(current);
        current = next;
    }

    *list = NULL;
}

int main(int argc, char* argv[]) {
    if (argc != 2) {
        printf("Errore: sono necessari due argomenti [Nome programma][Numero di nodi]\n");
        return 1;
    }

    int argInput = atoi(argv[1]);
    Node* randList = NULL;

    srand(time(NULL));

    genera_lista(&randList, argInput);
    visualizza_lista(randList);

    int inputValue;
    int inputPosition;

    while (inputValue != 0) {
        printf("\nScegliere una delle seguenti opzioni:\n");
        printf("Cancellare un nodo in testa\n");
        printf("Cancellare un nodo in base alla posizione\n");
        printf("Cancellare un nodo in base al valore\n");
        printf("-----------------------------------------\n");
        printf("Digitare una opzione: ");
        scanf("%d", &inputValue);

        switch (inputValue) {
            case 1:
                cancella_nodo_testa(&randList);
                visualizza_lista(randList);
                break;
            case 2:
                printf("Inserire a quale posizione rimuovere il nodo: ");
                scanf("%d", &inputPosition);

                cancella_nodo_posizione(&randList, inputPosition);
                visualizza_lista(randList);
                break;
            case 3:
                printf("Inserire il valore da rimuovere dalla lista: ");
                scanf("%d", &inputValue);

                cancella_nodo_valore(&randList, inputValue);
                visualizza_lista(randList);
                break;
            case 4:
                cancella_lista(&randList);
                inputValue = 0;
                break;
            case 0:
                break;
            default:
                printf("Questa operazione non esiste\n\n");
                break;
        }
    }

    return 0;
}