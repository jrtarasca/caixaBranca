package login;

public class Main {

    public static void main(String[] args) {

        User usuario = new User();
        boolean ok = usuario.verificarUsuario("lopes", "123");

        if (ok) {
            System.out.println("Acesso permitido!");
        } else {
            System.out.println("Acesso negado!");
        }
    }
}
