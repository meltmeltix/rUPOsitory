#%% Esercizio 1
# Definisci un generatore mioRange(start,end,step) 
# che ha 3 parametri e che produce i numeri che 
# produrrebbe una chiamata di range
# list(mioRange(3,7,1)) => [3,4,5,6]
# list(mioRange(7,3,1)) => []
# list(mioRange(3,7,2)) => [3,5]
# list(mioRange(3,7,-2)) => []
# list(mioRange(3,7,-1)) => []
# list(mioRange(7,3,-1)) => [7, 6, 5, 4]
# list(mioRange(3,7,-2)) => []
# list(mioRange(7,3,-2)) => [7,5]



#%% Esercizio 2
# Definisci un generatore genFib(n) che produce i primi n numeri Fibonacci
# list(genFib(7)) => [1, 1, 2, 3, 5, 8, 13, 21]
# for x in genFib(7):
#    print(x)
# stampa 1 1 2 3 5 8 13 21

#%% Esercizio 3
# Definisci un generatore genFibInf() che produce una enumerazione di tutti i numeri 
# Fibonacci
# Per testare il generatore non potete usare come nel caso precedente      
# for x in genFibInf():
#    print(x)
# ma potete usare la funzione next
# fib=genFibInf()
# for x in range(1,10):
#     print(next(fib))        
        


#%% Esercizio 4
# Definisci un generatore genFibN(n) che produce una generalizzazione dei 
# numeri di Fibonacci:
#   Ogni numero della serie e' uguale alla somma degli N numeri precedenti        
#   I primi N numeri sono 0,0,...,0,1  
#   Questo sara' un generatore per una sequenza infinita!  
#
#   Per esempio, genFibN(3) produce 1, 1, 2, 4, 7, 13, 24, 44, 81, 149...
#                genFibN(4) produce 1, 1, 2, 4, 8, 15, 29, 56, 108, 208, ...
#                genFibN(2) produce i normali numeri Fibonacci		
#
#   Di nuovo se provate a scrivere le seguenti espressioni
#     list(genFibN(4)) oppure
#     [x for x in genFibN(3) if x < 100]		
#   producete un loop infinito. Come in precedenza potete usare next, 
#     g5 = genFibN(5)
#     next(g5)
#     next(g5)
#     next(g5)  
#
#   Ogni chiamata produce l'elemento successivo.oppure con (il generatore) zip 
#   produrre una lista di coppie (ad esempio 10):
#
#     list(zip(range(10),genFibN(3)))	 
#   
#   il secondo elemento sara' il numero di Fibonacci. Questo funziona perche' 
#   zip si ferma quando la lista piu' corta (fra) dei suoi argomenti finisce.
#

        
    
    
