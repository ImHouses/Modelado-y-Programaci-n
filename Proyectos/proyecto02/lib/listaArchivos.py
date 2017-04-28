#!/usr/bin/env python
# -*- coding: utf-8 -*-
from os.path import dirname, abspath
class ListaArchivos(object):
	"""Clase para lista de archivos"""

	def __init__(self):
		d = dirname(dirname(abspath(__file__)))
		l = open(str(d) + "/files/FILES.dat", "r").readlines()
		self.lista = []
		for linea in l:
			self.lista.append(linea)

	def __str__(self):
		if len(self.lista) == 0:
			return "NO HAY ARCHIVOS"
		cadena = ""
		for fila in self.lista:
			cadena += fila
		return cadena