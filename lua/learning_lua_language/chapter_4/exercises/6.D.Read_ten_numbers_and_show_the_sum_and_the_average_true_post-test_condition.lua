I = 1
N1, N2, N3, N4, N5, N6, N7, N8, N9, N10 = 0
repeat
    if (I == 1) then
        N1 = tonumber(io.read())
    elseif (I == 2) then
        N2 = tonumber(io.read())
    elseif (I == 3) then
        N3 = tonumber(io.read())
    elseif (I == 4) then
        N4 = tonumber(io.read())
    elseif (I == 5) then
        N5 = tonumber(io.read())
    elseif (I == 6) then
        N6 = tonumber(io.read())
    elseif (I == 7) then
        N7 = tonumber(io.read())
    elseif (I == 8) then
        N8 = tonumber(io.read())
    elseif (I == 9) then
        N9 = tonumber(io.read())
    elseif (I == 10) then
        N10 = tonumber(io.read())
    end
    I = I + 1
until not (I <= 10)
SUM = N1 + N2 + N3 + N4 + N5 + N6 + N7 + N8 + N9 + N10
io.write("Soma: "..SUM, "\n")
io.write("Media: "..SUM / 10, "\n")