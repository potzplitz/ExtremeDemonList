package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import data.FetchData;
import data.ManageFiles;

public class LoadingStatus {
    
    private static LoadingStatus instance = null;
    
    private JTextArea area = new JTextArea();
    private JProgressBar bar = new JProgressBar();
    private JFrame main = new JFrame("Updater");
    private JLabel info = new JLabel("Updateroutine wird durchgeführt...");
    private FetchData data = new FetchData();
    
    private LoadingStatus() {
        initialize();
    }
    
    public void initialize() {
        main.setSize(400, 300);
        main.setLayout(null); 
        main.setResizable(false);
        main.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        
        info.setBounds(80, 1, 500, 30);
        
        area.setEditable(false);
        area.setLineWrap(true);
        area.setWrapStyleWord(true);
        area.setCaretPosition(area.getDocument().getLength());
        
        JScrollPane scroll = new JScrollPane(area);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setBounds(1, 60, 383, 201);
        
        
        bar.setBounds(1, 29, 382, 30);
        bar.setMinimum(0);

        bar.setStringPainted(true);
        
        main.add(info);
        main.add(scroll);
        main.add(bar);
        main.setVisible(true);
    }
    
    public static synchronized LoadingStatus getInstance() {
        if (instance == null) {
            instance = new LoadingStatus();
        }
        return instance;
    }
    
    public void update(String level, int barValue) {
        area.append(level + "\n");
        bar.setValue(barValue + 1);
    }
    
    public void dispose() {
        main.dispose();
    }
    
    public void changeState(String state) {
        bar.setMaximum(data.allLevels().size());
        bar.setValue(0);
        info.setText(state);
        area.setText("");
    }
}
