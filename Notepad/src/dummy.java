import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.UndoManager;

import java.io.*;

public class dummy extends JFrame{
	private JMenu file;  
	private JMenu attribute;
	private JMenu edit;
    private JMenuItem saveOption,openOption,newFileOption;  
    private JMenuItem fontColorOption,backgroundOption;
    private JMenuItem cutOption,copyOption,pasteOption,undoOption,redoOption;
	private JTextArea userText;
	private JLabel fileName;
	private Color color = (Color.WHITE);
	private JScrollPane scroll;
	private JToolBar toolbar;
	private JComboBox fontbox,sizebox;
	private JCheckBox BoldBox,ItalicBox,BoldItalicBox;
	
	private String fonts[] = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();

	Border blackline = BorderFactory.createLineBorder(Color.black);
	   private final String[] sizes = new String[]
	            { "8","10","11","12","13","14","16","18","20","22","24","30","36","48","72" };
	
	   private String f ;
	   private int s ;
	   private boolean saveCheck=false;
	
	   UndoManager um;
	   
	   public dummy() {
		super("Dummy notepad");
		userText = new JTextArea();
		userText.setEditable(true);
		add(userText);
		
		scroll = new JScrollPane(userText);
      scroll = new JScrollPane(userText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
      this.add(scroll);
		
		fileName = new JLabel("Untitled");
		fileName.setBorder(blackline);
		add(fileName, BorderLayout.NORTH);
		
		fontbox = new JComboBox(fonts);
		sizebox = new JComboBox(sizes);
		BoldBox = new JCheckBox("Bold");
		ItalicBox = new JCheckBox("Italic");
		BoldItalicBox = new JCheckBox("Bold + Italic");
        JToolBar toolbar = new JToolBar();  
        toolbar.setRollover(true); 
        toolbar.add(fontbox); 
        toolbar.add(sizebox); 
        toolbar.add(BoldBox);
        toolbar.add(ItalicBox);
        toolbar.add(BoldItalicBox);
        add(toolbar, BorderLayout.SOUTH); 
		
        um = new UndoManager();
        
		 JMenuBar mb=new JMenuBar();  
         file=new JMenu("File");
        // file.setMnemonic(KeyEvent.VK_F);
         
         attribute = new JMenu("Attribute");
        // attribute.setMnemonic(KeyEvent.VK_A);

         edit = new JMenu("Edit");
         
         saveOption=new JMenuItem("Save File");
         saveOption.setMnemonic(KeyEvent.VK_S);
         
         openOption = new JMenuItem("Open File");
         openOption.setMnemonic(KeyEvent.VK_O);
         
         newFileOption = new JMenuItem("New File");
         newFileOption.setMnemonic(KeyEvent.VK_N);

         file.add(saveOption);
         file.add(openOption);
         file.add(newFileOption);
         
         fontColorOption = new JMenuItem("Font Color"); 
         fontColorOption.setMnemonic(KeyEvent.VK_F);

         backgroundOption = new JMenuItem("Background Color");
         backgroundOption.setMnemonic(KeyEvent.VK_B);
         
         attribute.add(fontColorOption);
         attribute.add(backgroundOption);
         
         cutOption = new JMenuItem("Cut");
         cutOption.setMnemonic(KeyEvent.VK_X);
         
         copyOption = new JMenuItem("Copy");
         copyOption.setMnemonic(KeyEvent.VK_C);
         
         pasteOption = new JMenuItem("Paste");
         pasteOption.setMnemonic(KeyEvent.VK_V);
         
         undoOption = new JMenuItem("Undo");
         undoOption.setMnemonic(KeyEvent.VK_Z);
         
         redoOption = new JMenuItem("Redo");
         redoOption.setMnemonic(KeyEvent.VK_Y);
         
         edit.add(cutOption);
         edit.add(copyOption);
         edit.add(pasteOption);
         edit.add(undoOption);
         edit.add(redoOption);
             
         mb.add(file); 
         mb.add(attribute);
         mb.add(edit);
         this.setJMenuBar(mb);  
         this.setSize(500,500);  
      
         KeyStroke keyStrokeToNewFile
         = KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK);
         newFileOption.setAccelerator(keyStrokeToNewFile);
         
         KeyStroke keyStrokeToOpen
         = KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK);
         openOption.setAccelerator(keyStrokeToOpen);
         
