public class TrafficSystemSimulation {

    public static void main(String[] args) {
        // —оздаем светофоры с различными базовыми временами дл€ моделировани€ приоритетов
        TrafficLight light1 = new TrafficLight(1, 30);
        TrafficLight light2 = new TrafficLight(2, 25);
        TrafficLight light3 = new TrafficLight(3, 20);
        TrafficLight light4 = new TrafficLight(4, 15);

        // ”станавливаем соседей
        light1.addNeighbor(light2);
        light1.addNeighbor(light3);
        light2.addNeighbor(light4);
        light3.addNeighbor(light4);

        // Ёмул€ци€ изменени€ очередей и циклов работы
        light1.updateQueue(12);
        light2.updateQueue(15);
        light3.updateQueue(5);
        light4.updateQueue(3);

        // «апуск цикла обработки событий и вывода состо€ни€
        for (int i = 0; i < 5; i++) {
            System.out.println("=== Cycle " + (i + 1) + " ===");
            light1.processEvents();
            light2.processEvents();
            light3.processEvents();
            light4.processEvents();

            light1.runCycle();
            light2.runCycle();
            light3.runCycle();
            light4.runCycle();

            System.out.println();
        }
    }
}
