# -*- coding: utf-8 -*-
import socket


"""Método auxiliar para recibir un mensaje de una conexión. Regresa una cadena
con el mensaje recibido.
:param conexion: La conexión de dónde se recibirá el mensaje.
"""
def recibir_mensaje(conexion):
	return conexion.recv(2048).decode()

def enviar_mensaje(mensaje, conexion):
	conexion.send(mensaje.encode())

def construye_bandera(cadena):
	b = {"1-1": "cuentahabiente_consulta", "1-2": "cuentahabiente_abonar:",
			"1-3": "cuentahabiente_retirar:", "2-1": "gerente_registro:",
			"2-2-1": "gerente_consultah:nombre"}
	return b[cadena]

def main():
	HOST = "127.0.0.1"
	PORT = 5002
	print("Cliente de banco...")
	print("Iniciando conexión...")
	s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
	s.connect((HOST, PORT))
	print("Conexión iniciada!")
	mensaje_servidor = ""
	while True:
		menu = ("Selecciona tu tipo de usuario...\n" +
				"1. Cuentahabiente\n" +
				"2. Gerente\n" +
				"3. Cajero\n")
		opcion = str(input(menu))
	
		
		if opcion == "1":
			print("Has escogido iniciar sesión como cuentahabiente.\n")
			curp = input("Por favor, introduce tu CURP\n")
			passwd = input("Por favor, introduce tu contraseña.\n")
			login = ("login_cuentahabiente:" + curp + "," + passwd)
			enviar_mensaje(login, s)
			mensaje_servidor = recibir_mensaje(s)
			if mensaje_servidor == "mensaje_servidor>ERROR":
				print("Ha ocurrido un error")
			elif mensaje_servidor == "mensaje_servidor>OK":
				print("INICIO DE SESIÓN CORRECTO")
				menu = ("Selecciona lo que quieres hacer...\n" +
						"1. Consultar saldo\n" +
						"2. Abonar\n" +
						"3. Retirar\n")
				n_opcion = input(menu)
				if n_opcion == "1":
					enviar_mensaje(construye_bandera(opcion + "-" + n_opcion), s)
				elif n_opcion == "2" or n_opcion == "3":
					monto = str(float(input("Introduce el monto")))
					enviar_mensaje(construye_bandera(opcion + "-" + n_opcion) +
									monto, s)
				print(recibir_mensaje(s))
		elif opcion == "2":
			while True:
				print("Has escogido iniciar sesión como gerente.\n")
				g_cuenta = str(input("Introduce tu cuenta de gerente...\n"))
				g_passwd = str(input("Introduce tu contraseña...\n"))
				login = ("login_gerente:" + g_cuenta + "," + g_passwd)
				enviar_mensaje(login, s)
				mensaje_servidor = recibir_mensaje(s)
				if mensaje_servidor == "mensaje_servidor>ERROR":
					print("Ha ocurrido un error")
				elif mensaje_servidor == "mensaje_servidor>OK":
					print("INICIO DE SESIÓN CORRECTO")
					menu = ("Selecciona lo que quieres hacer...\n" +
							"1. Registrar cuentahabiente\n" +
							"2. Obtener información de un cuentahabiente\n" +
							"3. Obtener información de una cuenta\n" +
							"4. Cancelar una cuenta\n")
					n_opcion = input(menu)
					if n_opcion == "1":
						nombre = input("Introduce el nombre completo\n")
						curp = input("Introduce el CURP\n")
						direccion = input("Introduce la dirección\n")
						telefono = input("Introduce el teléfono\n")
						email = "None"
						if input("¿Correo electrónico? S/N") == "S":
							email = input("Introduce el correo electrónico...\n")
						m = (construye_bandera(opcion + "-" + n_opcion) + 
																nombre + "," +
																curp + "," +
																direccion + "," +
																telefono + "," +
																email)
						enviar_mensaje(m, s)
						print(recibir_mensaje(s))
					elif n_opcion == "2":
						menu = ("¿Qué quieres obtener?\n" +
								"1. Nombre completo\n" +
								"2. Dirección\n" +
								"3. Número de cuenta activa\n" +
								"4. Números de cuenta inactivos\n")
						print(menu)
						o = input("Introduce la opción\n")
						curp = input("Introduce la curp del cuentahabiente.\n")
						if o == "1":
							enviar_mensaje("gerente_consultah:" + curp + "," + "nombre", s)
						elif o == "2":
							enviar_mensaje("gerente_consultah:" + curp + "," + "direccion", s)
						elif o == "3":
							enviar_mensaje("gerente_consultah:" + curp + "," + "n_cuenta", s)
						elif o == "4:":
							enviar_mensaje("gerente_consultah:" + curp + "," + "n_cuenta_inactivos", s)
						print(recibir_mensaje(s))
					elif n_opcion == "3":
						menu = ("¿Qué quieres obtener?\n" +
								"1. Número de cuenta\n" +
								"2. Fecha de creación\n" +
								"3. Saldo\n")
						print(menu)
						c = input("Introduce la cuenta\n")
						o = input("Introduce la opción\n")
						if o == "1":
							enviar_mensaje("gerente_consultac:" + c + "," + "numero", s)
						elif o == "2":
							enviar_mensaje("gerente_consultac:" + c + "," + "fecha", s)
						elif o == "3":
							enviar_mensaje("gerente_consultac:" + c + "," + "saldo", s)
						print(recibir_mensaje(s))
					elif n_opcion == "4":
						c = input("Introduce la cuenta\n")
						enviar_mensaje("gerente_desactiva:" + c, s)
						print(recibir_mensaje(s))
					salir = input("¿SALIR? S/N")
					if salir == "S":
						break
					else: continue

		elif opcion == "3":
			print("Has escogido iniciar sesión como cajero.\n")
			usuario = input("Por favor, introduce tu usuario de cajero\n")
			passwd = input("Por favor, introduce tu contraseña de cajero\n")
			login = ("login_cajero:" + usuario + "," + passwd)
			enviar_mensaje(login, s)
			mensaje_servidor = recibir_mensaje(s)
			if mensaje_servidor == "mensaje_servidor>OK":
				menu = ("¿Qué quieres hacer?\n" +
						"1. Abonar a cuenta\n" +
						"2. Retirar de cuenta\n")
				opcion = input(menu)
				cuenta = input("Introduce la cuenta...")
				if opcion == "1":
					monto = str(input("Introduce el monto a abonar...\n"))
					enviar_mensaje("cajero_abonar:" + cuenta + "," + monto, s)
					print(recibir_mensaje(s))
				elif opcion == "2":
					monto = str(input("Introduce el monto a retirar...\n"))
					enviar_mensaje("cajero_retirar:" + cuenta + "," + monto, s)
					print(recibir_mensaje(s))

		else: print("Opción inválida")
		salir = input("¿SALIR? S/N")
		if salir == "S":
			break
		else: continue

if __name__ == '__main__':
	main()