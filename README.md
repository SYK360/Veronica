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
```json
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
```

Response body:
```json
{
	"codigo": "0",
	"mensaje": null,
	"contenido": "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/..."
}
```

### POST /generacion/guia-remision

Example: http://localhost:8080/generacion/guia-remision

### POST /generacion/nota-credito

Example: http://localhost:8080/generacion/nota-credito

### POST /generacion/nota-debito

Example: http://localhost:8080/generacion/nota-debito

### POST /generacion/comprobante-retencion

Example: http://localhost:8080/generacion/comprobante-retencion

### POST /firma/comprobante-electronico

Request body:
```json
{
	"contenidoXML" : "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/Pgo8ZmFjdHVyYSBpZD0iY29tcHJvYmFudGUiIHZlcnNpb249IjEuMC4wIj4KICAgIDxpbmZvVHJpYnV0YXJpYT4KICAgICAgICA8YW1iaWVudGU+MTwvYW1iaWVudGU+CiAgICAgICAgPHRpcG9FbWlzaW9uPjE8L3RpcG9FbWlzaW9uPgogICAgICAgIDxyYXpvblNvY2lhbD5ST0xBTkRPUEFMRVJNTyBDT05TVUxUSU5HIENJQS4gTFREQS48L3Jhem9uU29jaWFsPgogICAgICAgIDxub21icmVDb21lcmNpYWw+UlAgQ29uc3VsdGluZzwvbm9tYnJlQ29tZXJjaWFsPgogICAgICAgIDxydWM+MTc5MTI2MTE1MTAwMTwvcnVjPgogICAgICAgIDxjbGF2ZUFjY2Vzbz4xMDA0MjAxODAxMTc5MTI2MTE1MTAwMTEwMDEwMDEwMDAwMDAxMTcwMDAwMzA4NTE0PC9jbGF2ZUFjY2Vzbz4KICAgICAgICA8Y29kRG9jPjAxPC9jb2REb2M+CiAgICAgICAgPGVzdGFiPjAwMTwvZXN0YWI+CiAgICAgICAgPHB0b0VtaT4wMDE8L3B0b0VtaT4KICAgICAgICA8c2VjdWVuY2lhbD4wMDAwMDAxMTc8L3NlY3VlbmNpYWw+CiAgICAgICAgPGRpck1hdHJpej5DZGxhLiBVcmRlc2EgQ2VudHJhbCAtIENhbGxlIENlZHJvcyAjMTA4IHkgVsOtY3RvciBFbWlsaW8gRXN0cmFkYTwvZGlyTWF0cml6PgogICAgPC9pbmZvVHJpYnV0YXJpYT4KICAgIDxpbmZvRmFjdHVyYT4KICAgICAgICA8ZmVjaGFFbWlzaW9uPjEwLzA0LzIwMTg8L2ZlY2hhRW1pc2lvbj4KICAgICAgICA8ZGlyRXN0YWJsZWNpbWllbnRvPktNIDIuNSBBdi4gSnVhbiBUYW5jYSBNYXJlbmdvIFMvTiB5IEF2LiBBLiBGcmVpcmU8L2RpckVzdGFibGVjaW1pZW50bz4KICAgICAgICA8Y29udHJpYnV5ZW50ZUVzcGVjaWFsPjAwMDwvY29udHJpYnV5ZW50ZUVzcGVjaWFsPgogICAgICAgIDxvYmxpZ2Fkb0NvbnRhYmlsaWRhZD5TSTwvb2JsaWdhZG9Db250YWJpbGlkYWQ+CiAgICAgICAgPHRpcG9JZGVudGlmaWNhY2lvbkNvbXByYWRvcj4wNDwvdGlwb0lkZW50aWZpY2FjaW9uQ29tcHJhZG9yPgogICAgICAgIDxyYXpvblNvY2lhbENvbXByYWRvcj5ISVZJTUFSIFMuQS48L3Jhem9uU29jaWFsQ29tcHJhZG9yPgogICAgICAgIDxpZGVudGlmaWNhY2lvbkNvbXByYWRvcj4wOTkwMTI5MTg1MDAxPC9pZGVudGlmaWNhY2lvbkNvbXByYWRvcj4KICAgICAgICA8dG90YWxTaW5JbXB1ZXN0b3M+NjMxNi44MDwvdG90YWxTaW5JbXB1ZXN0b3M+CiAgICAgICAgPHRvdGFsRGVzY3VlbnRvPjAuMDA8L3RvdGFsRGVzY3VlbnRvPgogICAgICAgIDx0b3RhbENvbkltcHVlc3Rvcz4KICAgICAgICAgICAgPHRvdGFsSW1wdWVzdG8+CiAgICAgICAgICAgICAgICA8Y29kaWdvPjI8L2NvZGlnbz4KICAgICAgICAgICAgICAgIDxjb2RpZ29Qb3JjZW50YWplPjI8L2NvZGlnb1BvcmNlbnRhamU+CiAgICAgICAgICAgICAgICA8YmFzZUltcG9uaWJsZT42MzE2LjgwPC9iYXNlSW1wb25pYmxlPgogICAgICAgICAgICAgICAgPHRhcmlmYT4xMi4wMDwvdGFyaWZhPgogICAgICAgICAgICAgICAgPHZhbG9yPjc1OC4wMjwvdmFsb3I+CiAgICAgICAgICAgIDwvdG90YWxJbXB1ZXN0bz4KICAgICAgICA8L3RvdGFsQ29uSW1wdWVzdG9zPgogICAgICAgIDxwcm9waW5hPjAuMDA8L3Byb3BpbmE+CiAgICAgICAgPGltcG9ydGVUb3RhbD43MDc0LjgyPC9pbXBvcnRlVG90YWw+CiAgICAgICAgPG1vbmVkYT5ET0xBUjwvbW9uZWRhPgogICAgPC9pbmZvRmFjdHVyYT4KICAgIDxkZXRhbGxlcz4KICAgICAgICA8ZGV0YWxsZT4KICAgICAgICAgICAgPGNvZGlnb1ByaW5jaXBhbD4wMDE8L2NvZGlnb1ByaW5jaXBhbD4KICAgICAgICAgICAgPGNvZGlnb0F1eGlsaWFyPjAwMTwvY29kaWdvQXV4aWxpYXI+CiAgICAgICAgICAgIDxkZXNjcmlwY2lvbj5TRVJWSUNJTyBERSBDT05TVUxUT1LDjUE8L2Rlc2NyaXBjaW9uPgogICAgICAgICAgICA8Y2FudGlkYWQ+MS4wMDwvY2FudGlkYWQ+CiAgICAgICAgICAgIDxwcmVjaW9Vbml0YXJpbz42MzE2LjgwPC9wcmVjaW9Vbml0YXJpbz4KICAgICAgICAgICAgPGRlc2N1ZW50bz4wLjAwPC9kZXNjdWVudG8+CiAgICAgICAgICAgIDxwcmVjaW9Ub3RhbFNpbkltcHVlc3RvPjYzMTYuODA8L3ByZWNpb1RvdGFsU2luSW1wdWVzdG8+CiAgICAgICAgICAgIDxpbXB1ZXN0b3M+CiAgICAgICAgICAgICAgICA8aW1wdWVzdG8+CiAgICAgICAgICAgICAgICAgICAgPGNvZGlnbz4yPC9jb2RpZ28+CiAgICAgICAgICAgICAgICAgICAgPGNvZGlnb1BvcmNlbnRhamU+MjwvY29kaWdvUG9yY2VudGFqZT4KICAgICAgICAgICAgICAgICAgICA8dGFyaWZhPjEyLjAwPC90YXJpZmE+CiAgICAgICAgICAgICAgICAgICAgPGJhc2VJbXBvbmlibGU+NjMxNi44MDwvYmFzZUltcG9uaWJsZT4KICAgICAgICAgICAgICAgICAgICA8dmFsb3I+NzU4LjAyPC92YWxvcj4KICAgICAgICAgICAgICAgIDwvaW1wdWVzdG8+CiAgICAgICAgICAgIDwvaW1wdWVzdG9zPgogICAgICAgIDwvZGV0YWxsZT4KICAgIDwvZGV0YWxsZXM+CiAgICA8aW5mb0FkaWNpb25hbD4KICAgICAgICA8Y2FtcG9BZGljaW9uYWwgbm9tYnJlPSJEaXJlY2Npw7NuIj5MT1MgUEVSQUxFUyBZIEFWLiBFTE9ZIEFMRkFSTzwvY2FtcG9BZGljaW9uYWw+CiAgICAgICAgPGNhbXBvQWRpY2lvbmFsIG5vbWJyZT0iVGVsw6lmb25vIj4yMTIzMTIzPC9jYW1wb0FkaWNpb25hbD4KICAgICAgICA8Y2FtcG9BZGljaW9uYWwgbm9tYnJlPSJFbWFpbCI+Z2ZlZ3VpZ3VyZW5Ac3JpLmdvYi5lYzwvY2FtcG9BZGljaW9uYWw+CiAgICA8L2luZm9BZGljaW9uYWw+CjwvZmFjdHVyYT4K"
}
```

Response body:
```json
{
    "codigo": "0",
    "mensaje": null,
    "contenido": "PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iVVRGLTgiIHN0YW5kYWxvbmU9InllcyI/..."
}
```

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