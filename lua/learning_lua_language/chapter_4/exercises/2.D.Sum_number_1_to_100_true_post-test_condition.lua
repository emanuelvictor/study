I = 0
SUM = 0
repeat
    io.write(I .. " + " .. SUM .. " = " .. string.format('%3d', (SUM + I)), "\n")
    SUM = SUM + I
    I = I + 1
until not(I <= 100)