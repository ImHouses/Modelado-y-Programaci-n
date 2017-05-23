Casas Monreal Juan
Proyecto 2

Para este proyecto se usaron los siguientes patrones de diseño:
1. Fachada se usó para la ejecución del servidor y del cliente.
2. Se usó MVC para separar los archivos descargados de los controladores y éstos
   mismos usan la vista (imprimir en terminal).
3. Iterador se usó en la clase ListaArchivos para poder hacerla iterable.

El servidor y el cliente corren por defecto en 127.0.0.1 con puerto 5001.

Ejecución:
	python fachada_servidor.py

	python fachada_cliente.py 