package Catalogo;

public class Libro extends ElementoCatalogo{
    private String Genere;
    private String Autore;

    public Libro(String isbn, String titolo, int annoPubblicazione,
                 int numeroPagina, String Genere, String Autore){
        super(isbn, titolo, annoPubblicazione, numeroPagina);
        this.Autore=Autore;
        this.Genere=Genere;
    }

    public String getAutore() {
        return Autore;
    }
    public String getGenere(){
        return Genere;
    }

    @Override
    public String toString() {
        return super.toString() + ", Autore: " + Autore + ", Genere: " + Genere;
    }
}
