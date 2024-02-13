# Crea una funziona che prende una stringa come parametro, 
# e ritorna la stringa in cui i caratteri di indice 
# dispari sono rimossi. 
# Se la stringa e' vuota ritorna None
# Attenzione le stringhe sono immutabili
# Esempio: fn2('abcdefg') = 'aceg'

def removeUneven(str):
    if len(str) == 0: return
    return str[0::2]

s = input("Inserire stringa: ")
print(removeUneven(s))