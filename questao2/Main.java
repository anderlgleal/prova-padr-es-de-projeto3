package questao2;

interface Tarifa {
    double calcular(int minutos);
}

class Util {
    static int horasIniciadas(int minutos) {
        return (int) Math.ceil(minutos / 60.0);
    }
}

class Rotativo implements Tarifa {
    static final double VALOR_HORA = 10.0;
    public double calcular(int minutos) {
        return Util.horasIniciadas(minutos) * VALOR_HORA;
    }
}

class Mensalista implements Tarifa {
    static final double VALOR_FIXO = 200.0;
    public double calcular(int minutos) {
        return VALOR_FIXO;
    }
}

class Conveniado implements Tarifa {
    static final int MINUTOS_GRATIS = 30;
    static final double VALOR_HORA = 10.0;
    static final double DESCONTO = 0.20;
    public double calcular(int minutos) {
        if (minutos <= MINUTOS_GRATIS) return 0;
        return Util.horasIniciadas(minutos - MINUTOS_GRATIS) * VALOR_HORA * (1 - DESCONTO);
    }
}

class Ticket {
    private final String placa;
    private final int minutos;
    private Tarifa tarifa;

    Ticket(String placa, int minutos, Tarifa tarifa) {
        this.placa = placa;
        this.minutos = minutos;
        this.tarifa = tarifa;
    }

    void setTarifa(Tarifa t) { this.tarifa = t; }
    double valor()          { return tarifa.calcular(minutos); }
    String getPlaca()       { return placa; }
}

public class Main {
    public static void main(String[] args) {
        int minutos = 150;
        System.out.println("Permanência: " + minutos + " min");
        System.out.println("Rotativo:   R$ " + new Ticket("BRA2E19", minutos, new Rotativo()).valor());
        System.out.println("Mensalista: R$ " + new Ticket("BRA2E19", minutos, new Mensalista()).valor());
        System.out.println("Conveniado: R$ " + new Ticket("BRA2E19", minutos, new Conveniado()).valor());

        System.out.println("--- Troca de perfil no mesmo tíquete ---");
        Ticket t = new Ticket("QWE4R56", minutos, new Rotativo());
        System.out.println("Rotativo:   R$ " + t.valor());
        t.setTarifa(new Conveniado());
        System.out.println("Conveniado: R$ " + t.valor());
    }
}
