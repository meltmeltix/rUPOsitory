#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>

int main (int argc, char *argv[]) {
    int f, n, ne, x;
    char testo[100] = "Something else";

    if (argc != 2) {
        perror("Nome del file non presente o troppi valori inseriti");
        return 1;
    }

    f = open(argv[1], O_RDWR | O_CREAT, S_IRUSR | S_IWUSR);
    
    close(1);                               // Chiude standard output
    dup(f);                                 /* 
                                            *  Si va praticamente a duplicare l'output del file
                                            *  in modo da chiudere f per poi poter scrivere
                                            *  direttamente su file.
                                            */
    close(f);
    
    printf(testo);

    return 0;
}