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
  private int anno;
  private int ejemplaresDisponibles;

  /**
   * Método para la creación de un libro
   * @param isbn String con el isbn del libro
   * @param titulo String con el titulo del libro
   * @param autor String con el autor del libro
   * @param genero String con el genero del libro
   * @param anno Año de salida del libro
   * @param ejemplaresDisponibles Cantidad de ejemplares disponibles
   */
  public Libro(String isbn, String titulo, String autor, String genero, int anno, int ejemplaresDisponibles) {
    this.isbn = isbn;
    this.titulo = titulo;
    this.autor = autor;
    this.genero = genero;
    this.anno = anno;
    this.ejemplaresDisponibles = ejemplaresDisponibles;
  }

  /**
   * El programa devuelve True si hay algún ejemplare disponible del libro y False
   * si no
   * @return True si hay ejemplares disponibles False si no
   */
  public boolean estaDisponible() {
    return this.ejemplaresDisponibles > 0;
  }

  /**
   * El programa reduce el número de ejemplares disponibles de un libro que ha
   * sido prestado
   * @return True si se ha prestado el objeto, False si no
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
   * Devuelve el genero del libro
   * @return genero del libro
   */
  public String getgenero(){
    return this.genero;
  }

  /**
   * Devuelve el año del libro
   * @return año del libro
   */
  public int getanno(){
    return this.anno;
  }

  /**
   * El programa devuelve la descripción del libro como "{Título} de {Autor}"",
   * seguido por su estado (Disponible / No Disponible))
   * @return String con la descripcion de un libro
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
        + " ejemplares disponibles). " + "Género: " + this.genero + ". " + "Año: " + this.anno;
  }

}
