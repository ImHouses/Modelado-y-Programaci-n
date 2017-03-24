# -*- coding: utf-8 -*-

import socket
import shelve
import random
import datetime


# login_cuentahabiente:CURP,PASSWORD
# login_gerente:CUENTA,PASSWORD
# login_cajero:CUENTA,PASSWORD

# cuentahabiente_consulta
# cuentahabiente_abonar:MONTO
# cuentahabiiente_retirar:MONTO

# gerente_registro:NOMBRE,CURP,DIRECCIÓN,TELÉFONO,CORREO
# gerente_consultah:CURP,CAMPO
# gerente_consultac:CUENTA,CAMPO
# gerente_desactiva:CUENTA

# cajero_abonar:CUENTA,MONTO
# cajero_retirar:CUENTA,MONTO

"""Clase para gerentes de banco"""
class Gerente:
	def __init__(self, cuenta, passwd):
		self.cuenta = cuenta
		self.passwd = passwd

"""Genera una nueva contraseña de 8 dígitos"""
def generar_pass():
	return random.randint(10000000, 99999999)


"""Genera un nuevo número de cuenta que no esté en las cuentas del banco"""
def generar_cuenta():
	ncuenta = str(random.randint(1000000000000000, 9999999999999999))
	while ncuenta in cuentas:
		ncuenta = str(random.randint(1000000000000000, 9999999999999999))
	return ncuenta

"""Nos dice si la contraseña del cuentahabiente coincide con la 
contraseña dada"""
def login_cuentahabiente(curp, passwd):
	print(cuentahabientes[curp].passwd == passwd)
	return cuentahabientes[curp].passwd == passwd

"""Nos dice si la contraseña del gerente coincide con la contraseña dada"""
def login_gerente(cuenta, passwd):
	print(gerentes[cuenta].passwd == passwd)
	return gerentes[cuenta].passwd == passwd

"""Nos dice si la contraseña del cajero coincide con la contraseña dada"""
def login_cajero(cuenta, passwd):
	print(cajeros[cuenta].passwd == passwd)
	return cajeros[cuenta].passwd == passwd

"""Regresa un arreglo que contiene los datos dada una cadena en formato CSV
:param cadena: La cadena que contiene los datos."""
def get_datos(cadena):
	return cadena.split(":")[1].split(",")

"""Regresa el cuentahabiente a buscar, None en caso de que no se encuentre.
:param CURP: La CURP del cuentahabiente a buscar."""
def buscar_cuentahabiente(CURP, cuentahabientes):
	ch = None
	try:
		ch = self.cuentahabientes[CURP]
	except KeyError:
		return cuentahabiente
	else:
		return cuentahabiente

"""Clase para cuentahabientes."""
class Cuentahabiente:

	def __init__(self, nombre, curp, direccion, telefono, email, cuenta,
				cuentas_inactivas,
				passwd):
		self.nombre = nombre
		self.curp = curp
		self.direccion = direccion
		self.telefono = telefono
		self.email = email
		self.cuenta = cuenta
		self.cuentas_inactivas = cuentas_inactivas
		self.passwd = passwd

	def __str__(self):
		return ("Nombre: %s\n" % self.nombre +
				"CURP: %s\n" % self.curp +
				"DIRECCIÓN: %s\n" % self.direccion +
				"EMAIL: %s\n" % self.email +
				"CUENTA: %s\n" % self.cuenta +
				"CUENTAS INACTIVAS: %s\n" % self.cuentas_inactivas +
				"CONTRASEÑA: %d\n" % self.passwd)

	"""Regresa el saldo de la cuenta del cuentahabiente"""
	def consultar(self):
		return cuentas[str(self.cuenta)].monto

	"""Suma el monto a depositar al monto de la cuenta
	:param monto: El monto a sumar a los fondos de la cuenta."""
	def abonar(self, monto):
		m_cuenta = cuentas[self.cuenta]
		m_cuenta.monto += monto
		cuentas[self.cuenta] = m_cuenta

	"""Resta el monto dado al monto de la cuenta.
	:param monto: El monto a restar a los fondos de la cuenta."""
	def retirar(self, monto):
		m_cuenta = cuentas[self.cuenta]
		m_cuenta.monto -= monto
		cuentas[self.cuenta] = m_cuenta



"""Clase para las cuentas"""
class Cuenta:
	def __init__(self, numero, monto):
		self.numero = numero
		self.monto = monto
		self.fecha = str(datetime.datetime.today())

