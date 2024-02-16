print("In lua we have trhee logical operators: 'and', 'or' and 'not'.")
print([[
With 'not' operator we can invert return of condition. We can put 'not' operator to verify if condition return false.
So the condition will return true if it's false in original form, and return false if the original it is true.
See the follow example:
A = false
if not(A) then
    print('It must be printed')
end
]])

A = false
if not(A) then
    print('It must be printed')
end

print([[
You must remember that nil is return false to.
A = nil
if not(A) then
    print('It must be printed')
end
]])
A = nil
if not(A) then
    print('It must be printed')
end


io.write('Press <Enter> to exit...')
io.read '*l'