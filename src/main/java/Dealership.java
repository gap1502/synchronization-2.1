import java.util.ArrayList;
import java.util.List;

public class Dealership {
    private static final int BUILD_TIME = 2500;
    private static final int BUY_TIME = 1000;
    private static final int CAR_COUNT = 10;
    List<Car> cars = new ArrayList<>();
    Dealer dealer;

    {
        dealer = new Dealer(this);
    }

    public void receiveCar() {
        for (int i = 1; i <= CAR_COUNT; i++) {
            synchronized (this) {
                try {
                    Thread.sleep(BUILD_TIME);
                    cars.add(new Car());
                    System.out.println(Thread.currentThread().getName() + " выпустил 1 авто");
                    notify();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void sellCar() {
        try {
            System.out.println(Thread.currentThread().getName() + " зашел в автосалон");
            while (cars.size() == 0) {
                System.out.println("Машин нет!");
                wait();
            }
            Thread.sleep(BUY_TIME);
            System.out.println(Thread.currentThread().getName() + " уехал на новеньком авто");
            cars.remove(0);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
