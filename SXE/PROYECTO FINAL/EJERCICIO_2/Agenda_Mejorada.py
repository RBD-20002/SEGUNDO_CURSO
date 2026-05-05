from datetime import datetime
        
class Agenda_Mejorada:
    def __init__(self, nombre, email, telefono, fecha_nacimiento, edad, signo):
        self.nombre_completo = nombre
        self.email = email
        self.telefono = telefono
        self.fecha_nacimiento = fecha_nacimiento
        self.edad = edad
        self.signo = signo

    def calcular_edad(fecha_nacimiento):
        hoy = datetime.today()
        nacimiento = datetime.strptime(fecha_nacimiento, "%Y-%m-%d")
        edad = hoy.year - nacimiento.year

        if (hoy.month, hoy.day) < (nacimiento.month, nacimiento.day):
            edad -= 1
        return edad

    def calcular_signo(fecha_nacimiento):
        dia = int(fecha_nacimiento[8:10])
        mes = int(fecha_nacimiento[5:7])

        match mes:
            case 1:
                return "Capricornio" if dia <= 19 else "Acuario"
            case 2:
                return "Acuario" if dia <= 18 else "Piscis"
            case 3:
                return "Piscis" if dia <= 20 else "Aries"
            case 4:
                return "Aries" if dia <= 19 else "Tauro"
            case 5:
                return "Tauro" if dia <= 20 else "Géminis"
            case 6:
                return "Géminis" if dia <= 20 else "Cáncer"
            case 7:
                return "Cáncer" if dia <= 22 else "Leo"
            case 8:
                return "Leo" if dia <= 22 else "Virgo"
            case 9:
                return "Virgo" if dia <= 22 else "Libra"
            case 10:
                return "Libra" if dia <= 22 else "Escorpio"
            case 11:
                return "Escorpio" if dia <= 21 else "Sagitario"
            case 12:
                return "Sagitario" if dia <= 21 else "Capricornio"
            case _:
                return "Fecha inválida"

    def to_dict(self):
        return {
            'nombre_completo': self.nombre_completo,
            'email': self.email,
            'telefono': self.telefono,
            'fecha_nacimiento': self.fecha_nacimiento,
            'edad': self.edad,
            'signo': self.signo
        }
    
    def __str__(self):
        return f"Nombre: {self.nombre_completo} | Email: {self.email} | Teléfono: {self.telefono} | Fecha de Nacimiento: {self.fecha_nacimiento} | Edad: {self.edad} | Signo: {self.signo}"