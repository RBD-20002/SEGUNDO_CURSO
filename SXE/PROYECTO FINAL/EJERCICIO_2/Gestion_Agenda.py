import json
import os
from Agenda_Mejorada import Agenda_Mejorada

FILE = "agenda2.json"

class GestionAgenda:
    def __init__(self):
        self.lista = []
        self.cargar()

    def agregar(self, contacto):
        self.lista.append(contacto)
        self.guardar()

    def mostrar(self):
        for contacto in self.lista:
            print(contacto)

    def eliminar(self, nombre):
        self.lista = [c for c in self.lista if c.nombre_completo.lower() != nombre.lower()]
        self.guardar()

    def filtrar_signo(self, signo):
        for c in self.lista:
            if c.signo.lower() == signo.lower():
                print(c)

    def guardar(self):
        with open(FILE, "w") as f:
            json.dump([c.to_dict() for c in self.lista], f, indent=4)

    def cargar(self):
        if not os.path.exists(FILE):
            return

        with open(FILE, "r") as f:
            data = json.load(f)
            self.lista = [
                Agenda_Mejorada(
                    d["nombre_completo"],
                    d["email"],
                    d["telefono"],
                    d["fecha_nacimiento"],
                    d["edad"],
                    d["signo"]
                ) for d in data
            ]