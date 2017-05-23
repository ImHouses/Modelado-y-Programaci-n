import socket

class ClienteFTP(object):
    """ Clase para el cliente FTP. """

    def __init__(self):
        self.HOST ="127.0.0.1"
        self.PORT = 5001
        # Creamos un socket
        print ("Intentamos conectarnos")
        self.miSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        #Conexion al servidor
        self.miSocket.connect((self.HOST, self.PORT))
        print ("Estamos conectados al servidor")

    def start(self):
        while 1:
            opcion = self.muestraMenu()
            if (opcion == 1):
                # Lista
                self.mirarLista()
            elif (opcion == 2):
                # Leer Archivo
                self.leerArchivo()
            elif (opcion == 3):
                # Descargar Archivo
                self.descargaArchivo()
            elif (opcion == 4):
                # Enviar Archivo
                self.enviaArchivo()
            elif (opcion == 5):
                self.miSocket.send("stop".encode())
                print ("Todos los procesos terminados")
                break
            else:
                print ("Opcion incorrecta")
                
        #Cerramos conexion
        print ("Conexion terminada")
        self.miSocket.close()

    def leerArchivo(self):
        self.miSocket.send("download".encode())
        print(self.miSocket.recv(4096).decode())
        mensaje_cliente = input("¿Qué archivo deseas visualizar?")
        descarga = "download:" + mensaje_cliente
        self.miSocket.send(descarga.encode())
        serverMessage = self.miSocket.recv(4096).decode()
        print("\n\n\n")
        print(serverMessage)
        print("\n\n\n")
        
    def descargaArchivo(self):
        self.miSocket.send("download".encode())
        print(self.miSocket.recv(4096).decode())
        mensaje_cliente = input("¿Qué archivo deseas descargar?")
        descarga = "download:" + mensaje_cliente
        self.miSocket.send(descarga.encode())
        mitxt = open(mensaje_cliente, "w")
        serverMessage = self.miSocket.recv(4096).decode()
        mitxt.write(serverMessage)
        print("ARCHIVO DESCARGADO CORRECTAMENTE\n\n")
        mitxt.close()

    def enviaArchivo(self):
        nombre_archivo = input("Introduce la ruta del archivo.")
        archivo = None
        try:
            archivo = open(nombre_archivo, "r").readlines()
        except Exception as e:
            print("Ruta inválida o no se encuentra el archivo.")
        else:
            query_envio = "load:"+ nombre_archivo
            self.miSocket.send(query_envio.encode())
            print("Servidor: " + self.miSocket.recv(1024).decode())
            cadena = ""
            for linea in archivo:
                cadena += linea
            self.miSocket.send(cadena.encode())
            print(self.miSocket.recv(1024).decode())
        
        

    def mirarLista(self):
        self.miSocket.send("list:".encode())
        lista = self.miSocket.recv(1024).decode()
        print(lista + "\n\n")

    def muestraMenu(self):
        print ("MENU:\n\n1. Obtener el listado de archivos disponibles.\n2. Leer archivo." + 
                "\n3. Descargar archivo.\n4. Enviar archivo de texto. \n5. Salir")
        opcion = int(input())
        return opcion

        
        
if __name__ == '__main__':
	cliente = ClienteFTP()
	cliente.start()

        
