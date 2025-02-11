package ReferenceSolution.lambda.lam2;


public class Main {
    public static void main(String[] args) {
        OnTaskDoneListener listener = result -> System.out.println("Success: " + result);

        Worker worker = new Worker(listener);

        worker.start();
    }
}