print([[
With selective loop we can stop the loop in anywhere loop itself.
So, it means we don't have dependency with the begin and the end of the loop.
We can use go to to simulate a selctive loop.
See the example:

io.write('Insert a numeric value: ')
N = tonumber(io.read())
print()

I = 1;
:: loop ::
R = N * I
io.write(string.format('%2d', N))
io.write(' x ')
io.write(string.format('%2d', I))
io.write(' = ')
io.write(string.format('%3d', R), '\n')
I = I + 1
if (I <= 10) then
    goto loop
end

]])

io.write('Insert a numeric value: ')
N = tonumber(io.read())
print()

I = 1;
:: loop ::
R = N * I
io.write(string.format('%2d', N))
io.write(' x ')
io.write(string.format('%2d', I))
io.write(' = ')
io.write(string.format('%3d', R), '\n')
I = I + 1
if (I <= 10) then
    goto loop
end