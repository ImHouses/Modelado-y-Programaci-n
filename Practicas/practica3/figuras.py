# -*- coding: utf-8 -*-
import math

class Figura:

    def __init__(self, radio):
        self.radio = radio
        
class Cilindro(Figura):

    def __init__(self, radio, altura):
        Figura.__init__(self, radio)
        self.altura = altura
        
    def calculaVolumen(self):
        return math. pi * self.radio * self.radio * self.altura

class Esfera(Figura):

    def __init__(self, radio):
        Figura.__init__(self,radio)

    def calculaVolumen(self):
        return (4.0 / 3.0) * math.pi * self.radio

class Cono(Figura):

    def __init__(self, radio, altura):
        Figura.__init__(self, radio)
        self.altura = altura

    def calculaVolumen(self):
        return (math.pi * radio * radio * self.altura) / 3.0

radio = float(input("Introduce un radio...\n"))
altura = float(input("Introduce una altura...\n"))

cilindro = Cilindro(radio, altura)
esfera = Esfera(radio)
cono = Cono(radio, altura)

print("Volumen del cilindro = %2.2f" % cilindro.calculaVolumen())
print("Volumen de la esfera = %2.2f" % esfera.calculaVolumen())
print("Volumen del cono = %2.2f" % cono.calculaVolumen())
