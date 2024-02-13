# Scrivi una funziona che accetta come argomenti una sequenza di funzioni
# e un valore, applica ogni funzione a quel valore, e ritorna la funzione che
# ha ritornato il valore massimo. (Se c'e' un pareggio, ritorna la prima
# funzione che ritorna quel valore)

def fun1(x):
    return x + 1

def fun2(x):
    return x + 2

def fun3(x):
    return x + 1

def checkResult(funs, x):
    maxFun = None
    maxRes = float('-inf')
    
    for f in funs:
        res = f(x)
        if res > x: 
            maxFun = f
            maxRes = res
            
    return maxFun

funs = [fun1, fun2, fun3]
x = 3
print(checkResult(funs, x))