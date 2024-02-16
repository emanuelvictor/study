print("You must make the follow tests in interact view from lua")

print("print(7.5 + 4.8) must show 13.3")
print(7.5 + 4.8)
print("print(-2 + -3) must show 5")
print(-2 + -3)
print("print(2 * 3) must show 6")
print(2 * 3)
print("print(10 / 4) must show 2.5")
print(10 / 4)
print("print(2 + (5 * 3)) must show 2")
print(10 % 4)
print("print(2 + (5 * 3)) must show 8")
print(2 ^ 3)
print("print(2 + (5 * 3)) must show 17")
print(2 + 5 * 3)
print("print(2 + (5 * 3)) must show 17")
print(2 + (5 * 3))
print("print((2 + 5) * 3) must show 21")
print((2 + 5) * 3)

--To get math results we can use "=" (equals) symbol instead print(). In version 3.6, and versions created after it, we can ommit the symbol "="
--6
--=2*3
--0.66666666666667
--=2/3
--5
--=2+3
-- -1
--=2-3

--Lua works with scientific notation too. For example, to obtain the decimal result from 9.5e2 the return must be 950.0. Because the calc is 9,5 * 10 ^ 2.
--Try  "=9.5e-2" to obtain 0.095.

--The interactive view also allow to works with hexadecimal values. If you input 0xa, 0xA, 0Xa or 0XA, you must receive 10 with return.

print("The interact view also allows input values into variables")
A = 2
B = 3
print(A+ B)

print("The lua language is case sensitive, see the follow examples")
print("COLOR = 1")
COLOR = 1
print("COLOr = 2")
COLOr = 2
print("COLor = 3")
COLor = 3
print("COlor = 4")
COlor = 4
print("Color = 5")
Color = 5
print("color = 6")
color = 6
print("ColoR = 7")
ColoR = 7
print("coloR = 8")
coloR = 8

print("print(COLOR)")
print(COLOR)
print("print(COLOr)")
print(COLOr)
print("print(COLor)")
print(COLor)
print("print(COlor)")
print(COlor)
print("print(Color)")
print(Color)
print("print(color)")
print(color)
print("print(ColoR)")
print(ColoR)
print("print(coloR)")
print(coloR)

--We can use one line to create multiples variables too
print("CODE, NAME = 1968, \"Bryan\"")
CODE, NAME = 1968, "Bryan"
print(COLOR)
print(NAME)