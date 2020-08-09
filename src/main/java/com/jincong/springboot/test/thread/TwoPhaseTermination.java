package com.jincong.springboot.test.thread;

class TwoPhaseTermination {

    private Thread monitor;

    public void start() {
        monitor = new Thread(()-> {

            while (true) {
                Thread curThread = Thread.currentThread();
                if(curThread.isInterrupted()) {
                    System.out.println("料理后事后，优雅停机");
                    break;
                }

                try {
                    Thread.sleep(1000);
                    System.out.println("监控线程执行监控任务");
                } catch (InterruptedException e) {
                    e.printStackTrace();

                    curThread.interrupt();
                }
            }
        });
    }


    public void stop() {
        monitor.interrupt();
    }
}
