
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


