<h1 align="center">
  Worldpay Gateway Java SDK
</h1>

<h4 align="center">
  Use this SDK on your server-side to communicate with the <a href="https://www.worldpay.com/global/products/gateway-services">Worldpay Gateway (WPG)</a>.
</h4>

<p align="center">
  <img src="https://img.shields.io/badge/Java-8+-blue.svg" alt="Java 8+" />
  <a href="license.md">
    <img src="https://img.shields.io/badge/license-MIT-blue.svg" alt="MIT License" />
  </a>
</p>

## Features
- Payments
  - Cards __(includes tokenisation)__
  - Tokenised cards
  - ThreeDS authentication
  - Hosted Payment Pages
  - PayPal __(includes tokenisation)__
  - Client-side Encryption (CSE)
- Order inquiries
- Order notifications
- Modifications
    - Cancel order
- Batch modifications
    - Cancel or refund
    - Capture
    - Refund
- Wallets
    - Apple Pay

Upcoming:
- Payouts
- Recurring

## Examples
- [Examples](wpg-examples/src/test/java/com/worldpay/sdk/wpg/examples)

## Getting Started
Download the jar from the [releases](../../releases) page, or use Maven.

Add dependency to your `pom.xml`:

````
<dependency>
    <groupId>com.worldpay.sdk</groupId>
    <artifactId>wpg</artifactId>
</dependency>
````

## Requirements
Currently supports Java 8 onwards.

## Docs
- [JavaDoc](docs/javadoc/index.html)
- [Contribute & Support](docs/contribute-and-support.md)
- [Worldpay XML API Docs](http://support.worldpay.com/support/kb/gg/corporate-gateway-guide/content/home.htm)
- [Payment Service XML DTD](http://dtd.worldpay.com/v1/)
- [SDK Development](docs/development.md)
