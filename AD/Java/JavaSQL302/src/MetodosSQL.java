import java.sql.SQLException;

public interface MetodosSQL {
    void selectInfoDataBase() throws SQLException;
    void selectComandTable() throws SQLException;
    void insertProjectNew() throws SQLException;
    void deleteProyect(int numProy) throws SQLException;
}
