print([[
We have 'repeat until' too, like 'do while' in java.
In this case, we always enter in the loop at least once.
Because 'repeat until' test the condition in the final each loop.
See the example:

repeat
    io.write('Insert a numeric value: ')
    N = tonumber(io.read())
    print()

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
    io.write('New calc? [Y] to yes, other response to not: ')
    resonse = io.read()
    print()

until (string.upper(resonse) ~= 'Y')

]])

repeat
    io.write('Insert a numeric value: ')
    N = tonumber(io.read())
    print()

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
    io.write('New calc? [Y] to yes, other response to not: ')
    resonse = io.read()
    print()

until (string.upper(resonse) ~= 'Y')