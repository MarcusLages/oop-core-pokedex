public class PokemonDriver {

    public static void main(final String[] args) {
        final Pokedex<Pokemon> pokemons;
        pokemons = new Pokedex<>();

        try {
            pokemons.add(new FirePokemon("Charizard", 50));
            pokemons.add(new GrassPokemon("Bulbasaur", 40));
            pokemons.add(new WaterPokemon("Squirtle", 30));
        } catch (final InvalidPokemonException | RuntimeException exception) {
            System.out.println(exception.getMessage());
        } finally {
            System.out.println("We have " + pokemons.size() + " pokemons!\n\n");
        }

        try {
            while(true) {
                pokemons.get(0).battle(pokemons.get(1));

            }
        } catch (final BattleNotPossibleException exception) {
            System.out.println("Pokemon " + pokemons.get(1).getName() + " is dead!");
        } finally {
            try {
                while(true) {
                    pokemons.get(0).battle(pokemons.get(2));

                }
            } catch (final BattleNotPossibleException exception) {
                System.out.println("Pokemon " + pokemons.get(2).getName() + " is dead!");
            }
        }

        pokemons.get(1).useAbility(pokemons.get(2));

        pokemons.printPokedex();
//        pokemons.sort(Pokedex.PokeSorting.BY_TYPE);
        pokemons.printPokedex();

        Pokemon charizard;
        Pokemon bulbasaur;
        Pokemon squirtle;

        charizard = pokemons.get(0);
        bulbasaur = pokemons.get(1);
        squirtle = pokemons.get(2);

        BattleCapable.displayFight(charizard, charizard);
        System.out.println(Pokemon.getMultiplier(charizard, charizard));

        BattleCapable.displayFight(charizard, squirtle);
        System.out.println(Pokemon.getMultiplier(charizard, squirtle));

        BattleCapable.displayFight(charizard, bulbasaur);
        System.out.println(Pokemon.getMultiplier(charizard, bulbasaur));

        BattleCapable.displayFight(bulbasaur, squirtle);
        System.out.println(Pokemon.getMultiplier(bulbasaur, squirtle));

        BattleCapable.displayFight(squirtle, bulbasaur);
        System.out.println(Pokemon.getMultiplier(squirtle, bulbasaur));

        pokemons.printStats();
    }

}
