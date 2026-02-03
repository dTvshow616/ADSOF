import java.util.*;

public class BibliotecaApp {
  public static void main(String[] args) {
    List<Libro> libros = new ArrayList<>(List.of(
        new Libro("1", "El Quijote", "Miguel de Cervantes", 5),
        new Libro("2", "El murciélago", "Jo Nesbo", 1),
        new Libro("3", "Learn Java", "David Hoffman", 6)));

    libros.get(1).prestar();
    for (Libro l : libros)
      System.out.println(l);

    libros.get(1).devolver();
    System.out.println(libros);

    libros.add(new Libro("4", "Con viento solano", "Ignacio Aldecoa", 1));
    System.out.println(libros);
  }
}
