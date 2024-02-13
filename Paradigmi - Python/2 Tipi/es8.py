# Scrivere una funzione che prende un parametro intero e una stringa produce
# lo shift della stringa a sinistra con ingresso di caratteri bianchi e perdita
# dei caratteri che eccedono la lunghezza
# Esempi:  spostaSx(6,'questaeraunastringa') ->  'eraunastringa      ' 
#          spostaSx(2,'domani') ->  'mani  '

def shiftLeft(amnt, s):
    shiftedWord = s[-len(s)+amnt::]
    return shiftedWord + " " * amnt

print(shiftLeft(6, 'questaeraunastringa'))
print(shiftLeft(2, 'domani'))