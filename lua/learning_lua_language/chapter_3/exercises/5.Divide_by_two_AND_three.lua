io.write('Insert first number: ')
firstNumber = tonumber(io.read())
io.write('Insert second number: ')
secondNumber = tonumber(io.read())
io.write('Insert third number: ')
thirdNumber = tonumber(io.read())
io.write('Insert fourth number: ')
fourthNumber = tonumber(io.read())

if (firstNumber ~= 0 and firstNumber % 2 == 0 and firstNumber % 3 == 0) then
    print(firstNumber .. ' is divisible by 2 and 3 ')
end
if (secondNumber ~= 0 and secondNumber % 2 == 0 and secondNumber % 3 == 0) then
    print(secondNumber .. ' is divisible by 2 and 3 ')
end
if (thirdNumber ~= 0 and thirdNumber % 2 == 0 and thirdNumber % 3 == 0) then
    print(thirdNumber .. ' is divisible by 2 and 3 ')
end
if (fourthNumber ~= 0 and fourthNumber % 2 == 0 and fourthNumber % 3 == 0) then
    print(fourthNumber .. ' is divisible by 2 and 3 ')
end
