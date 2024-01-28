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
tree2 = Tree('+',
               Tree(7),
               Tree(3))


#%%  
# Ecco una funzione utile che ritorna True se il nodo e' una foglia
def isLeaf(tree):
    return tree.sx == None and tree.dx == None

# Nel notebook abbbiamo anche defnito la funzione  getDepth(tree) che calcola 
# la profondita' di un albero.
    
def getDepth(tree):
    if isLeaf(tree): return 0
    return 1 + max([getDepth(subtree) for subtree in tree[1:] if subtree])

# proviamo 
print(getDepth(tree1))
print(getDepth(tree2))

optree1 = Tree('*',
              Tree('+',
                   Tree(7),
                   Tree(4)),
              Tree('-',
                   Tree(7),
                   Tree(4))
              )
              
optree2 = Tree('*',
              Tree('*',
                   Tree(3),
                   Tree(3)),
              Tree('-',
                   Tree(2),
                   Tree(4))
              )  

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


#%% 
# 2. Scrivere una funzione che prende un albero, tree, e un intero, n,  mette 
#  in una lista il contenuto dei nodi di livello n  di tree
#    depthN(optree1,1)  =>  ['+', '-']
#    depthN(optree1,2)  =>  [7, 4, 7, 4]

optree3 = Tree('*',
              Tree('+',
                   Tree(7,
                        Tree('*',
                             Tree('+',
                                  Tree(7),
                                  Tree(4)),
                             Tree('-',
                                  Tree(8),
                                  Tree(6)
                                  )
                             )
                        ),
                   Tree(4)),
              Tree('-',
                   Tree(8),
                   Tree(6))
              )
              
#depthN(optree3,1)   => ['+', '-']

#depthN(optree3,5)  => [7, 4, 8, 6]

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

#  maxProf(tree,6) => 4
#  maxProf(tree,11) => -1
#  maxProf(tree,4) => 4
#  maxProf(tree,9) => 5
#  maxProf(tree,5) => 0

           

#%%
# 4. Scrivi una funzione eleva(a,b) che calcola a**b ricorsivamente.
#    a**0=1  eleva(a,0)=1
#    eleva(a,b)=eleva(a,b2)*eleva(a,b2)) dove b2=b//2 e  b e' pari
#    a**b=(a**b2)* (a**b2)  se b2=b//2 e  b e' pari
#    a**b=(a**b2)* (a**b2)*a  se b2=b//2 e b e' dispari


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

# foldLeftRic(lis,0,add) => 16


