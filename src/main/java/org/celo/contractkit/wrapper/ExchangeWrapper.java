package org.celo.contractkit.wrapper;

import org.celo.contractkit.contract.Exchange;
import org.celo.contractkit.protocol.CeloGasProvider;
import org.celo.contractkit.protocol.CeloTransactionManager;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;

import java.math.BigInteger;

/**
 * Contract that allows to exchange StableToken for GoldToken and vice versa
 * using a Constant Product Market Maker Model
 */
public class ExchangeWrapper extends BaseWrapper<Exchange> {

    public ExchangeWrapper(Exchange contract, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        super(contract, web3j, transactionManager, gasProvider);
    }

    public static ExchangeWrapper load(String contractAddress, Web3j web3j, CeloTransactionManager transactionManager, CeloGasProvider gasProvider) {
        Exchange contract = Exchange.load(contractAddress, web3j, transactionManager, gasProvider);
        return new ExchangeWrapper(contract, web3j, transactionManager, gasProvider);
    }

    public RemoteCall<Exchange> deploy() {
        return null; //Exchange.deploy(web3j, transactionManager, gasProvider);
    }

    /**
     * Exchanges sellAmount of sellToken in exchange for at least minBuyAmount of buyToken
     * Requires the sellAmount to have been approved to the exchange
     * @param sellAmount The amount of sellToken the user is selling to the exchange
     * @param minBuyAmount The minimum amount of buyToken the user has to receive for this
     * transaction to succeed
     * @param sellGold `true` if gold is the sell token
     * @return The amount of buyToken that was transfered
     */
    public RemoteFunctionCall<TransactionReceipt> exchange(BigInteger sellAmount, BigInteger minBuyAmount, Boolean sellGold) {
        return contract.exchange(sellAmount, minBuyAmount, sellGold);
    }

    /**
     * @dev Returns the amount of buyToken a user would get for sellAmount of sellToken
     * @param sellAmount The amount of sellToken the user is selling to the exchange
     * @param sellGold `true` if gold is the sell token
     * @return The corresponding buyToken amount.
     */
    public RemoteFunctionCall<BigInteger> getBuyTokenAmount(BigInteger sellAmount, Boolean sellGold) {
        return contract.getBuyTokenAmount(sellAmount, sellGold);
    }

    /**
     * Returns the amount of sellToken a user would need to exchange to receive buyAmount of
     * buyToken.
     * @param buyAmount The amount of buyToken the user would like to purchase.
     * @param sellGold `true` if gold is the sell token
     * @return The corresponding sellToken amount.
     */
    public RemoteFunctionCall<BigInteger> getSellTokenAmount(BigInteger buyAmount, Boolean sellGold) {
        return contract.getSellTokenAmount(buyAmount, sellGold);
    }

    /**
     * Returns the buy token and sell token bucket sizes, in order. The ratio of
     * the two also represents the exchange rate between the two.
     * @param sellGold `true` if gold is the sell token
     * @return [buyTokenBucket, sellTokenBucket]
     */
    public RemoteFunctionCall<Tuple2<BigInteger, BigInteger>> getBuyAndSellBuckets(Boolean sellGold) {
        return contract.getBuyAndSellBuckets(sellGold);
    }

    public RemoteFunctionCall<TransactionReceipt> buy(BigInteger buyAmount, BigInteger maxSellAmount, Boolean buyGold) {
        return contract.buy(buyAmount, maxSellAmount, buyGold);
    }

    public RemoteFunctionCall<TransactionReceipt> sell(BigInteger sellAmount, BigInteger minBuyAmount, Boolean sellGold) {
        return contract.sell(sellAmount, minBuyAmount, sellGold);
    }

}
