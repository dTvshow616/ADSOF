package src;

/**
 * Esta clase maneja un src.Libro
 * Autor: Alvaro Gallego y Ana Olsson
 * Version: 1.0
 * Nombre del fichero: src.Libro.java
 */
public class Libro {
  private String isbn;
  private String titulo;
  private String autor;
  private String genero;
  private int anno;
  private int ejemplaresDisponibles;

  /**
   * Metodo para la creacion de un libro
   * @param isbn String con el isbn del libro
   * @param titulo String con el titulo del libro
   * @param autor String con el autor del libro
   * @param ejemplaresDisponibles Cantidad de ejemplares disponibles
   * @param genero String con el genero del libro
   * @param anno Anno de salida del libro
   */
  public Libro(String isbn, String titulo, String autor, int ejemplaresDisponibles, String genero, int anno) {
    this.isbn = isbn;
    this.titulo = titulo;
    this.autor = autor;
    this.anno = anno;
    this.genero = genero;
    this.ejemplaresDisponibles = ejemplaresDisponibles;
  }

  /**
   * Segundo constructor para el caso de que se cree un libro sin genero y sin anno
   * @param isbn String con el isbn del libro
   * @param titulo String con el titulo del libro
   * @param autor String con el autor del libro
   * @param ejemplaresDisponibles Cantidad de ejemplares disponibles
   */
  public Libro(String isbn, String titulo, String autor, int ejemplaresDisponibles) {
    this(isbn, titulo, autor, ejemplaresDisponibles, null,0);
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
   * Devuelve el anno del libro
   * @return anno del libro
   */
  public int getanno(){
    return this.anno;
  }

  /**
   * El programa devuelve la descripcion del libro como "{Título} de {Autor}"",
   * seguido por su estado (Disponible / No Disponible))
   * @return String con la descripcion de un libro
   */
  private String descripcion() {
    String estado = this.estaDisponible() ? "Disponible" : "No Disponible";
    return "'" + this.titulo + "' de " + this.autor + " [" + estado + "]";
  }
 
  @Override
  /**
   * El programa devuelve toda la informacion relevante de un libro, incluyendo su
   * ISBN, la descripcion, su número de ejemplares disponibles y el genero
   * literario al que pertenece
   */
  public String toString() {
    if (this.genero != null && this.anno > 0) {
      return "ISBN: " + this.isbn + ". " + this.descripcion() + " (" + this.ejemplaresDisponibles
              + " ejemplares disponibles). " + "Genero: " + this.genero + ". " + "Anno: " + this.anno;
    } else {
      return "ISBN: " + this.isbn + ". " + this.descripcion() + " (" + this.ejemplaresDisponibles
              + " ejemplares disponibles). ";
    }
  }

}
