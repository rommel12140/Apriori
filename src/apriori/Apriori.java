/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apriori;

import java.util.*;

/**
 *
 * @author rommel
 */
public class Apriori {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
      
      ArrayList<ArrayList<String>> transaction = new ArrayList();
      ArrayList<ArrayList<String>> itemSet = new ArrayList();
      int[] frequency = new int[20];
      int x = 0, total = 0;
      
      
      //===========================================================
      //===========================================================
      //===========================================================
      //ADD DATA
      Transaction ta = new Transaction();
      ta.addTransaction("M");
      ta.addTransaction("O");
      ta.addTransaction("N");
      ta.addTransaction("K");
      ta.addTransaction("E");
      ta.addTransaction("Y");
      transaction.add(ta.transObject());
      
      Transaction tb = new Transaction();
      tb.addTransaction("D");
      tb.addTransaction("O");
      tb.addTransaction("N");
      tb.addTransaction("K");
      tb.addTransaction("E");
      tb.addTransaction("Y");
      transaction.add(tb.transObject());
      
      Transaction tc = new Transaction();
      tc.addTransaction("M");
      tc.addTransaction("A");
      tc.addTransaction("K");
      tc.addTransaction("E");

      transaction.add(tc.transObject());
      
      Transaction td = new Transaction();
      td.addTransaction("M");
      td.addTransaction("U");
      td.addTransaction("C");
      td.addTransaction("K");
      td.addTransaction("Y");

      transaction.add(td.transObject());
      
      Transaction te = new Transaction();
      te.addTransaction("M");
      te.addTransaction("A");
      te.addTransaction("K");
      te.addTransaction("E");

      transaction.add(te.transObject());
      
      Transaction tf = new Transaction();
      tf.addTransaction("C");
      tf.addTransaction("O");
      tf.addTransaction("O");
      tf.addTransaction("K");
      tf.addTransaction("I");
      tf.addTransaction("E");

      transaction.add(tf.transObject());
      
      //===========================================================
      //===========================================================
      //===========================================================
      //ADD TRANSACTION TO DATABASE
      Database db =  new Database();
      db.addToDatabase(transaction);
      total = transaction.size();
      System.out.println("Number of Transactions:" + total);
      
      //===========================================================
      //===========================================================
      //===========================================================
      //PROCESS THE ITEM SET SO THAT ALL CHARACTERS ARE PRESENT
      //WITH THEIR FREQUENCIES
      
      ItemSet t = new ItemSet();
      t.processItemSet(transaction);
      itemSet = t.getItemSet();
      frequency = t.getItemFreq();
      System.out.println(frequency[0]);
      //===========================================================
      //===========================================================
      //ADD TO DATABASE
      //DISPLAY DATABASE
      db.addToDatabase(itemSet);
      db.display();
      //=================
      
      //===========================================================
      //===========================================================
      //DISPOSE THOSE ITEMS LOWER THAN MINIMUM SUPPORT
      ItemDisposer disp = new ItemDisposer();
      disp.ItemSupportChecker(itemSet, frequency);
//      for(String itemData: itemSet){
//            disp.ItemSupportChecker(itemData, frequency[x]+1);            
//            x++;
//      }
      //===========================================================
      //===========================================================
      //GET ITEMS FROM ITEMDISPOSER AND RELOCATE TO ITEMSET
      itemSet = disp.getItems();
      
      //===========================================================
      //===========================================================
      db.addToDatabase(itemSet);
      db.display();
      
      //THIS IS TO CHECK IF THE PROGRAM WILL END OR NOT
      if(itemSet.size()>1){
        //===========================================================
        //===========================================================
        //CONCATINATE THE CHARACTERS
        Concatinate conc = new Concatinate();
        conc.concatinateItems(itemSet);
        itemSet=conc.getItems();
        db.addToDatabase(itemSet);
        db.display();
        //===========================================================
        //===========================================================
        //SECOND FUNCTION THAT COULD GIVE FREQUENCY OF ITEMSETS
        ItemSet t2 = new ItemSet();
        t2.processItemSet2(transaction,itemSet);
        
        frequency = t2.getItemFreq();
        //===========================================================
        //===========================================================
        //DISPOSE ITEMS AGAIN THAT ARE LOWER THAN THE MINIMUM SUPPORT
        ItemDisposer disp2 = new ItemDisposer();
        disp2.ItemSupportChecker(itemSet, frequency);            

        //===========================================================
        //===========================================================
        //GET ITEMS FROM ITEMDISPOSER AND RELOCATE TO ITEMSET
        itemSet = disp2.getItems();
        
        db.addToDatabase(itemSet);
        db.display();

        //===========================================================
        //===========================================================
        //CONCATINATE THE CHARACTERS
        Concatinate conc2 = new Concatinate();
        conc2.concatinateItems(itemSet);
        itemSet=conc2.getItems();
        db.addToDatabase(itemSet);
        db.display();
        }
    }
    
    
    
}

//=============================================================================
//=============================================================================
//=============================================================================
class Transaction {
    ArrayList<String> transaction = new ArrayList();
    
    Transaction(){

    }
    
