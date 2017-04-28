#!/usr/bin/env python
# -*- coding: utf-8 -*-
import socket
from listaArchivos import ListaArchivos
from os.path import dirname, abspath
class ServidorFTP(object):
	"""Clase para el servidor FTP.

	Tiene métodos para enviar y recibir mensajes con un cliente conectado a él.
	"""
	def __init__(self):
		self.HOST = "127.0.0.1"
		self.PORT = 5004
		self.contador = 0
		self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		self.s.bind((self.HOST,self.PORT))
		self.CONEXION = None
		self.LISTA_ARCHIVOS = ListaArchivos()

	def start(self):
		"""El servidor escucha conexiones y hace todo su trabajo."""
		while True:
			mensaje_servidor = ">>SERVIDOR:"
			self.s.listen()
			print(mensaje_servidor + "ESPERANDO POR CLIENTES CTRL-C PARA TERMINAR EJECUCIÓN")
			self.CONEXION, direccion = self.s.accept()
			print(mensaje_servidor + "CONEXIÓN RECIBIDA DE " + str(direccion))
			while True:
				mensaje_cliente = self.recibir_mensaje()
				if mensaje_cliente.startswith("download"):
					self.enviar_mensaje(str(self.LISTA_ARCHIVOS))
					nombre_archivo = self.recibir_mensaje().split(":")[1]
					self.enviar_archivo(nombre_archivo)
				elif mensaje_cliente.startswith("load"):
					nombre_archivo = mensaje_cliente.split(":")[1]
					recibir_archivo(nombre_archivo)
				elif mensaje_cliente.startswith("list:"):
					self.enviar_mensaje(str(self.LISTA_ARCHIVOS))
				elif mensaje_cliente == "stop":
					break

	def enviar_mensaje(self, mensaje):
		"""Envia un mensaje a través de la conexión.
		
		Args:
			mensaje: El mensaje a enviar a través de la conexión.
		"""
		self.CONEXION.send(mensaje.encode())



	def recibir_mensaje(self):
		"""Recibe un mensaje a través de la conexión.
		Returns:
			El mensaje recibido a través de la conexión.
		"""
		return self.CONEXION.recv(2048).decode()

	def enviar_archivo(self, nombre_archivo):
		"""Envía el archivo seleccionado en la lista de archivos.

		Args:
			nombre_archivo: El nombre del archivo.
		"""
		d = dirname(dirname(abspath(__file__)))
		archivo = open(str(d) + "/files/" + nombre_archivo, "r")
		archivo_lineas = archivo.readlines()
		print(archivo_lineas)
		cadena = ""
		for linea in archivo_lineas:
			cadena += linea
		self.CONEXION.send(cadena.encode())
		archivo.close()

	def recibir_archivo(self, nombre_archivo):
		"""Recibe el archivo y guarda el nombre.

		Args:
			nombre_archivo: El nombre del archivo para guardar.
		"""
		archivo = open("../files/" + nombre_archivo, "w")
		while True:
			linea = self.CONEXION.recv(4096).decode()
			if linea == "/end":
				break
			else:
				archivo.write(self.CONEXION.recv(4096).decode())
		archivo.close()

if __name__ == '__main__':
	servidor = ServidorFTP()
	servidor.start()

	
