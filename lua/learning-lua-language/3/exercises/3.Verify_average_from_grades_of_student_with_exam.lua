io.write('Insert first grades: ')
firstGrade = tonumber(io.read())
io.write('Insert second grades: ')
secondGrade = tonumber(io.read())
io.write('Insert third grades: ')
thirdGrade = tonumber(io.read())
io.write('Insert fourth grades: ')
fourthGrade = tonumber(io.read())

average = (firstGrade + secondGrade + thirdGrade + fourthGrade) / 4

if (average >= 7) then
    print('Approved')
else
    io.write('Insert exam grade:')
    examGrade = tonumber(io.read())
    average = ((examGrade + average) / 2)
    if (average >= 5) then
        print('Approved by exam')
    else
        print('Repproved')
    end
end

print('The average of grades is ' .. average)