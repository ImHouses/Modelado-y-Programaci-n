#!/usr/bin/env python
# -*- coding: utf-8 -*-
import socket
class ServidorFTP(object):
	"""Clase para el servidor FTP.

	Tiene métodos para enviar y recibir mensajes con un cliente conectado a él.
	"""
	def __init__(self):
		self.HOST = "127.0.0.1"
		self.PORT = 5000
		self.contador = 0
		self.s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
		s.bind((HOST,PORT))
		self.CONEXION = None

	def start(self):
		"""El servidor escucha conexiones y hace todo su trabajo."""
		while True:
			self.s.listen()
			self.CONEXION, direccion = self.s.accept()
			mensaje_servidor = ">>SERVIDOR:"
			while True:
				mensaje_cliente = recibir_mensaje()
				if mensaje_cliente.startswith("download"):
					nombre_archivo = mensaje_cliente.split(":")[1]
					enviar_archivo(nombre_archivo)
				elif mensaje_cliente.startswith("load"):
					nombre_archivo = mensaje_cliente.split(":")[1]
					recibir_archivo(nombre_archivo)

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
		archivo = open("../files/" + nombre_archivo, "r").readlines()
		self.CONEXION.send("file".encode())
		for linea in archivo:
			self.CONEXION.send(linea.encode())
		self.CONEXION.send("end".encode())
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
		

	
