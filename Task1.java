import java.io.*;
import java.util.PriorityQueue;

class Job implements Comparable<Job> {
    int jobId;
    int processingTime;

    public Job(int jobId, int processingTime) {
        this.jobId = jobId;
        this.processingTime = processingTime;
    }

    @Override
    public int compareTo(Job other) {
        return this.processingTime - other.processingTime;
    }
}

public class Task1{

    public static void main(String[] args) {
        PriorityQueue<Job> minHeap = new PriorityQueue<>();
        String inputFile = "task1-input.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int jobId = Integer.parseInt(parts[0]);
                int processingTime = Integer.parseInt(parts[1]);
                minHeap.add(new Job(jobId, processingTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        int currentTime = 0;
        double totalCompletionTime = 0;
        StringBuilder executionOrder = new StringBuilder("Execution order: [");

        int totalJobs = minHeap.size();

        while (!minHeap.isEmpty()) {
            Job job = minHeap.poll();
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
