# Crea una funziona come quella sopra, ma rimuove i caratteri con indice
# pari e ritorna il resto in ordine inverso. Se la lista non ha almeno 
# 2 caratteri ritorna None    
# Esempio: fn3('abcdefg') = 'fdb'

def removeEven(str):
    res = str[-2::-2]
    
    if len(res) < 2:
        return "None"
    return res

s = input("Inserire stringa: ")
print(removeEven(s))