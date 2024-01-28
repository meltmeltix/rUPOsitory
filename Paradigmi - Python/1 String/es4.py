# Da fare dopo aver introdotto le liste
# Applica le due funzioni precedenti alla stringa 'teovruoj'
# str='teovruoj'
# str1=fn2(str)
# str2=fn3(str)
#
# Ora crea una funzione per fare l'inverso; cioe' per mescolare due 
# stringhe della stessa lunghezza, la prima nell'ordine originale
# e la seconda al rovescio. Se gli input non sono idonei, ritorna None
# Esempio: fn4(str1,str2) = 'teovruoj'

def removeUneven(str):
    if len(str) == 0:
        return "None"
    return str[0::2]

def removeEven(str):
    res = str[-1::-2]
    
    if len(res) < 2:
        return "None"
    return res

def reconstructString(str1, str2):
    strInv = str2[-1::-1]
    
    if len(str1) == len(str2):
        result = ""
        for c1, c2 in zip(str1, strInv):
            result += c1 + c2
        
        return result
    
    return

str = 'teovruoj'
print(str)

str1 = removeEven(str)
print(str1)

str2 = removeUneven(str)
print(str2)

str3 = reconstructString(str2, str1)
print(str3)