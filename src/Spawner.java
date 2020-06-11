import java.util.Random;

public class Spawner {
    public int curtime = 0, targetTime = 60 * 2;
    public Random random;

    public Spawner() {
        random = new Random();
    }

    public void update() {
        curtime++;

        if (curtime == targetTime) {
            curtime = 0;

            if (random.nextInt(100) < 50) {
                Game.crabs.add(new Crab(random.nextInt(Game.WIDTH - 40), - 40));
            } else {
                Game.crabs.add(new Crab(random.nextInt(Game.WIDTH - 40), Game.HEIGHT - 40));
            }
        }
    }
}
