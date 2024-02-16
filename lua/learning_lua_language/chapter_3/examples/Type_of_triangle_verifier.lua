io.write('Insert side A:') A = tonumber(io.read())
io.write('Insert side B:') B = tonumber(io.read())
io.write('Insert side C:') C = tonumber(io.read())

if(A < B + C) and (B < A + C) and (C < A + B) then
    if(A == B) and (B == C) then
        print("Equilateral triangle")
    else
        if(A == B) or (A==C) or (C == B) then
            print('Isosceles triangle');
        else
            print('Scalene triangle');
        end
    end
else
    print('Invalid triangle!')
end

print('This code is a structured algorithm, one day we must try make it with Object Orientation')