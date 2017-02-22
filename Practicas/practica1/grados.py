print("Escribe la acción a realizar.")
menu = ("Convertir a grados celsius desde farenheit: C\n" + 
		"Convertir a grados farenheit desde celsius: F\n")
opcion = input(menu)
grados = float(input("Introduce los grados a convertir..."))
if (opcion is "F"):
	print("Resultado: %2.2f°F" % ((grados * 1.8) + 32))
elif (opcion is "C"): print("Resultado:  %2.2f°C" % ((grados - 32) / 1.8))
else: print("Opción inválida")