# -*- coding: utf-8 -*-
"""
Created on Wed Jan  1 10:55:29 2020

@author: Paola
"""

import operator

#%% 
# people e' una lista di tuple. Ogni tupla rappresenta un personaggio.
# Gli elementi della tupla sono (<cognome>,<nome>,<programma televisivo>,<altezza>)
people = [
          ("Joey", "Tribbiani", "Friends", 185),
          ("Ralph", "Kramden", "The Honeymooners", 180),
          ("Ross", "Geller", "Friends", 180),
          ("Monica", "Geller", "Friends", 175),
          ("Spencer", "Cogswell", "The Jetsons", 170),
          ("Chandler", "Bing", "Friends", 175),
          ("Elroy", "Jetson", "The Jetsons", 140),
          ("Astro", "Jetson", "The Jetsons", 60),
          ("Fred", "Flintstone", "The Flintstones", 175),
          ("Barney", "Rubble", "The Flintstones", 150),
          ("Betty", "Rubble", "The Flintstones", 165),
          ("Bambam", "Rubble", "The Flintstones", 45),
          ("Alice", "Kramden", "The Honeymooners", 165),
          ("Ed", "Norton", "The Honeymooners", 170),
          ("Trixie", "Norton", "The Honeymooners", 165),
          ("George", "Jetson", "The Jetsons", 175),
          ("Jane", "Jetson", "The Jetsons", 170),
          ("Phoebe", "Buffay", "Friends", 175),
          ("Pebbles", "Flintstone", "The Flintstones", 30),
          ("Judy", "Jetson", "The Jetsons", 160),
          ("Cosmo", "Spacely", "The Jetsons", 140),
          ("Wilma", "Flintstone", "The Flintstones", 170),
          ("Rachel", "Green", "Friends", 170),
          ]

#%% Trova il personaggio piu' alto, specificare la funzione sia con una lambda
# espressione che con un operatore predefinito di Python
print(max(people, key=lambda p: p[3]))
print(max(people, key=operator.itemgetter(3)))

#%% Trova il personaggio piu' basso
print(min(people, key=lambda p: p[3]))
print(min(people, key=operator.itemgetter(3)))

#%% Trova il personaggio col nome che e' l'ultimo in ordine lessicografico
print(sorted(people, key=operator.itemgetter(0), reverse=True)[0])

#%% Trova il personaggio che sarebbe elencato per primo in un 
#   elenco telefonico
print(sorted(people, key=operator.itemgetter(0))[0])

#%% Crea una lista con gli elementi di people ordinati per altezza dal
# piu' alto al piu' basso
print(sorted(people, key=operator.itemgetter(3), reverse=True))

#%% Crea una lista con gli elementi di people ordinati per programma televisivo
print(sorted(people, key=operator.itemgetter(2)))

#%% Crea una lista con gli elementi di people ordinati per cognome poi nome
print(sorted(people, key=lambda p: (p[1], p[0])))

#%% Crea una lista con gli elementi di people ordinati per programma televisivo,
#   poi cognome, poi nome
print(sorted(people, key=lambda p: (p[2], p[1], p[0])))
