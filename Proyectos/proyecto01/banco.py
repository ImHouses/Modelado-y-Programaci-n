# -*- coding: utf-8 -*-

"""Clase para representar un banco.
En esta se agrupan todos los datos de las cuentas."""
class Banco:

	"""Constructor.
	:param cuentas: Un diccionario de cuentas.
	:param cuenta_habientes: Un diccionario de cuenta_habientes.
	"""
	def __init__(self, cuentas, cuenta_habientes):
		self.cuentas = cuentas
		self.cuenta_habientes = cuenta_habientes

	"""Busca la cuenta asociada al número de cuenta dado.
	:param numero: El número de cuenta asociado a la cuenta a buscar.
	"""
	def buscar_cuenta(numero):

"""Clase para cuentas, las cuentas disponen de un número de cuenta que sirve 
de identificador y un monto."""
class Cuenta:

	def __init__(self, numero, monto):
		self.numero = numero
		self.monto = monto

"""Clase para representar un cuentahabiente del banco.
"""
class CuentaHabiente:

	"""Constructor"""
	def __init__(self, nombre, direccion, cuenta_activa, cuentas_inactivas):
		self.nombre = nombre
		self.direccion = direccion
		self.cuenta_activa = cuenta_activa
		self.cuentas_inactivas = cuentas_inactivas

	"""Regresa el monto que el cuentahabiente tiene en su cuenta activa.""" 
	def obtener_monto(self):
		return self.cuenta_activa.monto

	"""Ingresa un monto de dinero en la cuenta activa del cuentahabiente.
	:param monto: El monto que el cuentahabiente depositará.
	"""
	def depositar(monto):
		self.cuenta_activa.monto += monto

	"""Retira dinero de la cuenta activa del cuentahabiente.
	:param monto: El monto que el cuentahabiente retirará.
	"""
	def retirar(monto):
		self.cuenta_activa.monto -= monto

"""Clase para representar un gerente de banco.
"""
class GerenteBanco:

	"""Registra a un nuevo cuentahabiente.
	:param nombre: El nombre del cuentahabiente.
	:param curp: El CURP del cuentahabiente.
	:param direccion: La dirección del cuentahabiente.
	:param telefono: El teléfono del cuentahabiente.
	:param correo: El correo del cuentahabiente, éste puede ser opcional.
	"""
	def registrar(nombre curp, direccion, telefono, correo):
		ch = CuentaHabiente(nombre, curp, direccion, telefono, correo)






