package de.imedia24.shop.controller

import de.imedia24.shop.domain.product.ProductCreateRequest
import de.imedia24.shop.domain.product.ProductPartialUpdateRequest
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.service.ProductService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
class ProductController(private val productService: ProductService) {

    private val logger = LoggerFactory.getLogger(ProductController::class.java)!!

    @GetMapping("/product/{sku}", produces = ["application/json;charset=utf-8"])
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
    fun findProductsDetailsListBySkus(
            @RequestParam("skus") skus: List<String>
    ): ResponseEntity<List<ProductResponse>> {
        val products = productService.findProductsDetailsListBySkus(skus)
        return ResponseEntity.ok(products)
    }

    @PostMapping("/product")
    fun addProduct(@RequestBody request: ProductCreateRequest): ResponseEntity<ProductResponse> {
        // Create and save the product using the productService
        val product = productService.addProduct(request)

        // Return the created product in the response with a status code of 201 (Created)
        return ResponseEntity.status(HttpStatus.CREATED).body(product)
    }

    @PatchMapping("/product/{sku}", produces = ["application/json;charset=utf-8"])
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
