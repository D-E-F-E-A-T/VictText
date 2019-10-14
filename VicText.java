import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.awt.datatransfer.*;

public class VicText extends JFrame implements ActionListener, WindowListener {
	
	JTextArea jta=new JTextArea();
	File fnameContainer;
	
	public VicText(){

		Font fnt=new Font("Arial",Font.PLAIN,15);
		Container con=getContentPane();
		
		JMenuBar jmb=new JMenuBar();
		JMenu jmfile = new JMenu("Menu Global");
		JMenu jmedit = new JMenu("Menu de Edicion");
		JMenu jmhelp = new JMenu("+");
		
		con.setLayout(new BorderLayout());
		//trying to add scrollbar
		JScrollPane sbrText = new JScrollPane(jta);
		sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		sbrText.setVisible(true);
		
		
		jta.setFont(fnt);
		jta.setLineWrap(true);
		jta.setWrapStyleWord(true);
		
		con.add(sbrText);

		createMenuItem(jmfile,"Nuevo");
		createMenuItem(jmfile,"Abrir");
		createMenuItem(jmfile,"Guardar");
		jmfile.addSeparator();
		createMenuItem(jmfile,"Salir");
		
		createMenuItem(jmedit,"Cortar");
		createMenuItem(jmedit,"Copiar");
		createMenuItem(jmedit,"Pegar");
		
		createMenuItem(jmhelp,"Acerca de VicText");
		
		jmb.add(jmfile);
		jmb.add(jmedit);
		jmb.add(jmhelp);
		
		setJMenuBar(jmb);
		setIconImage(Toolkit.getDefaultToolkit().getImage("VicText.gif"));
		addWindowListener(this);
		setSize(500,500);
		setTitle("SinTitulo.txt - VicText");
				
		setVisible(true);
	
	}

	public void createMenuItem(JMenu jm,String txt){
		JMenuItem jmi=new JMenuItem(txt);
		jmi.addActionListener(this);
		jm.add(jmi);
	}
	
	public void actionPerformed(ActionEvent e){	
		JFileChooser jfc=new JFileChooser();
		
		if(e.getActionCommand().equals("New")){ 
			this.setTitle("Untitled.txt - VicText");
			jta.setText("");
			fnameContainer=null;
		}else if(e.getActionCommand().equals("Abrir")){
			int ret=jfc.showDialog(null,"Abrir");
			
			if(ret == JFileChooser.APPROVE_OPTION)
			{
				try{
					File fyl=jfc.getSelectedFile();
					OpenFile(fyl.getAbsolutePath());
					this.setTitle(fyl.getName()+ " - VicText");
					fnameContainer=fyl;
				}catch(IOException ers){}
			}
			
		}else if(e.getActionCommand().equals("Guardar")){
			if(fnameContainer != null){
				jfc.setCurrentDirectory(fnameContainer);		
				jfc.setSelectedFile(fnameContainer);
			}
			else {
				//jfc.setCurrentDirectory(new File("."));
				jfc.setSelectedFile(new File("SinTitulo.txt"));
			}
			
			int ret=jfc.showSaveDialog(null);
				
			if(ret == JFileChooser.APPROVE_OPTION){
				try{
					
					File fyl=jfc.getSelectedFile();
					SaveFile(fyl.getAbsolutePath());
					this.setTitle(fyl.getName()+ " - VicText");
					fnameContainer=fyl;
					
				}catch(Exception ers2){}
			}
			
		}else if(e.getActionCommand().equals("Salir")){
			//exit
			Exiting();
		}else if(e.getActionCommand().equals("Copiar")){
			//copy
			jta.copy();
		}else if(e.getActionCommand().equals("Pegar")){
			//paste
			jta.paste();
		}else if(e.getActionCommand().equals("Acerca de VicText")){ 
			//about
			JOptionPane.showMessageDialog(this,"El projecto VicText es un editor de texto desarrollado en Java, con la finalidad de acreditar la materia de POO impartida en la Universidad Icep por el Docente:          , \nDesarrollado por: Victor Larios (http://www.github.com/victxrlarixs)","VicText",JOptionPane.INFORMATION_MESSAGE);
		}else if(e.getActionCommand().equals("Cortar")){
			jta.cut();
		}
	}
	
	public void OpenFile(String fname) throws IOException {	
		//open file code here
		BufferedReader d=new BufferedReader(new InputStreamReader(new FileInputStream(fname)));
		String l;
		//clear the textbox
		jta.setText("");
	
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
			
		while((l=d.readLine())!= null) {
			jta.setText(jta.getText()+l+"\r\n");
		}
		
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		d.close();
	}
	
	public void SaveFile(String fname) throws IOException {
		setCursor(new Cursor(Cursor.WAIT_CURSOR));
		DataOutputStream o=new DataOutputStream(new FileOutputStream(fname));
		o.writeBytes(jta.getText());
		o.close();		
		setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
	}
	
	public void windowDeactivated(WindowEvent e){}
	public void windowActivated(WindowEvent e){}
	public void windowDeiconified(WindowEvent e){}
	public void windowIconified(WindowEvent e){}
	public void windowClosed(WindowEvent e){}
	
	public void windowClosing(WindowEvent e) {
		Exiting();
	}
	
	public void windowOpened(WindowEvent e){}
	
	public void Exiting() {
		System.exit(0);
	}
	
	public static void main (String[] args) { 
	VicText notp=new VicText();	
	}
			
} 
