class VistaLibreria

	def self.muestra_mensaje(mensaje)
		puts(mensaje)
	end

	# Pide al usuario por los datos de un nuevo libro y regresa una lista con los
	# datos.
	def self.get_datos()
		puts("Introude el ISBN")
		isbn = gets().chomp()
		puts("Introduce el titulo.")
		titulo = gets().chomp()
		puts("Introduce el autor o los autores separados por punto y coma.")
		autor = gets().chomp()
		puts("Introduce el precio.")
		precio = gets().chomp().to_f()
		puts("Introduce las unidades.")
		unidades = gets().chomp().to_i()
		return [isbn,titulo,autor,precio,unidades]
	end

	def self.muestra_menu()
		menu = "REPOSITORIO DE LIBROS\n¿Qué deseas hacer?\n" \
					 "1. Registrar un nuevo libro\n" \
					 "2. Editar un libro\n" \
					 "3. Eliminar un libro\n" \
					 "4. Agregar existencias\n" \
					 "5. Ver libros existentes\n" \
					 "6. Salir\n"
		puts(menu)
	end

	def self.muestra_menu_edicion()
		menu = "¿Sobre qué campo deseas hacer la edición?\n" \
					 "1. Título\n" \
					 "2. Autor\n" \
					 "3. Precio\n"
		puts(menu)

	end

	def self.get_cadena()
		return gets().chomp()
	end

	def self.get_entero()
		return gets().chomp().to_i()
	end

	def self.get_float()
		return gets().chomp().to_f()
	end

	def self.get_isbn()
		puts("Introduce el ISBN")
		return get_cadena()
	end

end