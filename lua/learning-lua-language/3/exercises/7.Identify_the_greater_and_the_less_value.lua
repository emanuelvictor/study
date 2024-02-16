greaterValue = 0;
lessValue = 0;
io.write('Insert first number: ')
firstNumber = tonumber(io.read())
if (firstNumber > greaterValue) then
    greaterValue = firstNumber
end
lessValue = firstNumber;
io.write('Insert second number: ')
secondNumber = tonumber(io.read())
if (secondNumber > greaterValue) then
    greaterValue = secondNumber
end
if (secondNumber < lessValue) then
    lessValue = secondNumber
end
io.write('Insert third number: ')
thirdNumber = tonumber(io.read())
if (thirdNumber > greaterValue) then
    greaterValue = thirdNumber
end
if (thirdNumber < lessValue) then
    lessValue = thirdNumber
end
io.write('Insert fourth number: ')
fourthNumber = tonumber(io.read())
if (fourthNumber > greaterValue) then
    greaterValue = fourthNumber
end
if (fourthNumber < lessValue) then
    lessValue = fourthNumber
end
io.write('Insert fifth number: ')
fifthNumber = tonumber(io.read())
if (fifthNumber > greaterValue) then
    greaterValue = fifthNumber
end
if (fifthNumber < lessValue) then
    lessValue = fifthNumber
end

print('The greater value is ' .. greaterValue)
print('The less value is ' .. lessValue)