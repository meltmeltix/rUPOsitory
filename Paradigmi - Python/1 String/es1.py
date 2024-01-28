# 1.  crea un variabile c con valore "Winter is coming" utilizzando string
# 2.  crea un variabile d con valore "WinterWinterWinter"
# 3.  stampa la lunghezza di c
# 4.  controlla se c contiene il carattere 'a'
# 5.  controlla se c contiene il carattere 'g'
# 6.  controlla se c contiene il carattere 'w'
# 7.  stampa il terzo e il terzultimo carattere di c
# 8.  provate cosa ritorna min(c) e max(c)
# 9.  stampa quanti 'i' ci sono in c
# 10. stampa l'indice del primo 'i' in c
# 11. stampa quanti 'in' ci sono in c

a = "Winter"
b = " is coming"

#1
c = a + b
print(c, "\n")

#2
d = a * 3
print(d, "\n")

#3
print(len(c), "\n")

#4, 5, 6
print('a' in c)
print('g' in c)
print('w' in c, "\n")

#7
print(c[3])
print(c[-3], "\n")

#8
print(min(c))
print(max(c), "\n")

#9
print(c.count('i'), "\n")

#10
print(c.index('i'), "\n")

#11
print(c.count('in'), "\n")