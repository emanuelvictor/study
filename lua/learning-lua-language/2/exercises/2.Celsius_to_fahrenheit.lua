io.write('Insert fahrenheit temperaure: \n')
fahrenheitTemperature = io.read("*number")
celsiusTemperature = ((fahrenheitTemperature - 32) * 5) / 9
io.write('Fahrenheit temperature is '..fahrenheitTemperature..'\n')
io.write('Celsius temperature is '..string.format("%7.3f",celsiusTemperature)..'\n')