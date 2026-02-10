import java.util.*;

/**
 * Esta clase maneja una Biblioteca
 * Autor: Álvaro Gallego y Ana Olsson
 * Version: 1.0
 * Nombre del fichero: Biblioteca.java
 */
public class Biblioteca{
    private String nombre;
    private Map<String,List<Libro>> libros; 

    /**
   * Método para la creación de una biblioteca.
   * @param nombre nombre de la biblioteca
   */
    public Biblioteca(String nombre){
        this.nombre = nombre;
        this.libros = new HashMap<>();
    }

    /**
     * Añade un libro a la biblioteca
     * @param libro libro que se va a añadir
     */
    public void addLibro(Libro libro){
        libros.computeIfAbsent(libro.getgenero(), g -> new ArrayList<>()).add(libro);
    }

    /**
     * Devuelve una lista con todos los libros
     * del genero que se le de
     * @param genero Genero del que se quiere buscar los libros
     * @return Lista con los libros
     */
    public List LibrosPorGenero(String genero){
        return libros.get(genero);
    }

    /**
     * Devuelve una lista con todos los libros
     * posteriorea al año que se le de
     * @param añoPublicacion Año a partir del que se quiere buscar los libros posteriores
     * @return Lista con los libros
     */
    public List LibrosPosterioresA(int añoPublicacion){
        List<Libro> resultado = new ArrayList<>();

        for(List<Libro> lista : libros.values()){
            for(Libro l: lista){
                if(l.getanno() > añoPublicacion){
                    resultado.add(l);
                }
            }
        }
        return resultado;
    }


    @Override
    /**
   * El programa devuelve toda la información relevante de una biblioteca, incluyendo su
   * nombre y la información de cada libro, separados por generos.
   */
    public String toString(){
        StringBuilder result = new StringBuilder();
        result.append("Biblioteca:" + this.nombre + "\n");

        for(String genero : libros.keySet()){
            result.append("Genero: " + genero + "\n");
            result.append(libros.get(genero).toString() + "\n");    
        }


        return result.toString();   
    }

}