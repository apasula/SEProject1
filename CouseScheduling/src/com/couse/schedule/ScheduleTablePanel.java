package com.couse.schedule;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import com.course.schedule.db.SectionDB;
import com.couse.schedule.model.Schedule;

public class ScheduleTablePanel extends JPanel {

	private static final DefaultTableModel tableModel = new DefaultTableModel();
	private static JTable jtable;

	public ScheduleTablePanel() {

		init();
	}

	public void print() {

		try {
			JTable table = jtable;
			Document doc = new Document();
			PdfWriter.getInstance(doc, new FileOutputStream("table.pdf"));
			doc.open();
			PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
			for (int i = 0; i < table.getColumnCount(); i++) {
				pdfTable.addCell(table.getColumnName(i));
			}
			for (int rows = 0; rows < table.getRowCount() - 1; rows++) {
				for (int cols = 0; cols < table.getColumnCount(); cols++) {
					pdfTable.addCell(table.getModel().getValueAt(rows, cols)
							.toString());

				}
			}
			doc.add(pdfTable);
			doc.close();
        	JOptionPane.showMessageDialog(ScheduleTablePanel.this, "File download to"+System.getProperty("user.dir"));

			System.out.println("done");
		} catch (Exception ex) {
			ex.printStackTrace();
		} 
	}

	public static void loadData(final String refresh) {
		new SwingWorker<Void, Void>() {
			@Override
			protected Void doInBackground() throws Exception {
				SectionDB sectionDB = new SectionDB();
				sectionDB.getSchedule(tableModel);
				addTableListener();
				 jtable.removeColumn(jtable.getColumnModel().getColumn(0));
				 
				 
				 if(!refresh.equalsIgnoreCase(""))
					MainFrame.showCard("generateschedulepanel");

				return null;
			}
		}.execute();
	}

	public void init() {

		JPanel rootPanel = new JPanel();
		rootPanel.setPreferredSize(new Dimension(750, 400));

		BoxLayout box = new BoxLayout(rootPanel, BoxLayout.Y_AXIS);
		rootPanel.setLayout(box);
		add(rootPanel);

		jtable = new JTable(tableModel){
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return column == 2 || column == 3  || column==5 ? true : false;
		    }
		};
		
	
		jtable.addKeyListener(new KeyAdapter() {
		        @Override
		        public void keyPressed(KeyEvent e) {
		        	System.out.println(e.getKeyCode());
		            if (e.getKeyCode() == KeyEvent.VK_ENTER) {

		                int row = jtable.getSelectedRow();
		                int column = jtable.getSelectedColumn();

		                String resul = jtable.getValueAt(row, column).toString();
		                String id = jtable.getValueAt(row, 0).toString();

		              }
		        }
		    });
		rootPanel.add(new JScrollPane(jtable));

		loadData("");
		
	}
    private static void addTableListener() {
        tableModel.addTableModelListener(new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent tme) {
                if (tme.getType() == TableModelEvent.UPDATE) {

                    System.out.println("");
                    System.out.println(jtable.getSelectedRow());
                
                    int row = jtable.getSelectedRow();
                    int column = jtable.getSelectedColumn();
                    
                    if(row ==-1 || column ==-1)
                    	return;

                    String resul = jtable.getValueAt(row, column).toString();
                    String id = jtable.getModel().getValueAt(row, 0).toString();


                    System.out.println(id);
                    	Schedule schedule = new Schedule();
                        String meetinginfo = jtable.getValueAt(row, 2).toString();
                        String faculty = jtable.getValueAt(row, 3).toString();
                        String credits = jtable.getValueAt(row, 5).toString();

                        schedule.setMeetinginfo(meetinginfo);
                        schedule.setFaculty(faculty);
                        schedule.setCredits(credits);
                        
                        SectionDB sectionDB = new SectionDB();
                        sectionDB.updateSectionDetails(schedule, id);
                        
                    
                    

                 }
            }
        });
    }

}
