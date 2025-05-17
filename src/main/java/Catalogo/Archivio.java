package Catalogo;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    private List<ElementoCatalogo> catalogo = new ArrayList<>();

    // Aggiunta elemento
    public void aggiungiElemento(ElementoCatalogo elemento) throws Exception {
        if (catalogo.stream().anyMatch(e -> e.getIsbn().equals(elemento.getIsbn()))) {
            throw new Exception("Elemento con ISBN già presente: " + elemento.getIsbn());
        }
        catalogo.add(elemento);
    }

    // Ricerca per ISBN
    public ElementoCatalogo cercaPerIsbn(String isbn) throws Exception {
        return catalogo.stream()
                .filter(e -> e.getIsbn().equals(isbn))
                .findFirst()
                .orElseThrow(() -> new Exception("Elemento non trovato con ISBN: " + isbn));
    }

    // Rimozione per ISBN
    public void rimuoviPerIsbn(String isbn) {
        catalogo.removeIf(e -> e.getIsbn().equals(isbn));
    }

    // Ricerca per anno di pubblicazione
    public List<ElementoCatalogo> cercaPerAnno(int anno) {
        return catalogo.stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    // Ricerca per autore (solo libri)
    public List<Libro> cercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(l -> l.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    // Aggiornamento elemento esistente dato l'ISBN
    public void aggiornaElemento(String isbn, ElementoCatalogo nuovoElemento) throws Exception {
        rimuoviPerIsbn(isbn);
        aggiungiElemento(nuovoElemento);
    }

    // Statistiche del catalogo
    public void stampaStatistiche() {
        long totLibri = catalogo.stream().filter(e -> e instanceof Libro).count();
        long totRiviste = catalogo.stream().filter(e -> e instanceof Rivista).count();

        Optional<ElementoCatalogo> maxPagine = catalogo.stream()
                .max(Comparator.comparingInt(ElementoCatalogo::getNumeroPagine));

        double mediaPagine = catalogo.stream()
                .mapToInt(ElementoCatalogo::getNumeroPagine)
                .average()
                .orElse(0);

        System.out.println("Totale libri: " + totLibri);
        System.out.println("Totale riviste: " + totRiviste);
        System.out.println("Elemento con più pagine: " + (maxPagine.isPresent() ? maxPagine.get() : "Nessuno"));
        System.out.println("Media pagine: " + mediaPagine);
    }

    // Metodo per stampare tutto il catalogo
    public void stampaTutto() {
        catalogo.forEach(System.out::println);
    }
}