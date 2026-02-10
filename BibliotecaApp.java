import java.util.*;

/**
 * Esta clase controla y crea bibliotecas y libros
 * Autor: Álvaro Gallego y Ana Olsson
 * Version: 1.0
 * Nombre del fichero: BibliotecaApp.java
 */
public class BibliotecaApp {
  /**
 * Punto de entrada de la aplicación
 * El programa controla 1 o varias Bibliotecas con sus respectivos libros
 * @param args Argumentos de la línea de comandos
 */
  public static void main(String[] args) {
    Biblioteca b = new Biblioteca("Biblioteca Central");

    
    Libro l2 = new Libro("2", "El murciélago", "Jo Nesbo", "Novela", 2015, 1);
    Libro l3 = new Libro("3", "Learn Java", "David Hoffman", "Ciencia", 2015, 6);
    Libro l1 = new Libro("4", "El Quijote", "Miguel de Cervantes", "Novela", 1957, 5);

    b.addLibro(l1);
    b.addLibro(l2);
    b.addLibro(l3);

    System.out.println(b);

    System.out.println(b.LibrosPorGenero("Ciencia"));

    System.out.println(b.LibrosPosterioresA(1975));

  }
}
