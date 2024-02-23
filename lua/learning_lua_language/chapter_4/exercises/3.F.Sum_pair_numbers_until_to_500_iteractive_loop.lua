SUM = 0
for I = 0, 500 do
    if (I % 2 == 0) then
        io.write(I .. " + " .. SUM .. " = " .. string.format('%3d', (SUM + I)), "\n")
        SUM = SUM + I
    end
end