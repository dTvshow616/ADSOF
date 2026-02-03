public class Libro {
  private String isbn;
  private String titulo;
  private String autor;
  private String ejemplaresDisponibles;

  public Libro(String isbn, String titulo, String autor, int ejemplaresDisponibles){
    this.isbn = isbn;
    this.titulo = titulo;
    this.autor = autor;
    this.ejemplaresDisponibles = ejemplaresDisponibles;
  }

  public boolean prestar(){
    if(estaDisponible()){
      this.ejemplaresDisponibles--;
      return true;
    }
    return false;
  }

  public void devolver(){
    this.ejemplaresDisponibles++;
  }


  private String descripcion(){
    String estado = this.estaDisponible() ? "Disponible" : "No Disponible";
    return "'"+this.titulo + "' de" + this.autor + " [" + estado + "]"; 
  }

  @Override
  public String toString(){
    return "ISBN: " + this.isbn + ". " + this.descripcion() + " ("+this.ejemplaresDisponibles+ " ejemplares disponibles)";
  }
  
}
