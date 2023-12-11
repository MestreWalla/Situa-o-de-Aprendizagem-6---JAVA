package Model;

public class ListaEstoque {
    // Atributos
    int codigo;
    String tag;
    String descricao;
    int quantidade;
    double preco;
    // Construtor
    public ListaEstoque(int codigo, String tag, String descricao,int quantidade, double preco) {
        this.codigo = codigo;
        this.tag = tag;
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.preco = preco;
    }
    // Getters and Setters
    public int getCodigo() {
        return codigo;
    }
    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
    public String getTag() {
        return tag;
    }
    public void setTag(String tag) {
        this.tag = tag;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public int getQuantidade() {
        return quantidade;
    }
    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
    public double getPreco() {
        return preco;
    }
    public void setPreco(double preco) {
        this.preco = preco;
    }
    
}
