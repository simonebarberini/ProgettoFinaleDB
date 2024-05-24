package com.test;

import java.util.ArrayList;
import java.util.Scanner;

import com.test.dbService.UserDao;
import com.test.model.User;

public class App 
{
    public static void main( String[] args ){
        // User user = new User(10, "prova", "prova", "prova@prova.prova", 100);
        UserDao dao = new UserDao();
        User userNew = dao.getUserById(2);
        userNew.versamento();
    }


    public static boolean login(String email, String password, ArrayList<User> userList) {
        Scanner inLog = new Scanner(System.in);
        int tentativiEmail = 0; // Contatore dei tentativi per l'email
        int tentativiPassword = 0; // Contatore dei tentativi per la password

        while (tentativiEmail < 3 || tentativiPassword < 3) { // Ciclo per i tentativi
            boolean emailTrovata = false; // Flag per indicare se l'email è stata trovata

            for (User user : userList) { // Ciclo per ogni User
                if (email.equals(user.getEmail())) { // Se l'email corrisponde
                    emailTrovata = true;

                    if (password.equals(user.getPassword())) { // Se la password corrisponde
                        System.out.println("Benvenuto " + user.getUsername()); // Messaggio di benvenuto
                        return true; // Login confermato
                    } else { // Se la password non corrisponde
                        System.out.println("La password inserita non è corretta. Riprovare? SI/NO"); // Messaggio di errore
                        String riprovaPassword = inLog.nextLine();

                        if (riprovaPassword.equalsIgnoreCase("si")) { // Se l'User vuole riprovare
                            tentativiPassword++; // Incrementa il contatore dei tentativi per la password
                            System.out.println("Reinserisci email e password:"); // Richiesta di email e password
                            email = inLog.nextLine();
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
            if (!emailTrovata) {
                System.out.println("L'email inserita non è corretta. Riprovare? SI/NO");
                String riprovaEmail = inLog.nextLine();
                if (riprovaEmail.equalsIgnoreCase("si")) {
                    tentativiEmail++;
                    System.out.println("Reinserisci email e password:");
                    email = inLog.nextLine();
                    password = inLog.nextLine();
                } else if (riprovaEmail.equalsIgnoreCase("no")) {
                    System.out.println("Vuoi procedere con il recupero dell'email? SI/NO");
                    riprovaEmail = inLog.nextLine();
                    if (riprovaEmail.equalsIgnoreCase("si")) {
                        // Chiamata alla funzione per il recupero dell'email
                        System.out.println("Recupero email in corso");
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




    public static boolean registrazione(ArrayList<User> userList) {
        Scanner inReg = new Scanner(System.in);
        Scanner inRegDoub = new Scanner(System.in);
        System.out.println("Inserisci un'email");
        String email = inReg.nextLine();
        boolean emailEsistente = false;
    
        // Controlla se l'email inserita esiste già tra gli utenti registrati
        for (User user : userList) {
            if (user.getEmail().equals(email)) {
                emailEsistente = true;
                break;
            }
        }
    
        // Se l'email esiste già, chiede all'User se vuole procedere con il login
        if (emailEsistente) {
            System.out.println("Email già esistente, vuoi procedere con il login? SI/NO");
            String risposta = inReg.nextLine();
            if (risposta.equalsIgnoreCase("si")) {
                // Se l'User vuole procedere con il login, richiede email e password
                System.out.println("Inserisci email e password");
                String emailLog = inReg.nextLine();
                String passLog = inReg.nextLine();
                return login(emailLog, passLog, userList);
            } else {
                // Se l'User non vuole procedere con il login, termina la registrazione
                System.out.println("Deciditi, ciao!");
                return false;
            }
        } else {
            // Se l'email non esiste già, richiede password, username, numero e età per la registrazione
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
            User nuovoUser = new User(0, username, password, email, balance);
            userList.add(nuovoUser);
            System.out.println("User registrato con successo");
            return true;
        }
    }


}
