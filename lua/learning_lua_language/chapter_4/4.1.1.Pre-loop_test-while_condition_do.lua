print([[
Like Java, we have while do.
It means we can run a loop while one condition is true.
In while do we testing the condition before run the loop.
See the example:

io.write('Insert a numeric value: ')
N = tonumber(io.read())
print()

I = 1
while (I <= 10) do
    R = N * I
    io.write(string.format('%2d', N))
    io.write(' x ')
    io.write(string.format('%2d', I))
    io.write(' = ')
    io.write(string.format('%3d', R), "\n")
    I = I + 1
end

print()
io.write('Insert Enter to close ... ')
io.read '*l'
]])

io.write('Insert a numeric value: ')
N = tonumber(io.read())
print()

I = 1
while (I <= 10) do
    R = N * I
    io.write(string.format('%2d', N))
    io.write(' x ')
    io.write(string.format('%2d', I))
    io.write(' = ')
    io.write(string.format('%3d', R), "\n")
    I = I + 1
end

print()
io.write('Insert Enter to close ... ')
io.read '*l'