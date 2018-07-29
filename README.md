# VIES client

A Java client which uses [VIES (VAT Information Exchange System)](http://ec.europa.eu/taxation_customs/vies/)
SOAP service to check if a European VAT number is valid and return, whether it is valid, the business
information associated to it (_name_ and _address_).

## Usage with Maven

Add the Bintray repository and the client dependency in the POM file.

```xml
<repositories>
    <repository>
        <id>bintray-gilbertotcc-utils</id>
        <name>bintray</name>
        <url>https://dl.bintray.com/gilbertotcc/utils</url>
    </repository>
</repositories>

[...]

<dependencies>
    <dependency>
        <groupId>com.github.gilbertotcc.vies</groupId>
        <artifactId>vies-client</artifactId>
        <version>1.0.1</version>
    </dependency>
</dependencies>
```

Then, instantiate the client and call `ViesClient::checkVatNumber` method to verify if
a VAT number is valid. If it is valid, service returns also business name and address
associated to the VAT number.

```java
VatNumber vatNumber = VatNumber.of(Country.ITALY, "0123456789");
VatNumberInformation res = ViesClient.create().checkVatNumber(vatNumber);
```
