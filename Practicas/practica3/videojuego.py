# -*- coding: utf-8 -*- 

class Videojuego:

        # Constructor
	def __init__(self, nombre, genero, precio, desarrollador):
		self.nombre = nombre
		self.genero = genero
		if precio < 0:
			self.precio = 0
		else: self.precio = precio
		self.desarrollador = desarrollador
        # Regresa una representación en cadena del videojuego.        
	def toString(self):
		return ("\nNombre: %s\n" % self.nombre +
				"Género: %s\n" % self.genero +
				"Precio: $ %2.2f\n" % self.precio +
				"Desarrollador: %s" % self.desarrollador)
	def getNombre(self):
		return self.nombre

	def setNombre(self, nombre):
		self.nombre = nombre

	def getGenero(self):
		return self.genero

	def setGenero(self, genero):
		self.genero = genero

	def getPrecio(self):
		return self.precio

	def setPrecio(self, precio):
		self.precio = precio

	def getDesarrollador(self):
		return self.desarrollador

	def setDesarrollador(self, desarrollador):
		self.desarrollador = desarrollador
        

v1 = Videojuego("Halo 5: Guardians", "FPS", 999.99, "343 Industries")
v2 = Videojuego("Minecraft", "Sandbox", 545.50, "Mojang")
v3 = Videojuego("FIFA 17", "Deportes", 999.99, "EA Sports")
v4 = Videojuego("GTA V", "Acción", 679.99, "Rockstar North")
v5 = Videojuego("Call Of Duty: MW3", "FPS", 500, "Activision")
v6 = Videojuego("Battlefield", "FPS", 500, "Electronic Arts")
v7 = Videojuego("Sudeki", "RPG", 200, "Climax")
v8 = Videojuego("Halo 3: ODST", "FPS", 99, "Bungie")
v9 = Videojuego("Gears of War 3", "Shooter", 500, "Epic Games")
v10 = Videojuego("Gears of War 4", "Shooter", 900, "Microsoft Studios")

print(v1.toString())
print(v2.toString())
print(v3.toString())
print(v4.toString())
print(v5.toString())
print(v6.toString())
print(v7.toString())
print(v8.toString())
print(v9.toString())
print(v10.toString())

def masCaro(videojuegos):
        precio = 0
        caros = []
        for vj in videojuegos:
                if vj.getPrecio() >= precio:
                        precio = vj.getPrecio()
        for vj in videojuegos:
                if vj.getPrecio() >= precio:
                        caros.append(vj)
        return caros
def rebajaVideojuego(vj):
        return Videojuego(vj.getNombre(), vj.getGenero(), vj.getPrecio() - (vj.getPrecio() * 0.15), vj.getDesarrollador())

print("\nARTICULO: %s" % v1.toString())
print("ARTICULO REBAJADO %s" % rebajaVideojuego(v1).toString())
