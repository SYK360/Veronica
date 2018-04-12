# Verónica REST API
<!-- TOC depthFrom:1 depthTo:2 withLinks:1 updateOnSave:1 orderedList:0 -->

- [Veronica REST API](#veronica-rest-api)
	- [Preamble](#preamble)
	- [Request & Response Examples](#request--response-examples)
	- [Documentation history](#documentation-history)
	- [Authors](#authors)

<!-- /TOC -->
## Preamble
Veronica REST API is a set of RESTful web services that provide an abstraction layer which allows for easy issue of electronic invoicing, according with the Ecuadorian regulations imposed by the "Servicio de Rentas Internas".

## Request & Response Examples

### API Resources

  - [POST /generacion/factura](#post-generacionfactura)
  - [POST /generacion/guia-remision](#post-generacionguia-remision)
  - [POST /generacion/nota-credito](#post-generacionnota-credito)
  - [POST /generacion/nota-debito](#post-generacionnota-debito)
  - [POST /generacion/comprobante-retencion](#post-generacioncomprobante-retencion)
  - [POST /firma/comprobante-electronico](#post-firmacomprobante-electronico)
  - [POST /sri/enviar](#post-srienviar)
  - [POST /sri/autorizar](#post-sriautorizar)
  - [POST /sri/emitir/factura](#post-sriemitirfactura)
  - [POST /sri/emitir/guia-remision](#post-sriemitirguia-remision)
  - [POST /sri/emitir/nota-credito](#post-sriemitirnota-credito)
  - [POST /sri/emitir/nota-debito](#post-sriemitirnota-debito)
  - [POST /sri/emitir/comprobante-retencion](#post-sriemitircomprobante-retencion)

### POST /generacion/factura

Example: http://localhost:8080/generacion/factura

Request body:
{
	"comprobante": {
		"id": "comprobante",
		"version": "1.0.0",
		"infoTributaria": {
			"ambiente": "1",
			"tipoEmision": "1",
			"razonSocial": "ROLANDOPALERMO CONSULTING CIA. LTDA.",
			"nombreComercial": "RP Consulting",
			"ruc": "1791261151001",
			"claveAcceso": "1004201801179126115100110010010000001170000308514",
			"codDoc": "01",
			"estab": "001",
			"ptoEmi": "001",
			"secuencial": "000000117",
			"dirMatriz": "Cdla. Urdesa Central - Calle Cedros #108 y Víctor Emilio Estrada"
		},
		"campoAdicional": [{
			"value": "LOS PERALES Y AV. ELOY ALFARO",
			"nombre": "Dirección"
		}, {
			"value": "2123123",
			"nombre": "Teléfono"
		}, {
			"value": "gfeguiguren@sri.gob.ec",
			"nombre": "Email"
		}],
		"infoFactura": {
			"fechaEmision": "10/04/2018",
			"dirEstablecimiento": "KM 2.5 Av. Juan Tanca Marengo S/N y Av. A. Freire",
			"contribuyenteEspecial": "000",
			"obligadoContabilidad": "SI",
			"tipoIdentificacionComprador": "04",
			"guiaRemision": null,
			"razonSocialComprador": "HIVIMAR S.A.",
			"identificacionComprador": "0990129185001",
			"totalSinImpuestos": 6316.80,
			"totalDescuento": 0.00,
			"totalImpuesto": [{
				"codigo": "2",
				"codigoPorcentaje": "2",
				"baseImponible": 6316.80,
				"tarifa": 12.00,
				"valor": 758.02
			}],
			"propina": 0.00,
			"importeTotal": 7074.82,
			"moneda": "DOLAR"
		},
		"detalle": [{
			"codigoPrincipal": "001",
			"codigoAuxiliar": "001",
			"descripcion": "SERVICIO DE CONSULTORÍA",
			"cantidad": 1.00,
			"precioUnitario": 6316.80,
			"descuento": 0.00,
			"precioTotalSinImpuesto": 6316.80,
			"detAdicional": null,
			"impuesto": [{
				"codigo": "2",
				"codigoPorcentaje": "2",
				"tarifa": 12.00,
				"baseImponible": 6316.80,
				"valor": 758.02
			}]
		}]
	}
}

Response body:
{
    "codigo": "0",
    "mensaje": null,
    "contenido": "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/..."
}

### POST /generacion/guia-remision

Example: http://localhost:8080/generacion/guia-remision

### POST /generacion/nota-credito

Example: http://localhost:8080/generacion/nota-credito

### POST /generacion/nota-debito

Example: http://localhost:8080/generacion/nota-debito

### POST /generacion/comprobante-retencion

Example: http://localhost:8080/generacion/comprobante-retencion

### POST /firma/comprobante-electronico

Example: http://localhost:8080/generacion/comprobante-electronico

### POST /sri/enviar

Example: http://localhost:8080/sri/enviar

### POST /sri/autorizar

Example: http://localhost:8080/sri/autorizar

### POST /sri/emitir/factura

Example: http://localhost:8080/sri/emitir/factura

### POST /sri/emitir/guia-remision

Example: http://localhost:8080/sri/emitir/guia-remision

### POST /sri/emitir/nota-credito

Example: http://localhost:8080/sri/emitir/nota-credito

### POST /sri/emitir/nota-debito

Example: http://localhost:8080/sri/emitir/nota-debito

### POST /sri/emitir/comprobante-retencion

Example: http://localhost:8080/sri/emitir/comprobante-retencion
  
## Documentation history

- V1: 2018-04-12, first draft.

## Authors

| [![](https://avatars1.githubusercontent.com/u/11875482?v=4&s=80)](https://github.com/rolandopalermo) |
|-|
| [@rolandopalermo](https://github.com/rolandopalermo) |