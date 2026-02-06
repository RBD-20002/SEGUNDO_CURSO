import org.basex.api.client.ClientSession;

public class ManagerBaseX {

    private ClientSession session;

    public ManagerBaseX() throws Exception{
        session = new ClientSession("localhost",1984,"admin","admin");

        session.execute("OPEN productos");
    }

    /*----------------------------------------------------------------------------------------------------------------*/
    public void modificarValor(int id, String campoModificable, Object dato){
        try{
            if(campoModificable == null){
                System.out.println("operacion cancelada");
                return;
            }
            String datoFinal;
            if(dato instanceof String){
                datoFinal = "'"+dato+"'";
            }else{
                datoFinal = dato.toString();
            }
            String xQuery = "replace value of node collection('productos')/productos/producto[id="+id+"]/"+campoModificable+" with "+datoFinal;
            session.execute("xquery "+xQuery);
            System.out.println("Valor "+campoModificable+" modificado por "+dato);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public String obtenerCampoModificable(int opcion){
        return switch (opcion){
            case 1 -> "nombre";
            case 2 -> "descripcion";
            case 3 -> "precio";
            case 4 -> "disponibilidad";
            case 5 -> "categoria";
            case 6 -> "fabricante";
            default -> null;
        };
    }
    /*----------------------------------------------------------------------------------------------------------------*/

    public void eliminarPorId(int id){
        try{
            String xQuery = "delete node collection('productos')/productos/producto[id="+id+"]";
            session.execute("xquery "+xQuery);

            String comprobracion = "collection('productos')/productos/producto[id='"+id+"']";
            String resultado = session.execute("xquery "+comprobracion);

            if(resultado == null && resultado.isEmpty()){
                System.out.println("Se elimino el producto con id: "+id);
            }else System.out.println("NO SE ELIMINO PRODUCTO");

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void consulta1(){
        try{
            String xQuery = "for $p in collection('productos')/productos/producto "+
                            "order by $p/nombre "+
                            "return "+
                            "<producto>\n"+
                            "   <id>{$p/id}</id>\n"+
                            "   <nombre>{$p/nombre}</nombre>\n"+
                            "   <precio>{$p/precio}</precio>\n"+
                            "   <disponibilidad>{$p/disponibilidad}</disponibilidad>\n"+
                            "   <categoria>{$p/categoria}</categoria>\n"+
                            "</producto>\n";

            String resultado = session.execute("xquery "+xQuery);

            if(resultado != null && !resultado.isEmpty()){
                System.out.println("PRODUCTOS ORDENADOS ALFABETICAMENTE:\n"+resultado);
            }else System.out.println("NO HAY CON QUE EJECUTAR CONSULTA 1");

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void consulta2(int cantidadFiltrar){
        try{
            String xQuery = "for $p in collection('productos')/productos/producto "+
                            "where $p/disponibilidad > "+cantidadFiltrar+
                            "return "+
                            "<producto>\n"+
                            "   <id>{$p/id}</id>\n"+
                            "   <nombre>{$p/nombre}</nombre>\n"+
                            "   <precio>{$p/precio}</precio>\n"+
                            "   <disponibilidad>{$p/disponibilidad}</disponibilidad>\n"+
                            "   <categoria>{$p/categoria}</categoria>\n"+
                            "</producto>\n";

            String resultado = session.execute("xquery "+xQuery);

            if(resultado != null && !resultado.isEmpty()){
                System.out.println("Productos con disponibilidad mayor a "+cantidadFiltrar+":\n"+resultado);
            }else System.out.println("NO HAY CON QUE EJECUTAR CONSULTA 2");

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void consulta3(){
        try{
            String xQuery = "for $c in distinct-values(collection('productos')/productos/producto/categoria) "+
                            "for $p in collection('productos')/productos/producto "+
                            "where $p/categoria = $c "+
                            "and $p/precio = max(collection('productos')/productos/producto[categoria = $c]/precio) "+
                            "return "+
                            "<producto>\n"+
                            "   <categoria>{$p/categoria}</categoria>\n"+
                            "   <nombre>{$p/nombre}</nombre>\n"+
                            "   <precio>{$p/precio}</precio>\n"+
                            "</producto>\n";
            String resultado = session.execute("xquery "+xQuery);

            if(resultado != null && !resultado.isEmpty()){
                System.out.println("Productos mas caros segun sus categorias:\n"+resultado);
            }else System.out.println("NO HAY CON QUE EJECUTAR CONSULTA 3");

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void consulta4(String palabra){
        try{
            String xQuery = "for $p in collection('productos')/productos/producto "+
                            "where contains($p/descripcion,'"+palabra+"') "+
                            "order by $p/fabricante descending "+
                            "return "+
                            "<producto>\n"+
                            "   <nombre>{$p/nombre}</nombre>\n"+
                            "   <fabricante>{$p/fabricante}</fabricante>\n"+
                            "</producto>";
            String resultado = session.execute("xquery "+xQuery);

            if(resultado != null && !resultado.isEmpty()){
                System.out.println("Productos que en su descripcion contenga la palabra: '"+palabra+"'\n"+resultado);
            }else System.out.println("NO HAY CON QUE EJECUTAR CONSULTA 4");

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void consulta5(){
        try{
            String xQuery = "for $c in distinct-values(collection('productos')/productos/producto/categoria) " +
                    "return " +
                    "<categoria>\n" +
                    "   <nombre>{$c}</nombre>\n" +
                    "   <cantidad>{sum(collection('productos')/productos/producto[categoria = $c]/disponibilidad)}</cantidad>\n" +
                    "   <porcentaje>{round((sum(collection('productos')/productos/producto[categoria = $c]/disponibilidad) " +
                    "div sum(collection('productos')/productos/producto/disponibilidad)) * 100)}</porcentaje>\n" +
                    "</categoria>";
            String resultado = session.execute("xquery "+xQuery);

            if(resultado != null && !resultado.isEmpty()){
                System.out.println("Porcentaje de productos por categorias: "+resultado);
            }else System.out.println("NO HAY CON QUE EJECUTAR CONSULTA 5");

        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
