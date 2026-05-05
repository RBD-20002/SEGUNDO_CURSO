import json
import os
from Agenda import Agenda

FILE = "agenda1.json"

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
        if nombre in [contacto.nombre_completo for contacto in self.lista]:
            self.lista = [contacto for contacto in self.lista if contacto.nombre_completo != nombre]
        self.guardar()

    def guardar(self):
        with open(FILE, "w") as f:
            json.dump([contacto.to_dict() for contacto in self.lista], f, indent=4)

    def cargar(self):
        if not os.path.exists(FILE):
            return
        with open(FILE, "r") as f:
            data = json.load(f)
            self.lista = [
                Agenda(
                    d["nombre_completo"],
                    d["email"],
                    d["telefono"]
                ) for d in data
            ]   