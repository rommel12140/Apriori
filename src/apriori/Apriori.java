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
      ArrayList transaction = new ArrayList();
      ArrayList<String> itemSet = new ArrayList();
      ArrayList<ArrayList<String>> itemSets = new ArrayList();
      int[] frequency = new int[20];
      int x = 0, total = 0;
      
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
      
      Database db =  new Database();
      db.addToDatabase(transaction);
      total = transaction.size();
      System.out.println("Number of Transactions:" + total);
      
      ItemSet t = new ItemSet();
      t.processItemSet(transaction);
      itemSet = t.getItemSet();
      frequency = t.getItemFreq();
      
      //DISPLAY DATABASE
      db.addToDatabase(itemSet);
      db.display();
      //=================
      
      ItemDisposer disp = new ItemDisposer();

      
      for(String itemData: itemSet){
            disp.ItemSupportChecker(itemData, frequency[x]);            
            x++;
      }
      
      itemSet = disp.getItems();
      db.addToDatabase(itemSet);
      db.display();
      
      Concatinate conc = new Concatinate();
      conc.concatinateItems(itemSet);
      itemSets.add(conc.getItems());
      for(ArrayList<String> h: itemSets){
            db.addToDatabase(h);
      }
      
      db.display();
      
      ItemSet t2 = new ItemSet();
      t2.processItemSet2(transaction,conc.getItems());
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
    ArrayList<ArrayList> database = new ArrayList();
    
    Database(){
        
    }
    
    public void addToDatabase(ArrayList<String> transaction){
        database.add(transaction);
    }
    
    public ArrayList<ArrayList> getDatabase(){
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
    
    ArrayList<String> dataItem = new ArrayList();
    String[] index = new String[20];
    int[] frequency = new int[20];
    
    
    ItemSet(){
        
    }
    
    public ArrayList<String> getItemSet(){
        return dataItem;
    }
    
    public int[] getItemFreq(){
        return frequency;
    }
    
    public void processItemSet(ArrayList<ArrayList> data){
        ArrayList<String>[] exData = new ArrayList[20];
        int i = 0;
        int j = 0; 
        
        for(ArrayList d: data){ //Separates the array list to array of strings
            exData[i] = d; // Puts to array of strings the separated array list
            for(String s: exData[i]){ //For every input of the array of strings
                int comp = 0;
                for(int compare = 0; compare<19; compare++){    
                    if(s == index[compare]){
                        comp ++;
                        frequency[compare] += 1;
                        //System.out.println(a[compare] + "==");
                    }
                }
                
                if(comp == 0){
                    index[j] = s;
                    
                    dataItem.add(s);
                    j++;
                }
            }
            i++;
        }
    }
    
    public void processItemSet2(ArrayList<ArrayList> data1, ArrayList<ArrayList<String>> data2){
        ArrayList<String>[] exData2 = new ArrayList[20];
        ArrayList<String>[] exData1 = new ArrayList[20];
        ArrayList<String> newItemSet = new ArrayList();
        int[] frequency = new int[20];
        int i = 0;
        int j = 0;
        
        
        
        for(ArrayList<String> d: data2){ //Separates the array list to array of strings
            
            exData2[i] = d; // Puts to array of strings the separated array list
//            System.out.println("\nCompare: " + exData2[i]);
            boolean[] c = new boolean[20];
            
            int i2 = 0;
            int i3 = 0;
            int x = 0;
            
            for(String s1: d){
                //int[] c = new int[20];
//                System.out.print("\n"+ x + s1 + " compare with:"); 
                //Compare s1 with s2
                for(ArrayList d2: data1){ //Separates the array list to array of strings
                    exData1[i2] = d2;
                    int compare = 0;
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
                        
                        compare++;
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
        int jkl = 0;
        for(ArrayList hehe: exData2){
            if(hehe!=null){
                System.out.print("\n"+ hehe +": " + frequency[jkl]); 
                jkl++;
            }
        }
    }
    
    
}
//=============================================================================
//=============================================================================
//=============================================================================
class ItemDisposer{
    ArrayList<String> itemSet = new ArrayList();
    
    public ItemDisposer(){
        
    }
    
    public void ItemSupportChecker(String data1, int data2){
        if(data2+1 >= 3)
            itemSet.add(data1);
    }
    
    public ArrayList<String> getItems(){
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
    
    public void concatinateItems(ArrayList<String> data){
        int x = 0, i = 0;
        for(String s: data){
            item[i] = s;
            
            i++;
        }
        for(String s: data){
            item[x] = s;
            for(int y = x+1; y<data.size()+1; y++){
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
    
    public ArrayList getItems(){
        return itemSet;
    }
    
}