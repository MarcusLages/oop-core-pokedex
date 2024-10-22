public class FirePokemon extends Pokemon {

    public FirePokemon(final String name, final int maxHp)
            throws InvalidPokemonException {
        super(name, maxHp, Pokedex.PokeType.FIRE);
    }

    @Override
    public void attack() {
        System.out.println(getName() + " used Flamethrower!!!\n");
    }

    @Override
    public void useAbility(final Pokemon opponent) {
        combustion();
        battle(opponent);
    }

    public void combustion() {
        System.out.println(getName() + " used Combustion!!!\n" +
                "He attacks everyone in an area!\n");
    }
}
