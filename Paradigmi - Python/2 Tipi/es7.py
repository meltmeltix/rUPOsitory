# Scrivere una funzione che prende un parametro intero e una stringa produce
# lo shift della stringa a destra con ingresso di caratteri bianchi e perdita
# dei caratteri che eccedono la lunghezza
# Esempi:  spostaDx(6,'questaeraunastringa') ->  '      questaeraunas' 
#          spostaDx(2,'domani') ->  '  doma'

def shiftRight(amnt, s):
    shiftedWord = s[:len(s)-amnt]
    return " " * amnt + shiftedWord

print(shiftRight(6, 'questaeraunastringa'))
print(shiftRight(2, 'domani'))