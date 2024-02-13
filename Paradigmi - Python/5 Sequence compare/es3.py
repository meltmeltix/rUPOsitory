# Riscrivi la funzione righeColonne che prende in input una lista 
# di stringhe [r1,...,rn] tutte della stessa lunghezza m, e ritorna 
# la lista di stringhe [c1,...,cm] dove  ci=r1[i]r2[i]....rn[i]
# usando al Comprehension.

def swapRowCols(ls):
    return [''.join(col) for col in zip(*ls)]

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