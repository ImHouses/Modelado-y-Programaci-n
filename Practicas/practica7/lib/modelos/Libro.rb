# Clase para Libros.

class Libro
	# Get del ISBN.
	attr_reader :isbn
	# Get de las unidades.
	attr_reader :unidades
	# Get y Set del título.
	attr_accessor :titulo
	# Get y Set del autor.
	attr_accessor :autor
	# Get y Set del precio.
	attr_accessor :precio

	# Constructor
	def initialize(isbn, titulo, autor, precio, unidades)
		@isbn = isbn
		@titulo = titulo
		@autor = autor
		@precio = precio
		@unidades = unidades
	end

	# Regresa una representación en cadena del libro.
	def to_s
		return "ISBN: #{@isbn}\nTítulo: #{@titulo}\nAutor: #{@autor}\n" \
			   "Precio: #{@precio}\nUnidades: #{@unidades}"
	end
end

