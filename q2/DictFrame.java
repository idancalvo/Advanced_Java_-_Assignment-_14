/**Dictionary GUI
*
*@author Idan Calvo
*@version 1.0
*/

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;


public class DictFrame extends JFrame {

	public static final int WIDTH_WINDOW = 415;
	public static final int HEIGHT_WINDOS = 380;

//#Fields
	private JTextField JTterm, JTfileName;
	private JTextArea JTmeaning, JTdicVal;
	private JButton JBadd, JBdelete, JBUpdate, JBsearch, JBimport, JBexport;
	
	private Dictionary dictionary;

//#Constructor
	public DictFrame() {
		this.dictionary = new Dictionary();

		this.setLayout(new BorderLayout());
		JPanel JpCenter = new JPanel();
		handleJpCenter(JpCenter);
		this.add(JpCenter, BorderLayout.CENTER);
		
		
		this.setTitle("My Dictionary");
		this.setSize(WIDTH_WINDOW, HEIGHT_WINDOS);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

//#Methods
	
//-----------------------------Adding components------------------------//
	//
	private void handleJpCenter(JPanel jpCenter) {
		jpCenter.setLayout(new GridLayout(2, 1));

		JPanel JpTop = new JPanel();
		handleJpTop(JpTop);
		jpCenter.add(JpTop);

		JPanel JpMiddle = new JPanel();
		handleJpBottom(JpMiddle);
		jpCenter.add(JpMiddle);

	}

	//Responsible for the top (top1-top4)
	private void handleJpTop(JPanel JpTop) {
		JpTop.setLayout(new GridLayout(4, 1));

		JPanel JPtop1 = new JPanel();
		handleJpTop1(JPtop1);
		JpTop.add(JPtop1);

		JPanel JPtop2 = new JPanel();
		handleJpTop2(JPtop2);
		JpTop.add(JPtop2);

		JPanel JPtop3 = new JPanel();
		handleJpTop3(JPtop3);
		JpTop.add(JPtop3);

		JPanel JPtop4 = new JPanel();
		handleJpTop4(JPtop4);
		JpTop.add(JPtop4);
	}

	//Panel top 1: ["File name" + Import + Export]
	private void handleJpTop1(JPanel JPtop1) {
		JPtop1.setLayout(new GridLayout(1, 2));

		JPanel JPtop1Left = new JPanel();
		handleJPtop1Left(JPtop1Left);
		JPtop1.add(JPtop1Left);

		JPanel JPtop1Right = new JPanel();
		handleJPtop1Right(JPtop1Right);
		JPtop1.add(JPtop1Right);
	}

	//Panel "File name" Text Field
	private void handleJPtop1Right(JPanel jPBottom1) {
		jPBottom1.setLayout(new BorderLayout());

		JLabel JLfileName = new JLabel("File name");
		this.JTfileName = new JTextField();

		jPBottom1.add(JLfileName, BorderLayout.NORTH);
		jPBottom1.add(JTfileName, BorderLayout.CENTER);
	}

	//Panel Import and Export Button
	private void handleJPtop1Left(JPanel jPBottom1) {
		jPBottom1.setLayout(new FlowLayout());

		this.JBimport = new JButton("Import");
		jPBottom1.add(JBimport);
		this.JBimport.addActionListener(new ActionListener() {//Click on import
			@Override
			public void actionPerformed(ActionEvent arg0) {
				importDict();
			}
		});

		this.JBexport = new JButton("Export");
		jPBottom1.add(JBexport);
		this.JBexport.addActionListener(new ActionListener() {//Click on export
			@Override
			public void actionPerformed(ActionEvent arg0) {
				exportDict();
			}
		});
	}

	//Panel Term Label and Term Text Field
	private void handleJpTop2(JPanel JpTop2) {
		JpTop2.setLayout(new BorderLayout());

		JLabel JLterm = new JLabel("Term");
		this.JTterm = new JTextField();

		JpTop2.add(JLterm, BorderLayout.NORTH);
		JpTop2.add(this.JTterm, BorderLayout.CENTER);
	}

	//Panel Meaning Label and Meaning Text Area
	private void handleJpTop3(JPanel JpTop3) {
		JpTop3.setLayout(new BorderLayout());

		JLabel JLterm = new JLabel("Meaning");
		this.JTmeaning = new JTextArea();

		JpTop3.add(JLterm, BorderLayout.NORTH);
		JpTop3.add(this.JTmeaning, BorderLayout.CENTER);
	}

	//Panel Button : Add,Delete,Update,Search
	private void handleJpTop4(JPanel JpTop4) {
		JpTop4.setLayout(new FlowLayout());

		this.JBadd = new JButton("Add");
		this.JBdelete = new JButton("Delete");
		this.JBUpdate = new JButton("Update");
		this.JBsearch = new JButton("Search");

		this.JBadd.addActionListener(new ActionListener() {//Click on Add
			@Override
			public void actionPerformed(ActionEvent arg0) {
				add();
			}
		});
		this.JBdelete.addActionListener(new ActionListener() {//Click on Delete
			@Override
			public void actionPerformed(ActionEvent arg0) {
				delete();
			}
		});
		this.JBUpdate.addActionListener(new ActionListener() {//Click on Update
			@Override
			public void actionPerformed(ActionEvent arg0) {
				update();
			}
		});
		this.JBsearch.addActionListener(new ActionListener() {//Click on Search
			@Override
			public void actionPerformed(ActionEvent arg0) {
				search();
			}
		});

		JpTop4.add(JBadd);
		JpTop4.add(JBdelete);
		JpTop4.add(JBUpdate);
		JpTop4.add(JBsearch);
	}
	
	//Responsible for the Bottom - Text Area With all the values
	private void handleJpBottom(JPanel jpMiddle) {
		jpMiddle.setLayout(new BorderLayout());
		JTdicVal = new JTextArea();
		JScrollPane sp = new JScrollPane(JTdicVal); 
		jpMiddle.add(sp,BorderLayout.CENTER);
	}

//----------------------------------------------------------------------//
	
	//Export the dictionary to a file
	private void exportDict() {
		String title = "export Dictionary";
		String message;
		int messageType;

		String filename = JTfileName.getText();
		if (filename != null && filename.length() > 0) {
			try {
				FileOutputStream fo = new FileOutputStream(filename);
				ObjectOutputStream out = new ObjectOutputStream(fo);
				out.writeObject(this.dictionary);
				out.close();
				fo.close();
				message = "The dictionary exported successfully";
				messageType = JOptionPane.INFORMATION_MESSAGE;
			} catch (IOException e) { //export failed
				message = "dictionary export failed";
				messageType = JOptionPane.ERROR_MESSAGE;

			}
		} else {//filename == null or filename.length() <=0
			message = "Invalid file name"; 
			messageType = JOptionPane.ERROR_MESSAGE;
		}
		popBox(message, title, messageType);
	}

	//import the dictionary from a file
	private void importDict() {
		String title = "import Dictionary";
		String message;
		int messageType;

		String filename = JTfileName.getText();
		if (filename != null && filename.length() > 0) {
			try {
				FileInputStream fo = new FileInputStream(filename);
				ObjectInputStream in = new ObjectInputStream(fo);
				this.dictionary = (Dictionary) (in.readObject());
				in.close();
				fo.close();
				message = "The dictionary imported successfully";
				messageType = JOptionPane.INFORMATION_MESSAGE;
				Refresh();
			} catch (IOException e) {//imported failed
				message = "dictionary import failed";
				messageType = JOptionPane.ERROR_MESSAGE;
			} catch (ClassNotFoundException e) {//failed
				message = "This file is not a dictionary";
				messageType = JOptionPane.ERROR_MESSAGE;
			}
		} else {//filename == null or filename.length() <=0
			message = "Invalid file name";
			messageType = JOptionPane.ERROR_MESSAGE;
		}
		popBox(message, title, messageType);
	}

	//add value
	private void add() {
		String title = "Adding a new value to the Dictionary";
		String message;
		int messageType;

		String term = this.JTterm.getText();
		String meaning = this.JTmeaning.getText();

		if (term.length() > 0 && meaning.length() > 0) {
			int ans = this.dictionary.addVal(term, meaning);

			if (ans == Dictionary.SUCCESS) {
				this.JTterm.setText("");
				this.JTmeaning.setText("");
				message = "Value was added successfully";
				messageType = JOptionPane.INFORMATION_MESSAGE;
				Refresh();

			} else if (ans == Dictionary.FAILD_EXISTS) {
				message = "Adding failed: The term already exists";
				messageType = JOptionPane.ERROR_MESSAGE;

			} else {// Dictionary.FAILD_INVALID
				message = "Adding failed: Invalid values";
				messageType = JOptionPane.ERROR_MESSAGE;
			}

		} else {//term.length <=0 or meaning.length() <= 0
			message = "Invalid term or meaning ";
			messageType = JOptionPane.ERROR_MESSAGE;
		}
		popBox(message, title, messageType);

	}

	//update value
	private void update() {
		String title = "Updating value in the Dictionary";
		String message;
		int messageType;

		String term = this.JTterm.getText();
		String meaning = this.JTmeaning.getText();

		if (term.length() > 0 && meaning.length() > 0) {

			int ans = this.dictionary.updateVal(term, meaning);
			if (ans == Dictionary.SUCCESS) {
				this.JTterm.setText("");
				this.JTmeaning.setText("");
				message = "Value was updated successfully";
				messageType = JOptionPane.INFORMATION_MESSAGE;
				Refresh();

			} else if (ans == Dictionary.FAILD_EXISTS) {
				message = "Updating failed: The term doesn't exist";
				messageType = JOptionPane.ERROR_MESSAGE;

			} else {// Dictionary.FAILD_INVALID
				message = "Updating failed: Invalid values";
				messageType = JOptionPane.ERROR_MESSAGE;
			}
		} else {//term.length <=0 or meaning.length() <= 0
			message = "Invalid term or meaning";
			messageType = JOptionPane.ERROR_MESSAGE;
		}
		popBox(message, title, messageType);
	}

	//delete value
	private void delete() {
		String title = "Deleting value from the Dictionary";
		String message;
		int messageType;

		String term = this.JTterm.getText();

		if (term.length() > 0) {
			int ans = this.dictionary.delVal(term);
			if (ans == Dictionary.SUCCESS) {
				this.JTterm.setText("");
				this.JTmeaning.setText("");				
				message = "Value was Deleted successfully";
				messageType = JOptionPane.INFORMATION_MESSAGE;
				Refresh();

			} else if (ans == Dictionary.FAILD_EXISTS) {
				message = "Deletion failed: The term doesn't exist";
				messageType = JOptionPane.ERROR_MESSAGE;

			} else {// Dictionary.FAILD_INVALID
				message = "Deletion failed: Invalid values";
				messageType = JOptionPane.ERROR_MESSAGE;
			}

		} else {//term.length <=0
			message = "Invalid term";
			messageType = JOptionPane.ERROR_MESSAGE;
		}
		popBox(message, title, messageType);
	}

	//search value
	private void search() {
		String title = "searching value in the Dictionary";
		String message;
		int messageType;

		String term = this.JTterm.getText();

		if (term.length() > 0) {
			String ans = this.dictionary.Search(term);
			if (ans != null) {
				this.JTmeaning.setText(ans);
				message = "The term was found";
				messageType = JOptionPane.INFORMATION_MESSAGE;
			} else {
				message = "The term wasn't found";
				messageType = JOptionPane.ERROR_MESSAGE;
			}
		} else {//term.length <=0
			message = "Invalid term";
			messageType = JOptionPane.ERROR_MESSAGE;
		}
		popBox(message, title, messageType);
	}

	//Refresh
	private void Refresh() {
		JTdicVal.setText(this.dictionary.toString());
	}
	
	//"popBox" is short name for "JOptionPane.showMessageDialog"
	private void popBox(String message, String title, int messageType) {
		JOptionPane.showMessageDialog(null, message, title, messageType);
	}

//----------------------------------------------------------------------//
}


/**					|------------------------------DictFrame----------------------------------------|
*					|																				|
*					|	|----------------------------JpCenter-----------------------------------|	|
*					|	|																		|	|
*					|	|	|------------------------JpTop----------------------------------|	|	|
*					|	|	|																|	|	|
*					|	|	|	|--------------------JPtop1-----------------------------|	|	|	|
*					|	|	|	|		JPtop1Left		||			JPtop1Right			|	|	|	|
*					|	|	|	|														|	|	|	|
*					|	|	|	|--------------------JPtop2-----------------------------|	|	|	|
*					|	|	|	|														|	|	|	|
*					|	|	|	|														|	|	|	|
*					|	|	|	|--------------------JPtop3-----------------------------|	|	|	|
*					|	|	|	|														|	|	|	|
*					|	|	|	|														|	|	|	|
*					|	|	|	|--------------------JPtop4-----------------------------|	|	|	|
*					|	|	|	|														|	|	|	|
*					|	|	|	|														|	|	|	|
*					|	|	|	|-------------------------------------------------------|	|	|	|
*					|	|	|																|	|	|
*					|	|	|---------------------------------------------------------------|	|	|
*					|	|																		|	|
*					|	|	|-------------------------jpBottom------------------------------|	|	|
*					|	|	|																|	|	|
*					|	|	|																|	|	|
*					|	|	|																|	|	|
*					|	|	|																|	|	|
*					|	|	|																|	|	|
*					|	|	|																|	|	|
*					|	|	|																|	|	|
*					|	|	|																|	|	|
*					|	|	|---------------------------------------------------------------|	|	|
*					|	|																		|	|
*					|	|-----------------------------------------------------------------------|	|
*					|																				|
*					|-------------------------------------------------------------------------------|
**/