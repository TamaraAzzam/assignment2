import java.io.*;
import java.util.*;

class DynamicJob implements Comparable<DynamicJob> {
    int jobId;
    int processingTime;
    int arrivalTime;

    public DynamicJob(int jobId, int processingTime, int arrivalTime) {
        this.jobId = jobId;
        this.processingTime = processingTime;
        this.arrivalTime = arrivalTime;
    }

    @Override
    public int compareTo(DynamicJob other) {
        return this.processingTime - other.processingTime;
    }
}

public class Task3{

    public static void main(String[] args) {
        List<DynamicJob> jobs = new ArrayList<>();
        String inputFile = "task3-input.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                int jobId = Integer.parseInt(parts[0]);
                int processingTime = Integer.parseInt(parts[1]);
                int arrivalTime = Integer.parseInt(parts[2]);
                jobs.add(new DynamicJob(jobId, processingTime, arrivalTime));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        PriorityQueue<DynamicJob> minHeap = new PriorityQueue<>();

        int currentTime = 0;
        double totalCompletionTime = 0;
        StringBuilder executionOrder = new StringBuilder("Execution order: [");

        int index = 0;
        while (index < jobs.size() || !minHeap.isEmpty()) {
            while (index < jobs.size() && jobs.get(index).arrivalTime <= currentTime) {
                minHeap.add(jobs.get(index));
                index++;
            }
            if (!minHeap.isEmpty()) {
                DynamicJob job = minHeap.poll();
                currentTime += job.processingTime;
                totalCompletionTime += currentTime;
                executionOrder.append(job.jobId).append(", ");
            } else {
                currentTime++;
            }
        }
        executionOrder.setLength(executionOrder.length() - 2);
        executionOrder.append("]");

        System.out.println(executionOrder);
        System.out.printf("Average completion time: %.1f\n", totalCompletionTime / jobs.size());
    }
}
