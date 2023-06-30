import java.io.FileWriter;
import java.io.IOException;
import java.util.PriorityQueue;
// import java.util.Random;


public class ToyStore {
    private PriorityQueue<Toy> toyQueue;

    public ToyStore() {
        toyQueue = new PriorityQueue<>((t1, t2) -> Integer.compare(t2.getWeight(), t1.getWeight()));
    }

    public void addToy(Toy toy) {
        toyQueue.add(toy);
    }

    public int getRandomToyId() {
    double randomNumber = Math.random();
    double cumulativeProbability = 0.0;
    double[] probabilities = {0.2, 0.2, 0.6};

    for (int i = 0; i < toyQueue.size(); i++) {
        cumulativeProbability += probabilities[i];

        if (randomNumber <= cumulativeProbability) {
            return toyQueue.toArray(new Toy[0])[i].getId();
        }
    }

    return toyQueue.peek().getId();
}

    public static void main(String[] args) {
        ToyStore toyStore = new ToyStore();

        // Добавление игрушек в магазин
        toyStore.addToy(new Toy(1, "конструктор", 2));
        toyStore.addToy(new Toy(2, "робот", 2));
        toyStore.addToy(new Toy(3, "кукла", 6));

        try {
            FileWriter fileWriter = new FileWriter("output.txt");
            // Вызов метода Get 10 раз и запись результатов в файл
            for (int i = 0; i < 10; i++) {
                int randomToyId = toyStore.getRandomToyId();
                fileWriter.write(String.valueOf(randomToyId) + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
