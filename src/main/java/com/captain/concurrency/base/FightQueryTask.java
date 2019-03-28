package com.captain.concurrency.base;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @author Darcy
 * Created By Darcy on 2019-03-28 15:23
 */
public class FightQueryTask extends Thread implements FightQuery {
    private final String origin;
    private final String destination;
    private final List<String> flightList = new ArrayList<>();

    public FightQueryTask(String name, String origin, String destination) {
        super("[" + name + "]");
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public void run() {
        System.out.printf("%s-query from %s to %s \n", getName(), origin, destination);
        final int i = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(i);
            this.flightList.add(getName() + "-" + i);
            System.out.printf("The Fight:%s list query successful\n", getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> get() {
        return this.flightList;
    }


}
