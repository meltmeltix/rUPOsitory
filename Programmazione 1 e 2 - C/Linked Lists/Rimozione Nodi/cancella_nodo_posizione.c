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

void cancella_nodo_posizione(Node** listHead, int inputPosition) {
    // Ci si assicura che la lista sia effettivamente piena/non sia vuota/abbia un nodo come testa
    if(*listHead == NULL) {
        printf("Errore: impossibile eliminare il nodo nella posizione scelta\n\n");
        return;
    }

    //Creazione di un nodo temporaneo vuoto
    Node* temp = NULL;

    //Si assegna al nodo temporaneo il puntatore alla lista
    temp = *listHead;

    //Se la posizione e' zero, si elimina la testa della lista
    if(inputPosition == 0) {
        *listHead = temp->next;
        free(temp);
        return;
    }

    //Si itera nella lista finche' non si trova la posizione necessaria/selezionata
    for(unsigned short int i = 0; i < inputPosition; i++) {
        temp = temp->next;
    }

    //Si ricontrolla che la lista o il noto trovato esista/contenga valori
    if(temp == NULL || temp->next == NULL) {
        printf("Errore: impossibile eliminare il nodo nella posizione scelta\n\n");
        return;
    }

    //Si crea un nuovo nodo al quale si assegna un puntatore al nodo successivo a quello selezionato
    Node* next = temp->next->next;

    //Si rimuove dalla memoria il nodo successivo a quello temporaneo o precedente
    free(temp->next);

    //Si collega il puntatore temporaneo della lista al nodo next
    temp->next = next;
}

int main(void) {
    Node* randList;

    srand(time(NULL));

    genera_lsta(&randList);
    visualizza_lista(randList);

    int inputPosition;

    printf("Inserire la posizione alla quale verra eliminato un nodo: ");
    scanf("%d", &inputPosition);

    cancella_nodo_posizione(&randList, inputPosition);
    visualizza_lista(randList);

    return 0;
}