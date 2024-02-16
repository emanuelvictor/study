io.write("Enter the worked hours: \n")
workHours = io.read("*number")
io.write("Enter the value of hour \n")
valueHour = io.read("*number")
io.write("Enter the percentage of discount: \n")
percentageOfDiscount = io.read("*number")

grossSalary = workHours * valueHour
discount = (percentageOfDiscount/100) * grossSalary
netSalary = grossSalary - discount

io.write("Gross Salary : ",grossSalary,"\n")
io.write("Discount : ",discount,"\n")
io.write("Take-home pay (net salary) : ",netSalary,"\n")
io.write("--------------------- with string.format \n")
io.write("Gross Salary : ",string.format("%7.2f", grossSalary),"\n")
io.write("Discount : ",string.format("%7.2f", discount),"\n")
io.write("Take-home pay (net salary) : ",string.format("%7.2f", netSalary),"\n")
io.write("%7 to digits before the coma, .2f to digits after the coma")