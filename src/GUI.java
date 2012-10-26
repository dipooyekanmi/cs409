import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JToolBar;


public class GUI extends JPanel{
	JButton open = null;
	JButton close = null;
	static JFrame frame;
	JToolBar toolBar;
	JScrollPane scrollPane;
	JTextArea log;
	LongParameter lp = new LongParameter("test");
	switchStatements ss = new switchStatements();
	dataClass dc = new dataClass();
	
	
	public GUI(){
		
		
		toolBar = new JToolBar();
		toolBar.setFloatable(false);
		addButtons(toolBar);
		
		log = new JTextArea(5,20);
        log.setMargin(new Insets(5,5,5,5));
        log.setEditable(false);
        log.append("log ");
        JScrollPane logScrollPane = new JScrollPane(log);
        
		//scrollPane = new JScrollPane();
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setPreferredSize(new Dimension(510, 530));
		add(toolBar);
		add(logScrollPane, BorderLayout.CENTER);
		
		String[] columnNames = {"Bad Smell", "Score"};
	
	}
	private void createTable(String file){
		
		String[] columnNames = {"Bad Smell", "Score"};
		
		Object [][] data = {{"Long Method",new String(longMethod(file))}, {"Large Class", new Integer(largeClass(file))}, {"Long Parameter List", new String(longParList(file))}, {"Message Chains", new Integer(messChains(file))}, {"Switch Statements", new Integer(ss.calc(file))}, {"Temporary Fields", new Integer(tempField(file))}, {"Data Class", new Integer(dc.calc(file))}};
		final JTable table = new JTable(data,columnNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 150));
        table.setFillsViewportHeight(true);
        //table.setValueAt(data, 2, 2);
        scrollPane = new JScrollPane(table);
        add(scrollPane);
        frame.pack();
        validate();
	}

	private int tempField(String file) {
		// TODO Auto-generated method stub
		return 2;
	}

	private int messChains(String file) {
		// TODO Auto-generated method stub
		return 4;
	}

	private int largeClass(String file) {
		// TODO Auto-generated method stub
		return 8;
	}

	private String longParList(String file) {
		// TODO Auto-generated method stub
		LongParameter lp = new LongParameter(file);
		if(lp.getResult()){
		return "Long Parameters!!";
		}
		else return "System OK";
	}

	private String longMethod(String file) {
		// TODO Auto-generated method stub
		return "yes";
	}

	private void addButtons(JToolBar toolBar) {
		// TODO Auto-generated method stub
		
		final JFileChooser load = new JFileChooser();
		open = new JButton("Open");
		open.setToolTipText("Open a  file to anaylse");
		open.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				
			load.showOpenDialog(GUI.this);
			
			File file = load.getSelectedFile();
			String output;
			try {
				output = FileReader(file);
				createTable(output);
				log.append("" + file + " " + output);
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			
			}
		});
		close = new JButton("Close");
		close.setToolTipText("Close the program");
		close.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		toolBar.add(open);
		toolBar.add(close);
		
	}

	protected String FileReader(File file) throws FileNotFoundException {
				
		String result = null;
        DataInputStream in = null;

        try {
            byte[] buffer = new byte[(int) file.length()];
            in = new DataInputStream(new FileInputStream(file));
            in.readFully(buffer);
            result = new String(buffer);
        } catch (IOException e) {
            throw new RuntimeException("IO problem in fileToString", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) { 
            }
        }
        return result;
	}

	public static void main(String args[]){
		frame = new JFrame("FileChooserDemo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setPreferredSize(new Dimension(550, 250));
		frame.add(new GUI());
        frame.pack();
		frame.setVisible(true);
	}
	

}
