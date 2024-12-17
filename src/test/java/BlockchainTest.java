import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import demo.blockchain.model.Block;
import demo.blockchain.model.Blockchain;

public class BlockchainTest {

  @Test
  public void testBlockChainFunctions() {

    Block block = new Block("first mock", "0");

    Blockchain chain = new Blockchain(5);
    chain.addBlock(block);

    assertEquals(1, chain.getSize());
    assertEquals(block, chain.getBlockAtIndex(0));
    assertTrue(chain.isChainValid());
  }

  @Test
  public void testChainInvalid() {

    Block block = new Block("first mock", "0");

    Blockchain chain = new Blockchain(5);
    chain.addBlock(block);

    chain.addBlock(new Block("wrong prev hash", "1"));

    assertFalse(chain.isChainValid());
  }
}
