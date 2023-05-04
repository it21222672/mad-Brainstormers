package com.example.homepage.models

/**

A data class representing a business entity.
@property businessId The unique identifier of the business.
@property ownerName The name of the owner of the business.
@property businessName The name of the business.
@property regNumber The registration number of the business.
@property postAddress The postal address of the business.
 */

data class BusinessModel(
    var businessId: String? = null,
    var ownerName: String? = null,
    var businessName: String? = null,
    var regNumber: String? = null,
    var postAddress: String? = null,
)