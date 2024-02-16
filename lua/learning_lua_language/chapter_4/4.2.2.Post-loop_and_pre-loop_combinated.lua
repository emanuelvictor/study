print([[
We can use 'while do' and 'repeat until' combinated too.
See the example:

io.write('Insert a numeric value: ')
N = tonumber(io.read())
print()

while (N > 0) do

    I = 1;
    repeat
        R = N * I
        io.write(string.format('%2d', N))
        io.write(' x ')
        io.write(string.format('%2d', I))
        io.write(' = ')
        io.write(string.format('%3d', R), '\n')
        I = I + 1
    until (I > 10)

    print()
    io.write('Insert a new numeric value, or 0 to exit: ')
    N = tonumber(io.read())
    print()
end

]])

io.write('Insert a numeric value: ')
N = tonumber(io.read())
print()

while (N > 0) do

    I = 1;
    repeat
        R = N * I
        io.write(string.format('%2d', N))
        io.write(' x ')
        io.write(string.format('%2d', I))
        io.write(' = ')
        io.write(string.format('%3d', R), '\n')
        I = I + 1
    until (I > 10)

    print()
    io.write('Insert a new numeric value, or 0 to exit: ')
    N = tonumber(io.read())
    print()
end