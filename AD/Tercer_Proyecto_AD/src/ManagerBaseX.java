import org.basex.api.client.ClientSession;

public class ManagerBaseX {

    private ClientSession session;

    public ManagerBaseX() throws Exception{
        session = new ClientSession("localhost",1984,"admin","admin");

        session.execute("OPEN productos");
    }

    public void modificarValor(int id, double precio){
        try{
            String xQuery = "replace value of node collection('productos')/productos/producto[id="+id+"]/precio with "+precio;
            session.execute("xquery "+xQuery);
            System.out.println("Precio actualizado del producto con id: "+id);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void eliminarPorId(int id){
        try{
            String xQuery = "delete node collection('productos')/productos/producto[id="+id+"]";
            session.execute("xquery "+xQuery);
            System.out.println("Se elimino el producto con id: "+id);
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
                            "   <id>$p/id</id>\n"+
                            "   <nombre>$p/nombre</nombre>\n"+
                            "   <precio>$p/precio</precio>\n"+
                            "   <disponibilidad>$p/disponibilidad</disponibilidad>\n"+
                            "   <categoria>$p/categoria</categoria>\n"+
                            "</producto>\n";

            String resultado = session.execute("xquery "+xQuery);
            System.out.println("PRODUCTOS ORDENADOS ALFABETICAMENTE:\n"+resultado);

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
                            "   <id>$p/id</id>\n"+
                            "   <nombre>$p/nombre</nombre>\n"+
                            "   <precio>$p/precio</precio>\n"+
                            "   <disponibilidad>$p/disponibilidad</disponibilidad>\n"+
                            "   <categoria>$p/categoria</categoria>\n"+
                            "</producto>\n";

            String resultado = session.execute("xquery "+xQuery);
            System.out.println("Productos con disponibilidad mayor a "+cantidadFiltrar+":\n"+resultado);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void consulta3(){
        try{
            String xQuery = "for $c in distinct-values(collection('productos')/productos/producto/categoria) " +
                            "for $p in collection('productos')/productos/producto " +
                            "where $p/categoria = $c " +
                            "and $p/precio = max(collection('productos')/productos/producto[categoria = $c]/precio) " +
                            "return " +
                            "<producto>\n" +
                            "   <categoria>{$p/categoria}</categoria>\n" +
                            "   <nombre>{$p/nombre}</nombre>\n" +
                            "   <precio>{$p/precio}</precio>\n" +
                            "</producto>\n";
            String resultado = session.execute("xquery "+xQuery);
            System.out.println("Productos mas caros segun sus categorias:\n"+resultado);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void consulta4(){
        try{
            String xQuery = "";
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }

    public void consulta5(){
        try{
            String xQuery = "for $p in collection('productos')/productos/producto "+
                            "group by $p/categoria";
        }catch (Exception e){
            System.err.println(e.getMessage());
        }
    }
}
