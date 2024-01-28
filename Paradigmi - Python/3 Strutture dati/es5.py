# Assumendo di avere una lista di triple (stringa, booleano, lista persone) in cui
# una tripla rappresenti una persona (nome, sesso, lista dei figli) con Donna associata 
# True e Uomo a False.
# Scrivere due funzioni che ritornano rispettivamente
# 1) la lista delle persone il cui nome inizia con un certo carattere (parametro)
# 2) la lista delle coppie (nome madre, nome figlia/o)  

paola = ("Paola", True,[])
andrea = ("Andrea", True,[paola])
peter = ("Peter", False,[])
giulia = ("Giulia", True, [paola, peter])
persone = [paola, peter, giulia, andrea]

def srcPerson(c):
    result = []
    
    for p in persone:
        if p[0].lower().startswith(c.lower()):
            result.append(p)
    
    return result

def showFamilies():
    result = []
    
    for p in persone:
        if p[1] and len(p[2]) > 0:
            for f in p[2]:
                result.append((p[0], f[0]))
    
    return result

print(srcPerson("p"))
print(showFamilies())


