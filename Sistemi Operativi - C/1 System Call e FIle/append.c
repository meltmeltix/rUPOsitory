#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

int main() {
    int f, n, ne, x;
    char testo[100];

    f = open("fileprova", O_RDWR | O_APPEND | O_CREAT, S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP);

    if (f == -1) {
        perror("Errore apertura file");
        return 1; /* qualcosa non e' andato bene */
    }

    else {
        printf("Scrivere una striga: ");
        scanf("%[^\n]s", testo);
        n = strlen(testo);

        x = write(f, testo, n);
        if (x == -1) {
            perror("Errore scritura file");
            return 1; /* qualcosa non e' andato bene */
        }

        close(f);
        return 0;
    }
}
