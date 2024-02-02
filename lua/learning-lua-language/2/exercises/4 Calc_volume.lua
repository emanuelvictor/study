io.write('Insert length to calc volume: \n')
length = io.read()
io.write('Insert width to calc volume: \n')
width = io.read()
io.write('Insert heigth to calc volume: \n')
heigth = io.read()
volume = length * width * heigth
io.write('The volume is '..string.format("%7.2f",volume))