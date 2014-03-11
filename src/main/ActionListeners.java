package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class ActionListeners
{

	static int mouseX = MouseInfo.getPointerInfo().getLocation().x ;
	static int mouseY = MouseInfo.getPointerInfo().getLocation().y ;
	 
	//*************************  ADD  *********************************************//
	public ActionListener addNewKeypoint(final JFrame frame, final List<String> coords)
	{
		 
		ActionListener addNewKeypoint = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final JDialog dialog = new JDialog(frame,"Add New Keypoints",false);
				dialog.setUndecorated(true);
				dialog.setBackground(new Color(0,0,0,90));
				
				JLabel label = new JLabel("<html><p style='margin-top:5px;margin-left:5px;'>Add New Keypoints</p></html>");
				label.setHorizontalAlignment(JLabel.LEFT);
				label.setFont(label.getFont().deriveFont(Font.PLAIN,18));
				label.setForeground(Color.decode("#ffffff"));
				
				JPanel mainPanel = new JPanel();
				mainPanel.setMinimumSize(new Dimension(340,110));
				mainPanel.setPreferredSize(new Dimension(340,110));
				mainPanel.setSize(new Dimension(340,110));
				mainPanel.setBackground(Color.decode("#111111"));
				
					JPanel frameName = new JPanel();
					frameName.setPreferredSize(new Dimension(300,40));
					frameName.setBackground(Color.decode("#111111"));
						JLabel keypointNameLabel = new JLabel("Name                    ");
						keypointNameLabel.setHorizontalAlignment(JLabel.LEFT);
						frameName.add(keypointNameLabel, BorderLayout.WEST);
						
						final JTextPane keypointNameText = new JTextPane();
						keypointNameText.setPreferredSize(new Dimension(150,25));
						keypointNameText.setMargin(new Insets(5,5,5,5));
						frameName.add(keypointNameText , BorderLayout.WEST);
					mainPanel.add(frameName);
					
					JPanel frameX = new JPanel();
					frameX.setPreferredSize(new Dimension(300,40));
					frameX.setBackground(Color.decode("#111111"));
						JLabel keypointXLabel = new JLabel("X                         ");
						keypointXLabel.setHorizontalAlignment(JLabel.LEFT);
						frameX.add(keypointXLabel, BorderLayout.SOUTH);
						
						final JTextPane keypointXText = new JTextPane();
						keypointXText.setPreferredSize(new Dimension(150,25));
						keypointXText.setMargin(new Insets(5,5,5,5));
						frameX.add(keypointXText , BorderLayout.WEST);
					mainPanel.add(frameX);
						
						
					JPanel frameY = new JPanel();
					frameY.setBackground(Color.decode("#111111"));
					frameY.setPreferredSize(new Dimension(300,40));
						JLabel keypointYLabel = new JLabel("Y                        ");
						keypointYLabel.setHorizontalAlignment(JLabel.LEFT);
						frameY.add(keypointYLabel, BorderLayout.SOUTH);
						
						final JTextPane keypointYText = new JTextPane();
						keypointYText.setPreferredSize(new Dimension(150,25));
						keypointYText.setMargin(new Insets(5,5,5,5));
						frameY.add(keypointYText , BorderLayout.WEST);
					mainPanel.add(frameY);
				
				 JButton closeButton = new JButton("   Close   ");
				 closeButton.setBackground(Color.decode("#ffffff"));
				 closeButton.setFocusPainted(false);
				 closeButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				         dialog.setVisible(false);
				         dialog.dispose();
				     }
				 });
				 
				 JButton applyButton = new JButton("   Apply   ");
				 applyButton.setBackground(Color.decode("#ffffff"));
				 applyButton.setFocusPainted(false);
				 applyButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				    	 String pointName = keypointNameText.getText().toString();
				    	 String pointX = keypointXText.getText().toString();
				    	 String pointY = keypointYText.getText().toString();
				    	 
				    	 if(pointName.matches(""))
				    		 JOptionPane.showMessageDialog(frame, "Point name empty", "Error", JOptionPane.ERROR_MESSAGE);
				    	 else
				    	 if(pointX.matches(""))
					    	JOptionPane.showMessageDialog(frame, "X coordinate empty", "Error", JOptionPane.ERROR_MESSAGE);	 
				    	 else
					     if(pointY.matches(""))
						    JOptionPane.showMessageDialog(frame, "Y coordinate empty", "Error", JOptionPane.ERROR_MESSAGE);	 
					     else
					     if(pointName.substring(0,1).matches("\\d"))
					     {
					    	 JOptionPane.showMessageDialog(frame, "Point name should start with alphabet", "Error", JOptionPane.ERROR_MESSAGE);
					     }
					     else
					     if(checkNumberFormat(pointX,"X") && checkNumberFormat(pointY,"Y"))
				    	 {	 
					    	 coords.add(pointName+","+(Integer.parseInt(pointX)+15)+","+(350-Integer.parseInt(pointY)));
					    	 System.out.println("Name: "+pointName+", X: "+(Float.parseFloat(pointX)+15)+", Y: "+(350-Float.parseFloat(pointY)));
					         keypointNameText.setText("");keypointXText.setText("");keypointYText.setText("");
					         	//MainWindow.questionWindowObject.addNewKeypoint(pointName,  Integer.parseInt(pointX), Integer.parseInt(pointY));
								MainWindow.questionWindowObject.repaint();
				    	 }
				     }
				     public boolean checkNumberFormat(String pointX, String name)
				     {
				    	 try {@SuppressWarnings("unused")
						Float pointXnum = Float.parseFloat(pointX);return true;}
				    	 catch(NumberFormatException e1) {JOptionPane.showMessageDialog(frame, "Point "+name+" appears to be invalid integer", "Error", JOptionPane.ERROR_MESSAGE);return false;} 	 
				     }
				 });
				
				 JPanel emptyFrame = new JPanel();
				 emptyFrame.setSize(new Dimension(5,10));
				 emptyFrame.setMaximumSize(new Dimension(5,10));
				 emptyFrame.setMinimumSize(new Dimension(5,10));
				 emptyFrame.setPreferredSize(new Dimension(5,10));
				 emptyFrame.setBackground(new Color(0,0,0,0));
				 
				 JPanel closePanel = new JPanel();
				 closePanel.setLayout(new BoxLayout(closePanel,BoxLayout.LINE_AXIS));
				 closePanel.setBackground(new Color(0,0,0,0));
				 closePanel.add(Box.createHorizontalGlue());
				 closePanel.add(applyButton);
				 closePanel.add(emptyFrame);
				 closePanel.add(closeButton);
				 closePanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
				
				 JPanel contentPane = new JPanel(new BorderLayout());
				 contentPane.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.decode("#424242")));
				 contentPane.setBackground(new Color(0,0,0,0));
				 contentPane.add(label, BorderLayout.PAGE_START);
				 contentPane.add(mainPanel, BorderLayout.WEST);
				 contentPane.add(closePanel, BorderLayout.PAGE_END);
				 contentPane.setOpaque(true);
				 
				
				 contentPane.addMouseListener(new MouseListener()
					{
						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {
							mouseX = e.getX();
							mouseY = e.getY();
						}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {}

						@Override
						public void mouseExited(MouseEvent e) {}
												
					});
				 
				 contentPane.addMouseMotionListener(new MouseMotionListener()
					{
						@Override
						public void mouseDragged(MouseEvent e)
						{
							//System.out.print(e.getX());
							dialog.setLocation(e.getXOnScreen()-mouseX, e.getYOnScreen()-mouseY);
						}

						@Override
						public void mouseMoved(MouseEvent e)  {}
						
					});

				 
				 dialog.setContentPane(contentPane);
				
				 //Show it.
				 dialog.setSize(new Dimension(350, 350));
				 dialog.setLocationRelativeTo(frame);
				 dialog.setVisible(true);
			}
		};
		return addNewKeypoint;
		
	}
	
	public ActionListener addNewMember(final JFrame frame, final List<String> coords, final List<String> members)
	{
		ActionListener addNewMember = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final JDialog dialog = new JDialog(frame,"Add New Member",false);
				dialog.setUndecorated(true);
				dialog.setBackground(new Color(0,0,0,90));
				
				JLabel label = new JLabel("<html><p style='margin-top:5px;margin-left:5px;'>Add New Member</p></html>");
				label.setHorizontalAlignment(JLabel.LEFT);
				label.setFont(label.getFont().deriveFont(Font.PLAIN,18));
				label.setForeground(Color.decode("#ffffff"));
				
				JPanel mainPanel = new JPanel();
				mainPanel.setMinimumSize(new Dimension(340,210));
				mainPanel.setPreferredSize(new Dimension(340,210));
				mainPanel.setSize(new Dimension(340,210));
				List<String> pointNames = new ArrayList<String>();
				for(int i=0;i<coords.size();i++)
				{
					pointNames.add(coords.get(i).substring(0,coords.get(i).indexOf(",", 0)).toString());
				}
				JPanel framePoint1 = new JPanel();
				framePoint1.setPreferredSize(new Dimension(300,80));
				framePoint1.setBackground(Color.decode("#111111"));
					JLabel keypoint1Label = new JLabel("Point 1                    ");
					keypoint1Label.setHorizontalAlignment(JLabel.LEFT);
					framePoint1.add(keypoint1Label, BorderLayout.WEST);
					
				final JComboBox keypointNamesDropdown1 = new JComboBox(pointNames.toArray());
					keypointNamesDropdown1.setPreferredSize(new Dimension(150,30));
					framePoint1.add(keypointNamesDropdown1 , BorderLayout.WEST);
				mainPanel.add(framePoint1);
				
				JPanel framePoint2 = new JPanel();
				framePoint2.setPreferredSize(new Dimension(300,80));
				framePoint2.setBackground(Color.decode("#111111"));
					JLabel keypoint2Label = new JLabel("Point 2                    ");
					keypoint2Label.setHorizontalAlignment(JLabel.LEFT);
					framePoint2.add(keypoint2Label, BorderLayout.WEST);
					
					final JComboBox keypointNamesDropdown2 = new JComboBox(pointNames.toArray());
					keypointNamesDropdown2.setPreferredSize(new Dimension(150,30));
					framePoint2.add(keypointNamesDropdown2 , BorderLayout.WEST);
				mainPanel.add(framePoint2);
				if(pointNames.size()==0)
				{
					keypointNamesDropdown1.setEnabled(false);
					keypointNamesDropdown2.setEnabled(false);
				}
				mainPanel.setBackground(Color.decode("#111111"));
				
				 JButton closeButton = new JButton("   Close   ");
				 closeButton.setBackground(Color.decode("#ffffff"));
				 closeButton.setFocusPainted(false);
				 closeButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				         dialog.setVisible(false);
				         dialog.dispose();
				     }
				 });
				 
				 JButton applyButton = new JButton("   Apply   ");
				 applyButton.setBackground(Color.decode("#ffffff"));
				 applyButton.setFocusPainted(false);
				 applyButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				    	 String point1 = keypointNamesDropdown1.getSelectedItem().toString();
				    	 String point2 = keypointNamesDropdown2.getSelectedItem().toString();
				    	 System.out.println(point1+","+point2);
				    	 members.add(point1+","+point2);
				    	 keypointNamesDropdown1.setSelectedIndex(0);
				    	 keypointNamesDropdown2.setSelectedIndex(0);
				    	MainWindow.questionWindowObject.repaint();
				         //dialog.setVisible(false);
				         //dialog.dispose();
				     }
				 });
				
				 JPanel emptyFrame = new JPanel();
				 emptyFrame.setSize(new Dimension(5,10));
				 emptyFrame.setMaximumSize(new Dimension(5,10));
				 emptyFrame.setMinimumSize(new Dimension(5,10));
				 emptyFrame.setPreferredSize(new Dimension(5,10));
				 emptyFrame.setBackground(new Color(0,0,0,0));
				 
				 JPanel closePanel = new JPanel();
				 closePanel.setLayout(new BoxLayout(closePanel,BoxLayout.LINE_AXIS));
				 closePanel.setBackground(new Color(0,0,0,0));
				 closePanel.add(Box.createHorizontalGlue());
				 //closePanel.add(emptyFrame2);
				 closePanel.add(applyButton);
				 closePanel.add(emptyFrame);
				 closePanel.add(closeButton);
				 closePanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
				
				 JPanel contentPane = new JPanel(new BorderLayout());
				 contentPane.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.decode("#424242")));
				 contentPane.setBackground(new Color(0,0,0,0));
				 contentPane.add(label, BorderLayout.PAGE_START);
				 contentPane.add(mainPanel, BorderLayout.WEST);
				 contentPane.add(closePanel, BorderLayout.PAGE_END);
				 contentPane.setOpaque(true);
				 
				
				 contentPane.addMouseListener(new MouseListener()
					{
						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {
							mouseX = e.getX();
							mouseY = e.getY();
						}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {}

						@Override
						public void mouseExited(MouseEvent e) {}
												
					});
				 
				 contentPane.addMouseMotionListener(new MouseMotionListener()
					{
						@Override
						public void mouseDragged(MouseEvent e)
						{
							//System.out.print(e.getX());
							dialog.setLocation(e.getXOnScreen()-mouseX, e.getYOnScreen()-mouseY);
						}

						@Override
						public void mouseMoved(MouseEvent e)  {}
						
					});

				 
				 dialog.setContentPane(contentPane);
				
				 //Show it.
				 dialog.setSize(new Dimension(350, 350));
				 dialog.setLocationRelativeTo(frame);
				 dialog.setVisible(true);
			}
		};
		return addNewMember;
		
	}
	
	public ActionListener defineNewForce(final JFrame frame, final List<String> coords, final List<String> forces)
	{
		ActionListener defineNewForce = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final JDialog dialog = new JDialog(frame,"Define New Force",false);
				dialog.setUndecorated(true);
				dialog.setBackground(new Color(0,0,0,90));
				
				JLabel label = new JLabel("<html><p style='margin-top:5px;margin-left:5px;'>Define New Force</p></html>");
				label.setHorizontalAlignment(JLabel.LEFT);
				label.setFont(label.getFont().deriveFont(Font.PLAIN,18));
				label.setForeground(Color.decode("#ffffff"));
				
				JPanel mainPanel = new JPanel();
				mainPanel.setMinimumSize(new Dimension(340,190));
				mainPanel.setPreferredSize(new Dimension(340,190));
				mainPanel.setSize(new Dimension(340,190));
				mainPanel.setBackground(Color.decode("#111111"));
					List<String> pointNames = new ArrayList<String>();
					for(int i=0;i<coords.size();i++)
					{
						pointNames.add(coords.get(i).substring(0,coords.get(i).indexOf(",", 0)).toString());
					}
					JPanel framePoint1 = new JPanel();
					framePoint1.setPreferredSize(new Dimension(300,50));
					framePoint1.setBackground(Color.decode("#111111"));
					JLabel keypoint1Label = new JLabel("Select Force Point            ");
					keypoint1Label.setHorizontalAlignment(JLabel.LEFT);
					framePoint1.add(keypoint1Label, BorderLayout.WEST);
					
					final JComboBox keypointNameDropdown = new JComboBox(pointNames.toArray());
					keypointNameDropdown.setPreferredSize(new Dimension(150,30));
					framePoint1.add(keypointNameDropdown , BorderLayout.WEST);
				  mainPanel.add(framePoint1);
				  
				  	JPanel frameFX = new JPanel();
					frameFX.setPreferredSize(new Dimension(300,50));
					frameFX.setBackground(Color.decode("#111111"));
						JLabel keypointFXLabel = new JLabel("Force vector: x                ");
						keypointFXLabel.setHorizontalAlignment(JLabel.LEFT);
						frameFX.add(keypointFXLabel, BorderLayout.SOUTH);
						
						final JTextPane keypointFXText = new JTextPane();
						keypointFXText.setPreferredSize(new Dimension(150,25));
						keypointFXText.setMargin(new Insets(5,5,5,5));
						frameFX.add(keypointFXText , BorderLayout.WEST);
				mainPanel.add(frameFX);
						
						
					JPanel frameFY = new JPanel();
					frameFY.setBackground(Color.decode("#111111"));
					frameFY.setPreferredSize(new Dimension(300,40));
						JLabel keypointFYLabel = new JLabel("Force vector: y               ");
						keypointFYLabel.setHorizontalAlignment(JLabel.LEFT);
						frameFY.add(keypointFYLabel, BorderLayout.SOUTH);
						
						final JTextPane keypointFYText = new JTextPane();
						keypointFYText.setPreferredSize(new Dimension(150,25));
						keypointFYText.setMargin(new Insets(5,5,5,5));
						frameFY.add(keypointFYText , BorderLayout.WEST);
						if(pointNames.size()==0)
						{
							keypointNameDropdown.setEnabled(false);
							keypointFXText.setEditable(false);
							keypointFYText.setEditable(false);
						}
				mainPanel.add(frameFY);
					
					
					
				
				
				 JButton closeButton = new JButton("   Close   ");
				 closeButton.setBackground(Color.decode("#ffffff"));
				 closeButton.setFocusPainted(false);
				 closeButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				         dialog.setVisible(false);
				         dialog.dispose();
				     }
				 });
				 
				 JButton applyButton = new JButton("   Apply   ");
				 applyButton.setBackground(Color.decode("#ffffff"));
				 applyButton.setFocusPainted(false);
				 applyButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				    	 String pointName  = keypointNameDropdown.getSelectedItem().toString();
				    	 String Fx  = keypointFXText.getText().toString();
				    	 String Fy  = keypointFYText.getText().toString();
				    	 System.out.println(pointName+","+Fx+","+Fy);
				    	 forces.add(pointName+","+Fx+","+Fy);
				         //dialog.setVisible(false);
				         //dialog.dispose();
				     }
				 });
				
				 JPanel emptyFrame = new JPanel();
				 emptyFrame.setSize(new Dimension(5,10));
				 emptyFrame.setMaximumSize(new Dimension(5,10));
				 emptyFrame.setMinimumSize(new Dimension(5,10));
				 emptyFrame.setPreferredSize(new Dimension(5,10));
				 emptyFrame.setBackground(new Color(0,0,0,0));
				 
				 JPanel closePanel = new JPanel();
				 closePanel.setLayout(new BoxLayout(closePanel,BoxLayout.LINE_AXIS));
				 closePanel.setBackground(new Color(0,0,0,0));
				 closePanel.add(Box.createHorizontalGlue());
				 //closePanel.add(emptyFrame2);
				 closePanel.add(applyButton);
				 closePanel.add(emptyFrame);
				 closePanel.add(closeButton);
				 closePanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
				
				 JPanel contentPane = new JPanel(new BorderLayout());
				 contentPane.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.decode("#424242")));
				 contentPane.setBackground(new Color(0,0,0,0));
				 contentPane.add(label, BorderLayout.PAGE_START);
				 contentPane.add(mainPanel, BorderLayout.WEST);
				 contentPane.add(closePanel, BorderLayout.PAGE_END);
				 contentPane.setOpaque(true);
				 
				
				 contentPane.addMouseListener(new MouseListener()
					{
						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {
							mouseX = e.getX();
							mouseY = e.getY();
						}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {}

						@Override
						public void mouseExited(MouseEvent e) {}
												
					});
				 
				 contentPane.addMouseMotionListener(new MouseMotionListener()
					{
						@Override
						public void mouseDragged(MouseEvent e)
						{
							//System.out.print(e.getX());
							dialog.setLocation(e.getXOnScreen()-mouseX, e.getYOnScreen()-mouseY);
						}

						@Override
						public void mouseMoved(MouseEvent e)  {}
						
					});

				 
				 dialog.setContentPane(contentPane);
				
				 //Show it.
				 dialog.setSize(new Dimension(350, 350));
				 dialog.setLocationRelativeTo(frame);
				 dialog.setVisible(true);
			}
		};
		return defineNewForce;
	}

	
	public ActionListener defineNewJoint(final JFrame frame, final List<String> coords, final List<String> joints)
	{
		ActionListener defineNewJoint = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final JDialog dialog = new JDialog(frame,"Define New Joint",false);
				dialog.setUndecorated(true);
				dialog.setBackground(new Color(0,0,0,90));
				
				JLabel label = new JLabel("<html><p style='margin-top:5px;margin-left:5px;'>Define New Joint</p></html>");
				label.setHorizontalAlignment(JLabel.LEFT);
				label.setFont(label.getFont().deriveFont(Font.PLAIN,18));
				label.setForeground(Color.decode("#ffffff"));
				
				JPanel mainPanel = new JPanel();
				mainPanel.setMinimumSize(new Dimension(340,190));
				mainPanel.setPreferredSize(new Dimension(340,190));
				mainPanel.setSize(new Dimension(340,190));
				mainPanel.setBackground(Color.decode("#111111"));
					List<String> pointNames = new ArrayList<String>();
					for(int i=0;i<coords.size();i++)
					{
						pointNames.add(coords.get(i).substring(0,coords.get(i).indexOf(",", 0)).toString());
					}
					JPanel framePoint1 = new JPanel();
					framePoint1.setPreferredSize(new Dimension(300,50));
					framePoint1.setBackground(Color.decode("#111111"));
					JLabel keypoint1Label = new JLabel("Select Support Point            ");
					keypoint1Label.setHorizontalAlignment(JLabel.LEFT);
					framePoint1.add(keypoint1Label, BorderLayout.WEST);
					
					final JComboBox keypointNameDropdown = new JComboBox(pointNames.toArray());
					keypointNameDropdown.setPreferredSize(new Dimension(150,30));
					framePoint1.add(keypointNameDropdown , BorderLayout.WEST);
				  mainPanel.add(framePoint1);
				  
				  	JPanel frameJointType = new JPanel();
				  	frameJointType.setPreferredSize(new Dimension(300,50));
				  	frameJointType.setBackground(Color.decode("#111111"));
						JLabel keypointFXLabel = new JLabel("Support type:                  ");
						keypointFXLabel.setHorizontalAlignment(JLabel.LEFT);
						frameJointType.add(keypointFXLabel, BorderLayout.SOUTH);
						
						String[] support_types = {"Fixed Support", "Round Support"}; 
     					final JComboBox jointTypesNameDropdown = new JComboBox(support_types);
						jointTypesNameDropdown.setPreferredSize(new Dimension(150,30));
						frameJointType.add(jointTypesNameDropdown , BorderLayout.WEST);
				mainPanel.add(frameJointType);
						
						
						if(pointNames.size()==0)
						{
							keypointNameDropdown.setEnabled(false);
							jointTypesNameDropdown.setEnabled(false);
						}
					
					
					
				
				
				 JButton closeButton = new JButton("   Close   ");
				 closeButton.setBackground(Color.decode("#ffffff"));
				 closeButton.setFocusPainted(false);
				 closeButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				         dialog.setVisible(false);
				         dialog.dispose();
				     }
				 });
				 
				 JButton applyButton = new JButton("   Apply   ");
				 applyButton.setBackground(Color.decode("#ffffff"));
				 applyButton.setFocusPainted(false);
				 applyButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				    	 String pointName  = keypointNameDropdown.getSelectedItem().toString();
				    	 String jointType  = jointTypesNameDropdown.getSelectedItem().toString();
				    	 System.out.println(pointName+","+jointType);
				    	 joints.add(pointName+","+jointType);
				    	 MainWindow.questionWindowObject.repaint();
				         //dialog.setVisible(false);
				         //dialog.dispose();
				     }
				 });
				
				 JPanel emptyFrame = new JPanel();
				 emptyFrame.setSize(new Dimension(5,10));
				 emptyFrame.setMaximumSize(new Dimension(5,10));
				 emptyFrame.setMinimumSize(new Dimension(5,10));
				 emptyFrame.setPreferredSize(new Dimension(5,10));
				 emptyFrame.setBackground(new Color(0,0,0,0));
				 
				 JPanel closePanel = new JPanel();
				 closePanel.setLayout(new BoxLayout(closePanel,BoxLayout.LINE_AXIS));
				 closePanel.setBackground(new Color(0,0,0,0));
				 closePanel.add(Box.createHorizontalGlue());
				 //closePanel.add(emptyFrame2);
				 closePanel.add(applyButton);
				 closePanel.add(emptyFrame);
				 closePanel.add(closeButton);
				 closePanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
				
				 JPanel contentPane = new JPanel(new BorderLayout());
				 contentPane.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.decode("#424242")));
				 contentPane.setBackground(new Color(0,0,0,0));
				 contentPane.add(label, BorderLayout.PAGE_START);
				 contentPane.add(mainPanel, BorderLayout.WEST);
				 contentPane.add(closePanel, BorderLayout.PAGE_END);
				 contentPane.setOpaque(true);
				 
				
				 contentPane.addMouseListener(new MouseListener()
					{
						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {
							mouseX = e.getX();
							mouseY = e.getY();
						}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {}

						@Override
						public void mouseExited(MouseEvent e) {}
												
					});
				 
				 contentPane.addMouseMotionListener(new MouseMotionListener()
					{
						@Override
						public void mouseDragged(MouseEvent e)
						{
							//System.out.print(e.getX());
							dialog.setLocation(e.getXOnScreen()-mouseX, e.getYOnScreen()-mouseY);
						}

						@Override
						public void mouseMoved(MouseEvent e)  {}
						
					});

				 
				 dialog.setContentPane(contentPane);
				
				 //Show it.
				 dialog.setSize(new Dimension(350, 350));
				 dialog.setLocationRelativeTo(frame);
				 dialog.setVisible(true);
			}
		};
		return defineNewJoint;
	}
	//*************************  DELETE  *********************************************//
	public ActionListener deleteNewKeypoint(final JFrame frame, final List<String> coords)
	{
		ActionListener deleteNewKeypoint = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final JDialog dialog = new JDialog(frame,"Delete Keypoints",false);
				dialog.setUndecorated(true);
				dialog.setBackground(new Color(0,0,0,90));
				
				JLabel label = new JLabel("<html><p style='margin-top:5px;margin-left:5px;'>Delete Keypoint</p></html>");
				label.setHorizontalAlignment(JLabel.LEFT);
				label.setFont(label.getFont().deriveFont(Font.PLAIN,18));
				label.setForeground(Color.decode("#ffffff"));
				
				JPanel mainPanel = new JPanel();
				mainPanel.setMinimumSize(new Dimension(340,110));
				mainPanel.setPreferredSize(new Dimension(340,110));
				mainPanel.setSize(new Dimension(340,110));
				mainPanel.setBackground(Color.decode("#111111"));
				
				 JButton closeButton = new JButton("   Close   ");
				 closeButton.setBackground(Color.decode("#ffffff"));
				 closeButton.setFocusPainted(false);
				 closeButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				         dialog.setVisible(false);
				         dialog.dispose();
				     }
				 });
				 
				 JButton applyButton = new JButton("   Apply   ");
				 applyButton.setBackground(Color.decode("#ffffff"));
				 applyButton.setFocusPainted(false);
				 applyButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				         dialog.setVisible(false);
				         dialog.dispose();
				     }
				 });
				
				 JPanel emptyFrame = new JPanel();
				 emptyFrame.setSize(new Dimension(5,10));
				 emptyFrame.setMaximumSize(new Dimension(5,10));
				 emptyFrame.setMinimumSize(new Dimension(5,10));
				 emptyFrame.setPreferredSize(new Dimension(5,10));
				 emptyFrame.setBackground(new Color(0,0,0,0));
				 
				 JPanel closePanel = new JPanel();
				 closePanel.setLayout(new BoxLayout(closePanel,BoxLayout.LINE_AXIS));
				 closePanel.setBackground(new Color(0,0,0,0));
				 closePanel.add(Box.createHorizontalGlue());
				 //closePanel.add(emptyFrame2);
				 closePanel.add(applyButton);
				 closePanel.add(emptyFrame);
				 closePanel.add(closeButton);
				 closePanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
				
				 JPanel contentPane = new JPanel(new BorderLayout());
				 contentPane.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.decode("#424242")));
				 contentPane.setBackground(new Color(0,0,0,0));
				 contentPane.add(label, BorderLayout.PAGE_START);
				 contentPane.add(mainPanel, BorderLayout.WEST);
				 contentPane.add(closePanel, BorderLayout.PAGE_END);
				 contentPane.setOpaque(true);
				 
				
				 contentPane.addMouseListener(new MouseListener()
					{
						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {
							mouseX = e.getX();
							mouseY = e.getY();
						}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {}

						@Override
						public void mouseExited(MouseEvent e) {}
												
					});
				 
				 contentPane.addMouseMotionListener(new MouseMotionListener()
					{
						@Override
						public void mouseDragged(MouseEvent e)
						{
							//System.out.print(e.getX());
							dialog.setLocation(e.getXOnScreen()-mouseX, e.getYOnScreen()-mouseY);
						}

						@Override
						public void mouseMoved(MouseEvent e)  {}
						
					});

				 
				 dialog.setContentPane(contentPane);
				
				 //Show it.
				 dialog.setSize(new Dimension(350, 350));
				 dialog.setLocationRelativeTo(frame);
				 dialog.setVisible(true);
			}
		};
		return deleteNewKeypoint;
		
	}
	
	public ActionListener deleteNewMember(final JFrame frame)
	{
		ActionListener deleteNewMember = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final JDialog dialog = new JDialog(frame,"Delete New Member",false);
				dialog.setUndecorated(true);
				dialog.setBackground(new Color(0,0,0,90));
				
				JLabel label = new JLabel("<html><p style='margin-top:5px;margin-left:5px;'>Delete New Member</p></html>");
				label.setHorizontalAlignment(JLabel.LEFT);
				label.setFont(label.getFont().deriveFont(Font.PLAIN,18));
				label.setForeground(Color.decode("#ffffff"));
				
				JPanel mainPanel = new JPanel();
				mainPanel.setMinimumSize(new Dimension(340,110));
				mainPanel.setPreferredSize(new Dimension(340,110));
				mainPanel.setSize(new Dimension(340,110));
				mainPanel.setBackground(Color.decode("#111111"));
				
				 JButton closeButton = new JButton("   Close   ");
				 closeButton.setBackground(Color.decode("#ffffff"));
				 closeButton.setFocusPainted(false);
				 closeButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				         dialog.setVisible(false);
				         dialog.dispose();
				     }
				 });
				 
				 JButton applyButton = new JButton("   Apply   ");
				 applyButton.setBackground(Color.decode("#ffffff"));
				 applyButton.setFocusPainted(false);
				 applyButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				         dialog.setVisible(false);
				         dialog.dispose();
				     }
				 });
				
				 JPanel emptyFrame = new JPanel();
				 emptyFrame.setSize(new Dimension(5,10));
				 emptyFrame.setMaximumSize(new Dimension(5,10));
				 emptyFrame.setMinimumSize(new Dimension(5,10));
				 emptyFrame.setPreferredSize(new Dimension(5,10));
				 emptyFrame.setBackground(new Color(0,0,0,0));
				 
				 JPanel closePanel = new JPanel();
				 closePanel.setLayout(new BoxLayout(closePanel,BoxLayout.LINE_AXIS));
				 closePanel.setBackground(new Color(0,0,0,0));
				 closePanel.add(Box.createHorizontalGlue());
				 //closePanel.add(emptyFrame2);
				 closePanel.add(applyButton);
				 closePanel.add(emptyFrame);
				 closePanel.add(closeButton);
				 closePanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
				
				 JPanel contentPane = new JPanel(new BorderLayout());
				 contentPane.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.decode("#424242")));
				 contentPane.setBackground(new Color(0,0,0,0));
				 contentPane.add(label, BorderLayout.PAGE_START);
				 contentPane.add(mainPanel, BorderLayout.WEST);
				 contentPane.add(closePanel, BorderLayout.PAGE_END);
				 contentPane.setOpaque(true);
				 
				
				 contentPane.addMouseListener(new MouseListener()
					{
						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {
							mouseX = e.getX();
							mouseY = e.getY();
						}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {}

						@Override
						public void mouseExited(MouseEvent e) {}
												
					});
				 
				 contentPane.addMouseMotionListener(new MouseMotionListener()
					{
						@Override
						public void mouseDragged(MouseEvent e)
						{
							//System.out.print(e.getX());
							dialog.setLocation(e.getXOnScreen()-mouseX, e.getYOnScreen()-mouseY);
						}

						@Override
						public void mouseMoved(MouseEvent e)  {}
						
					});

				 
				 dialog.setContentPane(contentPane);
				
				 //Show it.
				 dialog.setSize(new Dimension(350, 350));
				 dialog.setLocationRelativeTo(frame);
				 dialog.setVisible(true);
			}
		};
		return deleteNewMember;
		
	}

	public ActionListener deleteNewForce(final JFrame frame)
	{
		ActionListener deleteNewForce = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final JDialog dialog = new JDialog(frame,"Delete Force",false);
				dialog.setUndecorated(true);
				dialog.setBackground(new Color(0,0,0,90));
				
				JLabel label = new JLabel("<html><p style='margin-top:5px;margin-left:5px;'>Delete Force</p></html>");
				label.setHorizontalAlignment(JLabel.LEFT);
				label.setFont(label.getFont().deriveFont(Font.PLAIN,18));
				label.setForeground(Color.decode("#ffffff"));
				
				JPanel mainPanel = new JPanel();
				mainPanel.setMinimumSize(new Dimension(340,110));
				mainPanel.setPreferredSize(new Dimension(340,110));
				mainPanel.setSize(new Dimension(340,110));
				mainPanel.setBackground(Color.decode("#111111"));
				
				 JButton closeButton = new JButton("   Close   ");
				 closeButton.setBackground(Color.decode("#ffffff"));
				 closeButton.setFocusPainted(false);
				 closeButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				         dialog.setVisible(false);
				         dialog.dispose();
				     }
				 });
				 
				 JButton applyButton = new JButton("   Apply   ");
				 applyButton.setBackground(Color.decode("#ffffff"));
				 applyButton.setFocusPainted(false);
				 applyButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				         dialog.setVisible(false);
				         dialog.dispose();
				     }
				 });
				
				 JPanel emptyFrame = new JPanel();
				 emptyFrame.setSize(new Dimension(5,10));
				 emptyFrame.setMaximumSize(new Dimension(5,10));
				 emptyFrame.setMinimumSize(new Dimension(5,10));
				 emptyFrame.setPreferredSize(new Dimension(5,10));
				 emptyFrame.setBackground(new Color(0,0,0,0));
				 
				 JPanel closePanel = new JPanel();
				 closePanel.setLayout(new BoxLayout(closePanel,BoxLayout.LINE_AXIS));
				 closePanel.setBackground(new Color(0,0,0,0));
				 closePanel.add(Box.createHorizontalGlue());
				 //closePanel.add(emptyFrame2);
				 closePanel.add(applyButton);
				 closePanel.add(emptyFrame);
				 closePanel.add(closeButton);
				 closePanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
				
				 JPanel contentPane = new JPanel(new BorderLayout());
				 contentPane.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.decode("#424242")));
				 contentPane.setBackground(new Color(0,0,0,0));
				 contentPane.add(label, BorderLayout.PAGE_START);
				 contentPane.add(mainPanel, BorderLayout.WEST);
				 contentPane.add(closePanel, BorderLayout.PAGE_END);
				 contentPane.setOpaque(true);
				 
				
				 contentPane.addMouseListener(new MouseListener()
					{
						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {
							mouseX = e.getX();
							mouseY = e.getY();
						}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {}

						@Override
						public void mouseExited(MouseEvent e) {}
												
					});
				 
				 contentPane.addMouseMotionListener(new MouseMotionListener()
					{
						@Override
						public void mouseDragged(MouseEvent e)
						{
							//System.out.print(e.getX());
							dialog.setLocation(e.getXOnScreen()-mouseX, e.getYOnScreen()-mouseY);
						}

						@Override
						public void mouseMoved(MouseEvent e)  {}
						
					});

				 
				 dialog.setContentPane(contentPane);
				
				 //Show it.
				 dialog.setSize(new Dimension(350, 350));
				 dialog.setLocationRelativeTo(frame);
				 dialog.setVisible(true);
			}
		};
		return deleteNewForce;
	}

	public ActionListener getHelp(final JFrame frame)
	{
		ActionListener getHelp = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final JDialog dialog = new JDialog(frame,"Help",false);
				dialog.setUndecorated(true);
				dialog.setBackground(new Color(0,0,0,90));
				
				JLabel label = new JLabel("<html><p style='margin-top:5px;margin-left:5px;'>Help</p></html>");
				label.setHorizontalAlignment(JLabel.LEFT);
				label.setFont(label.getFont().deriveFont(Font.PLAIN,18));
				label.setForeground(Color.decode("#ffffff"));
				
				
				JPanel mainPanel = new JPanel();
				mainPanel.setMinimumSize(new Dimension(310,410));
				mainPanel.setPreferredSize(new Dimension(310,410));
				mainPanel.setSize(new Dimension(310,410));
				mainPanel.setBackground(Color.decode("#111111"));
				
				JLabel mainContent = new JLabel("<html><p style='margin-top:5px;margin-left:5px;font-size:10px;font-family:arial;padding-top:4px;'>Line#1 informs user about Information#1<br>Line#2 informs user about Information#2<br>Line#3 informs user about Information#3<br>Line#4 informs user about Information#4<br>Line#5 informs user about Information#5<br>Line#6 informs user about Information#6<br>Line#7 informs user about Information#7<br>Line#8 informs user about Information#8<br>Line#9 informs user about Information#9<br>Line#10 informs user about Information#10<br>Line#11 informs user about Information#11<br>Line#12 informs user about Information#12<br>Line#13 informs user about Information#13<br>Line#14 informs user about Information#14<br>Line#15 informs user about Information#15<br>Line#16 informs user about Information#16<br>Line#17 informs user about Information#17<br>Line#18 informs user about Information#18<br>Line#19 informs user about Information#19<br>Line#20 informs user about Information#20<br>Line#21 informs user about Information#21<br></p></html>");
				mainContent.setHorizontalAlignment(JLabel.LEFT);
				mainContent.setFont(label.getFont().deriveFont(Font.PLAIN,18));
				mainContent.setForeground(Color.decode("#ffffff"));
				mainPanel.add(mainContent, BorderLayout.WEST);
				
				JScrollPane helpScrollPanel = new JScrollPane(mainPanel);
				helpScrollPanel.setPreferredSize(new Dimension(340,110));
				helpScrollPanel.setMaximumSize(new Dimension(340,110));
				helpScrollPanel.setBorder(null);
				helpScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				helpScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				helpScrollPanel.setBackground(Color.DARK_GRAY);
				helpScrollPanel.getVerticalScrollBar().setUI(new MyScrollBarUI());
				
				 JButton closeButton = new JButton("   Close   ");
				 closeButton.setBackground(Color.decode("#ffffff"));
				 closeButton.setFocusPainted(false);
				 closeButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				         dialog.setVisible(false);
				         dialog.dispose();
				     }
				 });
				 
				 JPanel closePanel = new JPanel();
				 closePanel.setLayout(new BoxLayout(closePanel,BoxLayout.LINE_AXIS));
				 closePanel.setBackground(new Color(0,0,0,0));
				 closePanel.add(Box.createHorizontalGlue());
				 closePanel.add(closeButton);
				 closePanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
				
				 JPanel contentPane = new JPanel(new BorderLayout());
				 contentPane.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.decode("#424242")));
				 contentPane.setBackground(new Color(0,0,0,0));
				 contentPane.add(label, BorderLayout.PAGE_START);
				 contentPane.add(helpScrollPanel, BorderLayout.WEST);
				 contentPane.add(closePanel, BorderLayout.PAGE_END);
				 contentPane.setOpaque(true);
				 
				
				 contentPane.addMouseListener(new MouseListener()
					{
						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {
							mouseX = e.getX();
							mouseY = e.getY();
						}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {}

						@Override
						public void mouseExited(MouseEvent e) {}
												
					});
				 
				 contentPane.addMouseMotionListener(new MouseMotionListener()
					{
						@Override
						public void mouseDragged(MouseEvent e)
						{
							//System.out.print(e.getX());
							dialog.setLocation(e.getXOnScreen()-mouseX, e.getYOnScreen()-mouseY);
						}

						@Override
						public void mouseMoved(MouseEvent e)  {}
						
					});

				 
				 dialog.setContentPane(contentPane);
				
				 //Show it.
				 dialog.setSize(new Dimension(350, 350));
				 dialog.setLocationRelativeTo(frame);
				 dialog.setVisible(true);
			}
		};
		return getHelp;
	}

	public ActionListener about(final JFrame frame)
	{
		ActionListener about = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final JDialog dialog = new JDialog(frame,"Help",false);
				dialog.setUndecorated(true);
				dialog.setBackground(new Color(0,0,0,90));
				
				JLabel label = new JLabel("<html><p style='margin-top:5px;margin-left:5px;'>About</p></html>");
				label.setHorizontalAlignment(JLabel.LEFT);
				label.setFont(label.getFont().deriveFont(Font.PLAIN,18));
				label.setForeground(Color.decode("#ffffff"));
				
				
				JPanel mainPanel = new JPanel();
				mainPanel.setMinimumSize(new Dimension(310,410));
				mainPanel.setPreferredSize(new Dimension(310,410));
				mainPanel.setSize(new Dimension(310,410));
				mainPanel.setBackground(Color.decode("#111111"));
				
				JLabel mainContent = new JLabel("<html><p style='margin-top:5px;margin-left:5px;font-size:9px;font-family:arial;line-height:10px;'>The project has been created by <i><b> Shashank Jaiswal</b></i><br>The project is still incomplete. <br>So, contributions are welcome.<br> The source code can be found on gitHub. <br> <i>https://github.com/shashank-jaiswal-2013/force-On-Truss</i></p></html>");
				mainContent.setHorizontalAlignment(JLabel.LEFT);
				mainContent.setFont(label.getFont().deriveFont(Font.PLAIN,18));
				mainContent.setForeground(Color.decode("#ffffff"));
				mainPanel.add(mainContent, BorderLayout.WEST);
				
				JScrollPane helpScrollPanel = new JScrollPane(mainPanel);
				helpScrollPanel.setPreferredSize(new Dimension(340,110));
				helpScrollPanel.setMaximumSize(new Dimension(340,110));
				helpScrollPanel.setBorder(null);
				helpScrollPanel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
				helpScrollPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				helpScrollPanel.setBackground(Color.DARK_GRAY);
				helpScrollPanel.getVerticalScrollBar().setUI(new MyScrollBarUI());
				
				 JButton closeButton = new JButton("   Close   ");
				 closeButton.setBackground(Color.decode("#ffffff"));
				 closeButton.setFocusPainted(false);
				 closeButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				         dialog.setVisible(false);
				         dialog.dispose();
				     }
				 });
				 
				 JPanel closePanel = new JPanel();
				 closePanel.setLayout(new BoxLayout(closePanel,BoxLayout.LINE_AXIS));
				 closePanel.setBackground(new Color(0,0,0,0));
				 closePanel.add(Box.createHorizontalGlue());
				 closePanel.add(closeButton);
				 closePanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
				
				 JPanel contentPane = new JPanel(new BorderLayout());
				 contentPane.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.decode("#424242")));
				 contentPane.setBackground(new Color(0,0,0,0));
				 contentPane.add(label, BorderLayout.PAGE_START);
				 contentPane.add(helpScrollPanel, BorderLayout.WEST);
				 contentPane.add(closePanel, BorderLayout.PAGE_END);
				 contentPane.setOpaque(true);
				 
				
				 contentPane.addMouseListener(new MouseListener()
					{
						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {
							mouseX = e.getX();
							mouseY = e.getY();
						}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {}

						@Override
						public void mouseExited(MouseEvent e) {}
												
					});
				 
				 contentPane.addMouseMotionListener(new MouseMotionListener()
					{
						@Override
						public void mouseDragged(MouseEvent e)
						{
							//System.out.print(e.getX());
							dialog.setLocation(e.getXOnScreen()-mouseX, e.getYOnScreen()-mouseY);
						}

						@Override
						public void mouseMoved(MouseEvent e)  {}
						
					});

				 
				 dialog.setContentPane(contentPane);
				
				 //Show it.
				 dialog.setSize(new Dimension(350, 250));
				 dialog.setLocationRelativeTo(frame);
				 dialog.setVisible(true);
			}
		};
		return about;
	}

	public ActionListener solve(final JFrame frame, final List<String> coords, final List<String> members, final List<String> forces, final List<String> joints)
	{
		ActionListener solve = new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				final JDialog dialog = new JDialog(frame,"Help",false);
				dialog.setUndecorated(true);
				dialog.setBackground(new Color(0,0,0,90));
				
				JLabel label = new JLabel("<html><p style='margin-top:5px;margin-left:5px;'>Solve</p></html>");
				label.setHorizontalAlignment(JLabel.LEFT);
				label.setFont(label.getFont().deriveFont(Font.PLAIN,18));
				label.setForeground(Color.decode("#ffffff"));
				
				
				final JPanel mainPanel = new JPanel();
				mainPanel.setMinimumSize(new Dimension(340,210));
				mainPanel.setPreferredSize(new Dimension(340,210));
				mainPanel.setSize(new Dimension(340,210));
				mainPanel.setBackground(Color.decode("#111111"));
				
				String labelText = "<html><p style='margin-top:0px;margin-left:5px;font-size:10px;font-family:arial;line-height:10px;float:left;'><i>Keypoints: </i>"+coords.size()+"<br><i>Members: </i>"+members.size()+"<br><i>Forces: </i>"+forces.size()+"<br><i>Supports: </i>"+joints.size()+"</p></html>";
				final JLabel mainContent = new JLabel(labelText);
				mainContent.setHorizontalAlignment(JLabel.LEFT);
				mainContent.setFont(label.getFont().deriveFont(Font.PLAIN,18));
				mainContent.setForeground(Color.decode("#ffffff"));
				mainPanel.add(mainContent, BorderLayout.WEST);
				
				
				 JButton closeButton = new JButton("   Close   ");
				 closeButton.setBackground(Color.decode("#ffffff"));
				 closeButton.setFocusPainted(false);
				 closeButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				         dialog.setVisible(false);
				         dialog.dispose();
				     }
				 });
				 
				 final JButton applyButton = new JButton("   Solve   ");
				 applyButton.setBackground(Color.decode("#ffffff"));
				 applyButton.setFocusPainted(false);
				 applyButton.addActionListener(new ActionListener()
				 {
				     public void actionPerformed(ActionEvent e)
				     {
				         //dialog.setVisible(false);
				         //dialog.dispose();
				    	 JProgressBar progressBar = new JProgressBar();
				    	 progressBar.setMinimum(0);
				    	 progressBar.setMaximum(100);
				    	 progressBar.setPreferredSize(new Dimension(300,60));
				    	 applyButton.setEnabled(false);
				    	 mainPanel.removeAll();
				    	 mainPanel.add(progressBar);
				    	 mainPanel.revalidate();
				    	 mainPanel.validate();
				    	 mainPanel.repaint();
				    	 
				    	new Solver(frame, dialog, progressBar, coords, members, forces, joints).solve();
				     }
				 });
				
				 JPanel emptyFrame = new JPanel();
				 emptyFrame.setSize(new Dimension(5,10));
				 emptyFrame.setMaximumSize(new Dimension(5,10));
				 emptyFrame.setMinimumSize(new Dimension(5,10));
				 emptyFrame.setPreferredSize(new Dimension(5,10));
				 emptyFrame.setBackground(new Color(0,0,0,0));
				 
				 JPanel closePanel = new JPanel();
				 closePanel.setLayout(new BoxLayout(closePanel,BoxLayout.LINE_AXIS));
				 closePanel.setBackground(new Color(0,0,0,0));
				 closePanel.add(Box.createHorizontalGlue());
				 closePanel.add(applyButton);
				 closePanel.add(emptyFrame);
				 closePanel.add(closeButton);
				 closePanel.setBorder(BorderFactory.createEmptyBorder(5,0,5,5));
				
				 JPanel contentPane = new JPanel(new BorderLayout());
				 contentPane.setBorder(BorderFactory.createMatteBorder(5,5,5,5, Color.decode("#424242")));
				 contentPane.setBackground(new Color(0,0,0,0));
				 contentPane.add(label, BorderLayout.PAGE_START);
				 contentPane.add(mainPanel, BorderLayout.WEST);
				 contentPane.add(closePanel, BorderLayout.PAGE_END);
				 contentPane.setOpaque(true);
				
				
				 contentPane.addMouseListener(new MouseListener()
					{
						@Override
						public void mouseClicked(MouseEvent e) {}

						@Override
						public void mousePressed(MouseEvent e) {
							mouseX = e.getX();
							mouseY = e.getY();
						}

						@Override
						public void mouseReleased(MouseEvent e) {}

						@Override
						public void mouseEntered(MouseEvent e) {}

						@Override
						public void mouseExited(MouseEvent e) {}
												
					});
				 
				 contentPane.addMouseMotionListener(new MouseMotionListener()
					{
						@Override
						public void mouseDragged(MouseEvent e)
						{
							//System.out.print(e.getX());
							dialog.setLocation(e.getXOnScreen()-mouseX, e.getYOnScreen()-mouseY);
						}

						@Override
						public void mouseMoved(MouseEvent e)  {}
						
					});

				 
				 dialog.setContentPane(contentPane);
				
				 //Show it.
				 dialog.setSize(new Dimension(350, 150));
				 dialog.setLocationRelativeTo(frame);
				 dialog.setVisible(true);
			}
		};
		return solve;
	}

}
