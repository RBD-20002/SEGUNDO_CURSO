# PROYECTO FINAL ACCESO A DATOS: Sistema de Reservas y Comentarios (**Microservicios**)

## Presentacion:

- Este proyecto se plantea la implementacion de una arquitectura de miscroservicios distribuida para la gestion de usuarios, hoteles, reservas y comentarios.
- Implementacion en sistemas de **Spring Cloud, MySQL, MongoDB y GraphQL**

## Arquitectura:

- Servidor Eureka (**8500**) : Gestion de servicios
- API Gateway (**8080**) : Punto de entrada de los otros modulos
- Modulo Usuarios (**8502**) : REST + MySQL (**usuariosproyecto**)
- Modulo Reservas (**8501**) : REST + MySQL (**reservasproyecto**) + OpenFeign
- Modulo Comentarios (**8503**) : GraphQL + MongoDB (**comentariosproyecto**) + OpenFeign

## Funcionalidades por Modulo:

### 1. **Modulo Eureka**

- Actúa como el directorio central donde todos los microservicios se resgistran para permitir la comunicacion.

### 2. **Modulo Gateway**

- Actúa como la central para las rutas a traves del puerto **8080**, permitiendo el acceso de los modulos.

### 3. **Modulo Usuarios**

- `crearUsuario` -> Registro de nuevos usuarios en MySQL.
- `actualizarUsuario` -> Modificación de datos de usuario existentes.
- `eliminarUsuario` -> Borrado de usuarios validando credenciales.
- `validarUsuario` -> Comprobación de usuario y contraseña (usado por otros módulos).
- `obtenerInfoUsuarioPorId` -> Retorna el nombre a partir de un ID.
- `obtenerInfoUsuarioPorNombre` -> Retorna el ID a partir de un nombre.
- `checkIfExist` -> Valida la existencia de un ID de usuario.

### 4. **Modulo Reservas**

1. **Habitaciones:**
   - `crearHabitación` -> Alta de habitaciones vinculadas a un hotel.
   - `actualizarHabitacion` -> Modificación de datos y disponibilidad.
   - `eliminarHabitacion` -> Borrado físico de la habitación por ID.
2. **Hoteles:**
   - `crearHotel` -> Alta de nuevos hoteles en el sistema.
   - `actualizarHotel` -> Modificación de nombre y dirección.
   - `eliminarHotel` -> Borrado de hotel y sus habitaciones asociadas.
   - `obtenerIdApartirNombre` -> Traducción de nombre de hotel a ID.
   - `obtenerNombreAPartirId` -> Traducción de ID a nombre de hotel.
3. **Reservas:**
   - `crearReserva` -> Generación de reserva y bloqueo de habitación.
   - `cambiarEstado` -> Gestión de estados (CONFIRMADA, CANCELADA, PAGADA).
   - `listarReservasUsuario` -> Muestra las reservas del usuario activo.
   - `listarReservasSegunEstado` -> Filtro global de reservas por estado.
   - `checkReserva` -> Validación de seguridad para el módulo de comentarios.

### 5. Modulo Comentarios:

- `crearComentario` -> Alta en MongoDB tras validar usuario, hotel y reserva vía Feign.
- `eliminarComentarios` -> Limpieza total de la colección de comentarios.
- `eliminarComentarioDeUsuario` -> Borrado de un comentario específico por su ID.
- `listarComentariosHotel` -> Muestra opiniones de todos los usuarios sobre un hotel.
- `listarComentariosUsuario` -> Muestra el historial de comentarios del usuario.
- `mostrarComentarioUsuarioReserva` -> Filtro de comentario por ID de reserva.
- `puntuacionMediaHotel` -> Cálculo de media de puntuaciones para un hotel.
- `puntuacionesMediasUsuario` -> Cálculo de media de valoraciones de un usuario.

## Nota a optar con el proyecto:

|   Modulo    | Funcionalidades                                                        |  Puntos  |
|:-----------:|:-----------------------------------------------------------------------|:--------:|
|  Usuarios   | Gestion de los Usuarios                                                |   0.85   |
|  Reservas   | Gestion de los hoteles, habitaciones, reservas con relacion a Usuarios |   1.25   |
| Comentarios | Gestion de los comentarios con relacion a Reservas y Usuarios          |   1.50   |
|   Gateway   | Enrutamiento unificado y acceso a GraphQL                              |   0.20   |
|   Eureka    | Registro y descubrimiento de servicios en puerto 8500                  |   0.20   |
|  **TOTAL**  | **Puntuacion Final del Proyecto**                                      | **4.00** |

## Instrucciones para el despliegue:

1. Iniciar **Eureka Server**
2. Iniciar **Usuarios y Reservas (requiere MySQL)**
3. Iniciar **Comentarios (requiere MongoDB)**
4. Iniciar **Gateway** 