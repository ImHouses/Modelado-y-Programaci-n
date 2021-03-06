# Módulo para graficar.
module Practica4
  # Función para procesar las entradas de los dulces.
  def procesar_dulces(archivo)
    dulces = {}
    for linea in archivo.readlines
      next if linea.eql?("\n")
      datos = linea.split('-')
      nombre = datos[0].to_s.delete(' ')
      datos = datos[1].split(':')
      id = datos[0].to_i
      cantidad = datos[1].to_i
      if cantidad > 0 && !dulces.key?(id)
        dulces[id] = Dulce.new(nombre, id, cantidad)
      elsif dulces.key?(id)
        nuevo_dulce = dulces[id]
        nuevo_dulce.cantidad = nuevo_dulce.cantidad + cantidad
        dulces[id] = nuevo_dulce
      end
    end
    salida = File.open('salida.txt', 'w')
    lista_dulces = dulces.values.sort
    for dulce in lista_dulces
      salida.write("#{dulce.nombre_dulce}-#{dulce.id}:#{dulce.cantidad}\n")
      puts("#{dulce.nombre_dulce}-#{dulce.id}:#{dulce.cantidad}")
    end
    salida.close
    print("\nGRÁFICA:\n")
    print(graficar(dulces))
    puts('Salida procesada correctamente')
  end

  # Función auxiliar para graficar los dulces que se tienen.
  def graficar(hash_dulces)
    lista_dulces = hash_dulces.values.sort
    top = 0
    for dulce in lista_dulces
      top = [dulce.cantidad, top].max
    end
    grafica = ''
    punto_inicio = "#{top}|".index('|')
    while top >= 1
      grafica += top.to_s.size == 1 ? "0#{top}| " : "#{top}| "
      for dulce in lista_dulces
        grafica += top <= dulce.cantidad ? '*  ' : '   '
      end
      grafica += "\n"
      top -= 1
    end
    grafica = agrega_espacios(grafica, punto_inicio + 2)
    for dulce in lista_dulces
      i = 0
      while i <= dulce.id.to_s.size
        grafica += '-'
        i += 1
      end
      grafica += ' '
    end
    grafica += "\n"
    grafica = agrega_espacios(grafica, punto_inicio + 2)
    for dulce in lista_dulces
      grafica += dulce.id.to_s.size == 1 ? "0#{dulce.id} " : "#{dulce.id} "
    end
    grafica += "\n"
    grafica
  end

  # Agregar espacios
  def agrega_espacios(cadena, punto)
    i = 0
    while i < punto
      cadena += ' '
      i += 1
    end
    cadena
  end
end

# Clase para dulces.
class Dulce
  # Get del nombre del dulce
  attr_reader :nombre_dulce

  # Get del id.
  attr_reader :id

  # Set y get de cantidad.
  attr_accessor :cantidad

  # Constructor
  def initialize(nombre_dulce, id, cantidad)
    @nombre_dulce = nombre_dulce
    @id = id
    @cantidad = cantidad
  end

  # Sobreescritura del operador '<=>' para ordenar los dulces de acuerdo al ID
  def <=>(other)
    id <=> other.id
  end
end

# Main
if $PROGRAM_NAME == __FILE__
  include Practica4
  begin
    entrada = File.open('entrada.txt', 'r')
  rescue
    print("Archivo no encontrado, debe llamarse \'entrada.txt\'...")
    exit
  end
  Practica4.procesar_dulces(entrada)
  entrada.close
end
