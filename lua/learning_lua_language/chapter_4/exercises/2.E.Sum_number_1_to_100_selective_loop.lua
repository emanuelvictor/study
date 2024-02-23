I = 0
SUM = 0
::begin::
io.write(I .. " + " .. SUM .. " = " .. string.format('%3d', (SUM + I)), "\n")
SUM = SUM + I
I = I + 1
if (I <= 100) then
    goto begin
end