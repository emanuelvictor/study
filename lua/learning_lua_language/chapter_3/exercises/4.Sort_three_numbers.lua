io.write('Insert first number: ')
firstNumber = tonumber(io.read())
io.write('Insert second number: ')
secondNumber = tonumber(io.read())
io.write('Insert third number: ')
thirdNumber = tonumber(io.read())

if (firstNumber > secondNumber and secondNumber > thirdNumber) then
    print(thirdNumber .. secondNumber .. firstNumber)
elseif (firstNumber > thirdNumber and thirdNumber > secondNumber) then
    print(secondNumber .. thirdNumber .. firstNumber)
elseif (thirdNumber > firstNumber and firstNumber > secondNumber) then
    print(secondNumber .. firstNumber .. thirdNumber)
elseif (secondNumber > firstNumber and firstNumber > thirdNumber) then
    print(thirdNumber .. firstNumber .. secondNumber)
elseif (secondNumber > thirdNumber and thirdNumber > firstNumber) then
    print(firstNumber .. thirdNumber .. secondNumber)
else
    print(firstNumber .. secondNumber .. thirdNumber)
end
