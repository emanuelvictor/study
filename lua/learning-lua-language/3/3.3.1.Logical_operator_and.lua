print("In lua we have trhee logical operators: 'and', 'or' and 'not'.")
print([[
With 'and' operator, all the terms of conditions must be true to condition return true.
See the example:
io.write("Insert a number: ") A = io.read("*n")
if (A >= 1) and (A <= 9)then
    print("A is greater than 2")
else
    print("A is less or equals to 2")
end
]])

io.write("Insert a number: ") A = io.read("*n")
if (A >= 1) and (A <= 9)then
    print("A is greater than 0 and less than 10")
else
    print("A is greater than 9 or less than 1")
end

io.write('Press <Enter> to exit...')
io.read '*l'