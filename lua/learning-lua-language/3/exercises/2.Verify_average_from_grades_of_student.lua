io.write('Insert first grades: ')
firstGrades = tonumber(io.read())
io.write('Insert second grades: ')
secondGrades = tonumber(io.read())
io.write('Insert third grades: ')
thirdGrades = tonumber(io.read())
io.write('Insert fourth grades: ')
fourthGrades = tonumber(io.read())

average = (firstGrades + secondGrades + thirdGrades + fourthGrades) / 4

if (average >= 5) then
    print('Approved')
else
    print('Repproved')
end

print('The average of grades is ' .. average)