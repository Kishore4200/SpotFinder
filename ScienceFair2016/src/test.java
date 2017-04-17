public class test {

	static int timeoutTotal = 0;
	static int timeoutTotal2 = 0;
	static boolean moduleness;
    private static class UpdaterThread extends Thread {
        private final int TIMEOUT = 3000;

        @Override
		public void run() {
            while (true) {
                try {
                    Thread.sleep(TIMEOUT);
                    timeoutTotal += TIMEOUT;
                    if (timeoutTotal % 15000 == 0) {
                    	moduleness = true;
                    }
                    System.out.println("3 seconds passed | " + timeoutTotal/1000 + " | " + moduleness);
                    print();
                } catch (InterruptedException ex) {
                }
            }
        }
    }
    
    /**
     * @param args
     *            the command line arguments
     */
    public static void main(String[] args) {
        try {
        	System.out.println(timeoutTotal + ", " + timeoutTotal2);
            Thread u = new UpdaterThread();
            u.start();
            /*while (true) {
                u.start();
            }*/
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static void print() {
    	System.out.println("Hello world!");
    	System.out.println("------------");
    }
}