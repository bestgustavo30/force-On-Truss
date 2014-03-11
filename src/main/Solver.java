package main;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JProgressBar;

public class Solver
{
	static JFrame frame = null;
	static JDialog dialog = null;
	static JProgressBar progressBar = null;
	static List<String> coords = null;
	static List<String> members = null;
	static List<String> forces = null;
	static List<String> joints = null;
	static int currentProgressBarPos = 0;
	
	public Solver(final JFrame framePassed, final JDialog dialogPassed, final JProgressBar progressBarPassed, final List<String> coordsPassed, final List<String> membersPassed, final List<String> forcesPassed, final List<String> jointsPassed)
	{
		Solver.frame = framePassed;
		Solver.dialog = dialogPassed;
		Solver.progressBar = progressBarPassed;
		Solver.coords = coordsPassed;
		Solver.members = membersPassed;
		Solver.forces = forcesPassed;
		Solver.joints = jointsPassed;
		Solver.currentProgressBarPos = progressBar.getValue();
	}
	
	public static void solve()
	{
		System.out.println("Solving");
		animateProgressBar(10);
		
	}

	
	
	private static void animateProgressBar(final int i)
	{
		final ScheduledExecutorService updateProgressBar = Executors.newSingleThreadScheduledExecutor();
	     updateProgressBar.scheduleWithFixedDelay(new Runnable()
	      {
	        @Override
	        public void run()
	        {
	          progressBar.setValue(currentProgressBarPos);
	          //progressBar.setToolTipText("<html><body style='background:#ffffff;'>"+Integer.toString(progressBar.getValue())+"</body></html>");
	          currentProgressBarPos ++;
	          if(currentProgressBarPos>=i)
	        	  updateProgressBar.shutdown();
	        	
	        }
	      }, 0, 20, TimeUnit.MILLISECONDS);
	}
}
