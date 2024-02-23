I = 1
:: begin ::
if (I % 4 == 0) then
    io.write(I, "\n")
end
I = I + 1
if (I <= 200) then
    goto begin
end