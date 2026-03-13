package blockchainServer;

import blockchainServer.blockcserver;
import blockchainServer.readblockreq;
import java.util.Date;

public class Block {

	public String hash;
	 String previousHash; 
         String tno;
         String supplier;
	 String partner; //our data will be a simple message.
         String pname;
         String quantity;
         String totalamount;
         
         String pmode; //our data will be a simple message.
         String cno;
         String day;
         
         String status;
	 
	 int nonce;

         
         public Block()
         {
             
         }
         
	//Block Constructor.
	public Block(String tno,String partner,String supplier,String pname,String quantity,String totalamount,
                String pmode,String cno,String day,String status,String previousHash ) {
            this.tno=tno;
            this.partner=partner;
		this.supplier=supplier;
		this.pname = pname;
		this.quantity = quantity;
                this.totalamount=totalamount;
                
                this.pmode=pmode;
                this.cno=cno;
                this.day=day;
                
                this.status=status;
                
                
                this.hash = calculateHash(); //Making sure we do this after we set the other values.
	
	}
        
        //Calculate new hash based on blocks contents
	public String calculateHash() {
		String calculatedhash = StringUtil.applySha256( 
				previousHash +
				
				tno + partner + 
				supplier + pname +  day  
				);
                System.out.println("calculated hash: "+calculatedhash);
		return calculatedhash;
	}
        
        
        public void mineBlock(int difficulty) {
		String target = new String(new char[difficulty]).replace('\0', '0'); //Create a string with difficulty * "0" 
		while(!hash.substring( 0, difficulty).equals(target)) {
			nonce ++;
			hash = calculateHash();
		}
		blockcserver.jTextArea1.append("Block Mined!!! : " + hash+"\n");
                readblockreq.previousHash=hash;
	}
}