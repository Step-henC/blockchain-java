package demo.blockchain.model;

import demo.blockchain.util.StringUtil;
import java.util.Date;

/*
 * Node on a block chain that 
 * functions as a linked list
 */
public class Block {

  public String hash;

  private String data;

  private long timeStamp;

  public Block next;

  public Block prev;

  /* Number of zeroes miners must solve for. */
  private int nonce;

  public Block(String data, Block prev, Block next) {
    this.data = data;
    this.prev = prev;
    this.next = next;
    this.timeStamp = new Date().getTime();
    this.hash = calculateHash();
  }

  public String calculateHash() {
    String calculatedHash = "";
    if (prev == null) { // if processing head
      calculatedHash = StringUtil.applySha256(Long.toString(timeStamp) + data + nonce);
    } else {

      // add prev hash and next node hash into this hash
      calculatedHash = StringUtil.applySha256(prev.hash + Long.toString(timeStamp) + data + nonce);

    }

    return calculatedHash;
  }

  /*
   * Miners do proof of work by trying different values in block
   * until hash begins with certain number of zeroes
   */
  public void mineBlock(int difficulty) {
    // create a string consisting of empty zeroes set to level of difficulty input
    // by user
    String target = new String(new char[difficulty]).replace('\0', '0');

    while (!hash.substring(0, difficulty).equals(target)) {
      nonce++;
      hash = calculateHash();
    }

    System.out.println("Block Mined: " + hash);

  }
}
