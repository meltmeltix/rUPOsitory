# Considera la seguente definizione della funzione foldLeft 

def foldLeft(lis,init,fn):
    acc = init
    for x in lis:
        acc = fn(acc,x)
    return acc

# foldLeft ha 3 parametri: una lista, lis, un valore iniziale di un 
# accumulatore, acc, e una funzione.
# La funziona passata fn che ha due parametri: un valore accumulatore e
# un elemento della lista. Questa ritorna un nuovo valore dell'accumulatore.

# L'idea e' che foldLeft applica la funzione fn agli elementi di lis, accumulando
# il risultato e ritornando il valore finale dell'accumulatore. Esempio:
#         foldLeft(lis,0,add) dove lis a add sono  
#

lis = [1,3,4,6,2]

def add (acc,x):
    return acc+x
#
# cosa ritorna? 

# Definite una funzione fn e un valore iniziale di accumulatore init tale
# che  
#        foldLeft(lis,init,fn) => [2, 6, 4, 3, 1] 

# cioe' il risultato e' il reverse della lista di input  

def fn(acc, x):
    return [x] + acc