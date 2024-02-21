#include <unistd.h>
#include <stdlib.h>
#include <sys/types.h>
#include <wait.h>
#include <stdio.h>
#include <string.h>

int main(int argc, char *argv[]){
  char line[128];
  int seconds;
  pid_t pid;
  char message[64];
  
  while(1){
    printf("Allarme >");
    
    //Legge da linea di comando e inserisce in line
    if(fgets(line, sizeof(line), stdin)==NULL) exit(0);
    if(strlen(line)<=1) continue;
    
    //Parsing deve essere un entero e un mesaggio .... [^\n] esclude lo \n
    if(sscanf(line, "%d %64[^\n]", &seconds, message)<2) {
      fprintf(stderr, "Comando sconosciuto\n");
    } 
    else {
      pid = fork();
      if(pid==(pid_t)-1){
	      printf("Errore nella fork\n");
	      exit(-1);
      }
      
      if(pid == (pid_t) 0){    // FIGLIO
	      sleep(seconds);
        printf("(%d) %s\n", seconds, message);
        exit(0);      
      }      
    }              
  }
}