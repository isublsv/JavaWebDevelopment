package by.epam.thread.helloworld.ex01;

public class MyThread extends Thread {

    MyThread(final String s) {
        this.setName(s);
    }

    /**
     * If this thread was constructed using a separate
     * <code>Runnable</code> run object, then that
     * <code>Runnable</code> object's <code>run</code> method is called;
     * otherwise, this method does nothing and returns.
     * <p>
     * Subclasses of <code>Thread</code> should override this method.
     *
     * @see #start()
     */
    @Override
    public void run() {
        System.out.println(this.getName());
    }
}
