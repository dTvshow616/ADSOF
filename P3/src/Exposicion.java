public enum Exposicion{
    OCULTA,
    BAJA,
    MEDIA,
    ALTA,
    VIRAL;

    public Exposicion subir(){
        if(this.ordinal() < Exposicion.values().length - 1){
            return Exposicion.values()[this.ordinal() + 1];
        }
        return this;
    }

    public Exposicion bajar(){
        if(this.ordinal() > 0){
            return Exposicion.values()[this.ordinal() - 1];
        }
        return this;
    }

}