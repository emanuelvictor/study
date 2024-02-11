-- This algorithm calc whats the necessary number of quotes to receive provents to buy a new quote (quote or action)
print('Insert price of action: ')
quotePrice = io.read '*number'
print('Insert the provents: ')
provent = io.read '*number'
magicNumber = (quotePrice/provent)
print("The magic number is "..string.format("%7.2f",magicNumber))
print("Money necessary to get magic number is "..string.format("%7.2f",magicNumber*quotePrice))

