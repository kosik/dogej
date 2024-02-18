## doge-j

The lib is an open source toolkit to operate with money straight in the Bitcoin derived currency block-chains.

The purpose of this library is open comprehensive implementation of JSON-RPC protocol in Java.

The library has done in an Object oriented style. All commands sent to Node are converted from JSON-RPC objects to classic POJO. The library makes this part of the work automatic.

Below is an example of how to receive block header information, and how to send a monetary transfer:

```java
    final DogecoinNodeClient dogecoinClient
          = new DogecoinNodeClient("http://127.0.0.1:22555", "myusername", "secret");

    String firstHash = dogecoinClient.getBlockchainAPI().getBlockHash(0L);
    BlockHeader blockHeader = dogecoinClient.getBlockchainAPI().getBlockHeader(firstHash);

    Double balance = dogecoinClient.getWalletAPI().getBalance();
    String transactionId = dogecoinClient.getWalletAPI().sendToAddress("ADDRESS-HERE", 200D);

```
No need more to focus on protocol specifics and other routines. Just work on your application logic. 

Find more examples [here](https://github.com/kosik/dogej/tree/main/utility/src/main/java/org/dogej).

## Configurations

To use DOGE-J lib you need a configured node server. To save your time, here are instructions how do this fast with Dogecoin:
1. Download binary distribution [here](https://github.com/dogecoin/dogecoin/releases/).
2. Set an environment variable to the distro.
```
export DOGE_HOME=/var/opt/dogecoin-1.14.6
export PATH=$PATH:$DOGE_HOME/bin
```
3. Generate node user and password. The CLI utility for this is in Dogecoin source code [here](https://github.com/dogecoin/dogecoin/tree/master/share/rpcuser). The output string should be appended to `dogecoin.conf` file.
```
rpcauth=myusername:f7efda5c189b999524f151318c0c86$d5b51b3beffbc02b724e5d095828e0bc8b2456e9ac8757ae3211a5d9b16a22ae
```
4. Launch node in test mode.
```
dogecoin-qt -testnet -server -daemon -rest -rpcauth -alerts -datadir=/var/opt/.dogecoin -conf=/var/opt/.dogecoin/dogecoin.conf &
```

5. Import DOGE-J into your application as a regular Maven dependency. Done! Accept crypto has never been as simple as it is now. You have plenty of time to focus on your business itself.
```xml
<dependency>
    <groupId>org.dogej</groupId>
    <artifactId>dogej-core</artifactId>
    <version>000-SNAPSHOT</version>
</dependency>

<dependency>
    <groupId>org.dogej</groupId>
    <artifactId>dogej-models</artifactId>
    <version>000-SNAPSHOT</version>
</dependency>
```

Future has come!
