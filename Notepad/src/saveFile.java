import java.util.*;
import java.lang.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class saveFile extends JFrame {

	 private Formatter x;
	 private String saveText,pathText;
//save 
public saveFile(String path) {
	pathText = path;
}
	 
	 public void openFile() {
	 try { 
		 x = new Formatter(pathText);
		 System.out.println("You created a file");
	 }catch(Exception e) {
		 System.out.println("crap!");
	 }
  }
 
	 public void addRecords(String text) {
		 saveText = text;
		 x.format("%s", saveText);
	 }
	 
	 public void closeFile() {
		 x.close();
	 }
	 
	 //if user click X button
	 public saveFile(boolean b, String text) {
		 if(b == true) {
			 File path = javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory();
			 pathText = path.getAbsolutePath()+"\\untitled.txt";
			 openFile();
			 addRecords(text);
			 closeFile();
		 }
	 }
	 
	 
}
