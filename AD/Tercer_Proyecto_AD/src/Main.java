public class Main {
    public static void main(String[] args) throws Exception{
        ManagerBaseX xml = new ManagerBaseX();
        ManagerMongoDB mongoDB = new ManagerMongoDB();
        int opcion;

        Integer clienteSeleccionadoId = null;

        do {
            Menu.opciones();
            opcion = ED.leerInt("opcion");
            switch (opcion){
                case 1:{
                    System.out.println(xml.listarProductos());
                    int id = ED.leerInt("id del producto a modificar");

                    Menu.datosModificableXML();
                    int idCampo = ED.leerInt("dato a modificar");
                    String campo = xml.obtenerCampoModificable(idCampo);
                    if(campo == null){
                        System.out.println("OPERACION CANCELADA");
                        break;
                    }
                    String dato = ED.leerString("nuevo valor para el campo");

                    xml.modificarValor(id, campo, dato);
                    break;
                }
                case 2:{
                    System.out.println(xml.listarProductos());
                    int id = ED.leerInt("id del producto a eliminar");

                    xml.eliminarPorId(id);
                    break;
                }
                case 3:{
                    xml.consulta1();
                    break;
                }
                case 4:{
                    int cantidad = ED.leerInt("cantidad minima disponible");

                    xml.consulta2(cantidad);
                    break;
                }
                case 5:{
                    xml.consulta3();
                    break;
                }
                case 6:{
                    String palabra = ED.leerString("palabra a buscar en descripcion");

                    xml.consulta4(palabra);
                    break;
                }
                case 7:{
                    xml.consulta5();
                    break;
                }
                case 8:{
                    mongoDB.listarCliente();
                    int id = ED.leerInt("id del nuevo cliente");
                    String nombre = ED.leerString("nombre del nuevo cliente");
                    String email = ED.leerString("email del nuevo cliente");
                    String direccion = ED.leerString("direccion del nuevo cliente");

                    mongoDB.agregarCliente(id,nombre,email,direccion);
                    break;
                }
                case 9:{
                    String email = ED.leerString("email del cliente");
                    clienteSeleccionadoId = mongoDB.filtrarEmail(email);
                    break;
                }
                case 10:{
                    if(clienteSeleccionadoId == null){
                        System.out.println("Debe seleccionar un cliente primero (opcion 9)");
                        break;
                    }

                    mongoDB.elimminarCliente(clienteSeleccionadoId);
                    clienteSeleccionadoId = null;
                    break;
                }
                case 11:{
                    if(clienteSeleccionadoId == null){
                        System.out.println("Debe seleccionar un cliente primero (opcion 9)");
                        break;
                    }

                    Menu.datosModificableMongoDB();
                    int campo = ED.leerInt("campo a modificar");
                    String campoModificable = mongoDB.obtenerCampoModificable(campo);

                    if(campoModificable != null){
                        String nuevoValor = ED.leerString("nuevo valor");
                        mongoDB.modificarCampo(clienteSeleccionadoId,campoModificable,nuevoValor);
                    }else{
                        System.out.println("OPERACION CANCELADA");
                    }
                    break;
                }
                case 12:{
                    if(clienteSeleccionadoId == null){
                        System.out.println("Debe seleccionar un cliente primero (opcion 9)");
                        break;
                    }

                    int productoId = ED.leerInt("id producto");
                    int cantidad = ED.leerInt("cantidad");

                    mongoDB.agregarProductoCarrito(clienteSeleccionadoId,productoId,cantidad,xml);
                    break;
                }
                case 13:{
                    if(clienteSeleccionadoId == null){
                        System.out.println("Debe seleccionar un cliente primero (opcion 9)");
                        break;
                    }

                    mongoDB.mostrarCarritoCliente(clienteSeleccionadoId);
                    break;
                }
                case 14:{
                    if(clienteSeleccionadoId == null){
                        System.out.println("Debe seleccionar un cliente primero (opcion 9)");
                        break;
                    }

                    mongoDB.mostrarPedidoCliente(clienteSeleccionadoId);
                    break;
                }
                case 15:{
                    if(clienteSeleccionadoId == null){
                        System.out.println("Debe seleccionar un cliente primero (opcion 9)");
                        break;
                    }

                    mongoDB.pagarCarrito(clienteSeleccionadoId);
                    break;
                }
                case 16:{
                    mongoDB.consulta1();
                    break;
                }
                case 17:{
                    mongoDB.consulta2();
                    break;
                }
                case 18:{
                    System.out.println("ADIOS..............");
                    break;
                }
                default:{
                    System.out.println("OPCION INVALIDA");
                    break;
                }
            }
        }while (opcion != 18);
    }
}
