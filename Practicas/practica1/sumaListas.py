def suma_lista(lista):
	res = 0
	for elemento in lista:
		if (isinstance(elemento, list)):
			res += suma_lista(elemento)
		elif (isinstance(elemento, str) and elemento.isdigit()):
			res += int(elemento)
		elif (isinstance(elemento, str) and not elemento.isdigit()):
			continue
		else: res += elemento
	return res

# lista_prueba = [1,1,1,1,[1,1], [1,2,3],1, "a", "1"]
# print(suma_lista(lista_prueba))