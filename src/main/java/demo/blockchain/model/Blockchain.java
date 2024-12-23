package demo.blockchain.model;

/* 
 * DoublyLinkedList BlockChain
 * 
*/

public class Blockchain {

  private Block head;

  public int difficulty;

  /*
   * @params int difficulty
   * 
   */
  public Blockchain(int difficulty) {
    this.head = null;

    this.difficulty = difficulty;
  }

  public void addBlock(Block block) {
    if (head == null) {
      head = block;
    } else {
      block.prev = head;
      head.next = block;

    }
  }

  public Block getBlockAtIndex(int index) {

    if (head == null) {
      return head;
    }

    if (index == 0) {
      return head;
    }

    Block curr = head;

    for (int i = 0; i < index && curr != null; i++) {
      curr = curr.next;
    }

    return curr == null ? head : curr;
  }

  public int getSize() {

    if (head == null) {
      return 0;
    }

    Block curr = head;

    int count = 0;

    while (curr != null) {
      count++;
      curr = curr.next;
    }

    return count;
  }

  public Boolean isChainValid() {
    String hashTarget = (new String(new char[difficulty])).replace('\u0000', '0');

    if (getSize() <= 1) {
      return true;
    }

    for (int i = 1; i < getSize(); ++i) {
      Block curr = (Block) getBlockAtIndex(i);
      Block prev = (Block) getBlockAtIndex(i - 1);
      if (!curr.hash.equals(curr.calculateHash())) {
        return Boolean.FALSE;
      }

      if (!prev.hash.equals(curr.prev.hash)) {
        return Boolean.FALSE;
      }

      if (!curr.hash.substring(0, difficulty).equals(hashTarget)) {
        return Boolean.FALSE;
      }
    }

    return Boolean.TRUE;
  }

}
