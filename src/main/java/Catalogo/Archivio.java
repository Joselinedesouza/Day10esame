package Catalogo;

import java.util.*;
import java.util.stream.Collectors;

public class Archivio {
    private final Set<ElementoCatalogo> catalogo = new HashSet<>();

    // Aggiunge un elemento al catalogo, evitando duplicati (basato su ISBN)
    public void aggiungiElemento(ElementoCatalogo elemento) throws Exception {
        if (!catalogo.add(elemento)) {
            throw new Exception("Elemento con ISBN già presente: " + elemento.getIsbn());
        }
    }

    // Ricerca un elemento per ISBN
    public ElementoCatalogo cercaPerIsbn(String isbn) throws Exception {
        return catalogo.stream()
                .filter(e -> e.getIsbn().equalsIgnoreCase(isbn))
                .findFirst()
                .orElseThrow(() -> new Exception("Elemento non trovato con ISBN: " + isbn));
    }

    // Rimuove un elemento per ISBN
    public void rimuoviPerIsbn(String isbn) {
        boolean removed = catalogo.removeIf(e -> e.getIsbn().equalsIgnoreCase(isbn));
        if (!removed) {
            System.out.println("Nessun elemento trovato con ISBN: " + isbn);
        }
    }

    // Ricerca elementi per anno di pubblicazione
    public List<ElementoCatalogo> cercaPerAnno(int anno) {
        return catalogo.stream()
                .filter(e -> e.getAnnoPubblicazione() == anno)
                .collect(Collectors.toList());
    }

    // Ricerca libri per autore
    public List<Libro> cercaPerAutore(String autore) {
        return catalogo.stream()
                .filter(e -> e instanceof Libro)
                .map(e -> (Libro) e)
                .filter(l -> l.getAutore().equalsIgnoreCase(autore))
                .collect(Collectors.toList());
    }

    // Aggiorna un elemento esistente cercandolo per ISBN
    public void aggiornaElemento(String isbn, ElementoCatalogo nuovoElemento) throws Exception {
        rimuoviPerIsbn(isbn);
        aggiungiElemento(nuovoElemento);
    }

    // statistiche sul catalogo
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
        System.out.println("Elemento con più pagine: " + maxPagine.orElse(null));
        System.out.printf("Media pagine: %.2f%n", mediaPagine);
    }

    // Mi ritorna tutto il contenuto dell'archivio
    public void stampaTutto() {
        if (catalogo.isEmpty()) {
            System.out.println("Il catalogo è vuoto.");
        } else {
            catalogo.forEach(System.out::println);
        }
    }
}
