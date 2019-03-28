package com.captain.concurrency.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Darcy
 * Created By Darcy on 2019-03-28 15:31
 */
public class FightQueryExample {
    private static List<String> fightCompany = Arrays.asList("CSA", "CEA", "HNA");

    public static void main(String[] args) {
        final List<String> results = search("SH", "BJ");
        results.forEach(System.out::println);
    }


    private static List<String> search(String original, String dest) {
        final List<String> result = new ArrayList<>();
        final List<FightQueryTask> tasks = fightCompany.stream()
                .map(f -> createSearchTask(f, original, dest))
                .collect(Collectors.toList());
        tasks.forEach(Thread::start);
        tasks.forEach(t -> {
            try {
                t.join();
            } catch (InterruptedException ignored) {

            }
        });
        tasks.stream().map(FightQuery::get).forEach(result::addAll);
        return result;

    }

    private static FightQueryTask createSearchTask(String fight, String original, String dest) {
        return new FightQueryTask(fight, original, dest);

    }

}
