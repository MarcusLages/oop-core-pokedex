import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class Pokemon
        implements BattleCapable, Comparable<Pokemon> {

    public class Stats {

        private int wins;
        private int losses;
        private int draws;

        public Stats() {
            wins = 0;
            losses = 0;
            draws = 0;
        }

        public int getWins() {
            return wins;
        }

        public int getLosses() {
            return losses;
        }

        public int getDraws() {
            return draws;
        }

        public void lost() {
            losses++;
        }

        public void draw() {
            draws++;
        }

        public void wins() {
            wins++;
        }

        public double winRate() {
            return (double) wins / (wins + draws + losses);
        }

        @Override
        public String toString() {
            return name + " [W: " + wins +
                    ", L: " + losses +
                    ", D: " + draws +
                    "], WR = " + String.format("%.2f%%", winRate() * 100);
        }

    }

    private static int idCounter = 0;

    private final String name;
    private int level;
    private int hp;
    private final int maxHp;
    private final Pokedex.PokeType type;
    private final int ID;
    private final Stats stats;
    private static final Map<Pokedex.PokeType, Map<Pokedex.PokeType, Double>> multiplier;

    static {
        multiplier = new HashMap<>();

        initializePokeTypeMultiplier(multiplier);
    }

    {
        ID = idCounter;
        idCounter++;
    }

    public Pokemon(final String name, final int maxHp, final Pokedex.PokeType type)
            throws InvalidPokemonException {
        validateHP(maxHp);

        this.name = name;
        this.level = 0;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.type = type;
        this.stats = new Stats();
    }


    public static int getIdCounter() {
        return idCounter;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public Pokedex.PokeType getType() {
        return type;
    }

    public String getTypeName() {
        return type.getType();
    }

    public int getID() {
        return ID;
    }

    public Stats getStats() {
        return stats;
    }

    public abstract void attack();

    private static void validateHP(final int maxHp)
            throws InvalidPokemonException {
        if(maxHp <= 0) throw new InvalidPokemonException();
    }

    @Override
    public void battle(final Pokemon opponent) {
        attack();

        if(opponent.isDead()) throw new BattleNotPossibleException();

        opponent.takeDamage(5);

        if(opponent.isDead()) {
            stats.wins();
        }
    }

    public void takeDamage(final int damage) {
        if(damage < 0) return;

        hp -= damage;

        if(hp <= 0) {
            hp = 0;
            stats.lost();
        }
    }

    public boolean isDead() {
        return hp <= 0;
    }

    public void levelUp() {
        level++;
    }

    public void heal(final int hpHealed) {
        if(hpHealed <= 0) return;

        hp += hpHealed;

        if(hp > maxHp) {
            hp = maxHp;
        }
    }

    public static double getMultiplier(final Pokemon attacker, final Pokemon opponent) {
        return multiplier.get(attacker.type).get(opponent.type);
    }

    private static void initializePokeTypeMultiplier(final Map<Pokedex.PokeType, Map<Pokedex.PokeType, Double>> multiplier) {
        Map<Pokedex.PokeType, Double> pokeTypeMultiplierMap;
        pokeTypeMultiplierMap = new HashMap<>();

        // Fire
        pokeTypeMultiplierMap.put(Pokedex.PokeType.FIRE, 0.8);
        pokeTypeMultiplierMap.put(Pokedex.PokeType.WATER, 0.8);
        pokeTypeMultiplierMap.put(Pokedex.PokeType.GRASS, 1.25);

        multiplier.put(Pokedex.PokeType.FIRE, pokeTypeMultiplierMap);

        pokeTypeMultiplierMap = new HashMap<>();

        // Water
        pokeTypeMultiplierMap.put(Pokedex.PokeType.FIRE, 1.25);
        pokeTypeMultiplierMap.put(Pokedex.PokeType.WATER, 0.8);
        pokeTypeMultiplierMap.put(Pokedex.PokeType.GRASS, 0.8);

        multiplier.put(Pokedex.PokeType.WATER, pokeTypeMultiplierMap);

        pokeTypeMultiplierMap = new HashMap<>();

        // Grass
        pokeTypeMultiplierMap.put(Pokedex.PokeType.FIRE, 0.8);
        pokeTypeMultiplierMap.put(Pokedex.PokeType.WATER, 1.25);
        pokeTypeMultiplierMap.put(Pokedex.PokeType.GRASS, 0.8);

        multiplier.put(Pokedex.PokeType.GRASS, pokeTypeMultiplierMap);
//        System.out.println(multiplier.size());
    }

    @Override
    public int compareTo(final Pokemon argPokemon) {
        return level - argPokemon.level;
    }

    @Override
    public boolean equals(final Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(!(obj instanceof Pokemon)) return false;

        final Pokemon argPokemon;
        argPokemon = (Pokemon) obj;

        return this.name.equals(argPokemon.name) &&
                this.type.equals(argPokemon.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, type);
    }

    @Override
    public String toString() {
        final StringBuilder builder;
        builder = new StringBuilder();

        builder.append(name)
                .append(", type ")
                .append(type)
                .append(" LVL")
                .append(level)
                .append("\nID: ")
                .append(ID)
                .append("\nHP: ")
                .append(hp)
                .append("/")
                .append(maxHp)
                .append("\n");

        return builder.toString();
    }

}
