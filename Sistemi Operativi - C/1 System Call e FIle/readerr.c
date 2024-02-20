#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>

int main() {
    int f, i, n, ne;									// File, iteratore, numero byte, size output
    char buf[20];										// Buffer di 20 caratteri, stringa

    f = open("fileprova", O_RDONLY); 					// Si apre il file in readonly

    if (f == -1) {
        perror("Errore apertura file");
        return 1;
    } else {
        printf("Numero bytes da leggere (max 20): ");
        scanf("%d", &n);

        if (n > 20) n = 20;
        ne = read(f, buf, n);							// Ritorna il size del buf letto

        printf("letti %d caratteri: ", ne);

        for (i = 0; i < ne; i++) putchar(buf[i]);		// Itera nel buffer

        printf("\n");

        return 0;
    }
}
