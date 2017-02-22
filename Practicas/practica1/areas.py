import math

def triangulo(base, altura):
	return (base * altura) / 2.0

def cuadrado(lado):
	return lado * lado

def rectangulo(base, altura):
	return base * altura

def rombo(dMayor, dmenor):
	return (dMayor * dmenor) / 2.0

def trapecio(altura, a, b):
	return altura * ((a + b) / 2.0)

def circulo(radio):
	return math.pi * (radio * radio)

def poligono_regular(long_lado, num_lados, apotema):
	return (perimetro(long_lado, num_lados) * apotema) / 2.0

def perimetro(long_lado, num_lados):
	return long_lado * num_lados

def elipse(semiejeA, semiejeB):
	return math.pi * semiejeA * semiejeB
