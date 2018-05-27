/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.onedollarshop1;
import com.mycompany.Event.GEvent;
import com.mycompany.Event.GEventListener;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Maya
 */
public class MenuListener implements GEventListener{

    Scanner input = new Scanner(System.in);
    ClientIO clientIO;
    
    MenuListener(String path){
        clientIO = new ClientIO(path);
    }
    
    @Override
    public void action(GEvent ev) { 
        
        if(ev.getData().toString().equals("1")){ 
            System.out.print("Entrer data séparée par \"-\" : ");
            ajouterClient(input.nextLine());
            
        }else if(ev.getData().toString().equals("2")){
            String id = InsererIdClient();
            System.out.print("nouveau Client avec modification: ");
            modifierClient(id,input.nextLine());
            
        }else if(ev.getData().toString().equals("3")){
            String id = InsererIdClient();
            supprimerClient(id);
            
        }else if(ev.getData().toString().equals("4")){
            String id = InsererIdClient();
            afficherClient(id);
            //System.out.println(MenuPrincipal.clientsLst.get(id).afficher());
            
        }    
    }
    
    public String InsererIdClient(){
        boolean exists = true;
        String id;
        do{
           if (exists == false){
              System.out.println("Index incorrecte"); 
           } 
           System.out.print("Entrer Id du client: ");
           id = input.nextLine();
           if(id.equals("0")){
               System.exit(0);
           }
           //exists = MenuPrincipal.clientsLst.containsKey(id);
           exists = new File(clientIO.getDirectoryPath() + "/" + id).isDirectory();
           
        }while(exists == false);
        return id;
    }
    
    public Client creerClient(String data){
        String [] dataDetails = data.split("-");
        Client c = null;
        if(dataDetails.length == 5){
            c = new Client.ClientBuilder(dataDetails[0]).prenom(dataDetails[1]).nom(dataDetails[2]).telephone(dataDetails[3]).adresse(dataDetails[4]).build();
        }
        return c;
    }
    
    public void ajouterClient(String data){
        Client c = creerClient(data);
        if(c != null){
            //MenuPrincipal.clientsLst.put(c.getId(),c);
            
            int res = clientIO.WritingToFile(c, c.getId(), c.getId());
            System.out.println(res==-1?"Client existe deja":"Client ajouté");
        }
        else{
            System.out.println("data non suffisante"); 
        }
    }
    
    public void modifierClient(String id,String modClient){
        Client c = creerClient(modClient);
        //MenuPrincipal.clientsLst.replace(id, c);
        clientIO.ModifyFile(c, id, id);
        
        System.out.println("Nouvelles valeurs ajoutée: "  + c.afficher());     
    } 
    
    public void supprimerClient(String id){
        //MenuPrincipal.clientsLst.remove(id);
        clientIO.removeDirectory(id);
        System.out.println("Client supprimé");
    }
    
    public void afficherClient(String id){
        Client c = clientIO.ReadingFromFile(id, id);
        System.out.println(c.afficher());
    }
}
