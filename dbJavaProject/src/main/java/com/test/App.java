package com.test;

import java.util.List;
import java.util.Scanner;

import com.test.model.User;

public class App 
{
    public static void main( String[] args ){
        
    }


    public static boolean login(String Username, String password, List<User> userList) {
        Scanner inLog = new Scanner(System.in);
        int tentativiUsername = 0; // Contatore dei tentativi per l'Username
        int tentativiPassword = 0; // Contatore dei tentativi per la password

        while (tentativiUsername < 3 || tentativiPassword < 3) { // Ciclo per i tentativi
            boolean UsernameTrovata = false; // Flag per indicare se l'Username è stata trovata

            for (User user : userList) { // Ciclo per ogni User
                if (Username.equals(user.getUsername())) { // Se l'Username corrisponde
                    UsernameTrovata = true;

                    if (password.equals(user.getPassword())) { // Se la password corrisponde
                        System.out.println("Benvenuto " + user.getUsername()); // Messaggio di benvenuto
                        return true; // Login confermato
                    } else { // Se la password non corrisponde
                        System.out.println("La password inserita non è corretta. Riprovare? SI/NO"); // Messaggio di errore
                        String riprovaPassword = inLog.nextLine();

                        if (riprovaPassword.equalsIgnoreCase("si")) { // Se l'User vuole riprovare
                            tentativiPassword++; // Incrementa il contatore dei tentativi per la password
                            System.out.println("Reinserisci Username e password:"); // Richiesta di Username e password
                            Username = inLog.nextLine();
                            password = inLog.nextLine();

                            break; // Esce dal ciclo for per riprovare con le nuove credenziali
                        } else if (riprovaPassword.equalsIgnoreCase("no")) { // Se l'User non vuole riprovare
                            System.out.println("Vuoi procedere con il recupero della password? SI/NO");
                            riprovaPassword = inLog.nextLine();
                            if (riprovaPassword.equalsIgnoreCase("si")) {
                                // Chiamata alla funzione per il recupero della password
                                System.out.println("Recupero password in corso");
                            } else {
                                System.out.println("CIAOOOOO!");
                                return false;
                            }
                        } else {
                            System.out.println("La risposta data non è valida. CIAO!");
                            return false;
                        }
                    }
                }
            }
            if (!UsernameTrovata) {
                System.out.println("L'Username inserita non è corretta. Riprovare? SI/NO");
                String riprovaUsername = inLog.nextLine();
                if (riprovaUsername.equalsIgnoreCase("si")) {
                    tentativiUsername++;
                    System.out.println("Reinserisci Username e password:");
                    Username = inLog.nextLine();
                    password = inLog.nextLine();
                } else if (riprovaUsername.equalsIgnoreCase("no")) {
                    System.out.println("Vuoi procedere con il recupero dell'Username? SI/NO");
                    riprovaUsername = inLog.nextLine();
                    if (riprovaUsername.equalsIgnoreCase("si")) {
                        // Chiamata alla funzione per il recupero dell'Username
                        System.out.println("Recupero Username in corso");
                    } else {
                        System.out.println("CIAOOOOO!");
                        return false;
                    }
                } else {
                    System.out.println("La risposta data non è valida. CIAO!");
                    return false;
                }
            }
        }
        System.out.println("Hai finito i tentativi");
        return false;
    }




    public static boolean registrazione(List<User> userList) {
        Scanner inReg = new Scanner(System.in);
        Scanner inRegDoub = new Scanner(System.in);
        System.out.println("Inserisci un'Username");
        String Username = inReg.nextLine();
        boolean UsernameEsistente = false;
    
        // Controlla se l'Username inserita esiste già tra gli utenti registrati
        for (User user : userList) {
            if (user.getUsername().equals(Username)) {
                UsernameEsistente = true;
                break;
            }
        }
    
        // Se l'Username esiste già, chiede all'User se vuole procedere con il login
        if (UsernameEsistente) {
            System.out.println("Username già esistente, vuoi procedere con il login? SI/NO");
            String risposta = inReg.nextLine();
            if (risposta.equalsIgnoreCase("si")) {
                // Se l'User vuole procedere con il login, richiede Username e password
                System.out.println("Inserisci Username e password");
                String UsernameLog = inReg.nextLine();
                String passLog = inReg.nextLine();
                return login(UsernameLog, passLog, userList);
            } else {
                // Se l'User non vuole procedere con il login, termina la registrazione
                System.out.println("Deciditi, ciao!");
                return false;
            }
        } else {
            // Se l'Username non esiste già, richiede password, username, numero e età per la registrazione
            System.out.println("Inserisci password");
            String password = inReg.nextLine();
            while (password.isEmpty()) {
                System.out.println("Devi inserire una password");
                password = inReg.nextLine();
            }
            System.out.println("Inserisci username");
            String username = inReg.nextLine();
            System.out.println("Inserisci saldo");
            double balance = inRegDoub.nextDouble();
    
            // Crea un nuovo oggetto User e lo aggiunge alla lista degli utenti registrati
            User nuovoUser = new User(0, username, password, Username, balance);
            userList.add(nuovoUser);
            System.out.println("User registrato con successo");
            return true;
        }
    }


}
