print('To read and write values in lua we use io lib of the language')
print('We use io.read to read and io.write o wite')
print('To read a numeric value we need to use \'io.read("*number")\'')
print('Pay attention in *number, its means that value of input only can be a number (real or integer). We can use only *n too')
print('We can use *all (or only *a) to read a full file, or *line (or *l) to read a line from file')
print('Let`s make some examples from numeric inputs:')
io.write("Input the first term to sum: \n")
A = io.read("*number")
io.write("Input the second term to sum: \n")
B = io.read("*n")
io.write("A + B = "..A+B.."\n")
print('This code was writed with read and write')
print('----------------------------------------')
print("Let's try other examples")
io.write("Input the new first: \n")
A = io.read("*number")
io.write("Input the new second term: \n")
B = io.read("*number")
io.write("A + B = "..(A+B).."\t")
io.write("A - B = "..(A-B).."\t")
io.write("A * B = "..(A*B).."\t")
io.write("A / B = "..(A/B).."\t\n")
