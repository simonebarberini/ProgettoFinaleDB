package com.test.dbservice;

import com.test.model.Utente;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UtenteDAO utenteDAO = new UtenteDAO();
        Scanner scannerN = new Scanner(System.in);
        Scanner scannerS = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("Scegli un'opzione:\n");
            System.out.println("1. Inserisci un nuovo utente");
            System.out.println("2. Visualizza tutti gli utenti");
            System.out.println("3. Aggiorna un utente");
            System.out.println("4. Cancella un utente");
            System.out.println("5. Esci\n");
            System.out.print("Choice: ");

            int choice = scannerN.nextInt();
            //scannerN.nextLine(); // Consuma il newline

            switch (choice) {
                case 1:
                    System.out.print("Inserisci il nome: ");
                    String username = scannerS.nextLine();
                    System.out.print("Inserisci la password: ");
                    String password = scannerS.nextLine();
                    System.out.print("Inserisci l'email: ");
                    String email = scannerS.nextLine();
                    System.out.print("Quanto soldi hai?: ");
                    double balance = scannerN.nextDouble();
                    Utente nuovoUtente = new Utente(0, username, password, email, balance);
                    utenteDAO.createUtente(nuovoUtente);
                    System.out.println("Utente inserito con successo!");
                    waitInput(scannerS);
                    clearDisplay();
                    break;

                case 2:
                    List<Utente> utenti = utenteDAO.getAllUtenti();
                    System.out.println("Elenco utenti:\n");
                    for (Utente utente : utenti) {
                        System.out.println(utente);
                    }
                    waitInput(scannerS);
                    clearDisplay();
                    break;

                case 3:
                    System.out.print("Inserisci l'ID dell'utente da aggiornare: ");
                    int idUpdate = scannerN.nextInt();
                    scannerN.nextLine(); // Consuma il newline
                    System.out.print("Inserisci il nuovo nome: ");
                    String nuovoUsername = scannerS.nextLine();
                    System.out.print("Inserisci la password: ");
                    String nuovaPassword = scannerS.nextLine();
                    System.out.print("Inserisci la nuova email: ");
                    String nuovaEmail = scannerS.nextLine();
                    System.out.print("Inserisci il nuovo bilancio: ");
                    double nuovoBilancio = scannerN.nextDouble();
                    Utente utenteDaAggiornare = new Utente(idUpdate, nuovoUsername, nuovaPassword, nuovaEmail,
                            nuovoBilancio);
                    utenteDAO.updateUtente(utenteDaAggiornare);
                    System.out.println("Utente aggiornato con successo!");
                    waitInput(scannerS);
                    clearDisplay();
                    break;

                case 4:
                    System.out.print("Inserisci l'ID dell'utente da cancellare: ");
                    int idDelete = scannerN.nextInt();
                    utenteDAO.deleteUtente(idDelete);
                    System.out.println("Utente cancellato con successo!");
                    waitInput(scannerS);
                    clearDisplay();
                    break;

                case 5:
                    running = false;
                    System.out.println("\nUscita dal programma.");
                    break;

                default:
                    System.out.println("Scelta non valida. Riprova.");
                    break;
            }
        }

        scannerS.close();
        scannerN.close();
    }
    
    static void clearDisplay() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    static void waitInput(Scanner scanner) {
        System.out.print("\n\nPress Any Key To Continue...");
        scanner.nextLine();
    }
}
