

#%%
# Cosa ritornano queste espressioni. Provate a caprilo prima di eseguire!


zip(range(5),range(5,10))

list(zip(range(5),range(5,10)))

list(zip("spam","eggs","spam"))

' and '.join(['one','two','three'])

ord('a')

chr(97)

chr(ord('a') + 1)

chr(ord('b') - 1)

#%%

# data questa definizione di fn:
def fn(a, b=7):
    print(a,b)
# cosa viene stampato quando valuti queste chiamate?            
fn(1,2,3)
fn(1,2)
fn(1)
fn()
fn(a=3)    
fn(b=1,a=2)
fn(b=3)
fn(7,a=5)
fn(*[1,3])
fn([1,3])
fn(3,c=5)
fn(2,**{'b': 5})

 

#%%
# che valori hanno le variabili a,b e c dopo questi assegnamenti?
a,b,c = [1,2,3]

a,b,c = "foo"

a,b,*c = "foobar"

a,*b,c = range(4)

#%% Eserczio 1
# Scrivi una funzione che prende una stringa e ritorna True 
# se e' un palindrome. Ignorate i caratteri che non sono lettere
# del'alfabeto e il test deve essere case-insensitive.
# isPal('A man, a plan, a canal: Panama!') => True
# isPal('Ettore evitava le madame lavative e rotte.') => True
# isPal('Bolton') => false



#%% Eserczio 2
# Scrivere una funzione righeColonne che prende in input una lista 
# di stringhe [r1,...,rn] tutte della stessa lunghezza m, e ritorna 
# la lista di stringhe [c1,...,cm] dove  ci=r1[i]r2[i]....rn[i]
# Se
   
ls=['olandesirap',
 'iiseraoimaa',
 'angelicosio',
 'tnvghrrcrtc',
 'oaacaeaeoai',
 'nrroilgonef',
 'ieirolagcei',
 'siosaiavaec',
 'svsirmaglia',
 'aatcramolap',
 'cboaicsaics'
]

# righeColonne(ls) deve ritornare
 

['oiatonissac',
 'linnareivab',
 'asgvariosto',
 'neegcorsica',
 'drlhaioarri',
 'eairellimac',
 'socragaaams',
 'iioceogvgoa',
 'rmsroncalli',
 'aaitaeeeiac',
 'paocificaps']       
 
 # SUGGERIMENTO: Come potete ottenere dalla lista inziale una sequenza
 # che raggruppi i caratteri che stanno in una specifica colonna??

#%% Eserczio 3

# Assumendo di avere una lista di triple (stringa, booleano, lista persone) in cui
# una tripla rappresenti una persona (nome, sesso, lista dei figli) con Donna associata 
# True e Uomo a False ad esempio:

paola =("Paola", True,[])
andrea =("Andrea", True,[paola])
peter =("Peter", False,[])
giulia =("Giulia", True, [paola, peter])
persone = [paola, peter, giulia]


# scrivere due funzioni che ritornano rispettivamente
# 1) la lista delle persone il cui nome inizia con un certo carattere (parametro)
# 2) la lista delle coppie (nome madre, nome figlia/o)  









