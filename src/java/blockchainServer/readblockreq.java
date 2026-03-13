/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package blockchainServer;

import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Vector;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Lenovo
 */
public class readblockreq extends Thread
{
    public static ArrayList<Block> blockchain = new ArrayList<Block>();
    public static int difficulty = 0;
    public static String previousHash="0";
    
    readblockreq()
    {
        super();
        start();
    }
    
    public void run()
    {
        try
        {
            ServerSocket ss=new ServerSocket(3000);
            
            while (true)
            {
                Socket soc=ss.accept();
                ObjectOutputStream oos=new ObjectOutputStream(soc.getOutputStream());
                ObjectInputStream oin=new ObjectInputStream(soc.getInputStream());
                
                String req=(String)oin.readObject();
                
                if (req.equals("ADDBLOCK"))
                {
                    String tno=(String)oin.readObject();
                   String partner=(String)oin.readObject();
                   String supplier=(String)oin.readObject();
                   String pname=(String)oin.readObject();
                   String quantity=(String)oin.readObject();
                   
                   String totalamount=(String)oin.readObject();
                   String pmode=(String)oin.readObject();
                   String cno=(String)oin.readObject();
                   String day=(String)oin.readObject();
                   String status=(String)oin.readObject();
                  
                                      
                   
                  // String opr=pid+"-->"+opr2+"-->"+opr3;
                   
                   blockcserver.jTextArea1.append("Order details received for partner "+partner+"\n");
                   
                  
                        
                        Block b=new Block(tno,partner,supplier,pname,quantity,totalamount,pmode,cno,day,
                        status,previousHash);
                        blockchain.add(b);
                        blockcserver.jTextArea1.append("Block Successfully added!\n");
                        blockchain.get(blockchain.size()-1).mineBlock(difficulty);
                        
                        
                        oos.writeObject("SUCCESS");
                        System.out.println("reply sent..");
                       
                  
                }
                else
                if (req.equals("UPDATEMBDASTATUS"))
                {
                    String orderid=(String)oin.readObject();
                    String status=(String)oin.readObject();
                    blockcserver.jTextArea1.append("UPDATEMBDASTATUS received for order "+orderid+"\n");
                    
                    String reply=updatestatus(orderid,status);
                    
                    oos.writeObject(reply);
                }
                else
                if (req.equals("UPDATESUPPLIERSTATUS"))
                {
                    String orderid=(String)oin.readObject();
                    String status=(String)oin.readObject();
                    blockcserver.jTextArea1.append(req+" received for order "+orderid+"\n");
                    
                    String reply=updatestatus(orderid,status);
                    
                    oos.writeObject(reply);
                }
                else
                if (req.equals("PARTNERORDERS"))
                {
                    String uname=(String)oin.readObject();
                    
                    blockcserver.jTextArea1.append(req+" request received for user "+uname+" \n");
                    
                    Vector v=getorders(uname);
                    System.out.println("v size: "+v.size());
                    if (v.size()==0)
                    {
                        oos.writeObject("NOTFOUND");
                    }
                    else
                    {
                        oos.writeObject("AVAILABLE");
                        oos.writeObject(v);
                        
                            
                    }
                    
                    
                }
                else
                if (req.equals("ALLORDERS"))
                {
                    String uname=(String)oin.readObject();
                    
                    blockcserver.jTextArea1.append(req+" request received for user "+uname+" \n");
                    
                    Vector v=allorders();
                    System.out.println("v size: "+v.size());
                    if (v.size()==0)
                    {
                        oos.writeObject("NOTFOUND");
                    }
                    else
                    {
                        oos.writeObject("AVAILABLE");
                        oos.writeObject(v);
                        
                            
                    }
                    
                    
                }
                else
                if (req.equals("SUPPLIERALLORDERS"))
                {
                    String uname=(String)oin.readObject();
                    
                    blockcserver.jTextArea1.append(req+" request received for Supplier "+uname+" \n");
                    
                    Vector v=supplierallorders(uname);
                    System.out.println("v size: "+v.size());
                    if (v.size()==0)
                    {
                        oos.writeObject("NOTFOUND");
                    }
                    else
                    {
                        oos.writeObject("AVAILABLE");
                        oos.writeObject(v);
                        
                            
                    }
                    
                    
                }
                else
                if (req.equals("GETMYAPPLICATION"))
                {
                    String uname=(String)oin.readObject();
                    String appid=(String)oin.readObject();
                    
                    blockcserver.jTextArea1.append(req+" request received from user "+uname+" for Application ID: "+appid+"\n");
                    
                    String data=getmyapp(uname,appid);
                    System.out.println(data);
                    if (data.equals("NOAPP"))
                    {
                        oos.writeObject("NOTFOUND");
                    }
                    else
                    {
                        
                        oos.writeObject(data);
                        
                            
                    }
                    
                    
                }
                else
                if (req.equals("GETALLAPPLICATIONS")) //admin
                {
                    
                    
                    blockcserver.jTextArea1.append(req+" request received for user admin \n");
                    
                    Vector v=getallapps();
                    System.out.println("v size: "+v.size());
                    if (v.size()==0)
                    {
                        oos.writeObject("NOTFOUND");
                    }
                    else
                    {
                        oos.writeObject("SUCCESS");
                        oos.writeObject(v);
                        
                            
                    }
                    
                    
                }
                    
                  /*
                else
                if (req.equals("GETMYDONATIONS"))
                {
                    String donor=(String)oin.readObject();
                    blockcserver.jTextArea1.append(req+" request received from donor"+donor+" \n");
                    Vector v=getmydonations(donor);
                    oos.writeObject(v);
                }
              
                else
                if (req.equals("GETDONATIONS"))
                {
                    String ngo=(String)oin.readObject();
                    String scheme=(String)oin.readObject();
                    blockcserver.jTextArea1.append(req+" request received from NGO "+ngo+" \n");
                    Vector v=getdonations(ngo,scheme);
                    oos.writeObject(v);
                }
                
                else
                if (req.equals("GETEXPENSE"))
                {
                    String pid=(String)oin.readObject();
                    Vector v=getexpense(pid);
                    oos.writeObject(v);
                }
                else
                if (req.equals("GETMYBIDS"))
                {
                    String uname=(String)oin.readObject();
                    Vector v=getmybids(uname);
                    oos.writeObject(v);
                }
                else
                if (req.equals("VIEWLOG"))
                {
                    String user=(String)oin.readObject();
                    System.out.println(user);
                    String log=getlog(user);
                    
                    oos.writeObject(log);
                }
                */
                
                oos.close();
                oin.close();
                soc.close();
                
            }
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
    }    
    
    
    
    
    Vector getorders(String uname)
    {
        
        Vector v=new Vector();
        //String reply="";
        
        try
        {
            for (int i=0;i<blockchain.size();i++)
            {
                Block b=blockchain.get(i);
                
                if (b.partner.equals(uname))
                {
                   String data=b.tno+"$"+b.supplier+"$"+b.pname+"$"+b.quantity+"$"+b.totalamount
                   +"$"+b.pmode+"$"+b.day+"$"+b.status;
                        
                   
                   v.add(data);
                   // System.out.println(v.get(0).toString().trim());
                    
                }
            }
            
           
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        return v;
    }
    
    
    Vector allorders()
    {
        
        Vector v=new Vector();
        //String reply="";
        
        try
        {
            for (int i=0;i<blockchain.size();i++)
            {
                Block b=blockchain.get(i);
                
               // if (b.partner.equals(uname))
                {
                   String data=b.tno+"$"+b.partner+"$"+b.supplier+"$"+b.pname+"$"+b.quantity+"$"+b.totalamount
                   +"$"+b.pmode+"$"+b.day+"$"+b.status;
                        
                   
                   v.add(data);
                   // System.out.println(v.get(0).toString().trim());
                    
                }
            }
            
           
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        return v;
    }
    
    Vector supplierallorders(String uname)
    {
        
        Vector v=new Vector();
        //String reply="";
        
        try
        {
            for (int i=0;i<blockchain.size();i++)
            {
                Block b=blockchain.get(i);
                
                if (b.supplier.equals(uname) && !b.status.equals("PENDING"))
                {
                   String data=b.tno+"$"+b.partner+"$"+b.pname+"$"+b.quantity+"$"+b.totalamount
                   +"$"+b.pmode+"$"+b.day+"$"+b.status;
                        
                   
                   v.add(data);
                   // System.out.println(v.get(0).toString().trim());
                    
                }
            }
            
           
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }

        return v;
    }
    
    String getmyapp(String uname,String appid)
    {
        
        String data="NOAPP";
        //String reply="";
        /*
        try
        {
            for (int i=0;i<blockchain.size();i++)
            {
                Block b=blockchain.get(i);
                
                if (b.uname.equals(uname) && b.appid == Integer.parseInt(appid))
                {
                    data=b.appid+"$"+b.village+"$"+b.taluk+"$"+b.district+"$"+b.state
                   +"$"+b.name+"$"+b.sex+"$"+b.dob+"$"+b.pob+"$"+b.mname+"$"+b.fname
                            +"$"+b.taddress+"$"+b.paddress
                           +"$"+b.appdate+"$"+b.status;
                   
                   //v.add(data);
                   // System.out.println(v.get(0).toString().trim());
                    
                }
            }
            
           
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
*/
        return data;
    }
    
    Vector getallapps()
    {
        
        Vector v=new Vector();
        //String reply="";
        /*
        try
        {
            for (int i=0;i<blockchain.size();i++)
            {
                Block b=blockchain.get(i);
                
               
                   String data=b.appid+"$"+b.village+"$"+b.taluk+"$"+b.district+"$"+b.state
                   +"$"+b.name+"$"+b.sex+"$"+b.dob+"$"+b.pob+"$"+b.mname+"$"+b.fname
                           +"$"+b.appdate+"$"+b.status;
                   
                   v.add(data);
                   // System.out.println(v.get(0).toString().trim());
                    
                
            }
            
           
            
        }
        catch(Exception e)
        {
            System.out.println(e);
            e.printStackTrace();
        }
*/
        return v;
    }
    
  
    
    
    String updatestatus(String tno,String status)
    {
        
        
        String reply="FAILED";
        
        try
        {
            for (int i=0;i<blockchain.size();i++)
            {
                Block b=blockchain.get(i);
                
                if (b.tno.equals(tno))
                {
                    b.status=status;
                    blockchain.set(i, b);
                    reply="SUCCESS";
                    break;
                   // System.out.println(v.get(0).toString().trim());
                    
                }
            }
            
           
            
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        return reply;
    }
    
   
    
    
    void writelogs()
    {
        try
        {
            if (blockchain.size()>0)
            {
                JSONArray blockList = new JSONArray();
                
                for (int i=0;i<blockchain.size();i++)
                {
                    Block b=(Block)blockchain.get(i);
                    JSONObject blockdetails = new JSONObject();
                    blockdetails.put("tno", b.tno);
                    blockdetails.put("partner", b.partner);
                    blockdetails.put("supplier", b.supplier);
                    blockdetails.put("pname", b.pname);
                    blockdetails.put("quantity", b.quantity);
                    blockdetails.put("totalamount", b.totalamount);
                    blockdetails.put("pmode", b.pmode);
                    blockdetails.put("cno", b.cno);
                    blockdetails.put("day", b.day);
                  
                    blockdetails.put("status", b.status);
                    blockdetails.put("previoushash", b.previousHash);
                    blockdetails.put("hash", b.hash);
                    
                    JSONObject blockObject = new JSONObject(); 
                    blockObject.put("block", blockdetails);
                    
                    blockList.add(blockObject);
                }
                
                FileWriter file = new FileWriter("userlogs.json");
                 file.write(blockList.toJSONString()); 
                 file.flush();
                 file.close();
                
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
        
}
