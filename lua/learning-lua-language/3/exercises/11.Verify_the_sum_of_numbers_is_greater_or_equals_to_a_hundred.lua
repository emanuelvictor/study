io.write('Insert first number: ')
firstNumber = tonumber(io.read())
io.write('Insert second number: ')
secondNumber = tonumber(io.read())
io.write('Insert third number: ')
thirdNumber = tonumber(io.read())

sum = (firstNumber + secondNumber + thirdNumber)
if (sum >= 100) then
    print(sum)
end
