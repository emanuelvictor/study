print([[
Like in java, in Lua we have the operator '%' to get the remainder from division operation.
Se the follow example:

io.write('Insert the numeric value: ')
N = tonumber(io.read())

R4 = N % 4
R5 = N % 5

if (R4 == 0) and (R5 == 0) then
    print(N)
else
    print('Indivisible by 4 or 5')
end
]])

io.write('Insert the numeric value: ')
N = tonumber(io.read())

R4 = N % 4
R5 = N % 5

if (R4 == 0) and (R5 == 0) then
    print(N)
else
    print('Indivisible by 4 or 5')
end