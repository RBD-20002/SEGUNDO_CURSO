public class Menu {

    public static void opciones(){
        System.out.println("""
                ╔════════════════════════════════════════════╗
                ║                opciones xml                ║
                ╠════════════════════════════════════════════╣
                ║1. modificar valor segun id                 ║
                ║2. eliminar producto por id                 ║
                ║3. consulta 1                               ║
                ║4. consulta 2                               ║
                ║5. consulta 3                               ║
                ║6. consulta 4                               ║
                ║7. consulta 5                               ║
                ╠════════════════════════════════════════════╣
                ║              opciones mongodb              ║
                ╠════════════════════════════════════════════╣
                ║8. crear nuevo cliente                      ║
                ║9. identificar segun email                  ║
                ║10. borrar cliente                          ║
                ║11. modificar valor de un campo de cliente  ║
                ║12. agregar producto al carrito de cliente  ║
                ║13. mostrar carrito de un cliente           ║
                ║14. mostrar pedidos de un cliente           ║
                ║15. pagar carrito de cliente                ║
                ║16. consulta 1                              ║
                ║17. consulta 2                              ║
                ║18. salir                                   ║
                ╚════════════════════════════════════════════╝
                """.toUpperCase()
        );
    }

    public static void datosModificableXML(){
        System.out.println("""
                ╔═════════════════════════════════╗
                ║   selecciona dato a modificar   ║
                ╠═════════════════════════════════╣
                ║1. nombre                        ║
                ║2. descripcion                   ║
                ║3. precio                        ║
                ║4. disponibilidad                ║
                ║5. categoria                     ║
                ║6. fabricante                    ║
                ║0. cancelar                      ║
                ╚═════════════════════════════════╝
                """.toUpperCase()
        );
    }

    public static void datosModificableMongoDB(){
        System.out.println("""
                ╔═════════════════════════════════╗
                ║   selecciona dato a modificar   ║
                ╠═════════════════════════════════╣
                ║║
                ╚═════════════════════════════════╝
                """.toUpperCase()
        );
    }
}
