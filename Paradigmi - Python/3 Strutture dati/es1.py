# data questa definizione di fn:
def fn(a, b=7):
    print(a,b)

# cosa viene stampato quando valuti queste chiamate?            
#fn(1,2,3)           # Errore: Troppi valori
fn(1,2)              # 1 2
fn(1)                # 1 7
#fn()                # Errore: Manca parametro 'a'
fn(a=3)              # 3 7
fn(b=1,a=2)          # 2 1
#fn(b=3)             # Errore: Manca parametro 'a'
#fn(7,a=5)           # Errore: Due valori per parametro 'a'
fn(*[1,3])           # 1 3        L'iterabile viene usato per parametro 'a' e 'b'
fn([1,3])            # [1, 3] 7   La lista e' usata come parametro 'a'
#fn(3,c=5)           # Errore: Parametro 'c' inesistente
fn(2,**{'b': 5})     # 2, 5       Il dizionario viene praticamente convertito come fn(2, b=5)