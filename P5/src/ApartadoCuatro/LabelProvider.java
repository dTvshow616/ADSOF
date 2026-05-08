package ApartadoCuatro;

/**
 * Permite asignar una etiqueta a un objeto (compatible ocn el tipo paramétrico)
 */
public interface LabelProvider<DATA, LABEL> {
    /**
     * It labels a certain object
     * @param object the desired object
     */
    LABEL provideLabel(DATA object);
}