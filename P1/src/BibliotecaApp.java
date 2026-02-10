package src;

/**
 * Esta clase controla y crea bibliotecas y libros
 * Autor: Alvaro Gallego y Ana Olsson
 * Version: 1.0
 * Nombre del fichero: src.BibliotecaApp.java
 */
public class BibliotecaApp {
  /**
   * Punto de entrada de la aplicacion
   * El programa controla 1 o varias Bibliotecas con sus respectivos libros
   * @param args Argumentos de la linea de comandos
   */
  public static void main(String[] args) {
    Biblioteca b = new Biblioteca("src.Biblioteca Central");

    Libro l2 = new Libro("2", "El murcielago", "Jo Nesbo", 5);
    Libro l3 = new Libro("3", "Learn Java", "David Hoffman", 2015,"Ciencia",  6);
    Libro l1 = new Libro("4", "El Quijote", "Miguel de Cervantes", 6);

    b.addLibro(l1);
    b.addLibro(l2);
    b.addLibro(l3);

    System.out.println(b);

    System.out.println(b.LibrosPorGenero("Ciencia"));

    System.out.println(b.LibrosPosterioresA(1975));

  }
}
