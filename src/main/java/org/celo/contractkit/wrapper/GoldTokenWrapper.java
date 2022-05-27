package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.GoldToken;
import org.celo.contractkit.protocol.CeloGasProvider;
import org.celo.contractkit.protocol.CeloTransactionManager;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.EthGetBalance;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.io.IOException;
import java.math.BigInteger;

public class GoldTokenWrapper extends BaseWrapper<GoldToken> implements ICeloTokenWrapper {

    public GoldTokenWrapper(GoldToken contract, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        super(contract, web3j, transactionManager, gasProvider);
    }

    public static GoldTokenWrapper load(String contractAddress, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        GoldToken contract = GoldToken.load(contractAddress, web3j, transactionManager, gasProvider);
        return new GoldTokenWrapper(contract, web3j, transactionManager, gasProvider);
    }

    public RemoteCall<GoldToken> deploy() {
        return GoldToken.deploy(web3j, transactionManager, gasProvider);
    }

    public RemoteFunctionCall<Boolean> initialized() {
        return contract.initialized();
    }

    public RemoteFunctionCall<Boolean> isOwner() {
        return contract.isOwner();
    }

    public RemoteFunctionCall<String> owner() {
        return contract.owner();
    }

    public RemoteFunctionCall<String> registry() {
        return contract.registry();
    }

    public RemoteFunctionCall<TransactionReceipt> renounceOwnership() {
        return contract.renounceOwnership();
    }

    public RemoteFunctionCall<TransactionReceipt> transferOwnership(String newOwner) {
        return contract.transferOwnership(newOwner);
    }

    public RemoteFunctionCall<TransactionReceipt> initialize(String registryAddress) {
        return contract.initialize(registryAddress);
    }

    public RemoteFunctionCall<TransactionReceipt> transferWithComment(String to, BigInteger value, String comment) {
        return contract.transferWithComment(to, value, comment);
    }

    public RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger value) {
        return contract.approve(spender, value);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger value) {
        return contract.increaseAllowance(spender, value);
    }

    public RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender, BigInteger value) {
        return contract.decreaseAllowance(spender, value);
    }

    public RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger value) {
        return contract.transferFrom(from, to, value);
    }

    public RemoteFunctionCall<TransactionReceipt> mint(String to, BigInteger value) {
        return contract.mint(to, value);
    }

    public RemoteFunctionCall<String> name() {
        return contract.name();
    }

    public RemoteFunctionCall<String> symbol() {
        return contract.symbol();
    }

    public RemoteFunctionCall<BigInteger> decimals() {
        return contract.decimals();
    }

    public RemoteFunctionCall<BigInteger> totalSupply() {
        return contract.totalSupply();
    }

    public RemoteFunctionCall<BigInteger> allowance(String owner, String spender) {
        return contract.allowance(owner, spender);
    }

    public RemoteFunctionCall<TransactionReceipt> increaseSupply(BigInteger amount) {
        return contract.increaseSupply(amount);
    }

    public BigInteger balanceOf(String owner) throws IOException {
        EthGetBalance ethGetBalance = web3j.ethGetBalance(owner, DefaultBlockParameterName.LATEST).send();
        return ethGetBalance.getBalance();
    }

    public RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger value) {
        return contract.transfer(to, value);
    }
}
