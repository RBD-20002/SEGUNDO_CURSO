import SQL.BaseDate2;

import java.util.List;

public class BD_Emplados2 {

    private final BaseDate2 baseDate2;

    BD_Emplados2(){
        baseDate2 = new BaseDate2();
    }

    public void filtrarPorLetra(){
        String letra = EntradaDatos.leerString("una letra");
        List<String> lineas = baseDate2.SelectAllNameForChar(letra);
        if(lineas.isEmpty()) System.out.println("NO HAY NADIE POR FILTRAR CON ESA LETRA");
        else{
            for(String l : lineas){
                System.out.println(l);
            }
        }
    }

    public void eliminarPorNumero(){
        int numero = EntradaDatos.leerInt("nss");
        baseDate2.DeleteEmpForNum(numero);
    }
}
