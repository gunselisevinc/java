package com.company;

import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Border;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Menu extends JFrame {


    Language language;

    JMenu file;
    JMenu edit;
    JMenu help;

    JMenuItem cut;
    JMenuItem copy;
    JMenuItem paste;
    JMenuItem selectall;

    JMenuItem neww;
    JMenuItem open;
    JMenuItem save;
    JMenuItem exitt;

    JMenu langu;
    JMenuItem tur;
    JMenuItem eng;
    JMenuItem about;

    public Menu()
    {
        this.setBounds(400,400,400,500);


        language = new Language();
        //JFrame frame = new JFrame();
       // frame.setLayout(null;
        //frame.setBounds(400,400,400,500);

        JMenuBar menubar = new JMenuBar();

        file = new JMenu();
        edit= new JMenu();
        help = new JMenu();

        menubar.add(file);
        menubar.add(edit);
        menubar.add(help);

        cut = new JMenuItem();
        copy = new JMenuItem();
        paste = new JMenuItem();
        selectall = new JMenuItem();

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);

        neww = new JMenuItem();
        open = new JMenuItem();
        save = new JMenuItem();
        exitt = new JMenuItem();

        file.add(neww);
        file.add(open);
        file.add(save);
        file.add(exitt);

        langu = new JMenu();
        tur = new JMenuItem();
        eng = new JMenuItem();
        about = new JMenuItem();

        tur.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChangeLanguage(1);
            }
        });

        eng.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ChangeLanguage(0);
            }
        });


        help.add(langu);
        help.add(about);
        langu.add(tur);
        langu.add(eng);

        JTextArea textarea = new JTextArea();
        textarea.setBounds(5,10,390,420);

        JScrollPane scrollPane = new JScrollPane(textarea);
        scrollPane.setBounds(3,8,394,420);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.getContentPane().setLayout(null);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.add(menubar);

        exitt.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });

        cut.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textarea.cut();
            }
        });

        copy.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textarea.copy();
            }
        });

        paste.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textarea.paste();
            }
        });

        selectall.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textarea.selectAll();
            }
        });

        neww.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                textarea.selectAll();
                textarea.cut();
            }
        });

        this.setJMenuBar(menubar);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setVisible(true);


        JFileChooser jFileChooser = new JFileChooser();
        save.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String output = textarea.getText();
                jFileChooser.showSaveDialog(getContentPane());

                File file = jFileChooser.getSelectedFile();
                String path = file.getAbsolutePath();
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(path));
                    writer.write(output);
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        open.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                jFileChooser.showOpenDialog(getContentPane());
                File file = jFileChooser.getSelectedFile();
                String path = file.getAbsolutePath();
                List<String> fileOutput = readFile(path);
                for(String item:fileOutput)
                    textarea.append(item+"\n");
            }
        });

        about.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                JFrame frame2 = new JFrame("About");
                frame2.setLayout(null);
                frame2.setBounds(600,300,600,400);

                JLabel text1 = new JLabel("GÜNSELİ SEVİNÇ");
                text1.setBounds(20,20,150,30);

                JLabel text2 = new JLabel("LinkedIn URL: ");
                text2.setBounds(20,50,100,30);

                JLabel text3 = new JLabel("Github URL: ");
                text3.setBounds(20,80,100,30);

                JEditorPane ep1 = new JEditorPane();
                ep1.setText("https://www.linkedin.com/in/günseli-sevinç-a06903189");
                ep1.setBounds(110,55,400,20);

                JEditorPane ep2 = new JEditorPane();
                ep2.setText("https://github.com/gunselisevinc");
                ep2.setBounds(110,85,250,20);

                frame2.add(text1);
                frame2.add(text2);
                frame2.add(text3);
                frame2.add(ep1);
                frame2.add(ep2);
                frame2.setVisible(true);

            }
        });


        ChangeLanguage(1);

    }

    private List<String> readFile(String filename)
    {

        List<String> records = new ArrayList<>();
        try
        {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null)
            {
                records.add(line);
            }
            reader.close();
            return records;
        }
        catch (Exception e)
        {
            System.err.format("Exception occurred trying to read '%s'.", filename);
            e.printStackTrace();
            return null;
        }
    }

    public void ChangeLanguage(int lang){
        file.setText(language.lang.get("file").get(lang));
        edit.setText(language.lang.get("edit").get(lang));
        help.setText(language.lang.get("help").get(lang));

        cut.setText(language.lang.get("cut").get(lang));
        copy.setText(language.lang.get("copy").get(lang));
        paste.setText(language.lang.get("paste").get(lang));
        selectall.setText(language.lang.get("selectall").get(lang));

        neww.setText(language.lang.get("neww").get(lang));
        open.setText(language.lang.get("open").get(lang));
        save.setText(language.lang.get("save").get(lang));
        exitt.setText(language.lang.get("exitt").get(lang));

        langu.setText(language.lang.get("lang").get(lang));
        tur.setText(language.lang.get("tur").get(lang));
        eng.setText(language.lang.get("eng").get(lang));

        if (lang==1)
            tur.setText("✓ "+tur.getText());
        else
            eng.setText("✓ "+eng.getText());
        about.setText(language.lang.get("about").get(lang));

    }


}
