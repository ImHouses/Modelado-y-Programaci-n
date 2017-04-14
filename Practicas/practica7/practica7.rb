require_relative "lib/controladores/controlador.rb"

# Main
if __FILE__ == $0
	include ControladorLibreria
	ControladorLibreria.start()
end