    public void addTransaction(String s){
        transaction.add(s);
    }
    
    public ArrayList<String> transObject(){
        return transaction;
    }
    
    public void display(){
        System.out.println(transaction);
    }
}

//=============================================================================
//=============================================================================
//=============================================================================
class Database {
    ArrayList<ArrayList<ArrayList<String>>> database = new ArrayList();
    
    Database(){
        
    }
    
    public void addToDatabase(ArrayList<ArrayList<String>> transaction){
        database.add(transaction);
    }
    
    public ArrayList<ArrayList<ArrayList<String>>> getDatabase(){
        return database;
    }
    
    public void display(){
        int i = 1;
        System.out.println("Display database:");

        for(ArrayList x: database){
            System.out.println("d" + i + ":\t" + x);
            i++;
        }
    }
}


//=============================================================================
//=============================================================================
//=============================================================================
class ItemSet {
    
    ArrayList<ArrayList<String>> itemSet = new ArrayList();
    String[] index = new String[20];
    int[] frequency = new int[20];
    
    
    ItemSet(){
        
    }
    
    public ArrayList<ArrayList<String>> getItemSet(){
        return itemSet;
    }
    
    public int[] getItemFreq(){
        return frequency;
    }
    
    public void processItemSet(ArrayList<ArrayList<String>> data){
        ArrayList<String>[] exData = new ArrayList[20];
        int i = 0;
        int j = 0; 
        
            
            
        for(ArrayList e: data){
            exData[i] = e; // Puts to array of strings the separated array list                
            for(String s: exData[i]){ //For every input of the array of strings
                
                ArrayList<String> dataItem = new ArrayList();
                int comp = 0;
                for(int compare = 0; compare<19; compare++){    
                    if(s == index[compare]){
                        comp ++;
                        if(frequency[compare]==0)
                            frequency[compare] = 1;
                        frequency[compare] += 1;
                    }
                }

                if(comp == 0){
                    index[j] = s;

                    dataItem.add(s);
                    itemSet.add(dataItem);
                    j++;
                }
            }
        i++;
        } 
    }
    
    public void processItemSet2(ArrayList<ArrayList<String>> data1, ArrayList<ArrayList<String>> data2){
        ArrayList<String>[] exData1 = new ArrayList[20];
        int i = 0;
        
        
        
        for(ArrayList<String> d: data2){ //Separates the array list to array of strings
                        int i2 = 0;
            int i3 = 0;
            int x = 0;
            boolean[] c = new boolean[20];
            

            
            for(String s1: d){
                //int[] c = new int[20];
//                System.out.print("\n"+ x + s1 + " compare with:"); 
                //Compare s1 with s2
                for(ArrayList d2: data1){ //Separates the array list to array of strings
                    exData1[i2] = d2;
//                    System.out.print(exData1[i2]);
//                    System.out.println(i2);
                    for(String s2: exData1[i2]){ //For every input of the array of strings
                        
                            if(s1 == s2){
                                if(x==0)
                                    c[i2] = true;
                                if(c[i3] == true && x==1){
                                    frequency[i] += 1;
                                    
                                    break;
                                
                                }
                                
                                break;
                            }
                            else{
                                c[i2] = false;
                            }
                    }
                    
                    i2++;
                    if(x==1){
                        i3++;
                    }    
//                    if(c[x] == true)
//                            break;
                }
                x++;

            }
            i++;
        }
//        int jkl = 0;
//        for(ArrayList hehe: exData2){
//            if(hehe!=null){
//                System.out.print("\n"+ hehe +": " + frequency[jkl]); 
//                jkl++;
//            }
//        }
    }
    
    
}
//=============================================================================
//=============================================================================
//=============================================================================
class ItemDisposer{
    ArrayList<ArrayList<String>> itemSet = new ArrayList();
    
    public ItemDisposer(){
        
    }
    
    public void ItemSupportChecker(ArrayList<ArrayList<String>> data1, int[] data2){
      int x =0;
      for(ArrayList<String> itemData: data1){   
            if(data2[x] >= 3)
                itemSet.add(itemData);
            x++;
      }

    }
    
    public ArrayList getItems(){
        return itemSet;
    }
    
    public void display(){
        System.out.println(itemSet);
    }
    
}

class Concatinate{
    ArrayList<ArrayList<String>> itemSet = new ArrayList();
    
    String[] item = new String[20];
    
    public Concatinate(){
        
    }
    
    public void concatinateItems(ArrayList<ArrayList<String>> dataSet){
        
        int i = 0;
        int x = 0;
        for(ArrayList<String> data: dataSet){
            for(String s: data){
                item[i] = s;
                i++;
            }
        }
        for(ArrayList<String> data: dataSet){
            for(String s: data){
                System.out.println(s);
                for(int y = x+1; y<dataSet.size()+1; y++){
                    ArrayList<String> itemConc = new ArrayList();
                    if(item[y]!=null){
                        itemConc.add(item[x]);
                        itemConc.add(item[y]);
                        itemSet.add(itemConc);     
                    }

                }

                 x++;
            }
        }   

        
    }
    
    public ArrayList<ArrayList<String>> getItems(){
        return itemSet;
    }
    
}