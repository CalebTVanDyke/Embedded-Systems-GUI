package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Arc2D;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private Robot robot;
	private ArrayList<SpaceObject> foundObjs;
	private JTextField textField;
	private JButton btnSubmit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		foundObjs = new ArrayList<>();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1300, 750);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		textField = new JTextField();
		menuBar.add(textField);
		textField.setColumns(10);
		textField.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				performCommand(textField.getText());
				
			}
		});
		
		btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				performCommand(textField.getText());
				
			}
		});
		menuBar.add(btnSubmit);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		robot = new Robot(getWidth()/2, getHeight()/2, 20);

		addMouseListener(new AddPole());
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.BLUE);
		g2d.fillOval(robot.getX(), robot.getY(), robot.getSize(), robot.getSize());
		g2d.setColor(Color.YELLOW);
		g.fillArc(robot.getX(), robot.getY(), robot.getSize(), robot.getSize(), 45 - robot.getDegrees(), 90);
		g2d.setColor(Color.RED);
		for(SpaceObject obj : foundObjs){
			g2d.fillOval(obj.getX(), obj.getY(), obj.getSize(), obj.getSize());
		}
	}
	private void performCommand(String text) {
		textField.setText("");
		Scanner scan = new Scanner(text);
		while(scan.hasNext()){
			String command = scan.next();
			if(command.equals("move")){
				if(scan.hasNextInt()){
					int distance = scan.nextInt();
					robot.move(distance);
				}
			}
			else if(command.equals("rotate")){
				if(scan.hasNextInt()){
					int degrees = scan.nextInt();
					robot.rotate(degrees);
				}
			}
		}
		scan.close();
		repaint();
		
	}
	
	private class AddPole implements MouseListener{

		@Override
		public void mouseClicked(MouseEvent e) {
			foundObjs.add(new SpaceObject(e.getX(), e.getY(), 15));
			repaint();
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}	
	}
}
