package streams;

import streams.exo.Produit;
import streams.exo.ProduitService;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        List<String> list = List.of(
                "Salut",
                "comment",
                new String("comment"),
                "ca",
                "va?",
                "co"
        );
        Stream<String> stream = list.stream();

        stream.filter( chaine -> {
            System.out.println(chaine);
            return chaine.contains("a");
        });

        // Distinction intermédiaire vs. terminale
        // les intermédiares renvoient des Streams et pas les terminales
        // les intermédiaires peuvent donc être chainé
        // Les intermédiaire enregistrent juste un nouvelle opération à exécuter sans l'exécuter

        // !!! On ne peut pas exécuter 2 opérations sur un meme Stream !!!

        stream = list.stream();

        stream = stream.filter( chaine -> {
            System.out.println(chaine);
            return chaine.contains("a");
        });

        stream = stream.map((chaine -> {
                System.out.println(chaine);
                return chaine + chaine;
            }));

        List<String> list2 = stream.toList();

        // Opérations intermédiaires

        list.stream()
                .filter(e -> e.contains("a"))
                .forEach(System.out::println);

        list.stream() // Stream<String>
                .map(String::length) // Stream<Integer>
                .forEach(System.out::println);

        System.out.println("------ flatMap ------");
        // comment = 7
        // com
        // ment
        list.stream()
                .flatMap(e -> Stream.of(
                        e.substring(0, e.length()/2),
                        e.substring(e.length()/2)
                ))
                .forEach(System.out::println);

        System.out.println("--------distinct-----------");
        System.out.println(list.get(1) == list.get(2));
        list.stream()
                .distinct()
                .forEach(System.out::println);

        System.out.println("SKIP / LIMIT");
        list.stream()
                .skip(2)
                .limit(2)
                .skip(1)
                .forEach(System.out::println);


        System.out.println("------- SORTED -------");
        list.stream()
                .sorted(Comparator.comparingInt(String::length).reversed())
                .limit(3)
                .forEach(System.out::println);


        // Opérations terminales

        System.out.println("--- TERMINALES ---");

        list.stream()
                .filter(chaine -> chaine.contains("a"))
                .forEach( System.out::println );

        LinkedList<String> links = list.stream()
                .filter(chaine -> chaine.contains("a"))
                .collect(Collectors.toCollection(LinkedList::new));

        long nombreElementContenantA = list.stream()
                .filter(chaine -> chaine.contains("a"))
                .count();

        System.out.println("---------------------------------");

        Integer nombreChar = list.stream()
                .map( String::length )
                .filter( (taille) -> {
                    return  taille >= 5;
                })
                .peek( System.out::println )
                .map( taille -> taille * 2 )
                .reduce(0, (acc, taille) -> acc+taille );

        System.out.println( nombreChar );

        System.out.println("---------------------");

        String chaine = list.stream()
                .findFirst()
                .orElse("inconnu");

        boolean b1 = list.stream().allMatch((element) -> element.contains("a"));
        boolean b2 = list.stream().noneMatch((element) -> element.contains("a"));
        boolean b3 = list.stream().anyMatch((element) -> element.contains("a"));

        list.stream().toList();

        list.stream().toArray();

        System.out.println("------------ MIN/MAX ------------");

        Optional<String> opt = list.stream()
                .max(Comparator.comparingInt(String::length));

        opt.ifPresent( System.out::println );

        opt = list.stream()
                .min((s1, s2) -> s1.length() - s2.length());

        opt.ifPresent( System.out::println );

        

    }

}
