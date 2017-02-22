def interseccion(c1, c2):
	res = []
	for elemento in c1:
		if (contiene(elemento, c2)):
			res.append(elemento)
	return res

def union(c1, c2):
	res = []
	for elemento in c1:
		if (contiene(elemento, res)):
			continue
		else: res.append(elemento)
	for elemento in c2:
		if (contiene(elemento, res)):
			continue
		else: res.append(elemento)
	return res

def contiene(elemento, l):
	for e in l:
		if(e is elemento):
			return True
	return False

l1 = [1,2,3,4]
l2 = [3,4,5,6,7]

print("Intersección: " + str(interseccion(l1,l2)))
print("Unión: " + str(union(l1,l2)))
