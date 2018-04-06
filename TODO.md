make it so sessionctx doesnt need to be passed-in for threeds
- store it in objects


in-progress:
- pay as order / futurepay

testing / demos:
- cse (tokenisation?)
- threeds submit pares
- order modifications (single and batch)
- payouts
- tokenisation management

immediate:
- refunds
- batch orders
- debug logging to output XML; would be useful for e.g. unrecognized responses etc
- integration docs / javadoc / enforce validation
- basic validation

long-term:
- strict validation
- unit tests
- enforce final on classes, people will otherwise try to override parts of classes with hacks
- proxy support for connections
- whether java 1.7 should be supported

descoped:
- inquiries
    - bad practice
- mobile wallets
- industry data (airline data etc)
- pos integration / iso8583
