package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;

public class MainWindow {
	
	static JFrame frame = null;
	public static questionWindow questionWindowObject = null;
	static Font agencyFBFont = null;
	static String currentDirectory="";
	
	static List<String> coords = new ArrayList<String>();
	static List<String> members = new ArrayList<String>();
	static List<String> forces = new ArrayList<String>();
	static List<String> joints = new ArrayList<String>();
	
	static Dimension mainWindowDimensions = new Dimension(800,400);
	static String mainWindowBackgroundColor = "#666666";
	static String mainWindowForegroundColor = "#ffffff";
	
	static Dimension mainMenuDimensions = new Dimension(75,25);
	static String mainMenuBackgroundColor = "#111111";
	static String mainMenuForegroundColor = "#999999";
	static String mainMenuBorderColor = "#333333";
	static int mainMenuBorderThickness = 3;
	
	static Dimension questionWindowDimensions = new Dimension(800,375);
	static String questionWindowBackgroundColor = "#111111";
	static String questionWindowForegroundColor = "#ffffff";
	static String questionWindowBorderColor = "#555555";
	static int questionWindowBorderThickness = 0;
	
	public static void main(String args[])
	{
		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				try {
					createGUI();
				} catch (FontFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}	
		});
	}

	/*public JPanel questionWindow()
	{
		
		questionWindowJPanel = new JPanel();
		questionWindowJPanel.setPreferredSize(questionWindowDimensions);
		questionWindowJPanel.setBackground(Color.decode(questionWindowBackgroundColor));
		questionWindowJPanel.setBorder(BorderFactory.createMatteBorder(questionWindowBorderThickness, 0, 0, 0, Color.decode(questionWindowBorderColor)));
		return questionWindowJPanel;
	}*/
	class questionWindow extends JPanel
	{
		private static final long serialVersionUID = 1L;

		public questionWindow() {
	    	addMouseListener(new MouseListener()
	    	{
				@Override
				public void mouseClicked(MouseEvent e)
				{
					
				}
				@Override
				public void mousePressed(MouseEvent e)
				{
					
				}
				@Override
				public void mouseReleased(MouseEvent e)
				{
					
				}
				@Override
				public void mouseEntered(MouseEvent e) 
				{
					
				}
				@Override
				public void mouseExited(MouseEvent e)
				{
					
				}
	    	});
	    	addMouseMotionListener(new MouseMotionListener()
	    	{

				@Override
				public void mouseDragged(MouseEvent e)
				{
					
				}

				@Override
				public void mouseMoved(MouseEvent e)
				{
					//System.out.println(e.getX()+","+e.getY());
				}
	    		
	    	});
	    	
	    	setPreferredSize(questionWindowDimensions);
			setBackground(Color.decode(questionWindowBackgroundColor));
			setBorder(BorderFactory.createMatteBorder(questionWindowBorderThickness, 0, 0, 0, Color.decode(questionWindowBorderColor)));
	    }
	    
	    public void addNewKeypoint(String name, int x,int y)
	    {
	    	JRadioButton radio = new JRadioButton();
	    	radio.setBackground(Color.decode("#111111"));
	    	radio.setFocusPainted(false);
	    	radio.setText(name);
	    	radio.setForeground(Color.white);
	    	radio.setLocation(x, y);
	    	add(radio);
			revalidate();
		}

	    public Dimension getPreferredSize() {
	        return questionWindowDimensions;
	    }

	    public void paintComponent(Graphics g) {
	        super.paintComponent(g);       

	        // Draw Text
	        //System.out.print("Hello"+coords.size());
	        g.setColor(Color.white);
	        g.setFont(agencyFBFont.deriveFont(Font.BOLD,14));
	        for(int i=0;i<coords.size();i++)
	        {
	        	g.fillArc(Integer.parseInt(coords.get(i).split(",")[1].toString().trim()), Integer.parseInt(coords.get(i).split(",")[2].toString().trim()), 10, 10, 0, 360);
	        	g.drawString(coords.get(i).split(",")[0].toString().trim(), Integer.parseInt(coords.get(i).split(",")[1].toString().trim())+15, Integer.parseInt(coords.get(i).split(",")[2].toString().trim())+15);
	        }
	        for(int i=0;i<joints.size();i++)
	        {
	        	String joint_name = joints.get(i).split(",")[0].toString().trim();
	        	String joint_type = joints.get(i).split(",")[1].toString().trim();
	        	int x1=0, y1=0;
	        	for(int j=0;j<coords.size();j++)
	        	{
	        		if(coords.get(j).split(",")[0].toString().trim().matches(joint_name))
	        		{
	        			x1 = Integer.parseInt(coords.get(j).split(",")[1]);
	        			y1 = Integer.parseInt(coords.get(j).split(",")[2]);
	        		}
	        	}
	        	// Draw joint_type shape at joint_name point
	        	// Shape: Fixed Support
	        	if(joint_type.matches("Fixed Support"))
	        	{
	        		g.setColor(Color.GREEN);
	        		g.drawLine(x1-8, y1+10, x1+8, y1+10);
	        		g.drawLine(x1, y1, x1-5, y1+10);
	        		g.drawLine(x1, y1, x1+5, y1+10);
	        	} else
	        	// Shape: Movable Support
	        	if(joint_type.matches("Round Support"))
		        {
	        		g.setColor(Color.GREEN);
	        		g.drawLine(x1-5, y1+10, x1+5, y1-10);
	        		g.fillArc(x1, y1+5, 10, 10, 0, 360);
		        }
	        }
	        for(int i=0;i<members.size();i++)
	        {
	        	String a1 = members.get(i).split(",")[0];
	        	String a2 = members.get(i).split(",")[1];
	        	int x1=0, y1=0, x2=0, y2=0;
	        	for(int j=0;j<coords.size();j++)
	        	{
	        		if(coords.get(j).split(",")[0].toString().trim().matches(a1))
	        		{
	        			x1 = Integer.parseInt(coords.get(j).split(",")[1]);
	        			y1 = Integer.parseInt(coords.get(j).split(",")[2]);
	        		}
	        		else
	        		if(coords.get(j).split(",")[0].toString().trim().matches(a2))
		        	{
		        		x2 = Integer.parseInt(coords.get(j).split(",")[1]);
		        		y2 = Integer.parseInt(coords.get(j).split(",")[2]);
		        	}	
	        	}
	        	
	        	g.drawLine(x1+5,y1+5,x2+5,y2+5);
	        }
	    }  
	}
	public JMenuBar mainMenuBar()
	{
		JMenuBar totalMainMenu = new JMenuBar()
		{
			private static final long serialVersionUID = 1L;
			@Override
	        protected void paintComponent(Graphics grphcs) {
	            super.paintComponent(grphcs);
	            Graphics2D g2d = (Graphics2D) grphcs;
	            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
	            GradientPaint gp = new GradientPaint(0, 0,getBackground().brighter().brighter().brighter(), 0, getHeight(),getBackground().darker());
	            g2d.setPaint(gp);
	            //g2d.fillRect(0, 0, getWidth(), getHeight()); 

	        }
		};
		totalMainMenu.setSize(mainMenuDimensions);
		totalMainMenu.setLocation(0,0);
		totalMainMenu.setOpaque(true);
		totalMainMenu.setMinimumSize(mainMenuDimensions);
		totalMainMenu.setPreferredSize(mainMenuDimensions);
		totalMainMenu.setBackground(Color.decode(mainMenuBackgroundColor));
		totalMainMenu.setForeground(Color.decode(mainMenuForegroundColor));
		totalMainMenu.setBorder(BorderFactory.createMatteBorder(mainMenuBorderThickness, mainMenuBorderThickness, 0, mainMenuBorderThickness, Color.decode(mainMenuBorderColor)));
		
		JMenu fileMenu = new JMenu();
	        fileMenu = new JMenu("   Commands");
	        fileMenu.setMnemonic(KeyEvent.VK_A);
	        fileMenu.getAccessibleContext().setAccessibleDescription("File Menu");
	        totalMainMenu.add(fileMenu);
		        JMenuItem fileMenu_New = new JMenuItem("New                             .",KeyEvent.VK_N);
		        fileMenu_New.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		        fileMenu_New.getAccessibleContext().setAccessibleDescription("Creates a new file");
		        fileMenu.add(fileMenu_New);
		        JMenuItem fileMenu_OpenFile = new JMenuItem("Open File",KeyEvent.VK_O);
		        fileMenu_OpenFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		        fileMenu_OpenFile.getAccessibleContext().setAccessibleDescription("Opens a file");
		        fileMenu_OpenFile.addActionListener(new ActionListener()
		        {
					@Override
					public void actionPerformed(ActionEvent e)
					{
						JFileChooser input = new JFileChooser();
						FileNameExtensionFilter SJfilter = new FileNameExtensionFilter("SJ (*.sj)", "sj");
						input.addChoosableFileFilter(SJfilter);
						recursivelySetFonts(input, agencyFBFont.deriveFont(Font.PLAIN, 12));
						input.setFileFilter(SJfilter);
						input.setAcceptAllFileFilterUsed(false);
						input.setDialogTitle("Open a SJ file");
						if(!currentDirectory.matches(""))
							input.setCurrentDirectory(new File(currentDirectory));
						int result = input.showOpenDialog(frame);
						if (result == JFileChooser.APPROVE_OPTION)
						{
							if(!input.getSelectedFile().exists())
								JOptionPane.showMessageDialog(frame, "File path not found", "Error", JOptionPane.ERROR_MESSAGE);
							if(!input.getSelectedFile().canRead())
								JOptionPane.showMessageDialog(frame, "Can not read the file", "Error", JOptionPane.ERROR_MESSAGE);
							if(!input.getSelectedFile().isFile())
								JOptionPane.showMessageDialog(frame, "File error", "Error", JOptionPane.ERROR_MESSAGE);
							else
								JOptionPane.showMessageDialog(frame, "OK", "OK", JOptionPane.INFORMATION_MESSAGE);
						} else if (result == JFileChooser.CANCEL_OPTION) {
						    System.out.println("Cancel was selected");
						}
					}			
		        });
		        fileMenu.add(fileMenu_OpenFile);
		        JMenuItem fileMenu_SaveFile = new JMenuItem("Save File",KeyEvent.VK_S);
		        fileMenu_SaveFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		        fileMenu_SaveFile.getAccessibleContext().setAccessibleDescription("Saves a file");
		        fileMenu_SaveFile.addActionListener(new ActionListener()
		        {
					@Override
					public void actionPerformed(ActionEvent e)
					{
						JFileChooser output = new JFileChooser();
						FileNameExtensionFilter SJfilter = new FileNameExtensionFilter("SJ (*.sj)", "sj");
						output.addChoosableFileFilter(SJfilter);
						recursivelySetFonts(output, agencyFBFont.deriveFont(Font.PLAIN, 12));
						output.setFileFilter(SJfilter);
						output.setAcceptAllFileFilterUsed(false);
						output.setDialogTitle("Save a SJF file");
						if(!currentDirectory.matches(""))
							output.setCurrentDirectory(new File(currentDirectory));
						int result = output.showSaveDialog(frame);
						if (result == JFileChooser.APPROVE_OPTION)
						{
						} else if (result == JFileChooser.CANCEL_OPTION) {
						    System.out.println("Cancel was selected");
						}
					}			
		        });
		        fileMenu.add(fileMenu_SaveFile);
		        fileMenu.addSeparator();
		        
		        
		        
		        JMenu addMenu = new JMenu("Add");
		        addMenu.setMnemonic(KeyEvent.VK_A);
		            JMenuItem addMenu_NewKeypoint = new JMenuItem("Add keypoints",KeyEvent.VK_K);
			        addMenu_NewKeypoint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.ALT_MASK));
			        addMenu_NewKeypoint.getAccessibleContext().setAccessibleDescription("Create new keypoints");
			        addMenu_NewKeypoint.addActionListener(new ActionListeners().addNewKeypoint(frame, coords));
			        addMenu.add(addMenu_NewKeypoint);
			       	
			        JMenuItem addMenu_NewMember = new JMenuItem("Define truss members",KeyEvent.VK_M);
			       	addMenu_NewMember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK));
			       	addMenu_NewMember.getAccessibleContext().setAccessibleDescription("Create new truss members");
			       	addMenu_NewMember.addActionListener(new ActionListeners().addNewMember(frame, coords, members));
			        addMenu.add(addMenu_NewMember);
			        
			        JMenuItem addMenu_NewForce = new JMenuItem("Define force",KeyEvent.VK_F);
			        addMenu_NewForce.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
			        addMenu_NewForce.getAccessibleContext().setAccessibleDescription("Create new force");
			        addMenu_NewForce.addActionListener(new ActionListeners().defineNewForce(frame, coords, forces));
			        addMenu.add(addMenu_NewForce);
			        
			        JMenuItem addMenu_NewJoint = new JMenuItem("Define supports",KeyEvent.VK_F);
			        addMenu_NewJoint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
			        addMenu_NewJoint.getAccessibleContext().setAccessibleDescription("Create new support");
			        addMenu_NewJoint.addActionListener(new ActionListeners().defineNewJoint(frame, coords, joints));
			        addMenu.add(addMenu_NewJoint);
			    
			    fileMenu.add(addMenu);
			    
			    
			    
			    JMenu deleteMenu = new JMenu("Delete");
		        deleteMenu.setMnemonic(KeyEvent.VK_D);
		            JMenuItem deleteMenu_DeleteKeypoint = new JMenuItem("Delete keypoints",KeyEvent.VK_K);
			        deleteMenu_DeleteKeypoint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.SHIFT_MASK));
			        deleteMenu_DeleteKeypoint.getAccessibleContext().setAccessibleDescription("Delete keypoints");
			        deleteMenu_DeleteKeypoint.addActionListener(new ActionListeners().deleteNewKeypoint(frame, coords));
			        deleteMenu.add(deleteMenu_DeleteKeypoint);
			       	
			        
			        JMenuItem deleteMenu_DeleteMember = new JMenuItem("Delete truss members",KeyEvent.VK_M);
			        deleteMenu_DeleteMember.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.SHIFT_MASK));
			        deleteMenu_DeleteMember.getAccessibleContext().setAccessibleDescription("Delete truss members");
			        deleteMenu_DeleteMember.addActionListener(new ActionListeners().deleteNewMember(frame));
			        deleteMenu.add(deleteMenu_DeleteMember);
			        
			        JMenuItem deleteMenu_DeleteForce = new JMenuItem("Delete force members",KeyEvent.VK_F);
			        deleteMenu_DeleteForce.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.SHIFT_MASK));
			        deleteMenu_DeleteForce.getAccessibleContext().setAccessibleDescription("Delete force members");
			        deleteMenu_DeleteForce.addActionListener(new ActionListeners().deleteNewForce(frame));
			        deleteMenu.add(deleteMenu_DeleteForce);
			       	
			    fileMenu.add(deleteMenu);
		            
			    
			    
			       	
			       	JMenuItem solveMenu_Solve = new JMenuItem("Solve",KeyEvent.VK_S);
			       	solveMenu_Solve.setBackground(null);
			       	solveMenu_Solve.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
			       	solveMenu_Solve.getAccessibleContext().setAccessibleDescription("Solve");
			       	solveMenu_Solve.addActionListener(new ActionListeners().solve(frame, coords, members,forces, joints));
			        fileMenu.add(solveMenu_Solve);
			        fileMenu.addSeparator();
			        
			        JMenuItem miscMenu_PrintReport = new JMenuItem("Print Report",KeyEvent.VK_P);
			        miscMenu_PrintReport.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));
			        miscMenu_PrintReport.getAccessibleContext().setAccessibleDescription("Print Report");
			        fileMenu.add(miscMenu_PrintReport);
			        
			        JMenuItem miscMenu_Help = new JMenuItem("Help",KeyEvent.VK_F1);
			        miscMenu_Help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.CTRL_MASK));
			        miscMenu_Help.getAccessibleContext().setAccessibleDescription("Help");
			        miscMenu_Help.addActionListener(new ActionListeners().getHelp(frame));
			        fileMenu.add(miscMenu_Help);
			        
			        JMenuItem miscMenu_About = new JMenuItem("About",KeyEvent.VK_F2);
			        miscMenu_About.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, ActionEvent.CTRL_MASK));
			        miscMenu_About.getAccessibleContext().setAccessibleDescription("About");
			        miscMenu_About.addActionListener(new ActionListeners().about(frame));
			        fileMenu.add(miscMenu_About);
			       	
			        JMenuItem miscMenu_Quit = new JMenuItem("Quit",KeyEvent.VK_Q);
			        miscMenu_Quit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
			        miscMenu_Quit.getAccessibleContext().setAccessibleDescription("Quit");
			        miscMenu_Quit.addActionListener(new ActionListener()
			        {
						@Override
						public void actionPerformed(ActionEvent e)
						{
							frame.setVisible(false);
							frame.dispose();
							System.exit(0);
						}
					});
			        fileMenu.add(miscMenu_Quit);
		
		return totalMainMenu;
	}
	
	 public static void recursivelySetFonts(Component comp, Font font) {
	        comp.setFont(font);
	        if (comp instanceof Container) {
	            Container cont = (Container) comp;
	            for(int j=0, ub=cont.getComponentCount(); j<ub; ++j)
	                recursivelySetFonts(cont.getComponent(j), font);
	        }
	    }
	
	public JPanel mainWindow()
	{
		JPanel main = new JPanel();
		main.setSize(mainWindowDimensions);
		main.setLocation(0,0);
		main.setPreferredSize(mainWindowDimensions);
		main.setBackground(Color.decode(mainWindowBackgroundColor));
		main.setForeground(Color.decode(mainWindowForegroundColor));
		
		main.add(mainMenuBar());
		questionWindowObject = new questionWindow();
		main.add(questionWindowObject);
		return main;
	}
	public static void createGUI() throws FontFormatException, IOException
	{
		agencyFBFont = Font.createFont(Font.TRUETYPE_FONT, new File(MainWindow.class.getClassLoader().getResource("AGENCY_FB.TTF").getFile()));
	    GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(agencyFBFont);
        frame = new JFrame("Forces on a Truss");
		UIManager.put("Menu.font", agencyFBFont.deriveFont(Font.PLAIN, 16));
		UIManager.put("Label.font", agencyFBFont.deriveFont(Font.PLAIN, 16));
		UIManager.put("Label.foreground", Color.decode("#777777"));
		UIManager.put("JPanel.background", Color.decode("#111111"));
		UIManager.put("Button.font", agencyFBFont.deriveFont(Font.PLAIN, 16));
		UIManager.put("Menu.foreground", mainMenuForegroundColor);
		UIManager.put("Menu.selectionBackground", Color.decode("#0074cc"));
		UIManager.put("Menu.selectionForeground", Color.decode("#ffffff"));
		UIManager.put("MenuItem.font", agencyFBFont.deriveFont(Font.PLAIN, 16));
		UIManager.put("MenuItem.background", Color.white);
		UIManager.put("MenuItem.selectionBackground", Color.decode("#0074cc"));
		UIManager.put("Separator.background", Color.decode("#93DB70"));
		UIManager.put("MenuItem.selectionForeground", Color.decode("#ffffff"));
		UIManager.put("ProgressBar.foreground", Color.DARK_GRAY);
		UIManager.put("ProgressBar.background", Color.white);
		
		MainWindow frames = new MainWindow();
		frame.setContentPane(frames.mainWindow());
		frame.setVisible(true);
		frame.setSize(new Dimension(800,400));
		frame.setResizable(false);
		frame.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setAlwaysOnTop(true);
		JFrame.setDefaultLookAndFeelDecorated(false);
		frame.pack();
	}
	
}
