print('Choice the math operation') -- separete this menu in a function in the future
print('[1] +')
print('[2] -')
print('[3] *')
print('[4] /')
OPERTAION = tonumber(io.read())
print()
--------------

if(OPERTAION > 0) and (OPERTAION < 5) then
    io.write('Insert value A:') A = tonumber(io.read())
    io.write('Insert value B:') B = tonumber(io.read())
    print()
    if(OPERTAION == 1) then
        result = A + B;
        print(string.format("\nThe result is %.2f", result).."\n") -- Must be separated in a function, to not broke DRY low and Clean Code.
    end
    if(OPERTAION == 2) then
        result = A - B;
        print(string.format("\nThe result is %.2f", result).."\n") -- Must be separated in a function, to not broke DRY low and Clean Code.
    end
    if(OPERTAION == 3) then
        result = A * B;
        print(string.format("\nThe result is %.2f", result).."\n") -- Must be separated in a function, to not broke DRY low and Clean Code.
    end
    if(OPERTAION == 4) then
        if(B == 0) then
            print('Error to divide '..A..' by '..B)
        else
            result = A / B;
            print(string.format("\nThe result is %.2f", result).."\n") -- Must be separated in a function, to not broke DRY low and Clean Code.
        end
    end
else
    print('Invalid operation')
end

print('This code is a structured algorithm, in the future we must try make it with Object Orientation')