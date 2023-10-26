package de.imedia24.shop.controller

import de.imedia24.shop.domain.product.ProductCreateRequest
import de.imedia24.shop.domain.product.ProductPartialUpdateRequest
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.service.ProductService
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@Api(tags = ["Product Controller"]) // Define the API group
class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java)!!

    @GetMapping("/product/{sku}", produces = ["application/json;charset=utf-8"])
    @ApiOperation("Get product by SKU") // Describe the purpose of the endpoint
    fun findProductBySku(
        @PathVariable("sku") sku: String
    ): ResponseEntity<ProductResponse> {
        logger.info("Request for product $sku")

        val product = productService.findProductBySku(sku)
        return if(product == null) {
            ResponseEntity.notFound().build()
        } else {
            ResponseEntity.ok(product)
        }
    }

    @GetMapping("/products", produces = ["application/json;charset=utf-8"])
    @ApiOperation("Get product details by SKUs") // Describe the purpose of the endpoint
    fun findProductsDetailsListBySkus(
        @RequestParam("skus") skus: List<String>
    ): ResponseEntity<List<ProductResponse>> {
        val products = productService.findProductsDetailsListBySkus(skus)
        return ResponseEntity.ok(products)
    }

    @PostMapping("/product")
    @ApiOperation("Add a new product") // Describe the purpose of the endpoint
    fun addProduct(@RequestBody request: ProductCreateRequest): ResponseEntity<ProductResponse> {
        // Create and save the product using the productService
        val product = productService.addProduct(request)

        // Return the created product in the response with a status code of 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(product)
    }

    @PatchMapping("/product/{sku}", produces = ["application/json;charset=utf-8"])
    @ApiOperation("Partially update a product") // Describe the purpose of the endpoint
    fun updateProduct(
        @PathVariable("sku") sku: String,
        @RequestBody request: ProductPartialUpdateRequest
    ): ResponseEntity<ProductResponse> {
        // Logic to partially update the product using the productService
        val product = productService.partialUpdateProduct(sku, request)

        // Return the updated product in the response
        return ResponseEntity.ok(product)
    }
}
