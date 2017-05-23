#!/usr/bin/env python
# -*- coding: utf-8 -*-
from os.path import dirname, abspath
class ListaArchivos(object):
	"""Clase para lista de archivos"""

	def __init__(self):
		"""Constructor."""
		d = dirname(dirname(abspath(__file__)))
		l = open(str(d) + "/files/FILES.dat", "r").readlines()
		self.lista = []
		for linea in l:
			self.lista.append(linea)
		self.i = 0
	
	def __iter__(self):
		"""Regresa un iterador de nuestro objeto, aquí se implementa el patrón
		de iterador.

		Returns:
			Un iterador de nuestro objeto de tipo ListaArchivos."""
		return self

	def __len__(self):
		"""Regresa la longitud de nuestra lista de archivos.
			
		Returns:
			La longitud de la lista de archivos.
		"""
		return len(self.lista)

	def __next__(self):
		"""Regresa el siguiente elemento al que nuestro iterador está apuntando.

		Returns:
			El siguiente objeto al que nuestro iterador está apuntando."""
		if (self.i < len(self.lista)):
			self.i += 1
			return self.lista[self.i-1]
		else:
			self.i = 0
			raise StopIteration()