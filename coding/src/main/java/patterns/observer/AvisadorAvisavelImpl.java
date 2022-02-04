package patterns.observer;

public class AvisadorAvisavelImpl extends AvisavelImpl implements Avisador {

    Avisavel avisavel;

    @Override
    public void enviarAviso(String aviso) {
        avisavel.receberAviso(aviso);
    }

    @Override
    public void deveAvisar(Avisavel avisavel) {
        this.avisavel = avisavel;
    }

    @Override
    public void lerAviso() {
        super.lerAviso();
        this.enviarAviso(aviso);
    }
}
