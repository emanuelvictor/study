SUM = 0
for I = 1, 100 do
    io.write(I .. " + " .. SUM .. " = " .. string.format('%3d', (SUM + I)), "\n")
    SUM = SUM + I
end