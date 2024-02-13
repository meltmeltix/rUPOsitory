# Crea una funzione che ha due parametri, assumi che siano stringhe.
# Ritorna una tupla con due stringhe che sono le due stringhe di input
# con il loro primo carattere swappato (l'uno con l'altro).
# Se uno dei due parametri e' una stringa vuota ritorna None
# Esempio: fn1('foo','bar') ritorna ('boo','far')

def swapLetter(str1, str2):
    if not str1 or not str2: return
    
    new_str1 = str2[0] + str1[1::]
    new_str2 = str1[0] + str2[1::]
    
    return (new_str1, new_str2)

print(swapLetter("foo", "bar"))