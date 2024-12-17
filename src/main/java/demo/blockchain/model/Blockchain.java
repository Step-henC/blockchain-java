package demo.blockchain.model;

import java.util.ArrayList;

public class Blockchain {

  private ArrayList<Block> blockChain;
  public int difficulty;

  public Blockchain(int difficulty) {
    this.blockChain = new ArrayList<Block>();
    this.difficulty = difficulty;
  }

  public void addBlock(Block block) {
    blockChain.add(block);
  }

  public Block getBlockAtIndex(int index) {
    return blockChain.get(index);
  }

  public int getSize() {
    return blockChain.size();
  }

  public Boolean isChainValid() {
    String hashTarget = (new String(new char[difficulty])).replace('\u0000', '0');

    for (int i = 1; i < blockChain.size(); ++i) {
      Block curr = (Block) blockChain.get(i);
      Block prev = (Block) blockChain.get(i - 1);
      if (!curr.hash.equals(curr.calculateHash())) {
        return Boolean.FALSE;
      }

      if (!prev.hash.equals(curr.prevHash)) {
        return Boolean.FALSE;
      }

      if (!curr.hash.substring(0, difficulty).equals(hashTarget)) {
        return Boolean.FALSE;
      }
    }

    return Boolean.TRUE;
  }

}
