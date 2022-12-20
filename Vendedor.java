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
