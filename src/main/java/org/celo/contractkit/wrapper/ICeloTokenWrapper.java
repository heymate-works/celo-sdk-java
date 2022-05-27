package org.celo.contractkit.wrapper;

import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;

import java.math.BigInteger;

public interface ICeloTokenWrapper {

    RemoteFunctionCall<TransactionReceipt> increaseAllowance(String spender, BigInteger value);

    RemoteFunctionCall<TransactionReceipt> decreaseAllowance(String spender, BigInteger value);

    RemoteFunctionCall<TransactionReceipt> approve(String spender, BigInteger value);

    RemoteFunctionCall<TransactionReceipt> transferWithComment(String to, BigInteger value, String comment);

    RemoteFunctionCall<TransactionReceipt> transferFrom(String from, String to, BigInteger value);

    RemoteFunctionCall<String> name();

    RemoteFunctionCall<String> symbol();

    RemoteFunctionCall<BigInteger> decimals();

    RemoteFunctionCall<BigInteger> allowance(String accountOwner, String spender);

    RemoteFunctionCall<BigInteger> totalSupply();

    RemoteFunctionCall<TransactionReceipt> transfer(String to, BigInteger value);

}
