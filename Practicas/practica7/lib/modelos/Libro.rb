class Libro

	attr_reader :isbn
	attr_reader :unidades
	attr_accessor :titulo
	attr_accessor :autor
	attr_accessor :precio

	def initialize(isbn, titulo, autor, precio, unidades)
		@isbn = isbn
		@titulo = titulo
		@autor = autor
		@precio = precio
		@unidades = unidades
	end

	def to_s
		return "ISBN: #{@isbn}\nTítulo: #{@titulo}\nAutor: #{@autor}\n" \
			   "Precio: #{@precio}\nUnidades: #{@unidades}"
	end
end

