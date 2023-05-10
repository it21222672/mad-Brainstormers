package com.example.homepage.utils

class Validator(
    var businessId: String,
    var ownerName: String,
    var businessName: String,
    var regNumber: String,
    var postAddress: String
) {

    // Function to check if the business name is not empty
    fun isBusinessNameValid(): Boolean {
        return businessName.isNotEmpty()
    }

    // Function to check if the owner name is not empty
    fun isOwnerNameValid(): Boolean {
        return ownerName.isNotEmpty()
    }

    // Function to check if the post address is not empty
    fun isPostAddressValid(): Boolean {
        return postAddress.isNotEmpty()
    }

    // Function to check if the registration number is not empty
    fun isRegNumberValid(): Boolean {
        return regNumber.isNotEmpty()
    }

    // Function to check if all input fields are valid
    fun isInputValid(): Boolean {
        return isBusinessNameValid() && isOwnerNameValid() && isPostAddressValid() && isRegNumberValid()
    }

    // Function to check if the registration number is unique
    fun isRegNumberUnique(businessList: List<Validator>): Boolean {
        for (b in businessList) {
            if (b.regNumber == regNumber && b.businessId != businessId) {
                return false
            }
        }
        return true
    }

    // Function to check if the input fields and registration number are valid
    fun isBusinessValid(businessList: List<Validator>): Boolean {
        return isInputValid() && isRegNumberUnique(businessList)
    }
}