"""Clase para cajeros"""
class Cajero:
	def __init__(self, n_cuenta, passwd):
		self.n_cuenta = n_cuenta
		self.passwd = passwd


BANCO = {"cuentahabientes": shelve.open("data/datos_cuentahabientes.dat"),
			"cuentas": shelve.open("data/datos_cuentas.dat")}
cuentahabientes = BANCO["cuentahabientes"]
cuentas = BANCO["cuentas"]
gerentes = {"G1": Gerente("G1", 12345678),
			"G2": Gerente("G2", 12345678),
			"G3": Gerente("G3", 12345678)}
cajeros = {"C1": Cajero("C1", 12345678),
			"C2": Cajero("C2", 12345678),
			"C3": Cajero("C3", 12345678)}
def main():
	print("Iniciando servidor del banco...")
	HOST = "127.0.0.1"
	PORT = 5002
	contador = 0
	s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	s.bind((HOST, PORT))
	print("Servidor iniciado.")
	while True:
		s.listen()
		CONEXION, direccion = s.accept()
		contador += 1
		mensaje_servidor = "mensaje_servidor>"
		while True:
			mensaje_cliente = recibir_mensaje(CONEXION)
			if mensaje_cliente.startswith("login_cuentahabiente"):
				cred_cuentahabiente = get_datos(mensaje_cliente)
				curp = cred_cuentahabiente[0]
				passwd = int(cred_cuentahabiente[1])
				if login_cuentahabiente(curp, passwd):
					cuenta_h = cuentahabientes[curp]
					enviar_mensaje(CONEXION, mensaje_servidor + "OK")
					mensaje_cliente = recibir_mensaje(CONEXION) # Recibimos la opción
					if mensaje_cliente == "cuentahabiente_consulta":
						enviar_mensaje(CONEXION,
										"Tu saldo es: %2.2f" % 
										cuenta_h.consultar())
					elif mensaje_cliente.startswith("cuentahabiente_abonar"):
						monto_abonar = float(get_datos(mensaje_cliente)[0])
						cuenta_h.abonar(monto_abonar)
						enviar_mensaje(CONEXION, mensaje_servidor +
										"Abonaste: %2.2f\n" % monto_abonar +
										"Saldo: %2.2f\n" % cuenta_h.consultar())
					elif mensaje_cliente.startswith("cuentahabiente_retirar"):
						monto_retirar = float(get_datos(mensaje_cliente)[0])
						cuenta_h.retirar(monto_retirar)
						enviar_mensaje(CONEXION, mensaje_servidor +
										"Retiraste: %2.2f\n" % monto_retirar +
										"Saldo: %2.2f\n" % cuenta_h.consultar())
				else:
					enviar_mensaje(CONEXION, mensaje_servidor + "ERROR")
					continue
			elif mensaje_cliente.startswith("login_gerente"):
				print(mensaje_cliente)
				cred_gerente = get_datos(mensaje_cliente)
				cuenta = cred_gerente[0].strip()
				passwd = int(cred_gerente[1].strip())
				print("cuenta: " + cuenta)
				print("passwd: " + str(passwd))
				if not cuenta in gerentes:
					print("ERROR")
					enviar_mensaje(CONEXION, mensaje_servidor + "ERROR")
				if login_gerente(cuenta, passwd):
					print("INICIO CORRECTO-REGISTRO")
					gerente = gerentes[cuenta]
					enviar_mensaje(CONEXION, mensaje_servidor + "OK")
					mensaje_cliente = recibir_mensaje(CONEXION)
					print(mensaje_cliente)
					if mensaje_cliente.startswith("gerente_registro"):
						datos_nuevo = get_datos(mensaje_cliente)
						print(datos_nuevo)
						n_nombre = datos_nuevo[0]
						n_curp = datos_nuevo[1]
						n_direccion = datos_nuevo[2]
						n_tel = datos_nuevo[3]
						n_email = datos_nuevo[4]
						nn_cuenta = generar_cuenta()
						n_passwd = generar_pass()
						n_cuentahabiente = Cuentahabiente(n_nombre,
															n_curp,
															n_direccion,
															n_tel,
															n_email,
															nn_cuenta,
															[],
															n_passwd)
						nueva_cuenta = Cuenta(nn_cuenta, 0)
						cuentahabientes[n_curp] = n_cuentahabiente
						cuentas[str(nn_cuenta)] = nueva_cuenta
						enviar_mensaje(CONEXION, n_cuentahabiente.__str__())
						enviar_mensaje(CONEXION, mensaje_servidor + "OK")

					elif mensaje_cliente.startswith("gerente_consultah"):
						datos = get_datos(mensaje_cliente)
						curp = str(datos[0])
						campo = str(datos[1])
						print(datos)
						cuenta_h = cuentahabientes[curp]
						if campo == "nombre":
							enviar_mensaje(CONEXION, "Nombre del cuentahabiente: %s\n"
											% cuenta_h.nombre)
						elif campo == "direccion":
							enviar_mensaje(CONEXION, "Dirección del cuentahabiente: %s\n"
											% cuenta_h.direccion)
						elif campo == "n_cuenta":
							enviar_mensaje(CONEXION, "Número de cuenta activa: %d\n"
											% cuenta_h.cuenta)
						elif campo == "n_cuenta_inactivos":
							enviar_mensaje(CONEXION, "Números de cuenta inactivos: %s\n"
											% cuenta_h.cuentas_inactivas)
					elif mensaje_cliente.startswith("gerente_consultac"):
						datos = get_datos(mensaje_cliente)
						cuenta = str(datos[0])
						campo = str(datos[1])
						print(datos)
						print(cuentas)
						cuenta = cuentas[cuenta]
						if campo == "numero":
							enviar_mensaje(CONEXION, "Número de cuenta: %d\n" 
											% cuenta.numero)
						elif campo == "fecha":
							enviar_mensaje(CONEXION, "Fecha de creación: %s\n"
											% cuenta.fecha)
						elif campo == "saldo":
							enviar_mensaje(CONEXION, "Saldo: %2.2f\n"
											% cuenta.monto)
					elif mensaje_cliente.startswith("gerente_desactiva"):
						datos = get_datos(mensaje_cliente)
						numero_cuenta = str(datos[0])
						print(datos)
						cuenta = cuentas[numero_cuenta]
						for usuario in cuentahabientes.values():
							if usuario.cuenta == cuenta.numero:
								usuario.cuenta = Cuenta(generar_cuenta(),
														cuenta.monto)
								usuario.cuentas_inactivas.append(numero_cuenta)
								break
						enviar_mensaje(CONEXION, numero_cuenta + "DESACTIVADO")
			elif mensaje_cliente.startswith("login_cajero"):
				cred_cajero = get_datos(mensaje_cliente)
				cuenta = cred_cajero[0].strip()
				passwd = int(cred_cajero[1].strip())
				if not cuenta in cajeros:
					print("ERROR, no existe el cajero")
					enviar_mensaje(CONEXION, mensaje_servidor + "ERROR")
				if login_cajero(cuenta, passwd):
					print("INICIO CORRECTO-CAJERO")
					cajero = cajeros[cuenta]
					enviar_mensaje(CONEXION, mensaje_servidor + "OK")
					mensaje_cliente = recibir_mensaje(CONEXION)
					print(mensaje_cliente)
					datos = get_datos(mensaje_cliente)
					cuenta = datos[0]
					monto = float(datos[1])
					print(datos)
					if not cuenta in cuentas:
						enviar_mensaje(mensaje_servidor + "ERROR")
					if mensaje_cliente.startswith("cajero_abonar"):
						c = cuentas[cuenta]
						c.monto -= monto
						cuentas[cuenta] = c
						enviar_mensaje(CONEXION, "Has abonado: %2.2f\n con éxito"
										% monto)
					elif mensaje_cliente.startswith("cajero_retirar"):
						c = cuentas[cuenta]
						if c.monto < monto:
							enviar_mensaje(CONEXION, "Fondos insuficientes..")
						else:
							c.monto += monto
							cuentas[cuenta] = c
							enviar_mensaje(CONEXION, "Has retirado: %2.2f con éxito"
											% monto)
					enviar_mensaje(CONEXION, mensaje_servidor + "OK")
			else: break

"""Función auxiliar para recibir un mensaje de una conexión. Regresa una cadena
con el mensaje recibido.
:param conexion: La conexión de donde se recibirá el mensaje.
"""
def recibir_mensaje(CONEXION):
	return CONEXION.recv(2048).decode()

"""Función auxiliar para enviar un mensaje a través de una conexion.
:param mensaje: El mensaje a enviar a través de la conexión.
:param conexion: La conexión a través de la cual se enviará el mensaje.
"""
def enviar_mensaje(CONEXION, mensaje):
	CONEXION.send(mensaje.encode())

if __name__ == '__main__':
	main()