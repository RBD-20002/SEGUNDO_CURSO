from Gestion_Agenda import GestionAgenda
from Agenda import Agenda

def main():
    gestion = GestionAgenda()

    while True:
        print(f"Opciones:\n1. Agregar contacto\n2. Mostrar contactos\n3. Eliminar contacto\n4. Salir")
        opcion = input("Seleccione una opción: ")

        match opcion:
            case "1":
                nombre = input("Nombre completo: ")
                email = input("Email: ")
                telefono = input("Teléfono: ")
                contacto = Agenda(nombre, email, telefono)
                gestion.agregar(contacto)
            case "2":
                print("Contactos:")
                gestion.mostrar()
            case "3":
                gestion.mostrar()
                nombre = input("Nombre del contacto a eliminar: ")
                gestion.eliminar(nombre)
            case "4":
                print("Adiós!")
                break
            case "_":
                print("Opción no válida. Intente nuevamente.")

if __name__ == "__main__":
    main()