# Rifai l'esercizio che segue usando la Comprehension
# Assumendo che una tripla (stringa, booleano, lista persone) rappresenti una
# persona (nome,sesso, lista dei figli) con Donna associata True e Uomo a False
# ad esempio:

paola =("Paola", True,[])
andrea =("Andrea", True,[paola])
peter =("Peter", False,[])
giulia =("Giulia", True, [paola, peter])
persone = [paola, peter, giulia]

# 1) la lista delle persone il cui nome inizia con “P”
# 2) la lista delle coppie (nome madre, nome figlia/o)

def srcPerson(c):    
    return [
        p
        for p in persone
            if p[0].lower().startswith(c.lower())
    ]

def showFamilies():
    return [
        (p[0], f[0])
        for p in persone
            if p[1] and len(p[2]) > 0
                for f in p[2]
    ]

print(srcPerson("p"))
print(showFamilies())