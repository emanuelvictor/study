print('We use "*l" parameter to define a pause until user inser ENTER keyword.')
print('But, it\'s only work if before we have to used tonumber function. For example:')
print('tonumber(io.read(\'*l\'))')
io.write("Press 'Enter' to exist: \n")
tonumber(io.read('*l'))