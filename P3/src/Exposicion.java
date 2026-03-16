/**
 * Nombre de la enumeración: Exposición
 * <p>
 * Description: Define las exposiciones
 * @author Alvaro G.S. and Ana O.R.
 * @version 1.1
 */
public enum Exposicion {
    /** La exposición es oculta */
    OCULTA,
    /** La exposición es baja */
    BAJA,
    /** La exposición es media */
    MEDIA,
    /** La exposición es alta */
    ALTA,
    /** La exposición es viral */
    VIRAL;

    /**
     * Sube la exposición al siguiente grado
     * @return La nueva exposición
     */
    public Exposicion subir() {
        if (this.ordinal() < Exposicion.values().length - 1) {
            return Exposicion.values()[this.ordinal() + 1];
        }
        return this;
    }

    /**
     * Baja la exposición al anterior grado
     * @return La nueva exposición
     */
    public Exposicion bajar() {
        if (this.ordinal() > 0) {
            return Exposicion.values()[this.ordinal() - 1];
        }
        return this;
    }

}