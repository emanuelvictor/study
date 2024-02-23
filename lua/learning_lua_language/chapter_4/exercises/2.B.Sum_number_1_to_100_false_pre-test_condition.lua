I = 0
SUM = 0
while not (I > 100) do
    io.write(I .. " + " .. SUM .. " = " .. string.format('%3d', (SUM + I)), "\n")
    SUM = SUM + I
    I = I + 1
end