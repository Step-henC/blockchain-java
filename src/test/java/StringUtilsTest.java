import org.junit.jupiter.api.Test;

import demo.blockchain.model.Block;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringUtilsTest {

  @Test
  public void testApplySha256() {
    Block block = new Block("mock", "0");

    String hash = block.calculateHash();

    assertEquals(block.hash, hash);
  }

}
