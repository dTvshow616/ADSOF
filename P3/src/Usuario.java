import java.util.ArrayList;
import java.util.List;

public class Usuario{
    private String nombre;
    private int capacidadAmplificacion;
    private List<Enlace> enlaces;

    public Usuario(String nombre){
        this.nombre = nombre;
        this.capacidadAmplificacion = 2;
        this.enlaces = new ArrayList<>();
    }

    public Usuario(String nombre, int capacidadAmplificacion){
        this.nombre = nombre;
        this.capacidadAmplificacion = capacidadAmplificacion;
        this.enlaces = new ArrayList<>();
    }

    public boolean addEnlace(Enlace e){
        if((e.getUsuarioOrigen() != this) || (this != e.getUsuarioDestino())){
            return false;
        }

        for(Enlace i: this.enlaces){
            if(i == e){
                return false;
            }
        }

        this.enlaces.add(e);
        return true;
    }

    public boolean addEnlace(Usuario usuarioDestino, int coste){
        Enlace e = new Enlace(this,usuarioDestino,coste);

        return addEnlace(e);
    }

    public String getNombre(){
        return this.nombre;
    }

    public int getCapacidadAmplificacion(){
        return this.capacidadAmplificacion;
    }

    public int getNumEnlaces(){
        return this.enlaces.size();
    }

    public Enlace getEnlace(int i){
        if(i >= this.getNumEnlaces()){
            return null;
        }

        return this.enlaces.get(i);
    }

    public Enlace getEnlace(Usuario destino){
        for(Enlace i: this.enlaces){
            if(i.getUsuarioDestino() == destino){
                return i;
            }
        }

        return null;
    }

    @Override
    public String toString(){
        String result;

        result = this.nombre + "(" + this.capacidadAmplificacion + ")" + "[";

        for(Enlace i: this.enlaces){
            result = result + i.toString()+ ",";
        }

        result = result + "]";

        return result;
    }

}