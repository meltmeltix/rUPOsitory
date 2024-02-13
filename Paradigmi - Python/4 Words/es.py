# Sto esercizio e' a dir poco terribile

# Scrivi una funzione che trova una lista di parole in una matrice di lettere
# La matrice di lettere viene letta dal file di testo "WordSquare.txt"x.    
# La parola puo' essere orizzontale o verticale e puo' essere
# in un qualsiasi orientamento (sinistra=> destra, destra=>sinistra,
# alto=>basso, basso => alto)
#
# La funzione ritorna un dizionario con chiave le parole da cercare
# se la parola c'e', viene associata alla coppia di indici di inizio e
# fine della parola nella matrice, se non c'e' viene associata a -1.    

# 1)  Prima scrivere una funzione: leggiFile(direct,nomeFile)
# che prende in input il nome della directory e quello del file nelle 
# directory in cui si trovano le stringhe che rappresentano le righe
# della matrice di lettere e ritorna la lista di tali stringhe 

# baseDir= "..."
# leggiFile(baseDir,'WordSquare.txt')
# leggi riga per riga eliminando gli  spazi bianchi a destra 

from pathlib import Path

def readFileList(fileName):
    path = Path(__file__).with_name(fileName)
    
    try:
        with path.open('r') as f:               # Letta la path, viene aperto il file come f
            words = []
            for row in f:                       # Si itera nel file, dove row e' la riga puntata dal cursore
                words.append(row.rstrip())      # Si concatena al dizionario la riga plain togliendo eventuali spazi
                
        return words
    except FileNotFoundError:
        return print("Errore")


# 2) Poi scrivere una funzione cercaInRighe(parola,righe) che prende in input 
# una parola e una lista di stringhe (le righe della matrice di lettere) 
# e cerca la parola nelle stringhe sia da sinistra a destra che 
# da destra a sinistra e ritorna gli indici di inizio e fine della parola
# e -1 se non la trova

# usate enumerate per associare a ogni riga il suo indice e
# il metodo find delle stringhe per cercare la parola in
# una riga
# per cercare da destra a sinistra rovesciate la stringa

def searchInRow(word, row):
    for idx, rw in enumerate(row):    # enumerate(row) ritorna una tupla contenente l'indice della riga e e la parola stessa
        c = rw.find(word)             # find(word) ritorna l'indice di word in rw. Se non trovato, ritorna -1
        if(c != -1):
            return ((idx, c), (idx, len(word) + c - 1))     # viene restituito indice iniziale e finale della parola
        
        c = rw.find(word[::-1])
        if(c != -1):
            return ((idx, c), (idx, len(word) + c - 1))     # uguale qua ma al contrario
        
    return -1


# 3)  Usare la funzione precedente per definire una funzione
# cercaInColonne(parola,righe) per cercare nelle colonne della matrice
# di lettere come la precedente la parola puo' essere dall'alto in basso
# o dal basso in alto e come la precedente ritorna le coordinate di inizio
# e fine oppure -1.    

# cercate di ottenere le colonne dalle righe usando zip e join

# se la ricerca per riga ritorna ((i,j1),(i,j2)) allora i e' l'indice
# della colonna e j1 e j2 l'indice della riga di inizio e fine della parola
# cioe' dobbiamo ritornare ((j1,i),(j2,i))

def searchInCol(word, row):
    result = searchInRow(word, [''.join(x) for x in zip(*row)])     # Si sfrutta searchInRow per cercare in colonna
                                                                    # Si crea un array, dove si fa lo zip
                                                                    # della row, resa iterabile (Guarda 3 Strutture Dati/es5)
    if(result != -1):
        return ((result[0][1], result[0][0]), (result[1][1], result[1][0]))
    return -1


# 4) Usare le 2 funzioni precedenti per definire la funzione 
# cercaParole (parole, righe) => dizionario con chiavi, dove
# viene ritornata stringa e valore -1 oppure se non trovato
# parole e gli indici di inizio e fine se trovato

# Questa e' la lista delle parole:      
words = [
    'angelico',
    'ariosto',
    'baviera', 
    'camilleri',
    'cassino',
    'corsica',
    'fenoglio',
    'maglia',
    'notaio',
    'olandesi',
    'pacifico',
    'palomar',
    'parise',
    'roncalli',
    'sciascia',
    'serao'
]

def searchWord(words, rows):
    result = {}
    
    for word in words:
        resultRows = searchInRow(word, rows)
        resultCols = searchInCol(word, rows)
        
        if(resultRows != -1):
            result[word] = resultRows
        elif(resultCols != -1):
            result[word] = resultCols
        else:
            result[word] = -1
    
    return result

wordList = readFileList("lista.txt")
print(searchWord(words, wordList))