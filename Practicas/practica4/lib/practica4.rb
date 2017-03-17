# Módulo para graficar.
module Practica4

	def procesar_dulces(archivo)
		dulces = Hash.new()
		for linea in archivo.readlines()
			datos = linea.split("-")
			nombre = datos[0].to_s().delete(' ')
			datos = datos[1].split(":")
			id = datos[0].to_i()
			cantidad = datos[1].to_i()
			if cantidad > 0 and not dulces.has_key?(id)
				dulces[id] = Dulce.new(nombre, id, cantidad)
			elsif dulces.has_key?(id)
				nuevo_dulce = dulces[id]
				nuevo_dulce.cantidad = cantidad
				dulces[id] = nuevo_dulce
			end
		end
		salida = File.open("salida.txt", "w")
		lista_dulces = dulces.values()
		for dulce in lista_dulces
			salida.write("#{dulce.nombre_dulce}-#{dulce.id}:#{dulce.cantidad}\n")
			puts("#{dulce.nombre_dulce}-#{dulce.id}:#{dulce.cantidad}")
		end
		salida.close()
		puts("Salida procesada correctamente")
	end
end

# Clase para dulces.
class Dulce

	attr_reader :nombre_dulce
	
	attr_reader :id
	
	#Set y get de cantidad
	attr_accessor :cantidad

	# Constructor
	def initialize(nombre_dulce, id, cantidad)
		@nombre_dulce, @id, @cantidad = nombre_dulce, id, cantidad
	end
end

if __FILE__ == $0
	include Practica4
	entrada = File.open("entrada.txt", "r")
	Practica4.procesar_dulces(entrada)
	entrada.close()
end