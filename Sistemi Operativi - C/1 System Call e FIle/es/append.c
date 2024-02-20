#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

int main (int argc, char *argv[]) {
    int f, n, ne, x;
    char testo[100] = "Something";

    if (argc != 2) {
        perror("Nome del file non presente o troppi valori inseriti");
        return 1;
    }

    f = open(argv[1], O_WRONLY | O_APPEND | O_CREAT, S_IRUSR | S_IWUSR | S_IRGRP | S_IWGRP);

    n = strlen(testo);
    x = write(f, testo, n);
    if (x == -1) {
        perror("Errore scrittura file"); 
        return 1;
    } 

    close(f);
    return 0;
}