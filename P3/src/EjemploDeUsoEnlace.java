public class EjemploDeUsoEnlace{
    public static void main (String[] args){
        Usuario ana = new Usuario("ana", 1);
        Usuario carmen = new Usuario("carmen",5);
        Usuario luis = new Usuario("luis",3);

        Enlace e1 = new Enlace(ana,carmen,5);
        System.out.println(e1);

        Enlace e2 = new Enlace(carmen,ana);
        System.out.println(e2);

        Enlace e3 = new Enlace(ana,luis,-30);
        System.out.println(e3);

        Enlace e4 = new Enlace(carmen,null);
    }
}