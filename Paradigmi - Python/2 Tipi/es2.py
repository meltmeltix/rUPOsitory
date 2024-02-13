# Scrivi una funzione che ritorna il numero cifre di un intero
# Esempi: cifre(0) -> 1
#         cifre(1003) => 4

def countInt(num):
    return len(str(num))

print(countInt(12345))