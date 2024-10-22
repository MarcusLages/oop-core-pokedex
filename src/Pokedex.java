import java.util.*;
import java.util.function.Consumer;

public class Pokedex<T extends Pokemon> {

    public enum PokeSorting {
        BY_LEVEL,
        BY_NAME,
        BY_TYPE
    }

    public enum PokeType {
        FIRE("Fire"),
        WATER("Water"),
        GRASS("Grass");

        public final String type;

        PokeType(final String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }

    private final List<T> pokemons;

    public Pokedex(final int initialSize) {
        pokemons = new ArrayList<>(initialSize);
    }

    public Pokedex() {
        this(10);
    }

    public void add(final T opponent) {
        if(opponent == null) return;

        pokemons.add(opponent);
    }

    public void remove(final T opponent) {
        if(opponent == null) return;

        pokemons.remove(opponent);
    }

    public void remove(final int index) {
        pokemons.remove(index);
    }

    public T get(final int index) {
        return pokemons.get(index);
    }

    public int size() {
        return pokemons.size();
    }

    public void printPokedex() {
        System.out.println(this);
    }

    public void deleteAllPokemonsOfType(final PokeType type) {
        final Iterator<T> it;
        it = pokemons.iterator();

        if(!pokemons.isEmpty()) {
            while(it.hasNext()) {
                final T pokemon;
                pokemon = it.next();

                if(pokemon != null &&
                        pokemon.getType().equals(type.getType())) {
                    it.remove();
                }
            }
        }
    }

    public static void printPokeList(final List<? extends Pokemon> pokedex) {
        if(pokedex == null) {
            throw new IllegalArgumentException("Null not accepted");
        }

        final StringBuilder builder;
        builder = new StringBuilder();

        if(!pokedex.isEmpty()) {
            for(final Pokemon pokemon: pokedex) {
                if(pokemon != null) {
                    builder.append(pokemon)
                            .append("\n");
                }
            }
        }

        if(builder.isEmpty()) {
            System.out.println("No pokemons in the pokedex!\n");
        } else {
            System.out.println(builder.toString());
        }
    }

    // This is inefficient, but I wanted to try using run() and a lambda with
    // functional interface for something, so I decided to use it for printing
    // the stats
    public void printStats() {
        final StringBuilder builder;
        builder = new StringBuilder();

        run((pokemon)->{
            builder.append(pokemon.getStats().toString())
                    .append("\n");
        });

        System.out.println(builder.toString());
    }

    public void run(final Consumer<T> pokemonFunction) {
        if(pokemons.isEmpty()) return;

        for(final T pokemon: pokemons) {
            if(pokemon != null) {
                pokemonFunction.accept(pokemon);
            }
        }
    }

    public void sort(final PokeSorting sortingMethod) {
        switch(sortingMethod) {
            case PokeSorting.BY_LEVEL:
                Collections.sort(pokemons);
                break;
            case PokeSorting.BY_NAME:
                Collections.sort(pokemons, (p1, p2) -> {
                    return p1.getName().compareToIgnoreCase(p2.getName());
                });
                break;
            case PokeSorting.BY_TYPE:
                Collections.sort(pokemons, Comparator.comparing(Pokemon::getType, String::compareToIgnoreCase));
                break;
            default:
                throw new IllegalArgumentException("Invalid sorting method.\n");
        }
    }

    @Override
    public String toString() {
        final StringBuilder builder;
        builder = new StringBuilder();

        if(!pokemons.isEmpty()) {
            for(final T pokemon: pokemons) {
                if(pokemon != null) {
                    builder.append(pokemon)
                            .append("\n");
                }
            }
        }

        if(builder.isEmpty()) {
            return "No pokemons in the pokedex!\n";
        } else {
            return builder.toString();
        }
    }

}
