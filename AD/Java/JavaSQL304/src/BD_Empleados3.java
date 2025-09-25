import SQL.BaseDate3;

public class BD_Empleados3 {

    private BaseDate3 baseDate3;

    BD_Empleados3(){
        baseDate3 = new BaseDate3();
    }

    public void agregarEmpleados(){
        baseDate3.AddEmployeesForTransaction();
    }
}
