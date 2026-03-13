/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainServer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
 
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
 
public class readJSON {

public readJSON()
{
    //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();
         
        try (FileReader reader = new FileReader("userlogs.json"))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
 
            JSONArray blockList = (JSONArray) obj;
            System.out.println(blockList);
             
            //Iterate over block chain array
            blockList.forEach( blockobject -> parseLogObject( (JSONObject) blockobject ) );
 
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
}
        
        

   
 
    private static void parseLogObject(JSONObject blockobject) 
    {
        //Get employee object within list
        JSONObject blockdetails = (JSONObject) blockobject.get("block");
         
        //Get user name
        String tno =  blockdetails.get("tno").toString();    
        System.out.println(tno);
        
        
                
        String partner = (String) blockdetails.get("partner");  
        System.out.println(partner);
        
        String supplier = (String) blockdetails.get("supplier");  
        System.out.println(supplier);
        
        String pname = (String) blockdetails.get("pname");  
        System.out.println(pname);
        
        String quantity = (String) blockdetails.get("quantity");  
        System.out.println(quantity);
        
        String totalamount = (String) blockdetails.get("totalamount");  
        System.out.println(totalamount);
        
        String pmode = (String) blockdetails.get("pmode");  
        System.out.println(pmode);
        
        String cno = (String) blockdetails.get("cno");  
        System.out.println(cno);
        
        String day = (String) blockdetails.get("day");  
        System.out.println(day);
        
        String status = (String) blockdetails.get("status");  
        System.out.println(status);
        
        
        String prev = (String) blockdetails.get("previoushash");  
        System.out.println(prev);
        
       
        
        String hash = (String) blockdetails.get("hash");  
        System.out.println(hash);
        
        Block b=new Block();
        
        b.tno=tno;
       b.partner=partner;
        b.supplier=supplier;
        b.pname=pname;
        b.quantity=quantity;
        b.totalamount=totalamount;
        b.pmode=pmode;
        b.cno=cno;
        b.day=day;
        b.status=status;
        b.previousHash=prev;
        b.hash=hash;
        
        readblockreq.blockchain.add(b);
         
        
    }
}
