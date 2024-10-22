public class GrassPokemon extends Pokemon {

    public GrassPokemon(final String name, final int maxHp)
            throws InvalidPokemonException {
        super(name, maxHp, "Grass");
    }

    @Override
    public void attack() {
        System.out.println(getName() + " used Grass Cut!!!\n");
    }

    @Override
    public void useAbility(final Pokemon opponent) {
        motherNature();
    }

    public void motherNature() {
        System.out.println(getName() + " used Mother Nature!!!\n" +
                getName() + " leveled up!\n");
        levelUp();
    }
}
