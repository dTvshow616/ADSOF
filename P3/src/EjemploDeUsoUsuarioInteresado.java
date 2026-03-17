public class EjemploDeUsoUsuarioInteresado{
    public static void main(String args[]){
        UsuarioInteresado ana = new UsuarioInteresado("ana",1);
        Usuario carmen = new Usuario("carmen");
        Usuario luis = new Usuario("luis");
        Mensaje m1 = new Mensaje("Hi!", 1000, carmen);
        Mensaje m2 = new Mensaje("Hey!", 2, carmen);
        Mensaje m3 = new Mensaje("Holi!", 2, carmen);

        System.out.println(ana);
        System.out.println(carmen);
        System.out.println(luis);

        ana.addEnlace(carmen,3);
        ana.addEnlace(luis,10);
        
        System.out.println(ana.getEnlace(luis));

    }
}