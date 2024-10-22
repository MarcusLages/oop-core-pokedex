public class WaterPokemon extends Pokemon {

    public WaterPokemon(final String name, final int maxHp)
            throws InvalidPokemonException {
        super(name, maxHp, "Water");
    }

    @Override
    public void attack() {
        System.out.println(getName() + " used Water Gun!!!\n");
    }

    @Override
    public void useAbility(final Pokemon opponent) {
        hydrate();
    }

    public void hydrate() {
        System.out.println(getName() + " used Hydrate!!!\n" +
                getName() + " regenerates 5HP\n");
        heal(5);
    }
}
