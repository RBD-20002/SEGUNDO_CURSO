import java.util.Scanner;

public class DataEntry {

    static Scanner SC = new Scanner(System.in);

    public static String ReadString(String msg){
        while (true){
            System.out.println("ENTER "+msg.toUpperCase()+":");
            String data = SC.nextLine();
            if(!data.isEmpty()) return data;
            else System.out.println(msg.toUpperCase()+" INVALID");
        }
    }

    public static int ReadInt(String msg){
        while (true){
            try{
                System.out.println("ENTER "+msg.toUpperCase()+":");
                int data = Integer.parseInt(SC.nextLine());
                if(data > 0)return data;
                else System.out.println(msg.toUpperCase()+" INVALID");
            }catch (NumberFormatException e){
                System.out.println(e.getMessage());
            }
        }
    }
}
