package com.epam.mjc.io;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;


public class FileReader {

    public Profile getDataFromFile(File file) {
        String fileData;
        try (FileInputStream fis = new FileInputStream(file)) {
            StringBuilder sb = new StringBuilder();
            while (fis.available() > 0){
                sb.append((char) fis.read());
            }
            fileData = sb.toString();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return createProfile(fileData);
    }

    public Profile createProfile(String fileData){
        Profile newProfile = new Profile();
        Set<Map.Entry<String, String>> set= (makeMap(fileData)).entrySet();
        for(Map.Entry<String, String> me: set) {
            switch (me.getKey()){
                case "Name":
                    newProfile.setName(me.getValue());
                    break;
                case "Age":
                    newProfile.setAge(Integer.parseInt(me.getValue()));
                    break;
                case "Email":
                    newProfile.setEmail(me.getValue());
                    break;
                case "Phone":
                    newProfile.setPhone(Long.parseLong(me.getValue()));
                    break;
                default:
                    System.out.println("Invalid data in the file");
            }
        }
        //
        return newProfile;
    }

    public Map<String, String > makeMap(String fileData){
        Map<String, String> dataMap = new LinkedHashMap<>();
        String[] arrayData = fileData.split("\r\n");
        for (String str: arrayData){
            String[] lineData = str.split(": ");
            dataMap.put(lineData[0], lineData[1]);
        }
        return dataMap;
    }
}
