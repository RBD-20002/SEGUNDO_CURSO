from datetime import datetime

class Entrada_Datos:

    def pedir_texto(mensaje):
        while True:
            texto = input(mensaje )
            if texto.strip() == "":
                print("El campo no puede estar vacío.")
            else:
                return texto

    def pedir_email(mensaje):
        while True:
            email = input(mensaje)
            if "@" not in email or "." not in email:
                print("Email inválido")
            else:
                return email

    def pedir_telefono(mensaje):
        while True:
            telefono = input(mensaje)
            if not telefono.isdigit() or len(telefono) != 9:
                print("Teléfono inválido (9 dígitos)")
            else:
                return telefono

    def pedir_fecha(mensaje):
        while True:
            fecha = input(mensaje)
            try:
                datetime.strptime(fecha, "%Y-%m-%d")
                return fecha
            except ValueError:
                print("Fecha inválida (YYYY-MM-DD)")