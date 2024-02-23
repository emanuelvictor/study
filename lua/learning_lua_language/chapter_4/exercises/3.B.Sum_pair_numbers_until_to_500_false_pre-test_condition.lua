I = 0
SUM = 0
while not (I > 500) do
    if (I % 2 == 0) then
        io.write(I .. " + " .. SUM .. " = " .. string.format('%3d', (SUM + I)), "\n")
        SUM = SUM + I
    end
    I = I + 1
end