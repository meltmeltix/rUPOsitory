# Cosa ritornano queste espressioni:
[x*2 for x in [1,1,2,3,5,7]]  # [2,2,4 6,10,14]

[x for x in 'aloha'] # ['a','l',]
# TLDR l'x prima del for e' una variabile usata per espressioni inline
# Quindi volendo se si volesse usare per fare l'append da qualche parte
# si potrebbe fare inline, prima del for stesso

# (str.capitalize() ritorna una stringa con la prima lettera maiuscola
#  e le altre minuscole)
[x[::-1].capitalize() for x in "Peter Michael Neuss".split()]
# ['Retep', 'Leahcim', 'Ssuen']

[x+y for x in 'bfm' for y in 'aeiou']
# ['ba', 'be', 'bi', 'bo', 'bu', 'fa', 'fe', 'fi', 'fo', 'fu', 'ma', 'me', 'mi', 'mo', 'mu']

[x-ix for ix,x in enumerate(range(10,20))]
# [10, 10, 10, 10, 10, 10, 10, 10, 10, 10]

[x for x in 'AbcDeFGhijKL' if x.islower()]
# ['b', 'c', 'e', 'h', 'i', 'j']

[''.join(x) for x in 'AbcDeFGhijKL' if x.isupper()]
# ['A', 'D', 'F', 'G', 'K', 'L']