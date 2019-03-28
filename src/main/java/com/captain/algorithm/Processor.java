package com.captain.algorithm;

import com.captain.algorithm.Job;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Darcy
 * Created By Darcy on 2019-03-06 18:37
 */
public class Processor implements Comparable<Processor> {

    private List<Job> jobs;
    private double totalTime;

    public Processor() {
        this.jobs = new ArrayList<>();
        this.totalTime = 0;
    }


    public List<Job> getJobs() {
        return jobs;
    }

    public double getTotalTime() {
        return totalTime;
    }

    public void insert(Job job) {
        jobs.add(job);
        totalTime += job.getTime();
    }

    @Override
    public int compareTo(Processor o) {
        if (this.totalTime > o.totalTime) {
            return 1;
        } else if (this.totalTime < o.totalTime) {
            return -1;
        } else {
            return 0;
        }
    }
}
