package Catalogo;

public class Rivista  extends ElementoCatalogo{
    private Periodicita periodicita;

    public Rivista (String isbn, String titolo, int annoPubblicazione,
                    int numeroPagina, Periodicita periodicita){
        super(isbn,titolo,annoPubblicazione,numeroPagina);
        this.periodicita=periodicita;
    }
    public Periodicita getPeriodicita(){
        return periodicita;
    }

    @Override
    public String toString() {
        return super.toString() + ", Perdiocita: " + periodicita;
    }
}
