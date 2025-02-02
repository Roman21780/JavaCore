package HardWorker;

@FunctionalInterface
public interface OnTaskDoneListener {
    void onDone(String result);
}
