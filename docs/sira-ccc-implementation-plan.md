## SIRA CCC lib

The solution allows merchants to sell their own Equities and be compliant with CCC/SIRA regulation rules.  

The framework consists of two applications:

1) The library that creates Equity records in a particular block-chain. At this moment 3 blockchains are supported. 
The library itself is open source, thus can be extended by any new crypto or fiat currency instrument.

2) The Back-office application. The module is also an open source client-server application. 
It helps a merchant accept/reject new BUY orders and publish the resulting transaction in a blockchain.
The back-office application provides REST API and GUI interface. So each party can check regulatory information, or just see a list of transactions.


[Dogecoin-core](https://github.com/dogecoin/dogecoin) 

The solution is relaying on two dependencies:
- [https://www.mojeid.pl/](https://www.mojeid.pl) to verify identity;
- [https://www.bm.pkobp.pl/](https://www.bm.pkobp.pl/) - as a PPRA registry;

Below is the overall flow-diagram:



Future has come!
