package questao1;

import java.util.ArrayList;
import java.util.List;

interface Observador {
    void atualizar(String nomeProduto, int novaQuantidade);
}

class Produto {
    private final String nome;
    private int quantidade;
    private final List<Observador> observadores = new ArrayList<>();

    Produto(String nome, int quantidade) {
        this.nome = nome;
        this.quantidade = quantidade;
    }

    void adicionar(Observador o) { observadores.add(o); }
    void remover(Observador o)   { observadores.remove(o); }

    void setQuantidade(int nova) {
        if (nova == quantidade) return;
        quantidade = nova;
        for (Observador o : new ArrayList<>(observadores)) {
            o.atualizar(nome, nova);
        }
    }
}

class Compras implements Observador {
    public void atualizar(String nome, int qtd) {
        if (qtd < 5) System.out.println("[Compras] ALERTA: " + nome + " com estoque baixo (" + qtd + ")");
    }
}

class Vendas implements Observador {
    private boolean zerou = false;
    public void atualizar(String nome, int qtd) {
        if (qtd == 0) {
            zerou = true;
        } else if (zerou) {
            System.out.println("[Vendas] ALERTA: " + nome + " voltou ao estoque (" + qtd + ")");
            zerou = false;
        }
    }
}

class Auditoria implements Observador {
    public void atualizar(String nome, int qtd) {
        System.out.println("[Auditoria] " + nome + " -> " + qtd);
    }
}

public class Main {
    public static void main(String[] args) {
        Produto p = new Produto("Teclado Mecânico Redragon Kumara K552", 10);
        Compras compras = new Compras();
        Vendas vendas = new Vendas();
        Auditoria auditoria = new Auditoria();
        p.adicionar(compras);
        p.adicionar(vendas);
        p.adicionar(auditoria);

        p.setQuantidade(8);
        p.setQuantidade(8);
        p.setQuantidade(3);
        p.setQuantidade(0);
        p.setQuantidade(20);

        System.out.println("--- Removendo Compras ---");
        p.remover(compras);
        p.setQuantidade(2);
    }
}
