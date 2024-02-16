io.write('Insert the radius: \n')
R = io.read()
VOL = math.pi + R ^ 2
io.write('The value from radius is ' .. string.format("%5.5f", VOL))