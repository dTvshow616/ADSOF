import java.util.ArrayList;
import java.util.List;

/**
 * Nombre de la clase: Usuario
 * <p>
 * Description: Implementa los usuarios
 * @author Álvaro G.S. & Ana O.R.
 * @version 1.5
 * @see Mensaje
 * @see Enlace
 * @see Exposicion
 */
public class Usuario{
    /** La suma del alcance de todos los mensajes recibidos */
    protected static int sumaAlcance;
    /** Nombre del usario */
    protected String nombre;
    /** Capacidad de amplificar el alcance de un mensaje de un usuario */
    protected int capacidadAmplificacion;
    /** Lista con todos los enlaces a otros usuarios*/
    protected List<Enlace> enlaces;
    /** Lista con todos los mensajes recibidos*/
    protected List<Mensaje> mensajes;
    /** Exposicion del usuario */
    protected Exposicion expuesto;

    static {
        /* inicializa la variable estática a 0 al crear la clase */
        sumaAlcance = 0;
    }

    /**
     * Constructor para un usuario
     * @param nombre  nombre del usuario
     */
    public Usuario(String nombre){
        this(nombre,2);
    }

    /**
     * Constructor para un usuario
     * @param nombre  nombre del usuario
     * @param capacidadAmplificacion Capacidad de amplificar el alcance de un mensaje de un usuario
     */
    public Usuario(String nombre, int capacidadAmplificacion){
        this.nombre = nombre;
        this.capacidadAmplificacion = capacidadAmplificacion;
        this.expuesto = Exposicion.ALTA;
        this.enlaces = new ArrayList<>();
        this.mensajes = new ArrayList<>();
    }

    /**
     * Añade un enlace a la lista de enlaces del usuario
     * @param e  enlace que se va a añadir a la lista
     * @return true si se añade correctamente, false en caso de fallo
     */
    public boolean addEnlace(Enlace e){
        if((e.getUsuarioOrigen() != this) || (this == e.getUsuarioDestino())){
            return false;
        }

        for (Enlace i : this.enlaces) {
            if (i.getUsuarioDestino() == e.getUsuarioDestino()) {
                return false;
            }
        }

        this.enlaces.add(e);
        return true;
    }

    /**
     * Construye un enlace y lo añade a la lista de enlaces del usuario
     * @param usuarioDestino  usario con el que conecta el enlace
     * @param coste coste del enlace
     * @return true si se añade correctamente, false en caso de fallo
     */
    public boolean addEnlace(Usuario usuarioDestino, int coste){
        Enlace e = new Enlace(this,usuarioDestino,coste);

        return addEnlace(e);
    }

    /**
     * Añade un mensae a la lista de mensajes del usuario
     * @param m  mensaje que se va a añadir a la lista
     * @return true si se añade correctamente, false en caso de fallo
     */
    public boolean addMensaje(Mensaje m){
        int alcanceTotal = 0;
        if((m.getUsuarioActual() != this)){
            return false;
        }

        for(Mensaje i: this.mensajes){
            if(i == m){
                return false;
            }
        }

        if(m.getAlcanceDisponible() < alcanceTotal/this.getNumMensajes()){
            this.cambiarExposicion(this.expuesto.subir());
        } else{
            this.cambiarExposicion(this.expuesto.bajar());
        }

        alcanceTotal = alcanceTotal + m.getAlcanceDisponible();

        this.mensajes.add(m);
        return true;
    }

    /**
     * Cambia la exposicion del usuario
     * @param e  nueva exposicion
     */
    public void cambiarExposicion(Exposicion e){
        this.expuesto = e;
    }

    /**
     * Devuelve el nombre del usuario
     * @return el nombre del usuario
     */
    public String getNombre(){
        return this.nombre;
    }

    /**
     * Devuelve la capacidad de amplificacion del usuario
     * @return la capacidad de amplificacion del usuario
     */
    public int getCapacidadAmplificacion(){
        return this.capacidadAmplificacion;
    }

    /**
     * Devuelve la cantidad de enlaces que tiene el usuario
     * @return la cantidad de enlaces que tiene el usuario
     */
    public int getNumEnlaces(){
        return this.enlaces.size();
    }

    /**
     * Devuelve el enlace en la posicion i
     * @param i posicion de la que se va a sacar el enlace
     * @return el enlace en la posicion i o null si no se encuentra
     */
    public Enlace getEnlace(int i){
        if(i >= this.getNumEnlaces()){
            return null;
        }

        return this.enlaces.get(i);
    }

    /**
     * Devuelve el primer enlace que tenga el usuario destino que se pide
     * @param destino usuario del que se pide el enlace
     * @return el enlace con el usuario destino o null si no se encuentra
     */
    public Enlace getEnlace(Usuario destino){
        for(Enlace i: this.enlaces){
            if(i.getUsuarioDestino() == destino){
                return i;
            }
        }

        return null;
    }

    /**
     * Devuelve la cantidad de mensajes recibidos por el usuario
     * @return la cantidad de mensajes recibidos por el usuario
     */
    public int getNumMensajes(){
        if(this.mensajes.size() == 0){
            return 1;
        }

        return this.mensajes.size();
    }

    /**
     * Comprueba que el mensaje existe dentro del usuario
     * @return la cantidad de mensajes recibidos por el usuario
     */
    public boolean comprobarMensaje(Mensaje m){
        for(Mensaje i: this.mensajes){
            if(i == m){
                return true;
            }
        }
        return false;
    }

    /**
     * Devuelve la exposicion que tenga el usuario
     * @return la exposicion del usuario
     */
    public Exposicion getExposicion(){
        return this.expuesto;
    }

    @Override
    public String toString() {
        String result;

        result = this.nombre + "(" + this.capacidadAmplificacion + ")" + "[";

        for (Enlace i : this.enlaces) {
            result = result + i.toString() + ",";
        }

        result = result + "]";

        return result;
    }

}