import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import demo.blockchain.model.Block;
import demo.blockchain.model.Blockchain;

public class BlockchainTest {

  @Test
  public void testBlockChainAdd() {

    Blockchain chain = new Blockchain(5);

    Block block = new Block("first mock", null, null);
    block.mineBlock(chain.difficulty);
    chain.addBlock(block);

    Block add = new Block("second mock", block, null);
    add.mineBlock(chain.difficulty);
    chain.addBlock(add);

    assertEquals(2, chain.getSize());
    assertEquals(block, chain.getBlockAtIndex(0));
    assertEquals(add, chain.getBlockAtIndex(1));

    assertTrue(chain.isChainValid());

  }

  @Test
  public void testChainInvalid() {

    Block block = new Block("first mock", null, null);

    Blockchain chain = new Blockchain(5);
    block.mineBlock(chain.difficulty);
    chain.addBlock(block);

    Block notHead = new Block("bad block hacker tried to insert", null, null);

    chain.addBlock(new Block("wrong prev hash", notHead, null));
    chain.getBlockAtIndex(2).mineBlock(chain.difficulty);

    assertFalse(chain.isChainValid());

    // Assert that an index exceeding blockchain length returns head
    assertEquals(chain.getBlockAtIndex(10), block);
  }

  @Test
  public void testBlockChainValidOne() {

    Block block = new Block("first mock", null, null);

    Blockchain chain = new Blockchain(5);
    chain.addBlock(block);

    assertEquals(1, chain.getSize());
    assertEquals(block, chain.getBlockAtIndex(0));

    assertTrue(chain.isChainValid());
  }
}
