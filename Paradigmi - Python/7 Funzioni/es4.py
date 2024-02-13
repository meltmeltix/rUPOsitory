# Scrivi una funzione che accetta un iteratore e due funzioni booleane. 
# Ritorna un iteratore di tutti gli elementi dell'iteratore di input per
# cui entrambe le funzioni booleane ritornano un valore truthy

def returnBool(iterator, fun1, fun2):
    for i in iterator:
        if fun1(i) and fun2(i):
            yield i

print(list(returnBool(range(10), lambda x: x % 2 == 0, lambda x: x > 0)))

# Quando si vuole passare una funzione inline, e' possibile usare
# lambda n: funzione come funzione. Usando lambda, viene specificato
# il nome di tale e non sara' necessario specificare il nome o
# scrivere una funzione intera. 