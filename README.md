# VIES client

A Java client that uses the [VIES (VAT Information Exchange System)](http://ec.europa.eu/taxation_customs/vies/) SOAP
service to check whether an European VAT number is valid or not. Whether the VAT number is valid, the service returns,
if available, the business name and address.

## Usage

If you are using Gradle, include repository and dependency on the VIES client in your `build.gradle` file.

```groovy
repositories {
  maven {
    url  "https://dl.bintray.com/gilbertotcc/utils" 
  }
}

dependencies {
  compile "com.github.gilbertotcc.vies:vies-client:${version}"
}
```

Then, the client can be used as shown in the example below.

```java
public static void main() throws ViesServiceException {
  VatNumberInformation vatNumberInformation = ViesClient.create()
    .checkVatNumber(VatNumber.vatNumber(Country.ITALY, "100"));

  System.out.println(format("VAT number is %s. Business info: name=%s, address=%s",
    vatNumberInformation.isValid() ? "valid" : "not valid",
    vatNumberInformation.getBusiness().getName(),
    vatNumberInformation.getBusiness().getAddress()
  )); // Prints: VAT number is valid. Business info: name=John Doe, address=123 Main St, Anytown, UK
}
```
