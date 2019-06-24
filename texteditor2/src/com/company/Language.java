package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Language {
    public HashMap<String, List<String>> lang;

    public Language(){
        lang = new HashMap<>();

        lang.put("file",(Arrays.asList("File","Dosya")));
        lang.put("edit",(Arrays.asList("Edit","Düzenle")));
        lang.put("help",(Arrays.asList("Help","Yardım")));
        lang.put("cut",(Arrays.asList("cut","kes")));
        lang.put("copy",(Arrays.asList("copy","kopyala")));
        lang.put("paste",(Arrays.asList("paste ","yapıştır")));
        lang.put("selectall",(Arrays.asList("selectAll","Tümünü Seç")));
        lang.put("neww",(Arrays.asList("New","Yeni")));
        lang.put("open",(Arrays.asList("Open","Aç")));
        lang.put("save",(Arrays.asList("Save","Kaydet")));
        lang.put("exitt",(Arrays.asList("Exit","Çık")));
        lang.put("lang",(Arrays.asList("Language","Dil")));
        lang.put("tur",(Arrays.asList("Turkish","Türkçe")));
        lang.put("eng",(Arrays.asList("English","İngilizce")));
        lang.put("about",(Arrays.asList("About","Hakkındaa")));
    }

}
