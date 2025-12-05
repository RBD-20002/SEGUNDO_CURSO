package org.example.Extra;

public class Menus {

    public static void menu1(){
        System.out.println("""
                |opciones:         |
                |1. menu insertar  |
                |2. menu borrar    |
                |3. menu consultas |
                |4. salir          |
                """.toUpperCase()
        );
    }

    public void menu2(){
        System.out.println("""
                |opciones:                 |
                |1. insertar autor         |
                |2. insertar libro         |
                |3. enlazar autor libro    |
                |4. insertar telfono autor |
                |5. cancelar               |
                """.toUpperCase()
        );
    }
}
