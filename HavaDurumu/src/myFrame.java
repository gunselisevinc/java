import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;

public class myFrame extends JFrame {

    Weather weather;
    JComboBox<KeyValueItem> allcities = new JComboBox<>();
    JComboBox<KeyValueItem> alldistrict = new JComboBox<>();
    int gun = 1;

    JLabel lblNem = new JLabel("");
    JLabel lbleny = new JLabel("");
    JLabel lblend = new JLabel("");
    JLabel lblruz = new JLabel("");
    JLabel lblgun = new JLabel("");

    public myFrame()
    {
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setBounds(400,300,400,450);

        this.setLayout(null);

        weather = new Weather();
        JSONArray cities = weather.getCities();

        allcities.setBounds(25,45,300,30);
        alldistrict.setBounds(25,75,300,30);

        JLabel baslik = new JLabel("GÜNLÜK HAVADURUMU");
        baslik.setBounds(120,10,200,30);
        this.add(baslik);

        JButton right = new JButton(">");
        right.setBounds(220,300,50,30);
        this.add(right);

        JButton left = new JButton("<");
        left.setBounds(120,300,50,30);
        this.add(left);

       lblgun.setBounds(140,350,250,30);
       this.add(lblgun);

        right.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(gun!=5)
                {
                    gun++;
                }
                else
                {
                    gun=5;
                }
                setStatics(gun);
            }
        });

        left.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(gun!=1)
                {
                    gun--;
                }
                else
                {
                    gun=1;
                }
                setStatics(gun);
            }
        });


        List<KeyValueItem> cityList = new ArrayList<>();
        for (int i = 0; i < cities.length(); i++) {
            JSONObject jsonObject = cities.getJSONObject(i);
            KeyValueItem keyValueItem = new KeyValueItem();
            keyValueItem.setKey(jsonObject.getString("il"));
            keyValueItem.setValue(jsonObject.getString("il"));
            cityList.add(keyValueItem);
        }

        /*Collections.sort(keyValueItems, new Comparator<KeyValueItem>() {
            @Override
            public int compare(KeyValueItem k1, KeyValueItem k2) {
                return k1.getValue().compareToIgnoreCase(k2.getValue());
            }
        });*/

        cityList = setSort(cityList);

        for (int i = 0; i < cityList.size(); i++) {
            allcities.addItem(cityList.get(i));
        }


        this.add(allcities);
        this.add(alldistrict);


        allcities.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                addDistrict();


            }
        });

        alldistrict.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setStatics(gun);
            }


        });

        lblNem.setBounds(100,130,100,30);
        this.add(lblNem);
        lbleny.setBounds(300,130,100,30);
        this.add(lbleny);
        lblend.setBounds(100,210,100,30);
        this.add(lblend);
        lblruz.setBounds(300,210,100,30);
        this.add(lblruz);

        JLabel label = new JLabel("Nem: ");
        label.setBounds(10,130,100,30);
        //label.setFont(new Font("Calibri", Font.CENTER_BASELINE, 15));
        this.add(label);

        JLabel label1 = new JLabel("En Yüksek: ");
        label1.setBounds(200,130,100,30);
        //label1.setFont(new Font("Calibri", Font.CENTER_BASELINE, 15));
        this.add(label1);

        JLabel label2 = new JLabel("En Düşük: ");
        label2.setBounds(10,210,100,30);
        //label2.setFont(new Font("Calibri", Font.CENTER_BASELINE, 15));
        this.add(label2);

        JLabel label3 = new JLabel("Rüzgar Hızı: ");
        label3.setBounds(200,210,100,30);
        //label3.setFont(new Font("Calibri", Font.CENTER_BASELINE, 15));
        this.add(label3);

        addDistrict();
        //this.getContentPane().setBackground(Color.getHSBColor(72, 108, 165));
        //this.setBackground(Color.getHSBColor(72, 108, 165));
        //this.getContentPane().setBackground(Color.blue);
        setVisible(true);
    }

    public JSONArray getDistrict(String city){
        return  weather.getDistrict(city);

    }

    public JSONArray getStatics(String id,String zamanlama){
        return weather.getStatics(id,zamanlama);
    }

    public void addDistrict(){
        JSONArray objects = getDistrict(((KeyValueItem)allcities.getSelectedItem()).getValue());
        List<KeyValueItem> districtList = new ArrayList<>();


        districtList.clear();
        alldistrict.removeAllItems();

        for (int i = 0; i < objects.length(); i++) {
            JSONObject object = objects.getJSONObject(i);
            KeyValueItem keyValueItem = new KeyValueItem();
            keyValueItem.setKey(String.valueOf(object.getInt("gunlukTahminIstNo")));
            keyValueItem.setValue(object.getString("ilce"));
            districtList.add(keyValueItem);
        }



        districtList = setSort(districtList);

        for (int i = 0; i < districtList.size(); i++) {
            alldistrict.addItem(districtList.get(i));
        }
    }

    public List<KeyValueItem> setSort(List<KeyValueItem> list){
        Collections.sort(list, new Comparator<KeyValueItem>() {
            @Override
            public int compare(KeyValueItem k1, KeyValueItem k2) {
                return k1.getValue().compareToIgnoreCase(k2.getValue());
            }
        });

        return list;
    }

    private void setStatics(int gun) {
        KeyValueItem keyValueItem = ((KeyValueItem)alldistrict.getSelectedItem());
        if (keyValueItem==null)
            return;
        JSONArray objects = getStatics(keyValueItem.getKey(),"gunluk");
        lblNem.setText(String.valueOf(objects.getJSONObject(0).getInt("enYuksekNemGun"+gun))+" %");
        lblNem.setFont(new Font("Calibri", Font.CENTER_BASELINE, 17));
        lbleny.setText(String.valueOf(objects.getJSONObject(0).getInt("enYuksekGun"+gun))+" °C");
        lbleny.setFont(new Font("Calibri", Font.CENTER_BASELINE, 17));
        lblend.setText(String.valueOf(objects.getJSONObject(0).getInt("enDusukGun"+gun))+" °C");
        lblend.setFont(new Font("Calibri", Font.CENTER_BASELINE, 17));
        lblruz.setText(String.valueOf(objects.getJSONObject(0).getInt("ruzgarHizGun"+gun))+" km/s");
        lblruz.setFont(new Font("Calibri", Font.CENTER_BASELINE, 17));
        SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(objects.getJSONObject(0).getString("tarihGun"+gun));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        simpleDateFormat = new SimpleDateFormat("dd MMMM yyyy");

        lblgun.setText(String.valueOf(simpleDateFormat.format(date)));

    }
}
