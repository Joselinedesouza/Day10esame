package Catalogo;

import Catalogo.Archivio;
import Catalogo.Libro;
import Catalogo.Periodicita;
import Catalogo.Rivista;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Archivio archivio = new Archivio();
        Scanner scanner = new Scanner(System.in);
        boolean continua = true;

        while (continua) {
            System.out.println("\n--- MENU ARCHIVIO ---");
            System.out.println("1. Aggiungi libro");
            System.out.println("2. Aggiungi rivista");
            System.out.println("3. Rimuovi per ISBN");
            System.out.println("4. Cerca per ISBN");
            System.out.println("5. Cerca per anno");
            System.out.println("6. Cerca libri per autore");
            System.out.println("7. Aggiorna elemento");
            System.out.println("8. Mostra statistiche");
            System.out.println("9. Stampa archivio");
            System.out.println("0. Esci");
            System.out.print("Scelta: ");

            String scelta = scanner.nextLine();

            try {
                switch (scelta) {
                    case "1" -> aggiungiLibro(scanner, archivio);
                    case "2" -> aggiungiRivista(scanner, archivio);
                    case "3" -> rimuoviPerIsbn(scanner, archivio);
                    case "4" -> cercaPerIsbn(scanner, archivio);
                    case "5" -> cercaPerAnno(scanner, archivio);
                    case "6" -> cercaPerAutore(scanner, archivio);
                    case "7" -> aggiornaElemento(scanner, archivio);
                    case "8" -> archivio.stampaStatistiche();
                    case "9" -> archivio.stampaTutto();
                    case "0" -> {
                        continua = false;
                        System.out.println("Uscita dal programma...");
                    }
                    default -> System.out.println("Scelta non valida. Inserire un numero da 0 a 9.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Errore: formato numerico non valido.");
            } catch (IllegalArgumentException e) {
                System.out.println("Errore: valore non valido. " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

        scanner.close();
    }

    // --- Metodi di supporto ---

    private static void aggiungiLibro(Scanner scanner, Archivio archivio) throws Exception {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Anno pubblicazione: ");
        int anno = Integer.parseInt(scanner.nextLine());
        System.out.print("Numero pagine: ");
        int pagine = Integer.parseInt(scanner.nextLine());
        System.out.print("Autore: ");
        String autore = scanner.nextLine();
        System.out.print("Genere: ");
        String genere = scanner.nextLine();

        archivio.aggiungiElemento(new Libro(isbn, titolo, anno, pagine, autore, genere));
        System.out.println("Libro aggiunto con successo.");
    }

    private static void aggiungiRivista(Scanner scanner, Archivio archivio) throws Exception {
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Anno pubblicazione: ");
        int anno = Integer.parseInt(scanner.nextLine());
        System.out.print("Numero pagine: ");
        int pagine = Integer.parseInt(scanner.nextLine());
        System.out.print("Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
        Periodicita periodicita = Periodicita.valueOf(scanner.nextLine().toUpperCase());

        archivio.aggiungiElemento(new Rivista(isbn, titolo, anno, pagine, periodicita));
        System.out.println("Rivista aggiunta con successo.");
    }

    private static void rimuoviPerIsbn(Scanner scanner, Archivio archivio) {
        System.out.print("ISBN da rimuovere: ");
        String isbn = scanner.nextLine();
        archivio.rimuoviPerIsbn(isbn);
        System.out.println("Elemento rimosso se presente.");
    }

    private static void cercaPerIsbn(Scanner scanner, Archivio archivio) throws Exception {
        System.out.print("ISBN da cercare: ");
        String isbn = scanner.nextLine();
        System.out.println("Risultato: " + archivio.cercaPerIsbn(isbn));
    }

    private static void cercaPerAnno(Scanner scanner, Archivio archivio) {
        System.out.print("Anno da cercare: ");
        int anno = Integer.parseInt(scanner.nextLine());
        archivio.cercaPerAnno(anno).forEach(System.out::println);
    }

    private static void cercaPerAutore(Scanner scanner, Archivio archivio) {
        System.out.print("Autore da cercare: ");
        String autore = scanner.nextLine();
        archivio.cercaPerAutore(autore).forEach(System.out::println);
    }

    private static void aggiornaElemento(Scanner scanner, Archivio archivio) throws Exception {
        System.out.print("ISBN dell'elemento da aggiornare: ");
        String isbn = scanner.nextLine();

        System.out.print("Titolo: ");
        String titolo = scanner.nextLine();
        System.out.print("Anno pubblicazione: ");
        int anno = Integer.parseInt(scanner.nextLine());
        System.out.print("Numero pagine: ");
        int pagine = Integer.parseInt(scanner.nextLine());

        System.out.print("È un libro o una rivista? (L/R): ");
        String tipo = scanner.nextLine().toUpperCase();

        if (tipo.equals("L")) {
            System.out.print("Autore: ");
            String autore = scanner.nextLine();
            System.out.print("Genere: ");
            String genere = scanner.nextLine();
            archivio.aggiornaElemento(isbn, new Libro(isbn, titolo, anno, pagine, autore, genere));
        } else if (tipo.equals("R")) {
            System.out.print("Periodicità (SETTIMANALE, MENSILE, SEMESTRALE): ");
            Periodicita periodicita = Periodicita.valueOf(scanner.nextLine().toUpperCase());
            archivio.aggiornaElemento(isbn, new Rivista(isbn, titolo, anno, pagine, periodicita));
        } else {
            throw new IllegalArgumentException("Tipo non valido: inserisci 'L' per libro o 'R' per rivista.");
        }

        System.out.println("Elemento aggiornato con successo.");
    }
}