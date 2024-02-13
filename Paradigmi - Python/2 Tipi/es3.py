# Scrivi una funziona che accetta un int e ritorna il numero
# di zeri consecutivi con cui finisce.
# Esempi: lessZero(123) -> 0
#         lessZero(1200086000) -> 3
#         lessZero(-100) -> 2
#         lessZero(0) = 1

def tailZeroCount(num):
    counter = 0
    
    for i in reversed(str(num)):
        if i == '0': counter += 1
        else: break
        
    return counter

print(tailZeroCount(123))
print(tailZeroCount(1200086000))
print(tailZeroCount(-100))
print(tailZeroCount(0))