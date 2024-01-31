# Scrivi una funzione che accetta due sequenze della stessa lunghezza.
# La prima sequenza rappresenta input, la seconda output. Crea e ritorna
# una funzione che dato un valore della sequenza di input, ritorna il
# corrispondente output. Se il valore di input non c'e', ritorna None
# Per esempio:
#  foo = makeFn([1,2,3,4,6],(7,6,5,4,3))
#  print(list(map(foo,range(10))))     
# Stamperebbe:
# [None, 7, 6, 5, 4, None, 3, None, None, None]

def makeFn(sIn, sOut):
    seq = dict(zip(sIn, sOut))
    
    def fn(val):
        return seq.get(val, None)
    # get(value, default)       essenzialmente cerca e ritorna il valore o
    #                           None in base alla presenza dell'elemento
    
    return fn

foo = makeFn([1, 2, 3, 4, 6], (7, 6, 5, 4, 3))
print(list(map(foo, range(10))))

