package desafio01;

import java.util.Random;

/*
    1. Incluir vendedores dos outros mercados para vender com compradores
    2. Incluir atributo de total compras dos compradores [int compras]
    3. Imprimir top 3 maiores vendedores e dizer de qual mercado foi
    4. Imprimir top 3 maiores compradores e e dizer qual mercado mais comprou
    5. Qual mercados mais lucrativo
 */
public class Desafio01 {

    private static final String[] nomesVendedores = new String[] {
            "Caio", "Marina", "Luca", "Rafa"
    };

    private static final String[] sobrenomes = new String[] {
            "Matias", "Fernandes", "Maia", "Rocha", "Gaspar",
            "Leão", "Alves", "Fagundes", "Sineza", "Bertoldi"
    };

    private static final String[] nomesCompradores = new String [] {
            "Vinicius", "Jociele", "Breno", "Vitor", "Alesandro",
            "Cicero", "Debora", "Eduardo", "Ezio", "Grabriel",
            "Vitor", "Wallys"
    };

    private static final Random random = new Random();

    private static int sequencial = 0;

    public static void main(String[] args) {

        Vendedor[] vendedoresFlores = criarVendedores();
        Vendedor[] vendedoresFrutas = criarVendedores();
        Vendedor[] vendedoresRoupas = criarVendedores();

        Mercado mercadoFlores = new Mercado(vendedoresFlores, "flores");
        Mercado mercadoFrutas = new Mercado(vendedoresFrutas, "frutas");
        Mercado mercadoRoupas = new Mercado(vendedoresRoupas, "roupas");

        Comprador[] compradores = criarCompradores();

        int qtdeVendedores = mercadoFlores.getVendedores().length;
        int indiceComprador = 0;

        for (int i = 0; i < qtdeVendedores; i++) {
            Vendedor vendedor = mercadoFlores.getVendedores()[i];
            indiceComprador = random.nextInt(compradores.length);
            Comprador comprador = compradores[indiceComprador];
            comprador.comprar( vendedor );
        }

        Regulador regulador = new Regulador();
        regulador.aplicar(
                new Mercado[] {
                        mercadoFlores
                }
        );

    }

    private static Vendedor[] criarVendedores() {
        Vendedor[] vendedores = new Vendedor[nomesVendedores.length];
        for (int i = 0; i < nomesVendedores.length; i++) {
            int indiceNome = random.nextInt(nomesVendedores.length);
            String nome = nomesVendedores[indiceNome];

            int indiceSobrenome = random.nextInt(sobrenomes.length);
            String sobrenome = sobrenomes[indiceSobrenome] + " " + proximoSequencial();
            vendedores[i] = new Vendedor(nome, sobrenome);
        }
        return vendedores;
    }

    private static Comprador[] criarCompradores() {
        Comprador[] compradores = new Comprador[nomesCompradores.length];
        for (int i = 0; i < nomesCompradores.length; i++) {
            int indiceNome = random.nextInt(nomesCompradores.length);
            String nome = nomesCompradores[indiceNome];

            int indiceSobrenome = random.nextInt(sobrenomes.length);
            String sobrenome = sobrenomes[indiceSobrenome] + " " + proximoSequencial();

            compradores[i] = new Comprador(nome, sobrenome);
        }

        return compradores;
    }

    private static int proximoSequencial() {
        return ++sequencial;
    }

}

class Regulador {
    public void aplicar(Mercado[] mercados) {
        for (int i = 0; i < mercados.length; i++) { // para cada mercado ->
            Mercado mercado = mercados[i];
            Vendedor[] vendedores = mercado.getVendedores();
            for (int j = 0; j < vendedores.length; j++) { // para cada vendedor
                Vendedor vendedor = vendedores[j];
                if (isSuperiorMeta(vendedor)) {
                    this.bonificar(vendedor);
                }
                System.out.println( vendedor.anunciar() );
            }
        }
    }

    private boolean isSuperiorMeta(Vendedor vendedor) {
        return vendedor.getVendas() > 500;
    }

    // private, protected, package, public
    // segurança -> oportunidade e necessidade
    private void bonificar(Vendedor vendedor) {
        double total = vendedor.getVendas();
        double bonificacao = (total * 0.10);
        vendedor.receberBonificacao(bonificacao);
    }

}

class Comprador extends Object {

    private String nome;
    private String sobrenome;

    public Comprador(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    private static final Random random = new Random();
    public void comprar(Vendedor vendedor) {
        vendedor.vender(random.nextInt(1000));
    }

    @Override
    public String toString() {
        return this.nome + " " + this.sobrenome;
    }
}

class Mercado {
    private Vendedor[] vendedores;

    private String tipo;

    public Mercado(Vendedor[] vendedores, String tipo) {
        this.vendedores = vendedores;
        this.tipo = tipo;
    }

    public Vendedor[] getVendedores() {
        return vendedores;
    }

}

class Vendedor {
    private String nome;
    private String sobrenome;

    private double bonificacao;

    private double vendas;
    public Vendedor(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String anunciar() {
        String textoBonificacao = "";
        if (bonificacao > 0)
            textoBonificacao = " e fui bonificado " + bonificacao;
        return nome + " " + sobrenome
                + " e vendi " + vendas
                + textoBonificacao
                + " : total " + (vendas + bonificacao);
    }

    public void vender(double valor) {
        this.vendas += valor;
    }

    public void receberBonificacao(double bonificacao) {
        this.bonificacao += bonificacao;
    }

    public double getVendas() {
        return this.vendas;
    }

    @Override
    public String toString() {
        return this.nome + " " + this.sobrenome;
    }
}
