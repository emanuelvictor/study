I = 15
repeat
    io.write(string.format('%3d', I ^ 2), "\n")
    I = I + 1
until not (I <= 200)