print([[
We can stop pre-test and pos-test loops with clause 'break'.
But pay attention, Selective lops cannot been stopped by 'break' clause.
Tag break only works in pre-test or post-test loops, that is, 'while do' and 'repeat until' structures.
See the example:

io.write('Insert a numeric value: ')
N = tonumber(io.read())
print()

while (true) do

    if (N == 0) then
        break
    end

    I = 1;
    repeat
        R = N * I
        io.write(string.format('%2d', N))
        io.write(' x ')
        io.write(string.format('%2d', I))
        io.write(' = ')
        io.write(string.format('%3d', R), '\n')
        I = I + 1
        if (I == 10) then
            break
        end
    until not (true)

    print()
    io.write('Insert a new numeric value, or 0 to exit: ')
    N = tonumber(io.read())
    print()
end

]])

io.write('Insert a numeric value: ')
N = tonumber(io.read())
print()

while (true) do

    if (N == 0) then
        break
    end

    I = 1;
    repeat
        R = N * I
        io.write(string.format('%2d', N))
        io.write(' x ')
        io.write(string.format('%2d', I))
        io.write(' = ')
        io.write(string.format('%3d', R), '\n')
        I = I + 1
        if (I == 10) then
            break
        end
    until not (true)

    print()
    io.write('Insert a new numeric value, or 0 to exit: ')
    N = tonumber(io.read())
    print()
end