         KeyStroke keyStrokeToSave
         = KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK);
         saveOption.setAccelerator(keyStrokeToSave);
         
         KeyStroke keyStrokeToFontColor
         = KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK);
         fontColorOption.setAccelerator(keyStrokeToFontColor);
         
         KeyStroke keyStrokeTobackgroundColor
         = KeyStroke.getKeyStroke(KeyEvent.VK_B, KeyEvent.CTRL_DOWN_MASK);
         backgroundOption.setAccelerator(keyStrokeTobackgroundColor);
         
         KeyStroke keyStrokeTocutOption
         = KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK);
         cutOption.setAccelerator(keyStrokeTocutOption);
         
         KeyStroke keyStrokeTocopyOption
         = KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK);
         copyOption.setAccelerator(keyStrokeTocopyOption);
         
         KeyStroke keyStrokeTopasteOption
         = KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK);
         pasteOption.setAccelerator(keyStrokeTopasteOption);
         
         KeyStroke keyStrokeToundoOption
         = KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK);
         undoOption.setAccelerator(keyStrokeToundoOption);
         
         KeyStroke keyStrokeToredoOption
         = KeyStroke.getKeyStroke(KeyEvent.VK_Y, KeyEvent.CTRL_DOWN_MASK);
         redoOption.setAccelerator(keyStrokeToredoOption);
         
         userText.getDocument().addUndoableEditListener(
        		 new UndoableEditListener() {

					public void undoableEditHappened(UndoableEditEvent e) {
						um.addEdit(e.getEdit());
						
					}
        			 
        		 }
        	);
         
         saveOption.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						String filepath="" ;
						if(event.getSource()==saveOption) {
							JFileChooser fc=new JFileChooser(); 
							int i=fc.showSaveDialog(null);
							if(i==JFileChooser.APPROVE_OPTION) {
								File f = fc.getSelectedFile();
								filepath = f.getPath();
								System.out.println(filepath);
								String name = f.getName();
								fileName.setText("");
					        	fileName.setText(name);
								
							}
						}
						
						fileName.setName(filepath);
						saveCheck = true;
						saveFile obj = new saveFile(filepath);
					 	obj.openFile();
					 	obj.addRecords(userText.getText());
					 	obj.closeFile();
				}
			}
		);
         
         
         openOption.addActionListener(
				new ActionListener() {
					public void actionPerformed(ActionEvent event) {
						if(event.getSource()==openOption){    
					
						    JFileChooser fc=new JFileChooser();    
						    int i=fc.showOpenDialog(null);    
						    if(i==JFileChooser.APPROVE_OPTION){    
						        File f=fc.getSelectedFile();    
						        String filepath=f.getPath(); 
						        String name = f.getName();
						      
						        File x = new File(filepath);
						        if(x.exists()) {
							        try{
							        	fileName.setText("");
							        	fileName.setText(name);
							        BufferedReader br=new BufferedReader(new FileReader(filepath));    
							        String s1="",s2="";                         
							        while((s1=br.readLine())!=null){    
							        s2+=s1+"\n";    
							        }    
							        userText.setText(s2);    
							        br.close();    
							        }catch (Exception ex) {
							        	ex.printStackTrace();  }   
						        }
						        else {
						        	JOptionPane.showMessageDialog(null, "File does not exist");
						        }
              
						    }    
						}    
						
					}
				}
		);
         
         
         newFileOption.addActionListener(
 				new ActionListener() {
 					public void actionPerformed(ActionEvent event) {
 						
 						if(event.getSource()==newFileOption) {
 								userText.setText("");
 								fileName.setText("Untitled");
 								saveCheck = false;
 							
 						}
 						
 				}
 			}
 		);
         
         fontColorOption.addActionListener(
 				new ActionListener() {
 					public void actionPerformed(ActionEvent event) {
 						
 						if(event.getSource()==fontColorOption) {
 							
 							color = JColorChooser.showDialog(null, "Pick your color", color);
 							if(color == null) 
 								color = (Color.BLACK);
 								
 							userText.setForeground(color);
 						}
 						
 				}
 			}
 		);
         
         
         backgroundOption.addActionListener(
  				new ActionListener() {
  					public void actionPerformed(ActionEvent event) {
  						
  						if(event.getSource()==backgroundOption) {
  							
  							color = JColorChooser.showDialog(null, "Pick your color", color);
  							if(color == null) 
  								color = (Color.WHITE);
  								
  							userText.setBackground(color);
  						}
  						
  				}
  			}
  		);
         
         
         fontbox.addActionListener(
        		 new ActionListener() {
        			 public void actionPerformed(ActionEvent event) {
        				 //System.out.println(fontbox.getSelectedItem());
        				  f = (String) fontbox.getSelectedItem();
        				 userText.setFont(new Font(f, Font.PLAIN, 14));
        			 }
        		 }
        	);
         
      
         sizebox.addActionListener(
        		 new ActionListener() {
        			 public void actionPerformed(ActionEvent event) {
        				// System.out.println(sizebox.getSelectedItem());
        				  s = (int) sizebox.getSelectedIndex();
        				 userText.setFont(new Font(f, Font.PLAIN, s+10));
 
        			 }
        		 }
        	);
         
         BoldBox.addItemListener(
        	new ItemListener() {
        		public void itemStateChanged(ItemEvent event) {
        			
        				if(BoldBox.isSelected())
        					userText.setFont(new Font(f, Font.BOLD, s+10));
        				
        				if(!BoldBox.isSelected())
        					userText.setFont(new Font(f, Font.PLAIN, s+10));
   	
        		}
        	  }
        	);
         
         ItalicBox.addItemListener(
        	new ItemListener() {
        		public void itemStateChanged(ItemEvent event) {
        			
        				if(ItalicBox.isSelected())
        					userText.setFont(new Font(f, Font.ITALIC, s+10));
        				
        				if(!ItalicBox.isSelected())
        					userText.setFont(new Font(f, Font.PLAIN, s+10));
   	
        		}
        	  }
        	);

         BoldItalicBox.addItemListener(
             	new ItemListener() {
            		public void itemStateChanged(ItemEvent event) {
            			
            				if(BoldItalicBox.isSelected())
            					userText.setFont(new Font(f, Font.BOLD + Font.ITALIC, s+10));
            				
            				if(!BoldItalicBox.isSelected())
            					userText.setFont(new Font(f, Font.PLAIN, s+10));
       	
            		}
            	  }
            	);
         //when x is pressed
         this.addWindowListener(new java.awt.event.WindowAdapter() {
        	    @Override
        	    public void windowClosing(WindowEvent event) {
        	    	String data=userText.getText();
        	    	if(!data.isEmpty() && !saveCheck) {
        	    		saveFile obj = new saveFile(true,userText.getText());
        	    		JOptionPane.showMessageDialog(null, "Since Something was written on the file\n Therefore it was saved on desktop");
        	    	}
        	    		
        	    }
        	});
         
         cutOption.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent ae) {
                userText.cut();
             }
          });
         
         copyOption.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent ae) {
                userText.copy();
             }
          });
         
         pasteOption.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent le) {
                userText.paste();
             }
          });
         
         undoOption.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent le) {
                um.undo();
             }
          });
         
         redoOption.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent le) {
                um.redo();
             }
          });
	}

}
