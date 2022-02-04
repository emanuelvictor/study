package patterns.observer;

public interface Avisador {

    void deveAvisar(Avisavel avisavel);

    void enviarAviso(String aviso);
}
