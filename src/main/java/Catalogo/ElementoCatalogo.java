package Catalogo;

import java.util.Objects;

public abstract class ElementoCatalogo {
    private String isbn;
    private String titolo;
    private int annoPubblicazione;
    private int numeroPagine;

    public ElementoCatalogo(String isbn, String titolo, int annoPubblicazione, int numeroPagine) {
        this.isbn = isbn;
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.numeroPagine = numeroPagine;
    }

    // Getter
    public String getIsbn() {
        return isbn;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public int getNumeroPagine() {
        return numeroPagine;
    }

    // equals e hashCode basati su ISBN
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ElementoCatalogo)) return false;
        ElementoCatalogo that = (ElementoCatalogo) o;
        return isbn.equalsIgnoreCase(that.isbn);  // Ignora maiuscole/minuscole
    }

    @Override
    public int hashCode() {
        return Objects.hash(isbn.toLowerCase());  // Per coerenza con equals
    }

    @Override
    public String toString() {
        return "ISBN: " + isbn +
                ", Titolo: " + titolo +
                ", Anno: " + annoPubblicazione +
                ", Pagine: " + numeroPagine;
    }
}
