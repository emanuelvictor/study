package patterns.observer;

public class AvisavelImpl implements Avisavel {

    String aviso;

    @Override
    public void receberAviso(String aviso) {
        this.aviso = aviso;
        this.lerAviso();
    }

    public void lerAviso() {
        System.out.println(aviso);
    }
}