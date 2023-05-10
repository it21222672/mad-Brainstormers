package com.example.investorlog

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class registerUtilTest{


//input detail are empty
    @Test
    fun emptyInputDetailreturnFalse(){
        val rslt = registerUtil.validateregister(
            "",
            "abcd",
            "abcd"
        )
        assertThat(rslt).isFalse()
    }
    //the password is match
    @Test
    fun correctConfirmPasswordreturnTrue(){
        val rslt = registerUtil.validateregister(
            "anna",
            "abcd",
            "abcd"
        )
        assertThat(rslt).isTrue()

    }

    //the username previous use
    @Test
    fun usernameIsExitreturnFlse(){
        val rslt = registerUtil.validateregister(
            "anna",
            "abcd",
            "abcd"
        )
        assertThat(rslt).isFalse()
    }

    //the passsword not match
    @Test
    fun incorrectConfmpasswdreturnFalse(){
        val rslt = registerUtil.validateregister(
            "anna",
            "acbd",
            "abcd"
        )
        assertThat(rslt).isFalse()
    }

    //the password is empty
    @Test
    fun emptyPasswdreturnFalse(){
        val rslt = registerUtil.validateregister(
            "anna",
            "",
            ""
        )
        assertThat(rslt).isFalse()
    }
//the all input fill
    @Test
    fun fillInput(){
        val rslt = registerUtil.validateregister(
            "",
            "abcd",
            "abcd"
        )
        assertThat(rslt).isTrue()
    }

}