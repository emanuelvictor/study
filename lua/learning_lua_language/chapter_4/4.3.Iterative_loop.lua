print([[
In Lua we have for loop, as other languages. But here we have a very clean sintaxe.
In for loop of Lua, we define the name of iterative variable (index) and their initilizing.
Then we need to define where we can go with this index.
Then we need to define the count we want increment the index.
See the example:

io.write('Insert a numeric value: ')
N = tonumber(io.read())
print()

for I = 1, 10, 1 do
    io.write(string.format('%2d', N))
    io.write(' x ')
    io.write(string.format('%2d', I))
    io.write(' = ')
    io.write(string.format('%3d', R), '\n')
end

Two points:
1) If count of incrementation is 1, it can be suppresed;
2) Unlike other languages, we don't need increment the index by hands.

]])

io.write('Insert a numeric value: ')
N = tonumber(io.read())
print()

for I = 1, 10, 1 do
    R = N * I
    io.write(string.format('%2d', N))
    io.write(' x ')
    io.write(string.format('%2d', I))
    io.write(' = ')
    io.write(string.format('%3d', R), '\n')
end