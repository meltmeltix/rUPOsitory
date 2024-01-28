#%%
# crea una lista con il cubo dei numeri da 1 a 20 inclusi
[pow(x, 3) for x in range(1, 21)]

#%%
# crea una lista con il cubo dei numeri divisibili per 4 da 1 a 20 inclusi
[pow(x, 3) for x in range(1, 21) if x % 4 == 0]

#%%
# crea una lista con tutti i cubi divisibili per 4 dei numeri da 1 a 20 inclusi
[pow(x, 3) for x in range(1, 21) if pow(x, 3) % 4 == 0]

#%%
# scrivi una funziona exps1 che accetta 3 parametri:
#  1. esponente
#  2. limite
#  3. base
# e ritorna una lista con tutti i numeri da 1 al limite (inclusi) che
# sono divisibili per la base elevata all'esponente
# exps1(1,10,2) =>[2, 4, 6, 8, 10]

def exps1(power, limit, base):
    return [pow(x, power) for x in range(1, limit + 1) if x % pow(base, power) == 0]

exps1(1, 10, 2)


#%%    
# scrivi una funziona exps2 che accetta 3 parametri:
#  1. esponente
#  2. limite
#  3. base
# e ritorna una lista con tutti i numeri da 1 al limite (inclusi) 
# elevati all'esponente, se questo e' divisibile per la base
# exps2(2,10,1) => [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]

def exps2(power, limit, base):
    return [pow(x, power) for x in range(1, limit + 1) if pow(x, power) % base == 0]

exps2(2, 10, 1)

#%% 
# scrivi una funzione areEq che accetta gli stessi parametri di exps1 e exps2
# e ritorna True se e solo se exps1 e exps2 applicate a questi
# parametri ritornano liste uguali

def areEq(power, limit, base):
    return exps1(power, limit, base) == exps2(power, limit, base)

areEq(1, 10, 2)

#%% 
# scrivi un'espressione che chiama la funzione areEq con exp 
# da 2 a 9 (inclusi), base da 2 a 9, e limit 30 e ritorna una lista di
# tuple (exp,base) per tutti le coppie exp, base per cui areEq ritorna False.           

def expBase():
    return [(x, y) 
            for x in range(2, 10) 
                for y in range(2, 10) 
                    if not(areEq(x, 30, y))
            ]

expBase()

# expBase() =>
# [(2, 2),
# (2, 3),
# (2, 4),
# (2, 5),
# (3, 2),
# (3, 3),
# (4, 2),
# (4, 4),
# (5, 5),
# (6, 2),
# (6, 3),
# (6, 6),
# (7, 7),
# (8, 2),
# (8, 4),
# (8, 8),
# (9, 3),
# (9, 9)]
# %%
