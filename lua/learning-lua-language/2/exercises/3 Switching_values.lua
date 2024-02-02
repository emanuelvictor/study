io.write('Insert value from A variable: \n')
A = io.read()
io.write('Insert value from B variable: \n')
B = io.read()
io.write('Switching values ..... \n')
A, B = B, A
print('Value of A is '..string.format("%q", A))
print('Value of B is '..string.format("%q", B))