# Data una stringa di lunghezza divisibile per 3, ritorna una tupla con 
# il primo terzo, il terzo di mezzo, e l'ultimo terzo della stringa.
# Se la lunghezza non e' divisibile per 3, oppure e' 0, ritorna None
# Esempio: fn('abcdef') => ('ab','cd','ef')
#    st = st + 'x' * ((3-len(st)) % 3)

def divideAndTuple(s):
    if len(s) % 3 != 0 or len(s) == 0: return
    
    third = len(s)//3
    
    return (s[:third], s[third:third*2], s[third*2:len(s)])

inStr = input("Inserire stringa: ")
print(divideAndTuple(inStr))
