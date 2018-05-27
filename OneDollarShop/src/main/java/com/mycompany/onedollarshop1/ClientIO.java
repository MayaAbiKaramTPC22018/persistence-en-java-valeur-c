/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onedollarshop1;

/**
 *
 * @author Maya
 */
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientIO {
    
    private String _directoryPath;
    Gson gson;
    
    ClientIO(String directoryPath){
        _directoryPath = directoryPath;
        gson = new Gson();
    }
    
    public String getDirectoryPath(){
        return _directoryPath;
    }
    public void setDirectoryPath(String s){
        _directoryPath = s;
    }
    
    public void  ModifyFile(Client client, String id, String fileName){
        String json = gson.toJson(client);
        
        try {
            new File(_directoryPath + "/" + client.getId()).mkdirs();
            String fileLocation = _directoryPath + "/" + id + "/" + fileName + ".json";
            FileWriter writer = new FileWriter(fileLocation);
            writer.write(json);
            writer.close();

        } catch (IOException e) {
        }  
    }
    
    public int WritingToFile(Client client, String id, String fileName){
         
        File f = new File(_directoryPath + "/" + client.getId());
        if(!f.isDirectory()){
            f.mkdirs();
            ModifyFile(client,id,fileName);
            return 1;
        }
        else
        {
           return -1;
        }         
    }
    
    public Client ReadingFromFile (String id, String fileName){
        
        String fileLocation = _directoryPath + "/" + id + "/" + fileName + ".json";
        Client client = null;
       
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileLocation)); 
            client = gson.fromJson(br, Client.class);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ClientIO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return client;
    }
    
    public void removeDirectory (String fileName){
        String directoryPath = _directoryPath + "/" + fileName;
        File dir = new File(directoryPath);
        if(dir.isDirectory()){
            String[]entries = dir.list();
            for(String s: entries){
                File currentFile = new File(dir.getPath(),s);
                currentFile.delete();
            }
        }
        dir.delete();
    }
}
