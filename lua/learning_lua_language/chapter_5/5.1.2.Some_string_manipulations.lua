print([[Let's see string manipulation:]])
print([[Size of string:
We can use string.len function to show the size of string. Like the follow example:
sentence = 'Lua langague'
print(string.len(sentence))
Must show 12.
]])
sentence = 'Lua langague'
print(string.len(sentence))
print([[We can use # operator too. Like the folow example:
sentence = 'Lua langague'
print(#sentence)
Must show 12 too.
]])
print(#sentence)
print([[We can change the case of the string too, with string.upper and string.lower internal functions. Like the folow example:
sentence = 'Lua language'
print(string.upper(sentence))
Must show 'LUA LANGUAGE'.
print(string.lower(sentence))
Must show 'lua langage'.
]])
sentence = 'Lua language'
print(string.upper(sentence))
print(string.lower(sentence))
print([[We revert the string with string.reverse function. Se the folow example:
sentence = 'Lua language'
print(string.reverse(sentence))
Must show 'egaugnal auL'
]])
sentence = 'Lua language'
print(string.reverse(sentence))