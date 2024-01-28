# Scrivi una funzione che accetta una lista di numeri
# e ritorna true se e solo se (sse) tutti i numeri sono distinti 
# Esempi: distinti([1,2,3,4]) -> True
#         distinti([1,2,3,2]) -> False

def distinctNumbers(nums):
    return len(set(nums)) == len(nums)

print(distinctNumbers([1, 2, 3, 2]))
print(distinctNumbers([1, 2, 3, 4]))