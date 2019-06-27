package com.company;

import org.json.JSONArray;
import org.json.JSONObject;
import org.jsoup.Jsoup;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class myFrame extends JFrame {

    JLabel lblname = new JLabel("");
    JLabel lblcode = new JLabel("");
    JLabel lbllb = new JLabel("Lowest Buy:");
    JLabel lblhb = new JLabel("Highest Buy:");
    JLabel lblls = new JLabel("Lowest Sell:");
    JLabel lblhs = new JLabel("Hisghest Sell:");
    JLabel lblbuyp = new JLabel("Buy Price:");
    JLabel lblsellp = new JLabel("Sell Price:");
    JLabel lbldc = new JLabel("Daily Change:");
    JLabel lblupdate = new JLabel("Last Update");
    JLabel lbltl = new JLabel("TL   =");
    JLabel lblcode2 = new JLabel("");
    JTextField texttl = new JTextField();
    JTextField textx = new JTextField();

    JSONArray veriler;
    JList<KeyValueItem> list;
    DefaultListModel<KeyValueItem> l1;
    public myFrame()
    {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(400,100,500,700);

        JPanel panel = new JPanel();
        this.setLayout(null);

        l1 = new DefaultListModel<>();
        list = new JList<>(l1);
        list.setBounds(15,20,130,630);
        this.add(list);

        JScrollPane scrollPane = new JScrollPane(list);
        scrollPane.setBounds(12,17,134,634);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        this.getContentPane().setLayout(null);
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);

        lblname.setBounds(200,30,150,30);
        lblcode.setBounds(330,30,150,30);
        lbllb.setBounds(160,80,150,30);
        lblhb.setBounds(330,80,150,30);
        lblls.setBounds(160,130,150,30);
        lblhs.setBounds(330,130,150,30);
        lblbuyp.setBounds(160,200,150,30);
        lblsellp.setBounds(330,200,150,30);
        lbldc.setBounds(235,245,150,30);
        lblupdate.setBounds(235,350,200,30);
        lbltl.setBounds(250,300,150,30);
        lblcode2.setBounds(350,300,150,30);
        texttl.setBounds(190,300,60,30);
        textx.setBounds(290,300,60,30);

        this.add(lblname);
        this.add(lblcode);
        this.add(lbllb);
        this.add(lblhb);
        this.add(lblls);
        this.add(lblhs);
        this.add(lblbuyp);
        this.add(lblsellp);
        this.add(lbldc);
        this.add(lblupdate);
        this.add(lbltl);
        this.add(lblcode2);
        this.add(texttl);
        this.add(textx);

        getData();
        setData();

        ListSelectionListener listSelectionListener = new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                JList<KeyValueItem> templist = (JList<KeyValueItem>) listSelectionEvent.getSource();
                String code = list.getSelectedValue().getKey();
                for (int i = 0; i < veriler.length(); i++) {
                    JSONObject object = veriler.getJSONObject(i);
                    if (object.optString("code").equals(code)){
                        lblname.setText(object.optString("name"));
                        lblcode.setText(object.optString("code"));
                        lbllb.setText("Lowets Buy:"+String.format("%.4f",object.optDouble("todayLowestBuyPrice")));
                        lblhb.setText("Highest Buy:"+String.format("%.4f",object.optDouble("todayHighestBuyPrice")));
                        lblls.setText("Lowest Sell:"+String.format("%.4f",object.optDouble("todayLowestSellPrice")));
                        lblhs.setText("Highest Sell:"+String.format("%.4f",object.optDouble("todayHighestSellPrice")));
                        lblbuyp.setText("Buy Price:"+String.format("%.4f",object.optDouble("buyPrice")));
                        lblsellp.setText("Sell Price:"+String.format("%.4f",object.optDouble("sellPrice")));
                        lbldc.setText("Daily Change:"+String.format("%.4f",object.optDouble("dailyChange")));
                        //lblupdate.setText("Last Update:"+object.optDouble("lastUpdateDate"));
                        lblcode2.setText(object.optString("code"));

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
                        Date date = null;
                        try{
                            date = simpleDateFormat.parse(object.getString("lastUpdateDate"));
                            System.out.println("");

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");
                        lblupdate.setText("Last Update: "+String.valueOf(simpleDateFormat.format(date)));

                        //System.out.println(object.optDouble("yesterdayClosingSellPrice"));

                    }
                }

            }

        };

        list.addListSelectionListener(listSelectionListener);


        texttl.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String key = list.getSelectedValue().getKey();
                JSONObject object = null;

                for (int i = 0; i < veriler.length(); i++) {
                    JSONObject tempobject = veriler.getJSONObject(i);

                    if (tempobject.optString("code").equals(key)){
                        object = tempobject;
                        break;
                    }
                }

                double carpim = object.optDouble("sellPrice");
                if (tryFloat(texttl.getText()))
                {
                    double sonuc = carpim * Float.parseFloat(texttl.getText());
                    textx.setText(String.format("%.2f",(sonuc)));
                }

            }
        });
    }


    public boolean tryFloat(String str){
        try {
            Float.parseFloat(str);
            return  true;
        }
        catch (Exception ex){
            return false;
        }
    }

    public void getData(){
        borsa borsa = new borsa();
        this.veriler = borsa.getParaBirimi();
    }
//Made by Cem Eren
    public void setData(){
        for (int i = 0; i < veriler.length(); i++) {
            JSONObject jsonObject = veriler.getJSONObject(i);
            String code = jsonObject.optString("code");
            String name = jsonObject.optString("name");
            KeyValueItem keyValueItem = new KeyValueItem(code,name);
            l1.addElement(keyValueItem);
            list.setModel(l1);
        }
    }

}
