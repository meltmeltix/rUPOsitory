# -*- coding: utf-8 -*-



#%%
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

# 2) Poi scrivere una funzione cercaInRighe(parola,righe) che prende in input 
# una parola e una lista di stringhe (le righe della matrice di lettere) 
# e cerca la parola nelle stringhe sia da sinistra a destra che 
# da destra a sinistra e ritorna gli indici di inizio e fine della parola
# e -1 se non la trova

# usate enumerate per associare a ogni riga il suo indice e
# il metodo find delle stringhe per cercare la parola in
# una riga
# per cercare da destra a sinistra rovesciate la stringa


# 3)  Usare la funzione precedente per definire una funzione
# cercaInColonne(parola,righe) per cercare nelle colonne della matrice
# di lettere come la precedente la parola puo' essere dall'alto in basso
# o dal basso in alto e come la precedente ritorna le coordinate di inizio
# e fine oppure -1.    

# cercate di ottenere le colonne dalle righe usando zip e join
 
# 4) Usare le 2 funzioni precedenti per definire la funzione 
# cercaParole (parole, righe) => dizionario con chiavi le stringhe in parole
# e valore -1 o gli indici di inizio e fine della parole    
    
    

# Questa e' la lista delle parole:      
      
parole =['angelico','ariosto','baviera', 'camilleri','cassino','corsica',
           'fenoglio','maglia','notaio','olandesi','pacifico',
          'palomar','parise','roncalli','sciascia','serao']


# leggiFile(baseDir,'WordSquare.txt') dovrebbe ritornare

['olandesirap',
 'iiseraoimaa',
 'angelicosio',
 'tnvghrrcrtc',
 'oaacaeaeoai',
 'nrroilgonef',
 'ieirolagcei',
 'siosaiavaec',
 'svsirmaglia',
 'aatcramolap',
 'cboaicsaics',
]
      
      
      
      