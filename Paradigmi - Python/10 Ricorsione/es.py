# -*- coding: utf-8 -*-
"""

@author: Paola
"""

#%% 
# Di sotto trovate le definizioni dal notebook
from collections import namedtuple

Tree = namedtuple('Tree',['data','sx','dx'], defaults=[None]*3)

tree1 = Tree('root')
print(tree1)
tree2 = Tree('+', Tree(7), Tree(3))
print(tree2)


#%%  
# Ecco una funzione utile che ritorna True se il nodo e' una foglia
def isLeaf(tree):
     return tree.sx == None and tree.dx == None

# Nel notebook abbbiamo anche defnito la funzione getDepth(tree) che calcola 
# la profondita' di un albero.
def getDepth(tree):
     if isLeaf(tree): return 0
     return 1 + max([getDepth(subtree) for subtree in tree[1:] if subtree])

# proviamo 
print(getDepth(tree1))
print(getDepth(tree2))

optree1 = Tree('*',
          Tree('+', Tree(7), Tree(4)),
          Tree('-', Tree(7), Tree(4)))

optree2 = Tree('*',
          Tree('*', Tree(3), Tree(3)),
          Tree('-', Tree(2), Tree(4)))  

optree3 = Tree('+',optree1,optree2)  

print(optree1)
print(optree2)
print(optree3)

#%% 
# 1. Scrivere una funzione ppTree per stampare questi alberi in notazione infissa. 
# Per esempio, ppTree(optree1) => (7+4)*(7-4)
# Puo' essere utile sapere che la funzione print prende un argomento end, che 
# specifica l'ultimo carattere da stampare. Il default e' '\n'; se non si vuole 
# che vada a capo si puo' specificare print('foo',end='').
# Provate a eseguire print('foo','bar')
#                    print('foo') seguito da print('bar') e
#                    print('foo',end=' ') seguito da print('bar')
def ppTree(tree):
     if tree.data is None:
          return ""
     if isLeaf(tree):
          return str(tree.data)
     return "(" + ppTree(tree.sx) + str(tree.data) + ppTree(tree.dx) + ")"

# Test
print(ppTree(optree1))  # Output: (7+4)*(7-4)
print(ppTree(optree2))  # Output: (3*3)*(2-4)
print(ppTree(optree3))  # Output: ((7+4)*(7-4))*((3*3)*(2-4))


#%% 
# 2. Scrivere una funzione che prende un albero, tree, e un intero, n,  mette 
#  in una lista il contenuto dei nodi di livello n  di tree
#    depthN(optree1,1)  =>  ['+', '-']
#    depthN(optree1,2)  =>  [7, 4, 7, 4]

optree3 = Tree('*',
          Tree('+',
          Tree(7,
          Tree('*', 
               Tree('+', Tree(7), Tree(4)),
               Tree('-', Tree(8), Tree(6))
          )),
          Tree(4)),
          Tree('-', Tree(8), Tree(6)))

def depthN(tree, n):
     # Caso base: se l'albero è vuoto o il livello richiesto è inferiore a 1, restituisci una lista vuota
     if not tree or n < 1:
          return []
     # Caso base: se il livello richiesto è 1, restituisci una lista con il contenuto del nodo corrente
     if n == 1:
          return [tree.data]
     # Altrimenti, richiama ricorsivamente la funzione sui sottoalberi sinistro e destro
     # con decremento del livello richiesto
     return depthN(tree.sx, n-1) + depthN(tree.dx, n-1)

# Test della funzione
print(depthN(optree3, 1))  # ['+', '-']
print(depthN(optree3, 2))  # [7, 4, 7, 4]
print(depthN(optree3, 5))  # [7, 4, 8, 6]

#%% 
# 3. Scrivere una funzione maxProf che prende un albero t, e un intero, n, e ritorna
#  la massima profondita' di n (distanza dalla radice di t) se n e' un dato dell'albero
#  Se n non compare in t ritorna -1
tree = Tree( 5,
              Tree( 9,
                   Tree(7,
                        Tree( 7,
                             Tree( 4,
                                  Tree(9),
                                  None
                                  ),
                             Tree(6)
                             ),
                    Tree(4)
                    ),
                 Tree(4,
                   None,
                   Tree(6)
                   )
                 ),
              None
        )

def maxProf(tree, n):
    # Funzione ausiliaria di ricerca ricorsiva
    def findDepth(node, target, currentDepth):
        # Caso base: se il nodo è None, restituisci -1 (valore sentinella)
        if not node:
            return -1
        # Se il valore del nodo corrente è uguale al target, restituisci la profondità corrente
        if node.data == target:
            return currentDepth
        # Altrimenti, cerca ricorsivamente nei sottoalberi sinistro e destro
        leftDepth = findDepth(node.sx, target, currentDepth + 1)
        rightDepth = findDepth(node.dx, target, currentDepth + 1)
        # Restituisci la massima profondità trovata nei sottoalberi
        return max(leftDepth, rightDepth)
    
    # Avvia la ricerca ricorsiva dalla radice dell'albero
    return findDepth(tree, n, 0)

# Test della funzione
print(maxProf(tree, 6))   # 4
print(maxProf(tree, 11))  # -1
print(maxProf(tree, 4))   # 4
print(maxProf(tree, 9))   # 5
print(maxProf(tree, 5))   # 0

#%%
# 4. Scrivi una funzione eleva(a,b) che calcola a**b ricorsivamente.
#    a**0=1  eleva(a,0)=1
#    eleva(a,b)=eleva(a,b2)*eleva(a,b2)) dove b2=b//2 e  b e' pari
#    a**b=(a**b2)* (a**b2)  se b2=b//2 e  b e' pari
#    a**b=(a**b2)* (a**b2)*a  se b2=b//2 e b e' dispari
def eleva(a, b):
     # Caso base: se l'esponente è 0, restituisci 1
     if b == 0:
          return 1
     # Se l'esponente è pari, calcola a^b/2
     if b % 2 == 0:
          half_power = eleva(a, b // 2)
          return half_power * half_power
     # Se l'esponente è dispari, calcola a^b/2 e moltiplica per a
     else:
          half_power = eleva(a, b // 2)
          return half_power * half_power * a

# Test della funzione
print(eleva(2, 0))  # 1
print(eleva(2, 1))  # 2
print(eleva(2, 2))  # 4
print(eleva(2, 3))  # 8
print(eleva(3, 4))  # 81


#%% 
# 5. Scrivi una definizione ricorsiva foldLeftRic della seguente funzione foldLeft.

def foldLeft(lis,init,fn):
     acc = init
     for x in lis:
          acc = fn(acc,x)
     return acc


lis = [1,3,4,6,2]

def add (acc,x):
     return  acc+x

def foldLeftRic(lis, init, fn):
     # Caso base: se la lista è vuota, restituisci l'accumulatore corrente
     if not lis:
          return init
     # Applica la funzione al primo elemento della lista e all'accumulatore corrente
     new_acc = fn(init, lis[0])
     # Chiamata ricorsiva sulla lista rimanente e sull'accumulatore aggiornato
     return foldLeftRic(lis[1:], new_acc, fn)

# Test della funzione
lis = [1, 3, 4, 6, 2]
print(foldLeftRic(lis, 0, add))  # 16


