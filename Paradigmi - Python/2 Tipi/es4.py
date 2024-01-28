# Scrivi una funziona che conta la frequenza di tutte la parole in una stringa
# input: stringa con parole separati da spazi 
# output: dizionario con coppie (parola, numero di occorenza)  
# Se str="mele  rab  dfgghh mele arance   ddfsfs arance"
# freq(str) => {'mele': 2, 'rab': 1, 'dfgghh': 1, 'arance': 2, 'ddfsfs': 1}

def countRepititions(s):
    outDict = {}
    
    strList = s.split()
    for s in strList:
        s = s.strip(",.?!;:\"")
        outDict[s] = outDict.get(s, 0) + 1
    
    return outDict

inStr = input("Inserire una stringa con piu parole: ")

print(countRepititions(inStr))