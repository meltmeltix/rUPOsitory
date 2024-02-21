#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

int main(int argc, char *argv[]) {
    int i, j;
    pid_t n;

    n = fork();

    if (n == (pid_t) -1) {					// Ci si accerta che il processo sia stato creato
        perror("fork fallita");
        exit(1);
    };
																	// I processi saranno due
    if (n == (pid_t) 0) {											// Processo figlio
        for (j = 0; j < 50; j++) {									// Inizia ciclo di 50
            for (i = 0; i < 100000000; i++);						// Ciclo stupido di attesa
            printf("  Figlio %d di %d giro %d, n=%d \n", getpid(), getppid(), j, n);	
        }
    } else {														// Processo padre
        for (j = 0; j < 20; j++) {									// Inizia ciclo di 20
            for (i = atoi(argv[2]); i < 100000000; i++);			// Ciclo di attesa con conversione
            printf("Padre %d di n=%d giro %d\n", getpid(), n, j);
        }
    }
	
    return 0;
}
