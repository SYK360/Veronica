<h1 align="center">
  <a href="#"><img src="https://raw.githubusercontent.com/rolandopalermo/Veronica/master/static/veronica.png" alt="Markdownify" width="300"></a>
  <br>
  Veronica REST API
  <br>
</h1>

<h4 align="center">E-Invoicing Rest API for the integration with "Servicio de Rentas Internas" Web services 🇪🇨.</h4>

<p align="center">
  <a href="https://www.paypal.me/ride2pdf" target="_blank"><img alt="PayPal Donate" src="http://ionicabizau.github.io/badges/paypal.svg"></a>
</p>

<!-- TOC depthFrom:1 depthTo:2 withLinks:1 updateOnSave:1 orderedList:0 -->
Table of contents
=================
- [Veronica REST API](#veronica-rest-api)
	- [Preamble](#preamble)
	- [Startup Settings](#startup-settings)
	- [Request & Response Examples](#request--response-examples)
	- [Documentation history](#documentation-history)
	- [Authors](#authors)

<!-- /TOC -->
## Preamble
`Veronica REST API` is a set of RESTful web services that provide an abstraction layer which allows for easy issue of electronic invoicing, according with the Ecuadorian regulations imposed by the "Servicio de Rentas Internas".

## Startup Settings
If you want to make modifications to `Veronica`, you must configure your Maven repository appropriately, making sure to use the following instructions:
1. You first need to go to the `Veronica`’s directory and after that, you have to move to additional_libs directory. For Linux, Windows or Mac use the command:
```bash
$ cd additional_libs
```
2. As second step, you must install all the JAR files from additional_libs to the local Maven repository using the following commands:
```bash
mvn install:install-file -Dfile=jss-4.2.5.jar -DgroupId=org.mozilla -DartifactId=jss -Dversion=4.2.5 -Dpackaging=jar
mvn install:install-file -Dfile=MITyCLibAPI-1.0.4.jar -DgroupId=es.mityc.javasign -DartifactId=api -Dversion=1.0.4 -Dpackaging=jar
mvn install:install-file -Dfile=MITyCLibCert-1.0.4.jar -DgroupId=es.mityc.javasign -DartifactId=cert -Dversion=1.0.4 -Dpackaging=jar
mvn install:install-file -Dfile=MITyCLibOCSP-1.0.4.jar -DgroupId=es.mityc.javasign -DartifactId=ocsp  -Dversion=1.0.4 -Dpackaging=jar
mvn install:install-file -Dfile=MITyCLibPolicy-1.0.4.jar -DgroupId=es.mityc.javasign -DartifactId=policy -Dversion=1.0.4 -Dpackaging=jar
mvn install:install-file -Dfile=MITyCLibTrust-1.0.4.jar -DgroupId=es.mityc.javasign -DartifactId=trust -Dversion=1.0.4 -Dpackaging=jar
mvn install:install-file -Dfile=MITyCLibTSA-1.0.4.jar -DgroupId=es.mityc.javasign -DartifactId=tsa -Dversion=1.0.4 -Dpackaging=jar
mvn install:install-file -Dfile=MITyCLibXADES-1.0.4.jar -DgroupId=es.mityc.javasign -DartifactId=xades -Dversion=1.0.4 -Dpackaging=jar
mvn install:install-file -Dfile=xmlsec-1.4.2-ADSI-1.0.jar -DgroupId=org.apache.xmlsec-adsi -DartifactId=xmlsec-adsi -Dversion=1.4.2 -Dpackaging=jar
```
3.- This project provides two maven profiles. Using the next command, you will  be able the choose the correct profile according to your environment (DEV or PRD). 
```bash
mvn clean install -P development
```

```bash
mvn clean install -P production
```
Change the content with appropriate values, according with your configuration.

## Documentation
http://localhost:8080/veronica/swagger-ui.html

## Documentation history

- V1: 2018-04-12, first draft.
- V2: 2018-04-27, enable maven profiles.
- V3: 2018-04-28, enable swagger2 for api documentation.

## Authors

| [![](https://avatars1.githubusercontent.com/u/11875482?v=4&s=80)](https://github.com/rolandopalermo) |
|-|
| [@rolandopalermo](https://github.com/rolandopalermo) |