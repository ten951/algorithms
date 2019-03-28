package com.captain.algorithm;

import java.util.StringJoiner;

/**
 * @author Darcy
 * Created By Darcy on 2019-03-06 16:23
 */
public class Job implements Comparable<Job> {

    private String jobName;
    private double time;

    public Job(String jobName, double time) {
        this.jobName = jobName;
        this.time = time;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public double getTime() {
        return time;
    }

    public void setTime(double time) {
        this.time = time;
    }

    @Override
    public int compareTo(Job o) {
        if (this.time > o.time) {
            return 1;
        } else if (this.time < o.time) {
            return -1;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Job.class.getSimpleName() + "[", "]")
                .add("jobName='" + jobName + "'")
                .add("time=" + time)
                .toString();
    }
}
