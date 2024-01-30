print("We have any types of variables in lua")
print()
print("--------------------------------------------------------------------------------- nill => To indifined values")
print("A = nil")
print("print(A) -- must show nil")
A = nil
print(A)
print("type(A) -- must show nil")
print(type(A))
print()
print("-------------------------------------------------------------------------- boolean => To true or false values")
print("A = true")
print("print(A) -- must show true")
A = true
print(A)
print("type(A) -- must show boolean")
print(type(A))
print()
print("------------------------------------------------------------------------------------ number => Numeric values")
print("A = 10e2")
print("print(A) -- must show 1000.0")
A = 10e2
print(A)
print("type(A) -- must show number")
print(type(A))
print()
print("------------------------------------------------ string => Array of characters. Defined by \" (double quotes)")
print("A = \"TEST\"")
print("print(A) -- must show TEST")
A = "TEST"
print(A)
print("type(A) -- must show string")
print(type(A))
print()
print("------------------------------------------------- string => Array of characters. Defined by ' (simple quotes)")
print("A = 'TEST'")
print("print(A) -- must show TEST")
A = 'TEST'
print(A)
print("type(A) -- must show string")
print(type(A))
print()
print("-------------------------------------------------------------- string => Array of characters. Defined by [[]]")
print("A =",[[Test
with
multiline
string]])
print("print(A) -- must show")
print(
[[Test
with
multiline
string]])
A = [[Test
with
multiline
string]]
print(A)
print("type(A) -- must show string")
print(type(A))
print()
print("------------------------------------------------------------------- function => Used to functions definitions")
print("function some_function()  end")
print("A = some_function")
print("print(A) -- must show address of memory (the pointer)")
function some_function()  end
A = some_function
print(A)
print("type(A) -- must show function")
print(type(A))
print()
print("userdata => Used to pointers when Lua is associated with C")
print("thread => Execution of independents fluxes")
print("table => Utilized to lists, matrices and arrays")