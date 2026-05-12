package com.example;

import java.util.concurrent.Semaphore;

public class ContoCorrente {
    private int saldo;
    private Semaphore semaforo = new Semaphore(1);

    public ContoCorrente(int saldoIniziale) {
        this.saldo = saldoIniziale;
    }

    public void preleva(int importo) {

        // BLOCCO SULLA RISORSA ACQUISITA ( METTE IL SEMAFORO A ROSSO)
        try {
            semaforo.acquire();

            // CODICE DI SEZIONE CRITICA ( E' USATO DAI THREAD )

            System.out.println(Thread.currentThread().getName() + " tenta di prelevare " + importo + "€");

            if (saldo >= importo) {
                System.out.println(Thread.currentThread().getName() + " prelievo riuscito!");
                saldo -= importo;
            } else {
                System.out.println(Thread.currentThread().getName() + " prelievo fallito: saldo insufficiente (" + saldo + "€ disponibili)");
            }
            System.out.println("Saldo attuale: " + saldo + "€");

            // FINE CODICE DI SEZIONE CRITICA 

        } catch (InterruptedException e) { }
        finally {
            semaforo.release();           // RILASCIA L'USO DELLA RISPRSA ( METTE IL SEMAFORO A VERDE)
        }
    }

    public void versa(int importo) {

        // BLOCCO SULLA RISORSA ACQUISITA ( METTE IL SEMAFORO A ROSSO)
        try {
            semaforo.acquire();

            // CODICE DI SEZIONE CRITICA ( E' USATO DAI THREAD )
            System.out.println(Thread.currentThread().getName() + " tenta di versare " + importo + "€");
            saldo += importo;
            System.out.println(Thread.currentThread().getName() + " versamento riuscito!");
            System.out.println("Saldo attuale: " + saldo + "€");

            // FINE CODICE DI SEZIONE CRITICA 
        } catch (InterruptedException e) { }
        finally {
            semaforo.release();           // RILASCIA L'USO DELLA RISPRSA ( METTE IL SEMAFORO A VERDE)
        }
    }
}
