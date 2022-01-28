public interface Mangeur {
    void manger();

    default void boire(int litre){
        System.out.println("Je bois "+ litre + " litre");
    }

}
