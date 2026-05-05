class Agenda:
    def __init__(self, nombre_completo, email, telefono):
        self.nombre_completo = nombre_completo
        self.email = email
        self.telefono = telefono

    def to_dict(self):
        return {
            'nombre_completo': self.nombre_completo,
            'email': self.email,
            'telefono': self.telefono
        }

    def __str__(self):
        return f"Nombre: {self.nombre_completo} | Email: {self.email} | Teléfono: {self.telefono}"