public class Modelo {

    private int resultadoSuma;

    public void sumar(int elemento1, int elemento2){
        this.resultadoSuma = elemento1 + elemento2;
    }

    public int getSuma(){
        return this.resultadoSuma;
    }
}