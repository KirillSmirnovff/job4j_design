package ru.job4j.it;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();
    private int count = 0;

    public T poll() {
        for (int i = 0; i < count; i++) {
            out.push(in.pop());
        }
        T result = out.pop();
        for (int i = 1; i < count; i++) {
            in.push(out.pop());
        }
        count--;
        return result;
    }

    public void push(T value) {
        in.push(value);
        count++;
    }
}