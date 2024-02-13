# Scrivi una funzione che prende una stringa e ritorna True 
# se e' un palindrome. Ignorate i caratteri che non sono lettere
# del'alfabeto e il test deve essere case-insensitive.
# isPal('A man, a plan, a canal: Panama!') => True
# isPal('Ettore evitava le madame lavative e rotte.') => True
# isPal('Bolton') => false

def isPal(test):
    toRemove = ",.?!;:\"1234567890 "
    formatted = test.lower().strip()
    
    for c in toRemove:
        formatted = formatted.replace(c, "")
        
    return formatted == "".join(reversed(formatted))

print(isPal('A man, a plan, a canal: Panama!'))
print(isPal('Ettore evitava le madame lavative e rotte.'))
print(isPal('Bolton'))