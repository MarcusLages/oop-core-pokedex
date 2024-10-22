public interface BattleCapable {
    void battle(final Pokemon opponent);
    void useAbility(final Pokemon opponent);

    static void displayFight(final Pokemon p1, final Pokemon p2) {
        System.out.println(p1.getName() + " VS. " + p2.getName());
    }
}
