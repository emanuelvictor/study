io.write('Insert first number: ')
firstNumber = tonumber(io.read())
io.write('Insert second number: ')
::insertSecondNumberAgain::
secondNumber = tonumber(io.read())
if(secondNumber == firstNumber) then
    print('The first number and second number are equals, insert the seconde number again')
    goto insertSecondNumberAgain
end

if (firstNumber > secondNumber) then
    print(firstNumber - secondNumber)
else
    print(secondNumber - firstNumber)
end