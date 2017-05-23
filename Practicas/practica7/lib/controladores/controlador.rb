require_relative "../modelos/Libro.rb"
require_relative "../vistas/VistaLibreria.rb"

# Controlador para interactuar con la vista y el modelo.
module ControladorLibreria

	@libros = []
	@archivo = File.open(File.dirname(__FILE__) + "/../../data/libros.csv","r")

	# Inicia la ejecución de la librería.
	def start()
		inicializar_libros()
		opcion = nil
		while opcion != "6"
			VistaLibreria.muestra_menu()
			opcion = VistaLibreria.get_cadena()
			case opcion
				when "1"
					l = VistaLibreria.get_datos()
					registro(l[0], l[1], l[2], l[3], l[4])
				when "2"
					VistaLibreria.muestra_menu_edicion()
					o = VistaLibreria.get_cadena()
					isbn = VistaLibreria.get_isbn()
					case o
						when "1"
							VistaLibreria.muestra_mensaje("\nIntroduce el nuevo título\n")
							nuevo_titulo = VistaLibreria.get_cadena()
							editar_titulo(nuevo_titulo, isbn)
						when "2"
							VistaLibreria.muestra_mensaje("\nIntroduce el nuevo autor\n")
							nuevo_autor = VistaLibreria.get_cadena()
							editar_titulo(nuevo_autor, isbn)
						when "3"
							VistaLibreria.muestra_mensaje("\nIntroduce el nuevo precio\n")
							nuevo_precio = VistaLibreria.get_float()
							editar_precio(nuevo_precio, isbn)
						else VistaLibreria.muestra_mensaje("\nOpción inválida.")
					end
				when "3"
					isbn = VistaLibreria.get_isbn()
					eliminar(isbn)
				when "4" 
					isbn = VistaLibreria.get_isbn()
					nuevas_unidades = VistaLibreria.get_entero()
					agregar_libros(nuevas_unidades, isbn)
				when "5"
					ver_libros()
				when "6"
					break
				else VistaLibreria.muestra_mensaje("Opción inválida")
			end
		end
		VistaLibreria.muestra_mensaje("Guardando registros...")
		guarda_registros()
		VistaLibreria.muestra_mensaje("Programa finalizado con éxito.")
	end

	# Cons
	def inicializar_libros() 
		lineas = @archivo.readlines()
		for l in lineas
			if l == "\n" then next end
			datos = l.split(",")
			@libros.push(
				Libro.new(
					datos[0],
					datos[1],
					datos[2],
					datos[3],
					datos[4]
					)
				)
		end
		@archivo.close()
	end

	def guarda_registros()
		salida = File.open(File.dirname(__FILE__) + "/../../data/libros.csv","w")
		for libro in @libros
			salida.write("#{libro.isbn},#{libro.titulo},#{libro.autor},#{libro.precio},#{libro.unidades}\n")
		end
		salida.close()
	end

	# Registrar un nuevo libro.
	def registro(isbn, titulo, autor, precio, unidades)
		nuevo = Libro.new(isbn,titulo,autor,precio,unidades)
		@libros.push(nuevo)
		VistaLibreria.muestra_mensaje("Se ha agregado el libro:  \n#{nuevo.to_s}")
	end

	# Editar un libro por su título.
	def editar_titulo(titulo, isbn)
		libro = busca_libro(isbn)
		if libro
			@libros.delete(libro)
			libro.titulo = titulo
			@libros.push(libro)
		else 
			VistaLibreria.muestra_mensaje("No se encuentra el libro.")
		end
	end

	# Editar un libro por su autor.
	def editar_autor(autor, isbn)
		libro = busca_libro(isbn)
		if libro
			@libros.delete(libro)
			libro.autor = autor
			@libros.push(libro)
		else 
			VistaLibreria.muestra_mensaje("No se encuentra el libro.")
		end
	end

	# Editar un libro por su precio.
	def editar_precio(precio, isbn)
		libro = busca_libro(isbn)
		if libro
			@libros.delete(libro)
			libro.precio = precio
			@libros.push(libro)
		else 
			VistaLibreria.muestra_mensaje("No se encuentra el libro.")
		end
	end

	# Elimina un libro.
	def eliminar(isbn)
		libro = busca_libro(isbn)
		if libro
			@libros.delete(libro)
			VistaLibreria.muestra_mensaje("Se eliminó el libro: \n#{libro.to_s}\n con éxito")
		else 
			VistaLibreria.muestra_mensaje("No se encuentra el libro.")
		end
	end

	# Agrega más unidades al ISBN seleccionado.
	def agregar_libros(unidades, isbn)
		libro = busca_libro(isbn)
		if libro
			@libros.delete(libro)
			libro.unidades += unidades
			@libros.push(libro)
		else
			VistaLibreria.muestra_mensaje("No se encuentra el libro.")
		end
	end

	# Muestra todos los libros.
	def ver_libros
		cadena = "\n"
		num_registros = 0
		for libro in @libros
			cadena += "#{libro.to_s}\n"
			num_registros += 1
		end
		cadena += "\nNúmero de registros: #{num_registros}\n"
		VistaLibreria.muestra_mensaje(cadena)
	end

	# Busca un libro por ISBN y lo regresa; nil si no se encuentra.
	def busca_libro(isbn)
		for libro in @libros
			if libro.isbn == isbn
				return libro
			end
		end
		return nil
	end

end