package Catalogo;

public abstract class ElementoCatalogo {
    private String isbn;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagina;


    public ElementoCatalogo(String isbn, String titolo, int annoPubblicazione,
                            int numeroPagina) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagina = numeroPagina;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }


    @Override
    public String toString() {
        return "ISBN: " + isbn + ",Titolo: " + titolo + ",Anno: "
                + annoPubblicazione + ",Pagine: " + numeroPagina;
    }

    public int getNumeroPagine() {
        return numeroPagina;
    }
}
