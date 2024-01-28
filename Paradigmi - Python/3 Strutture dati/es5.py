# Scrivere una funzione righeColonne che prende in input una lista 
# di stringhe [r1,...,rn] tutte della stessa lunghezza m, e ritorna 
# la lista di stringhe [c1,...,cm] dove  ci=r1[i]r2[i]....rn[i]
# TLDR: Girare una lista di stringhe in modo che le righe diventino colonne
# SUGGERIMENTO: Come potete ottenere dalla lista inziale una sequenza
# che raggruppi i caratteri che stanno in una specifica colonna??

def swapRowCols(ls):
    zippedList = zip(*ls)
    # Si esegue l'unpacking di ls consentendo l'accesso a ogni elemento
    # al di fuori del contesto della lista.
    
    # Dopo aver praticamente fatto un casting, lo zip puo' prendere
    # le singole stringhe estratte dalla lista come argomenti, in
    # modo da raggrupparli carattere per carattere in base a ogni
    # stringa.
    
    cols = [''.join(col) for col in zippedList]
    # Si prende ogni colonna dello zip e tramite il join si
    # converte in stringa
    
    return cols

ls = [
    'olandesirap',
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

print(swapRowCols(ls))

