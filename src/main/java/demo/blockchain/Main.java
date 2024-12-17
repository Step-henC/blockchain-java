package demo.blockchain;

import demo.blockchain.model.Block;
import demo.blockchain.model.Blockchain;

import com.google.gson.*;

public class Main {

    /*
     * Method to validate blockchain
     * In Bitcoin, nodes share blockchain and longest valid chain is accepted
     * Proof of work prevents hackers from creating newer, longer chains to tamper
     * with data
     * Hackers need more computational power than all peers combined
     */

    public static void main(String[] args) throws Exception {

        Blockchain blockChain = new Blockchain(5);

        blockChain.addBlock(new Block("Hi, I am the first block", "0"));
        blockChain.getBlockAtIndex(0).mineBlock(blockChain.difficulty);

        blockChain.addBlock(
                new Block("Hi, I am the second block", blockChain.getBlockAtIndex(blockChain.getSize() - 1).hash));
        blockChain.getBlockAtIndex(1).mineBlock(blockChain.difficulty);

        blockChain.addBlock(
                new Block("Hi, I am the third block", blockChain.getBlockAtIndex(blockChain.getSize() - 1).hash));
        blockChain.getBlockAtIndex(2).mineBlock(blockChain.difficulty);

        String blockChainJson = new GsonBuilder().setPrettyPrinting().create().toJson(blockChain);

        System.out.println("Full BlockChain: " + blockChainJson);
        System.out.println("Valid?: " + blockChain.isChainValid());

    }
}