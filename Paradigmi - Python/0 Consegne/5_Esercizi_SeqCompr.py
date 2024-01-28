# -*- coding: utf-8 -*-
"""
Created on Fri Oct 25 08:16:47 2019

@author: Paola
"""


#%%
# Cosa ritornano queste espressioni:
[x*2 for x in [1,1,2,3,5,7]]  # [2,2,4 6,10,14]

[x for x in 'aloha'] #['a','l',]

# (str.capitalize() ritorna una stringa con la prima lettera maiuscola
#  e le altre minuscole)
[x[::-1].capitalize() for x in "Peter Michael Neuss".split()]

[x+y for x in 'bfm' for y in 'aeiou']

[x-ix for ix,x in enumerate(range(10,20))]

[x for x in 'AbcDeFGhijKL' if x.islower()]

[''.join(x) for x in 'AbcDeFGhijKL' if x.isupper()]


#%%

# crea una lista da 3 a 81 con tutti i numeri divisibili per 3

[ x   for x in range(3,82) if x%3==0 ]

#%%
# crea una lista con il cubo dei numeri da 1 a 20 inclusi


#%%
# crea una lista con il cubo dei numeri divisibili per 4 da 1 a 20 inclusi


#%%
# crea una lista con tutti i cubi divisibili per 4 dei numeri da 1 a 20 inclusi


#%%
# scrivi una funziona exps1 che accetta 3 parametri:
#  1. esponente
#  2. limite
#  3. base
# e ritorna una lista con tutti i numeri da 1 al limite (inclusi) che
# sono divisibili per la base elevata all'esponente
# exps1(1,10,2) =>[2, 4, 6, 8, 10]



#%%    
# scrivi una funziona exps2 che accetta 3 parametri:
#  1. esponente
#  2. limite
#  3. base
# e ritorna una lista con tutti i numeri da 1 al limite (inclusi) 
# elevati all'esponente, se questo e' divisibile per la base
# exps2(2,10,1) => [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]


#%% 
# scrivi una funzione areEq che accetta gli stessi parametri di exps1 e exps2
# e ritorna True se e solo se exps1 e exps2 applicate a questi
# parametri ritornano liste uguali


#%% 
# scrivi un'espressione che chiama la funzione areEq con exp 
# da 2 a 9 (inclusi), base da 2 a 9, e limit 30 e ritorna una lista di
# tuple (exp,base) per tutti le coppie exp, base per cui areEq ritorna False.           


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

#%% 
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

#%% 
# Riscrivi la funzione righeColonne che prende in input una lista 
# di stringhe [r1,...,rn] tutte della stessa lunghezza m, e ritorna 
# la lista di stringhe [c1,...,cm] dove  ci=r1[i]r2[i]....rn[i]
# usando al Comprehension.