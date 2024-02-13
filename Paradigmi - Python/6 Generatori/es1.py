# Definisci un generatore mioRange(start,end,step) 
# che ha 3 parametri e che produce i numeri che 
# produrrebbe una chiamata di range
# list(mioRange(3,7,1)) => [3,4,5,6]
# list(mioRange(7,3,1)) => []
# list(mioRange(3,7,2)) => [3,5]
# list(mioRange(3,7,-2)) => []
# list(mioRange(3,7,-1)) => []
# list(mioRange(7,3,-1)) => [7, 6, 5, 4]
# list(mioRange(3,7,-2)) => []
# list(mioRange(7,3,-2)) => [7,5]

def mioRange(start, end, step):
    return (x for x in range(start, end, step))

print(list(mioRange(3,7,1)))
print(list(mioRange(7,3,1)))
print(list(mioRange(3,7,2)))
print(list(mioRange(3,7,-2)))
print(list(mioRange(3,7,-1)))
print(list(mioRange(7,3,-1)))
print(list(mioRange(3,7,-2)))
print(list(mioRange(7,3,-2)))