package com.mycompany.onedollarshop1;

import com.mycompany.Event.Source;
import java.io.IOException;

/**
 *
 * @author Maya
 */
public class PriseEnMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {
        
    Source<String> source = new Source<>();
    source.addGEventListener(new MenuListener("C:/Users/Maya/Documents/NetBeansProjects2/OneDollarShop1/ClientDirectory"));
   

    Thread t = new Thread(new MenuPrincipal(source));
    t.start();
    t.join();
    }
    
}
