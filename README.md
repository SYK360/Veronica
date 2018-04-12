# Verónica REST API
<!-- TOC depthFrom:1 depthTo:2 withLinks:1 updateOnSave:1 orderedList:0 -->

- [Verónica REST API](#veronica-rest-api)
	- [Preamble](#preamble)
	- [Request & Response Examples](#request--response-examples)
	- [Documentation history](#documentation-history)
	- [Authors](#authors)

<!-- /TOC -->
## Preamble
Veronica REST API is a set of RESTful web services that provide an abstraction layer which allows for easy issue of electronic invoicing, according with the Ecuadorian regulations imposed by the "Servicio de Rentas Internas".

## Request & Response Examples

### API Resources

  - [POST /generacion/factura](#post-generacion-factura)
  - [POST /generacion/guia-remision](#post-generacion-guia-remision)
  - [POST /generacion/nota-credito](#post-generacion-nota-credito)
  - [POST /generacion/nota-debito](#post-generacion-nota-debito)
  - [POST /generacion/comprobante-retencion](#post-generacion-comprobante-retencion)
  - [POST /firma/comprobante-electronico](#post-firma-comprobante-electronico)
  - [POST /sri/enviar](#post-firma-comprobante-electronico)
  - [POST /sri/autorizar](#post-firma-comprobante-electronico)
  - [POST /sri/emitir/factura](#post-sri-emitir-factura)
  - [POST /sri/emitir/guia-remision](#post-sri-emitir-guia-remision)
  - [POST /sri/emitir/nota-credito](#post-sri-emitir-nota-credito)
  - [POST /sri/emitir/nota-debito](#post-sri-emitir-nota-debito)
  - [POST /sri/emitir/comprobante-retencion](#post-sri-emitir-comprobante-retencion)
  
## Documentation history

- V1: 2018-04-12, first draft.

## Authors

| [![](https://avatars1.githubusercontent.com/u/11875482?v=4&s=80)](https://github.com/rolandopalermo) |
|-|
| [@rolandopalermo](https://github.com/rolandopalermo) |