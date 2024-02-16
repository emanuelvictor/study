print([[
In lua we have a polemic instruction. It's ::goto::.
This instruct define a label in the code, and we can return to this step of code when we call 'goto ::label::'
In the follow example, always we choice a month, we print the month and then we go the '::endd::' label.



In java and javascript, we have return strucutre that makes something like that.
]])

io.write('Entry the month number: ')
N = tonumber(io.read())

if (N == 1) then
    print("January")
    goto endd
end
if (N == 2) then
    print("February")
    goto endd
end
if (N == 3) then
    print("March")
    goto endd
end
if (N == 4) then
    print("April")
    goto endd
end
if (N == 5) then
    print("May")
    goto endd
end
if (N == 6) then
    print("June")
    goto endd
end
if (N == 7) then
    print("July")
    goto endd
end
if (N == 8) then
    print("August")
    goto endd
end
if (N == 9) then
    print("September")
    goto endd
end
if (N == 10) then
    print("October")
    goto endd
end
if (N == 11) then
    print("November")
    goto endd
end
if (N == 12) then
    print("December")
    goto endd
end
if (N < 1 or N > 12) then
    print('Invalid month')
    goto endd
end

:: endd ::