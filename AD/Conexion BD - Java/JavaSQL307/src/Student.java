public class Student {

    private String id,name,surname;
    private int age;

    public Student(String id, String name, String surname, int age) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public int getAge() {
        return age;
    }

    public String toString(){
        return "|DNI: "+id+" | NOMBRE: "+name+" | APELLIDO: "+surname+" | EDAD: "+age+"|";
    }
}
