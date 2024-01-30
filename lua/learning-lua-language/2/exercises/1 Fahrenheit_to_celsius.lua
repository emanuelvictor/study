io.write('Insert celsius temperaure: \n')
celsiusTemperature = io.read("*number")
fahrenheitTemperature = (9 * celsiusTemperature + 160) / 5
io.write('Celsius temperature is '..celsiusTemperature..'\n')
io.write('Fahrenheit temperature is '..string.format("%7.3f",fahrenheitTemperature)..'\n')