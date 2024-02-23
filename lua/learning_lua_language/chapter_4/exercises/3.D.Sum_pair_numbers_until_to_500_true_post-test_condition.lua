I = 0
SUM = 0
repeat
    if (I % 2 == 0) then
        io.write(I .. " + " .. SUM .. " = " .. string.format('%3d', (SUM + I)), "\n")
        SUM = SUM + I
    end
    I = I + 1
until not (I <= 500)