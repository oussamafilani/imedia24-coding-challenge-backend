package de.imedia24.shop.service

import de.imedia24.shop.db.entity.ProductEntity
import de.imedia24.shop.db.repository.ProductRepository
import de.imedia24.shop.domain.product.ProductCreateRequest
import de.imedia24.shop.domain.product.ProductPartialUpdateRequest
import de.imedia24.shop.domain.product.ProductResponse
import de.imedia24.shop.domain.product.ProductResponse.Companion.toProductResponse
import de.imedia24.shop.exception.ProductNotFoundException
import org.springframework.stereotype.Service
import java.time.ZonedDateTime

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun findProductBySku(sku: String): ProductResponse? {
        val productEntity = productRepository.findBySku(sku)
        return productEntity?.toProductResponse()
    }

    fun findProductsDetailsListBySkus(skus: List<String>): List<ProductResponse> {
        val products = mutableListOf<ProductResponse>()

        for (sku in skus) {
            productRepository.findBySku(sku)?.let {
                products.add(it.toProductResponse())
            }
        }
    return products
}

    fun addProduct(request: ProductCreateRequest): ProductResponse {
            // Create a new ProductEntity from the request
            val productEntity = ProductEntity(
                    sku = request.sku,
                    name = request.name,
                    description = request.description,
                    price = request.price,
                    createdAt = ZonedDateTime.now(),
                    updatedAt = ZonedDateTime.now()
            )

            // Save the product to the database
            val savedProduct = productRepository.save(productEntity)

            // Convert the saved product to a response DTO
            return savedProduct.toProductResponse()
        }

    fun partialUpdateProduct(sku: String, request: ProductPartialUpdateRequest): ProductResponse {
        val existingProduct: ProductEntity
        existingProduct = productRepository.findBySku(sku)
                ?: throw ProductNotFoundException("Product with SKU $sku not found")

        // Update the fields if specified in the request
        request.name?.let { existingProduct.name = it }
        request.description?.let { existingProduct.description = it }
        request.price?.let { existingProduct.price = it }

        // Save the updated product
        val updatedProduct = productRepository.save(existingProduct)

        return updatedProduct.toProductResponse()
    }

}


