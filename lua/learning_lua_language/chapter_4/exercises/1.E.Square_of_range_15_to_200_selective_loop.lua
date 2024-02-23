I = 15
:: begin ::
io.write(string.format('%3d', I ^ 2), "\n")
I = I + 1
if (I >= 15 and I <= 200) then
    goto begin
end