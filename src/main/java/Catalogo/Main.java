package Catalogo;

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
                    case "1" -> {
                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.print("Titolo: ");
                        String titolo = scanner.nextLine();
                        System.out.print("Anno: ");
                        int anno = Integer.parseInt(scanner.nextLine());
                        System.out.print("Pagine: ");
                        int pagine = Integer.parseInt(scanner.nextLine());
                        System.out.print("Autore: ");
                        String autore = scanner.nextLine();
                        System.out.print("Genere: ");
                        String genere = scanner.nextLine();

                        archivio.aggiungiElemento(new Libro(isbn, titolo, anno, pagine, autore, genere));
                        System.out.println("Libro aggiunto.");
                    }

                    case "2" -> {
                        System.out.print("ISBN: ");
                        String isbn = scanner.nextLine();
                        System.out.print("Titolo: ");
                        String titolo = scanner.nextLine();
                        System.out.print("Anno: ");
                        int anno = Integer.parseInt(scanner.nextLine());
                        System.out.print("Pagine: ");
                        int pagine = Integer.parseInt(scanner.nextLine());
                        System.out.print("Periodicità (SETTIMANALE/MENSILE/SEMESTRALE): ");
                        Periodicita periodicita = Periodicita.valueOf(scanner.nextLine().toUpperCase());

                        archivio.aggiungiElemento(new Rivista(isbn, titolo, anno, pagine, periodicita));
                        System.out.println("Rivista aggiunta.");
                    }

                    case "3" -> {
                        System.out.print("ISBN da rimuovere: ");
                        archivio.rimuoviPerIsbn(scanner.nextLine());
                        System.out.println("Elemento rimosso.");
                    }

                    case "4" -> {
                        System.out.print("ISBN da cercare: ");
                        System.out.println(archivio.cercaPerIsbn(scanner.nextLine()));
                    }

                    case "5" -> {
                        System.out.print("Anno: ");
                        archivio.cercaPerAnno(Integer.parseInt(scanner.nextLine()))
                                .forEach(System.out::println);
                    }

                    case "6" -> {
                        System.out.print("Autore: ");
                        archivio.cercaPerAutore(scanner.nextLine())
                                .forEach(System.out::println);
                    }

                    case "7" -> {
                        System.out.print("ISBN dell'elemento da aggiornare: ");
                        String isbn = scanner.nextLine();
                        System.out.println("Inserisci i nuovi dati.");
                        System.out.print("Titolo: ");
                        String titolo = scanner.nextLine();
                        System.out.print("Anno: ");
                        int anno = Integer.parseInt(scanner.nextLine());
                        System.out.print("Pagine: ");
                        int pagine = Integer.parseInt(scanner.nextLine());

                        System.out.print("È un libro o una rivista? (L/R): ");
                        String tipo = scanner.nextLine().toUpperCase();
                        if (tipo.equals("L")) {
                            System.out.print("Autore: ");
                            String autore = scanner.nextLine();
                            System.out.print("Genere: ");
                            String genere = scanner.nextLine();
                            archivio.aggiornaElemento(isbn, new Libro(isbn, titolo, anno, pagine, autore, genere));
                        } else {
                            System.out.print("Periodicità (SETTIMANALE/MENSILE/SEMESTRALE): ");
                            Periodicita periodicita = Periodicita.valueOf(scanner.nextLine().toUpperCase());
                            archivio.aggiornaElemento(isbn, new Rivista(isbn, titolo, anno, pagine, periodicita));
                        }
                        System.out.println("Elemento aggiornato.");
                    }

                    case "8" -> archivio.stampaStatistiche();

                    case "9" -> archivio.stampaTutto();

                    case "0" -> {
                        continua = false;
                        System.out.println("Chiusura archivio...");
                    }

                    default -> System.out.println("Scelta non valida.");
                }

            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
