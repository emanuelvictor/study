I = 0
:: begin ::
if (I % 2 == 1) then
    io.write(I, "\n")
end
I = I + 1
if (I <= 20) then
    goto begin
end