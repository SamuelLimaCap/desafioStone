package desafio;

class Despesa {
    String nome;
    //Verificar se não é negativo
    Double quantidade;
    //Verificar se não ultrapssa Integer.MAX_VALUE e se não é negativo
    Long valorUnitario; //em centavos

    public Despesa() {
    }

    public Despesa(String nome, Double quantidade, Long valorUnitarioEmCentavos) {
        this.nome = nome;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitarioEmCentavos;
    }

    public Boolean verificarQuantidade() {
        if (quantidade == null || quantidade < 0) return false;
        return true;
    }

    public Boolean verificarValorUnitario() {
        if (valorUnitario == null || valorUnitario < 0) return false;
        return true;
    }

    @Override
    public String toString() {
        return "Despesa{" +
                "nome='" + nome + '\'' +
                ", quantidade=" + quantidade +
                ", valorUnitario=" + valorUnitario +
                '}';
    }
}
