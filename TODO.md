make it so sessionctx doesnt need to be passed-in for threeds/currencyconversion submission/approval
- store it in objects


in-progress:
- parsing order notifications for xml
- multiple payments sent in order status
- journal
    - sent back for notifications *or* inquiries

immediate:
- can be multiple payments
- pay as order / futurepay
- batch orders
- payouts
- refunds
- mobile wallets
- industry data (airline data etc)

testing:
- cse (tokenisation?)

long-term:
- strict validation everywhere.
- enforce final on classes, people will otherwise try to override parts of classes with hacks
- proxy support for connections
- whether java 1.7 should be supported
