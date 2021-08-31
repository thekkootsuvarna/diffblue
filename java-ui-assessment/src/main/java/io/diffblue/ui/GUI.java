package io.diffblue.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JRadioButton;
import java.awt.event.ItemListener;
import java.util.concurrent.ConcurrentHashMap;
import java.awt.event.ItemEvent;

public class GUI {

	private JFrame frame;
	private ConcurrentHashMap<Integer, Double> workersAndDataPoints;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 925, 639);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox comboBox = new JComboBox(UserInterface.values());
		comboBox.setBounds(53, 48, 191, 33);
		frame.getContentPane().add(comboBox);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(53, 138, 559, 369);
		frame.getContentPane().add(textArea);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		btnStart.setBounds(275, 52, 97, 25);
		frame.getContentPane().add(btnStart);
		
		JButton btnStop = new JButton("STOP");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnStop.setBounds(515, 52, 97, 25);
		frame.getContentPane().add(btnStop);
		
		JRadioButton rdbtnShowhideText = new JRadioButton("Show/Hide Text");
		rdbtnShowhideText.setSelected(true);
		rdbtnShowhideText.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent e) {
				if(rdbtnShowhideText.isSelected()) {
					textArea.setVisible(true);
				}else {
					textArea.setVisible(false);
				}
			}
		});
		rdbtnShowhideText.setBounds(53, 104, 127, 25);
		frame.getContentPane().add(rdbtnShowhideText);
		
		JButton btnLiveChart = new JButton("Live Chart");
		btnLiveChart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {			
				
				
				Timer timer = new Timer(100,this);
				timer.start();
			}
			
		});
		btnLiveChart.setBounds(746, 137, 97, 25);
		frame.getContentPane().add(btnLiveChart);
	}
}
