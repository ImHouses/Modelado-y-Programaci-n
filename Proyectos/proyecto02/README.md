# Proyecto 01
El proyecto consiste en un programa que simula un servidor FTP y un programa
(cliente) para la obtención de archivos desde el mismo.

### Detalles
Para el proyecto se establecerá una especie de "protocolo" en el que, para 
obtener un archivo y listar los archivos se necesitarán "peticiones" para el 
servidor de la siguiente forma.
	 
	
	```
	//Descarga de archivo.
	download:nombredelarchivo

	# Lista de archivos
	list:
	```
Además, se van a utilizar los siguientes patrones de diseño.
- 