package SQL;
import java.util.List;

public interface MetodosSQL {
    void selectInfoDataBase();
    List<String> selectProject();
    void insertProjectNew(int num, String nombreProyecto, String lugarProyecto, int departamento);
    void deleteProyect(int numProy);
}
