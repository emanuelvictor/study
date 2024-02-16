io.write('Insert the number: ')
value = tonumber(io.read())
if(value > 9 or value < 1) then
    print('The number is out of range')
else
    print('The number is in range')
end