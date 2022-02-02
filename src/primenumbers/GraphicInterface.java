/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package primenumbers;

import java.awt.Button;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Label;
import java.awt.TextArea;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 *
 * @author chuck
 */
public class GraphicInterface {
    private final Frame pnl;
    private final TextField single;
    private final TextField mStart;
    private final TextField mEnd;
    private final Button sTest;
    private final Button mTest;
    private final Label sTitle;
    private final Label result;
    private final Label mTitle;
    private final Label start;
    private final Label end;
    private final Label results;
    private final TextArea resultsPane;
    private ActionListener sTestListen;
    private ActionListener mTestListen;
    
    public GraphicInterface() {
        pnl = new Frame("Prime Test");
        pnl.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        sTitle = new Label("Check Single Number:");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 0;
        pnl.add(sTitle, c);
        
        single = new TextField("0", 15);
        single.setEditable(true);
        c.gridwidth = 2;
        c.gridy = 1;
        pnl.add(single, c);

        sTest = new Button("Check");
        c.gridwidth = 1;
        c.gridx = 2;
        pnl.add(sTest, c);
        
        result = new Label("Enter a Number", 1);
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 2;
        pnl.add(result, c);
        
        Label blank = new Label();
        c.gridy = 3;
        pnl.add(blank, c);
        
        mTitle = new Label("Search Number Range:");
        c.gridy = 4;
        pnl.add(mTitle, c);
        
        start = new Label("Start:");
        c.gridwidth = 1;
        c.gridy = 5;
        pnl.add(start, c);
        
        mStart = new TextField("", 15);
        c.gridwidth = 2;
        c.gridx = 1;
        pnl.add(mStart, c);
        
        end = new Label("End:");
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 6;
        pnl.add(end, c);
        
        mEnd = new TextField("", 15);
        c.gridwidth = 2;
        c.gridx = 1;
        pnl.add(mEnd, c);
        
        mTest = new Button("Search");
        c.gridwidth = 3;
        c.gridx = 0;
        c.gridy = 7;
        pnl.add(mTest, c);
        
        results = new Label("Results");
        c.gridy = 8;
        pnl.add(results, c);
        
        resultsPane = new TextArea();
        resultsPane.setEditable(false);
        c.gridy = 9;
        pnl.add(resultsPane, c);
        
        initializeListeners();
    }
    
    private void setSize(int w, int h){
        pnl.setSize(w,h);
    }
   
    private void setVisible(){
        pnl.setVisible(true);
    }    
    
    private void initializeListeners(){       
        sTestListen = (ActionEvent evt) -> {
            try {
                if (PrimeNumbers.primeTest(Long.parseLong(single.getText())))
                    result.setText("This number is prime!");
                else result.setText("This number is NOT prime");
            } catch (NumberFormatException e){
                result.setText("INVALID ENTRY");
            }            
        };
        sTest.addActionListener(sTestListen);

        mTestListen = (ActionEvent evt) -> {
            resultsPane.setText("");
            long st = 0;
            long ed = 0;
            boolean valid = false;
            while(!valid){
                try {    
                    st = Long.parseLong(mStart.getText());
                    valid = true;
                } catch (NumberFormatException e){
                    mStart.setText("INVALID ENTRY");
                    return;
                }
            }
            valid = false;
            while(!valid){
                try {    
                    ed = Long.parseLong(mEnd.getText());
                    valid = true;
                } catch (NumberFormatException e){
                    mEnd.setText("INVALID ENTRY");
                    return;
                } 
            }
            PrimeNumbers run = new PrimeNumbers(st, ed);
            PrintStream tb = new PrintStream(new OutputStream() {
                @Override
                    public void write(int b) throws IOException {
                        resultsPane.append(""+(char)(b & 0xFF));
                    }
            });
            System.setOut(tb);
            run.primeSearch();
            System.setOut(System.out);
        };
        mTest.addActionListener(mTestListen);

        pnl.addWindowListener(new MyWindowListener());
    }
    
    public static void main(String[] args) {
        GraphicInterface window = new GraphicInterface();
        System.out.println(Thread.activeCount());
        window.setSize(700,500);
        window.setVisible();
    }
    
    private class MyWindowListener implements WindowListener {
        @Override
        public void windowClosing(WindowEvent evt) {
            System.exit(0);
        }
        
        @Override public void windowOpened(WindowEvent evt) { }
        @Override public void windowClosed(WindowEvent evt) { }
        @Override public void windowIconified(WindowEvent evt) { System.out.println("Window Iconified"); }
        @Override public void windowDeiconified(WindowEvent evt) { System.out.println("Window Deiconified"); }
        @Override public void windowActivated(WindowEvent evt) { System.out.println("Window Activated"); }
        @Override public void windowDeactivated(WindowEvent evt) { System.out.println("Window Deactivated"); }
    }
}

