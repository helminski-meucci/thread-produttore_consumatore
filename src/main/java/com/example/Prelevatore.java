package com.example;

public class Prelevatore extends Thread {
    private ContoCorrente conto;
    private int importoDaPrelevare;

    public Prelevatore(ContoCorrente conto, String nome, int importo) {
        super(nome);
        this.conto = conto;
        this.importoDaPrelevare = importo;
    }

    @Override
    public void run() {
        conto.preleva(importoDaPrelevare);
    }
}
