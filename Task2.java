import java.io.*;
import java.util.PriorityQueue;

class PriorityJob implements Comparable<PriorityJob> {
    int jobId;
    int processingTime;
    int priorityClass;

    public PriorityJob(int jobId, int processingTime, int priorityClass) {
        this.jobId = jobId;
        this.processingTime = processingTime;
        this.priorityClass = priorityClass;
    }

    @Override
    public int compareTo(PriorityJob other) {
        if (this.priorityClass != other.priorityClass) {
            return this.priorityClass - other.priorityClass;  // Higher priority first
        } else {
            return this.processingTime - other.processingTime;  // SPT within same class
        }
    }
}

public class Task2 {

    public static void main(String[] args) {
        PriorityQueue<PriorityJob> minHeap = new PriorityQueue<>();
        String inputFile = "task2-input.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int jobId = Integer.parseInt(parts[0]);
                int processingTime = Integer.parseInt(parts[1]);
                int priorityClass = Integer.parseInt(parts[2]);
                minHeap.add(new PriorityJob(jobId, processingTime, priorityClass));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int currentTime = 0;
        double totalCompletionTime = 0;
        StringBuilder executionOrder = new StringBuilder("Execution order: [");

        int totalJobs = minHeap.size();

        while (!minHeap.isEmpty()) {
            PriorityJob job = minHeap.poll();
            currentTime += job.processingTime;
            totalCompletionTime += currentTime;
            executionOrder.append(job.jobId).append(", ");
        }

        executionOrder.setLength(executionOrder.length() - 2);
        executionOrder.append("]");

        System.out.println(executionOrder);
        System.out.printf("Average completion time: %.1f\n", totalCompletionTime / totalJobs);
    }
}
