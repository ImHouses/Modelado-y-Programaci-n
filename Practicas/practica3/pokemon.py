import random

# Clase para representar ataques.
# Los ataques tienen puntos de daño y un nombre para identificarlos.
class Ataque:

    #Constructor
    def __init__(self, nombre):
        self.nombre = nombre
        self.dano = random.randint(25,50)

# Clase para representar pokemon.
#
# Los pokemon tienen puntos de energía, cuando un pokemon tiene 0  puntos de
# energía se dice que perdió el combate.
class Pokemon:
            
    # Constructor
    def __init__(self, nombre, ataques):
        self.nombre = nombre
        self.energia = 100
        self.ataques = ataques

    # Método para atacar
    # Recibe un pokemon y un ataque y le quita energía.
    def ataca(pokemon, ataque):
        pokemon.energia = pokemon.energia - ataque.dano

    # Regresa una representación en cadena (una lista) de los ataques.
    def to_string_ataques(self):
        i = 1
        for a in self.ataques:
            print(str(i) + " - " +  a.nombre)
            i += 1

    # Imprime estado.
    def imprime_estado(self):
        estado = ("%s\n" % self.nombre +
                " --------------------\n" +
                "|  Energía: %sPS     |\n" % self.energia  +
                "|                    |\n" +
                "|                    |\n" +
                " --------------------\n")
        print(estado)
# Clase para pokemon de tipo agua.
class PAgua(Pokemon):

    # Constructor
    def __init__(self, nombre):
        super().__init__(nombre, [Ataque("Chorro de agua"), Ataque("Rayo burbuja"),
                            Ataque("Hidro-Cañón"), Ataque("Buceo")])

    # Lista ataques
    def lista_ataques(self):
        return super().to_string_ataques()

# Clase para pokemon de tipo fuego.
class PFuego(Pokemon):

    # Constructor
    def __init__(self, nombre):
        super().__init__(nombre, [Ataque("Lanzallamas"), Ataque("Llamarada"),
                            Ataque("Ascuas"), Ataque("Puño Fuego")])


    # Lista ataques
    def lista_ataques(self):
        return super().to_string_ataques()

# Clase para pokemon de tipo hierba.
class PHierba(Pokemon):

    # Constructor
    def __init__(self, nombre):
        super().__init__(nombre, [Ataque("Hoja aguda"), Ataque("Drenadoras"),
                          Ataque("Lluevehojas"), Ataque("Rayo Solar")])

    # Lista ataques
    def lista_ataques(self):
        return super().to_string_ataques()

# Clase para pokemon de tipo eléctrico.
class PElectrico(Pokemon):

    # Constructor
    def __init__(self, nombre):
        super().__init__(nombre, [Ataque("Carga"),
                                                Ataque("Chispa"),
                                                Ataque("Chispazo"),
                                                Ataque("Onda Voltio")])

    # Lista ataques
    def lista_ataques(self):
        return super().to_string_ataques()

def main():
    print(" ------------------------------ ")
    print("|                             |")
    print("|                             |")
    print("|  P o k é m on               |")
    print("|                             |")
    print("|                             |")
    print(" ------------------------------ ")
    input("Pulsa enter para continuar...")
    print("Elige el tipo de tu pokemon..")
    tipos = {"1": "Agua", "2": "Fuego",  "3": "Hierba", "4": "Eléctrico"}
    tipo = input("1. Agua\n2. Fuego\n3. Hierba\n4. Eléctrico\n")
    print("Escogiste tipo: %s" % tipos[tipo])
    tipoc = random.randint(1,4)
    print("La computadora escogió el tipo: %s" % tipos[str(tipoc)])
    tipoc = tipos[str(tipoc)]
    nombre = input("Introduce el nombre de tu pokemon.\n")
    nombrec = input("Introduce el nombre de tu pokemon adversario.\n")

    tipo = tipos[tipo]
    pkmn1 = None
    pkmn2 = None
    if tipo == "Agua":
        pkmn1 = PAgua(nombre)
    elif tipo == "Fuego":
        pkmn1 = PFuego(nombre)
    elif tipo == "Hierba":
        pkmn1 = PHierba(nombre)
    elif tipo == "Eléctrico":
        pkmn1 = PElectrico(nombre)

    if tipoc == "Agua":
        pkmn2 = PAgua(nombrec)
    elif tipoc == "Fuego":
        pkmn2 = PFuego(nombrec)
    elif tipoc == "Hierba":
        pkmn2 = PHierba(nombrec)
    elif tipoc == "Eléctrico":
        pkmn2 = PElectrico(nombrec)

    seleccion_ataque = None
    while True:
        pkmn1.imprime_estado()
        pkmn2.imprime_estado()
        pkmn1.lista_ataques()
        seleccion_ataque = pkmn1.ataques[int(input("Elige un ataque...\n")) - 1]
        Pokemon.ataca(pkmn2, seleccion_ataque)
        pkmn2.imprime_estado()

 

if __name__ == '__main__':
    main()

