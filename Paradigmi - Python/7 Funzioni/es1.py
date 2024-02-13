# Scrivi una funzione comp che ha come parametri due funzioni, f e g,  e 
# ritorna una funzione che e' la composizione delle due funzioni. Per esempio, 
# se chiamo comp(f,g) dove f e' una funzione che ritorna il quadrato del suo 
# argomento, e g e' una funzione che ritorna il doppio del suo argomento, 
# allora comp(f,g) ritorna una funzione che ritorna il quadrato del doppio 
# del suo argomento, se invece 
# def comp(f,g):

def square(n):
    return n * n

def double(n):
    return n * 2

def comp(f, g):
    def composition(x):
        return f(g(x))
    return composition

composed_function = comp(square, double)
print(composed_function(2))