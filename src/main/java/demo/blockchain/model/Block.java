package demo.blockchain.model;

import demo.blockchain.util.StringUtil;
import java.util.Date;

public class Block {

  public String hash;

  public String prevHash;

  private String data;

  private long timeStamp;

  /* Number of zeroes miners must solve for. */
  private int nonce;

  public Block(String data, String prevHash) {
    this.data = data;
    this.prevHash = prevHash;
    this.timeStamp = new Date().getTime();
    this.hash = calculateHash();
  }

  public String calculateHash() {
    String calculatedHash = StringUtil.applySha256(prevHash + Long.toString(timeStamp) + data + nonce);

    return calculatedHash;
  }

  /*
   * Miners do proof of work by trying different values in block
   * until hash begins with certain number of zeroes
   */
  public void mineBlock(int difficulty) {
    String target = new String(new char[difficulty]).replace('\0', '0');

    while (!hash.substring(0, difficulty).equals(target)) {
      nonce++;
      hash = calculateHash();
    }

    System.out.println("Block Mined: " + hash);

  }
}
