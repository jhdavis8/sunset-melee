package graphics;

public class ThreadRunner implements Runnable {

	private CardSelectorWindow param;
	
	@Override
	public void run() {
		param.frame.setVisible(true);
	}

	public ThreadRunner(CardSelectorWindow param) {
		param = param;
		
	}
}
