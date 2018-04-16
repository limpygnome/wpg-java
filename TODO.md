make it so sessionctx doesnt need to be passed-in for threeds
- store it in objects


in-progress:
- connect / read timeouts
- integration docs / javadoc / enforce validation
- basic validation
- unit tests

testing / examples:
- cse (tokenisation?)
- threeds submit pares
- order modifications (single and batch)
- payouts
- tokenisation management
- tokenised payment
- batch orders
- pay as order / futurepay

immediate:
- debug logging to output XML; would be useful for e.g. unrecognized responses etc

long-term:
- strict validation
- full unit test coverage
- enforce final on classes, people will otherwise try to override parts of classes with hacks
- proxy support for connections
- whether java 1.7 should be supported

descoped:
- fast funds
- order inquiries (batch and non-batch)
    - bad practice
- mobile wallets
- industry data (airline data etc)
- pos integration / iso8583
- currency conversion
    - likely only payment pages, looks like direct auto-converts
- APMs (except paypal)
- overriding details for a token payment request
