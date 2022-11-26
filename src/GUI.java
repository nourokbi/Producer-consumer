import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI {
	GUI() {
		JFrame frame = new JFrame("Prime Number Generator");

		final JLabel N = new JLabel("N");
		N.setBounds(20, 40, 80, 30);

		final JTextField nSize = new JTextField();
		nSize.setBounds(110, 47, 150, 20);

		final JLabel bufferSize = new JLabel("Buffer Size ");
		bufferSize.setBounds(20, 75, 80, 30);

		final JTextField bufferSizeInput = new JTextField();
		bufferSizeInput.setBounds(110, 82, 150, 20);

		JLabel fileName = new JLabel("Output file");
		fileName.setBounds(20, 110, 80, 30);

		final JTextField fileNameInput = new JTextField();
		fileNameInput.setBounds(110, 117, 150, 20);

		JButton button = new JButton("Start Produce");
		button.setBounds(110, 150, 150, 20);

		JLabel largestPrime = new JLabel("The largest prime number: ");
		largestPrime.setBounds(20, 100, 200, 200);

		JLabel largestPrimeOutput = new JLabel("-");
		largestPrimeOutput.setBounds(230, 190, 100, 20);

		JLabel primeCounter = new JLabel("#of prime numbers generated:");
		primeCounter.setBounds(20, 120, 220, 250);

		JLabel primeCounterOutput = new JLabel("-");
		primeCounterOutput.setBounds(230, 235, 100, 20);

		JLabel timeElapsed = new JLabel("Time Elapsed: ");
		timeElapsed.setBounds(20, 140, 300, 300);

		JLabel timeElapsedOutput = new JLabel("-");
		timeElapsedOutput.setBounds(230, 280, 100, 20);

		frame.add(N);
		frame.add(nSize);

		frame.add(bufferSize);
		frame.add(bufferSizeInput);

		frame.add(fileName);
		frame.add(fileNameInput);

		frame.add(button);

		frame.setSize(330, 370);
		frame.setLayout(null);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(largestPrime);
		frame.add(largestPrimeOutput);

		frame.add(primeCounter);
		frame.add(primeCounterOutput);

		frame.add(timeElapsed);
		frame.add(timeElapsedOutput);

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Integer num = nSize.getText().trim().isEmpty() ? 0 : Integer.parseInt(nSize.getText());
				Integer bs = bufferSizeInput.getText().trim().isEmpty() ? 8
						: Integer.parseInt(bufferSizeInput.getText());
				String out = fileNameInput.getText().trim().isEmpty() ? "output.txt" : fileNameInput.getText();

				Runner runner = new Runner(num, bs, out, largestPrimeOutput, primeCounterOutput, timeElapsedOutput);

				try {
					runner.run();
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
			}
		});
	}
}