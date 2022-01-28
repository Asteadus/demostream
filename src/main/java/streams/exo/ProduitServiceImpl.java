package streams.exo;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ProduitServiceImpl implements ProduitService{
    private final List<Produit> liste = new ArrayList<>();
    @Override
    public List<Produit> getAll() {
         return liste;
    }

    @Override
    public Produit getOne(int id) {
        Produit produitFirst = null;
        if(liste.stream().anyMatch(p->p.getId()== id)) {
            produitFirst = liste.stream().filter(p -> p.getId() == id).findFirst().get();
        }

        return produitFirst;
    }

    @Override
    public boolean insert(Produit toAdd) {
        if (toAdd != null && liste.stream().anyMatch(p -> p.getNom().equals(toAdd.getNom()))){
            liste.add(toAdd);
            return true;
        }
        return false;
    }

    @Override
    public Produit delete(int id) {
        Produit produitFirst = getOne(id);
        liste.remove(produitFirst);

        return null;
    }

    @Override
    public List<Produit> getAllSorted(Comparator<Produit> comparator) {
        List<Produit> produit = liste.stream().sorted(comparator).toList();
        return produit;
    }

    @Override
    public Produit getCheapest() {
        Optional<Produit> optProduitCheap = liste.stream().min(Comparator.comparing(Produit::getPrix));
        Produit produitCheap = null;
        if (optProduitCheap.isPresent()){
            produitCheap = optProduitCheap.get();
        }

        return produitCheap ;
    }

    @Override
    public Produit getMostExpensive() {
        Optional<Produit> optProduitExpensive = liste.stream().max(Comparator.comparing(Produit::getPrix));
        Produit produitExpensive = null;
        if (optProduitExpensive.isPresent()){
            produitExpensive = optProduitExpensive.get();
        }
        return produitExpensive ;
    }

    @Override
    public List<Produit> getAllByBrand(String brand) {
        List<Produit> produit = liste.stream().filter(p ->p.getMarque().equals(brand)).toList();
        return produit;
    }
}
