package com.example;

public class Versatore extends Thread {
    private ContoCorrente conto;
    private int importoDaVersare;

    public Versatore(ContoCorrente conto, String nome, int importo) {
        super(nome);
        this.conto = conto;
        this.importoDaVersare = importo;
    }

    @Override
    public void run() {
        conto.versa(importoDaVersare);
    }
}
