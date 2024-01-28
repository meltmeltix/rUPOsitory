# Cosa ritornano queste espressioni. Provate a caprilo prima di eseguire!

zip(range(5),range(5,10))
# Iterable

list(zip(range(5),range(5,10)))
# [(0, 5), (1, 6), (2, 7), (3, 8), (4, 9)]

list(zip("spam","eggs","spam"))
# [('s', 'e', 's'), ('p', 'g', 'p'), ('a', 'g', 'a'), ('m', 's', 'm')]

' and '.join(['one','two','three'])
# one and two and three

ord('a')
# Codice unicode di a: 97

chr(97)
# Restituisce carattere del codice inserito: a

chr(ord('a') + 1)
# b

chr(ord('b') - 1)
# a
