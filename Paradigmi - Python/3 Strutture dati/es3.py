# che valori hanno le variabili a,b e c dopo questi assegnamenti?
a,b,c = [1,2,3]     # a=1, b=2, c=3

a,b,c = "foo"       # a='f', b='o', c='o'

a,b,*c = "foobar"   # a='f', b='o', c=[o, b, a, r]

a,*b,c = range(4)   # a=1, b=[2, 3], c=4 