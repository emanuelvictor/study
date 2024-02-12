print([[
In lua we have 'elseif' command. It's very like switch of java or C, we can encadeate elseif until the end of block.

And we don't need make
if (condition) then
    something
else
    if (otherCondition) then
        otherSomething
    end
end

We can do:
if (condition) then
    something
elseif (otherCondition) then
    otherSomething
end

Look, in the second from we save two lines of code, and one 'end' command.
So far we don't have used this, but from now on we will use it.
Look more in examples/Month_choicer.lua
]])
print('Composite Condition Deviation have a block of code to false return from condition')
print("Composite Conditions begins with 'if'")
print("Then we have to put the condition if(condition)")
print("And third we put then")
print("After the signature we put the code we want to execute inner the condition")
print("After the condition we put 'else' and it will be executed in false return from condition")
print("See the follow example")
print([[A = io.read("*l")]])
print("if (A > 2) then")
print("     print(\"A is greater than 2\")")
print("else")
print("     print(\"A is less or equals to 2\")")
print("end")
A = io.read("*n")
if (A > 2) then
    print("A is greater than 2")
else
    print("A is less or equals to 2")
end