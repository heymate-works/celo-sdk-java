package org.celo.contractkit;

import org.celo.contractkit.contract.GoldToken;
import org.celo.contractkit.protocol.CeloRawTransaction;
import org.celo.contractkit.protocol.CeloRawTransactionBuilder;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;
import org.web3j.protocol.http.HttpService;
import org.web3j.utils.Convert;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.celo.contractkit.ContractKitOptions.GANACHE_OPTIONS;
import static org.celo.contractkit.TestData.*;
import static org.celo.contractkit.protocol.CeloGasProvider.GAS_LIMIT;
import static org.celo.contractkit.protocol.CeloGasProvider.GAS_PRICE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ContractKitTest {
    ContractKit contractKit;

    BigDecimal toEther(BigInteger value) {
        return Convert.fromWei(new BigDecimal(value), Convert.Unit.ETHER);
    }

    @Before
    public void initialize() {
        Web3j web3j = Web3j.build(new HttpService("http://localhost:8545"));

        contractKit = new ContractKit(web3j, GANACHE_OPTIONS);
        contractKit.addAccount(DERIV_PRIVATE_KEYS[0]);
    }

    @Test
    public void testContractKitSetup() {
        assertNotNull(contractKit.web3j);
    }

    @Test
    public void testGetTotalBalance() throws Exception {
        AccountBalance balance = contractKit.getTotalBalance(contractKit.getAddress());

        assertEquals(1, toEther(balance.CELO).compareTo(BigDecimal.valueOf(0.1)));
        assertEquals(1, toEther(balance.cUSD).compareTo(BigDecimal.valueOf(0.1)));
        assertEquals(BigInteger.ZERO, balance.lockedCELO);
        assertEquals(BigInteger.ZERO, balance.pending);
    }

    @Test
    public void testGetGasPriceMinimum() throws Exception {
        BigInteger oneGwei = Convert.toWei(BigDecimal.ONE, Convert.Unit.GWEI).toBigInteger();

        CeloRawTransaction tx = CeloRawTransaction.createCeloTransaction(
                BigInteger.ZERO,
                GAS_PRICE,
                GAS_LIMIT,
                PUBLIC_KEY_2,
                oneGwei,
                contractKit.contracts.addressFor(CeloContract.StableToken),
                null,
                null
        );
        BigInteger gasPrice = contractKit.getGasPriceMinimum(tx.getFeeCurrency(), BigInteger.ZERO);
        assertEquals(BigInteger.valueOf(8_000_000_000L), gasPrice);
    }

    @Test
    @Ignore
    public void testContractDeploy() throws Exception {
        CeloRawTransaction tx = new CeloRawTransactionBuilder()
                .setData(GoldToken.BINARY)
                .setTo(contractKit.contracts.addressFor(CeloContract.GoldToken))
                .build();
        EthSendTransaction receipt = contractKit.sendTransaction(tx, null);
        assertNotNull(receipt.getTransactionHash());
    }
}
