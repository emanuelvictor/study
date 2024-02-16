print("In lua we have trhee logical operators: 'and', 'or' and 'not'.")
print([[
With 'or' operator, only one the terms of conditions must be true to condition return true.
See the example:
io.write("Insert your gender: ") A = io.read()
if (A == "m") or (A == "f") or (A == "M") or (A == "F") then
    print("Valid gender")
else
    print("Invalid gender")
end
]])

io.write("Insert your gender: ") A = io.read()
if (A == "m") or (A == "f") or (A == "M") or (A == "F") then
    print("Valid gender")
else
    print("Invalid gender")
end

io.write('Press <Enter> to exit...')
io.read '*l'