# Definisci un generatore genFibInf() che produce una enumerazione di tutti i numeri 
# Fibonacci
# Per testare il generatore non potete usare come nel caso precedente      
# for x in genFibInf():
#    print(x)
# ma potete usare la funzione next
# fib=genFibInf()
# for x in range(1,10):
#     print(next(fib))

def genFibInf():
    a, b = 1, 1
    while True:
        yield a
        a, b = b, a + b

fib = genFibInf()
for _ in range(10):
    print(next(fib))
