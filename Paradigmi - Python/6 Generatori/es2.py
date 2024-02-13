# Definisci un generatore genFib(n) che produce i primi n numeri Fibonacci
# list(genFib(7)) => [1, 1, 2, 3, 5, 8, 13, 21]
# for x in genFib(7):
#    print(x)
# stampa 1 1 2 3 5 8 13 21

def genFib(times):
    a, b = 1, 1
    for _ in range(times + 1):
        yield a
        a, b = b, a + b

for x in genFib(7):
    print(x)