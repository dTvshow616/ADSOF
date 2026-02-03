/**
 * Esta clase maneja un Libro
 * Autor: Álvaro Gallego y Ana Olsson
 * Version: 1.0
 * Nombre del fichero: Libro.java
 */
public class Libro {
  private String isbn;
  private String titulo;
  private String autor;
  private String genero;
  private int ejemplaresDisponibles;

  /**
   * Método para la creación de un libro
   */
  public Libro(String isbn, String titulo, String autor, String genero, int ejemplaresDisponibles) {
    this.isbn = isbn;
    this.titulo = titulo;
    this.autor = autor;
    this.genero = genero;
    this.ejemplaresDisponibles = ejemplaresDisponibles;
  }

  /**
   * El programa devuelve True si hay algún ejemplare disponible del libro y False
   * si no
   */
  public boolean estaDisponible() {
    return this.ejemplaresDisponibles > 0;
  }

  /**
   * El programa reduce el número de ejemplares disponibles de un libro que ha
   * sido prestado
   */
  public boolean prestar() {
    if (estaDisponible()) {
      this.ejemplaresDisponibles--;
      return true;
    }
    return false;
  }

  /**
   * El programa aumenta el número de ejemplares disponibles de un libro que ha
   * sido devuelto
   */
  public void devolver() {
    this.ejemplaresDisponibles++;
  }

  /**
   * El programa devuelve la descripción del libro como "{Título} de {Autor}"",
   * seguido por su estado (Disponible / No Disponible))
   */
  private String descripcion() {
    String estado = this.estaDisponible() ? "Disponible" : "No Disponible";
    return "'" + this.titulo + "' de " + this.autor + " [" + estado + "]";
  }

  @Override
  /**
   * El programa devuelve toda la información relevante de un libro, incluyendo su
   * ISBN, la descripción, su número de ejemplares disponibles y el género
   * literario al que pertenece
   */
  public String toString() {
    return "ISBN: " + this.isbn + ". " + this.descripcion() + " (" + this.ejemplaresDisponibles
        + " ejemplares disponibles). " + "Género: " + this.genero;
  }

}
