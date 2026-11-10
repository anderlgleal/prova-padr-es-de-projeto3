package questao3;

interface Bebida {
    String getDescricao();
    double getPreco();
}

class Espresso implements Bebida {
    public String getDescricao() { return "Espresso"; }
    public double getPreco()     { return 6.0; }
}

class Cha implements Bebida {
    public String getDescricao() { return "Chá"; }
    public double getPreco()     { return 4.0; }
}

abstract class Complemento implements Bebida {
    protected final Bebida bebida;
    Complemento(Bebida bebida) { this.bebida = bebida; }
}

class Leite extends Complemento {
    Leite(Bebida b) { super(b); }
    public String getDescricao() { return bebida.getDescricao() + " + Leite"; }
    public double getPreco()     { return bebida.getPreco() + 2.0; }
}

class Chantilly extends Complemento {
    Chantilly(Bebida b) { super(b); }
    public String getDescricao() { return bebida.getDescricao() + " + Chantilly"; }
    public double getPreco()     { return bebida.getPreco() + 3.0; }
}

class Calda extends Complemento {
    Calda(Bebida b) { super(b); }
    public String getDescricao() { return bebida.getDescricao() + " + Calda"; }
    public double getPreco()     { return bebida.getPreco() + 2.5; }
}

public class Main {
    static void imprimir(Bebida b) {
        System.out.println(b.getDescricao() + " = R$ " + b.getPreco());
    }

    public static void main(String[] args) {
        imprimir(new Leite(new Espresso()));
        imprimir(new Calda(new Chantilly(new Cha())));
        imprimir(new Leite(new Leite(new Espresso())));
    }
}
