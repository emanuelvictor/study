package patterns.observer;

public class AvisadorImpl implements Avisador {

    Avisavel avisavel;

    @Override
    public void enviarAviso(String aviso) {
        avisavel.receberAviso(aviso);
    }

    @Override
    public void deveAvisar(Avisavel avisavel) {
        this.avisavel = avisavel;
    }
}