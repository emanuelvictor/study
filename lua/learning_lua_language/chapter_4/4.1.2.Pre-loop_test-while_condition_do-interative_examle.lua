print([[
With while do we can make ITERATIVE and INTERATIVE codes.
See the example:

io.write('Insert a new numeric value to calc, or 0 to exit: ')
N = tonumber(io.read())

while (N > 0) do

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

    io.write('Insert a new numeric value to other calc, or 0 to exit: ')
    N = tonumber(io.read())

end

]])

io.write('Insert a new numeric value to calc, or 0 to exit: ')
N = tonumber(io.read())

while (N > 0) do

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

    io.write('Insert a new numeric value to other calc, or 0 to exit: ')
    N = tonumber(io.read())

end