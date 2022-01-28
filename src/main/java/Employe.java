public class Employe extends Personne{

    public Employe(String nom, String prenom) {
        super(nom, prenom);
    }

    @Override
    public void manger() {
        System.out.println("L'employé mange");
    }
}
