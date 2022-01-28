import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.*;

public class Main {

    public static void main(String[] args) {

        Personne p = new Personne("luc", "luc") {
            @Override
            public void manger() {
                System.out.println("je range");
            }
        };
//        p = () -> System.out.println("manger");

        Mangeur m = new Mangeur() {
            @Override
            public void manger() {
                System.out.println("je mange");
            }
        };

        Mangeur m1 = () -> System.out.println("je mange");
        m1 = p::manger;

//        m1.manger();

        List<Personne> pers = new LinkedList<>();
        pers.add(new Personne("","") {
            @Override
            public void manger() {
                System.out.println("je mange dans la liste");
            }
        });
        pers.add(new Personne("dupont","luc") {
            @Override
            public void manger() {
                System.out.println("je mange");
            }
        });

        pers.forEach(personne -> {
            personne.manger();
            personne.boire(15);
        });

        Optional<Personne> opt = Optional.empty();
        opt.orElseThrow( () -> {
            return new RuntimeException();
        } );

        Personne p3 = opt.orElse(p);
        p3 = opt.orElseGet(() -> new Personne("","") {
            @Override
            public void manger() {
            }
        });

        Function<Personne, String> func = Personne::toString;
        BiFunction<String, String, Personne> biFunc = Employe::new;

        UnaryOperator<String> ope =  (chaine) -> chaine+"!";
        BinaryOperator<String> biOpe = (chaine1, chaine2) -> chaine1+chaine2;

        Function<String, String> f1 = ope;
        BiFunction<String,String,String> biF1 = biOpe;
    }

}
