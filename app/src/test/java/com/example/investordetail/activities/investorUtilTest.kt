package com.example.investordetail.activities

import com.google.common.truth.Truth.assertThat
import org.junit.Test


class investorUtilTest{

/*empty type of input return false
*/

    @Test
    fun fillinputreturntrue (){
        val rslt = investorUtil.validateInvest(
            "anna",
            "98",
            "srilanka"
        )
        assertThat(rslt).isTrue()
    }

}