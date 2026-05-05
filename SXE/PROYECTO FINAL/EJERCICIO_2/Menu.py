from Gestion_Agenda import GestionAgenda
from Agenda_Mejorada import Agenda_Mejorada
from Entrada_Datos import Entrada_Datos


def main():
    gestion = GestionAgenda()

    while True:
        print("Opciones:\n1. Agregar Contacto\n2. Mostrar Contactos\n3. Eliminar Contacto\n4. Filtrar por Signo\n5. Salir")

        opcion = input("Opción: ")

        match opcion:
            case "1":
                nombre = Entrada_Datos.pedir_texto("Nombre: ")
                email = Entrada_Datos.pedir_email("Email: ")
                telefono = Entrada_Datos.pedir_telefono("Teléfono: ")
                fecha = Entrada_Datos.pedir_fecha("Fecha nacimiento (YYYY-MM-DD): ")

                edad = Agenda_Mejorada.calcular_edad(str(fecha))
                signo = Agenda_Mejorada.calcular_signo(fecha)

                contacto = Agenda_Mejorada(nombre, email, telefono, fecha, edad, signo)
                gestion.agregar(contacto)

            case "2":
                gestion.mostrar()

            case "3":
                nombre = Entrada_Datos.pedir_texto("Nombre a eliminar: ")
                gestion.eliminar(nombre)

            case "4":
                signo = Entrada_Datos.pedir_texto("Signo: ")
                gestion.filtrar_signo(signo)

            case "5":
                print("ADIOS")
                break

            case _:
                print("Opción inválida")

if __name__ == "__main__":
    main()