package br.senac.model;

public enum TipoSegmento {
    Masculino("M"),
    Feminino("F"),
    Infantil("I"),
    Unisex("U");

    private String tipo;

    private TipoSegmento(String tipo) {
        this.tipo = tipo;
    }

    public String getTipoSegmento() {
        return tipo;
    }

    public static TipoSegmento getEnum(String x) {
        switch (x) {
            case "M":
                return Masculino;
            case "F":
                return Feminino;
            case "I":
                return Infantil;
            case "U":
                return Unisex;
            default:
                return null;
        }
    }
}
