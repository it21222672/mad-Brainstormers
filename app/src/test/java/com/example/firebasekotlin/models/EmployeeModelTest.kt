//EmployeeModelTest
package com.example.firebasekotlin.models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class EmployeeModelTest {

    @Test
    fun `getEmpId should return the employee ID`() {
        val emp = EmployeeModel(empId = "123")
        assertEquals("123", emp.empId)
    }

    @Test
    fun `setEmpId should update the employee ID`() {
        val emp = EmployeeModel(empId = "123")
        emp.empId = "456"
        assertEquals("456", emp.empId)
    }

    @Test
    fun `getEmpName should return the employee name`() {
        val emp = EmployeeModel(empName = "John Doe")
        assertEquals("John Doe", emp.empName)
    }

    @Test
    fun `setEmpName should update the employee name`() {
        val emp = EmployeeModel(empName = "John Doe")
        emp.empName = "Jane Smith"
        assertEquals("Jane Smith", emp.empName)
    }

    @Test
    fun `getEmpTitle should return the employee title`() {
        val emp = EmployeeModel(empTitle = "Manager")
        assertEquals("Manager", emp.empTitle)
    }

    @Test
    fun `setEmpTitle should update the employee title`() {
        val emp = EmployeeModel(empTitle = "Manager")
        emp.empTitle = "Director"
        assertEquals("Director", emp.empTitle)
    }

    @Test
    fun `getEmpCnum should return the employee contact number`() {
        val emp = EmployeeModel(empCnum = "555-1234")
        assertEquals("555-1234", emp.empCnum)
    }

    @Test
    fun `setEmpCnum should update the employee contact number`() {
        val emp = EmployeeModel(empCnum = "555-1234")
        emp.empCnum = "555-5678"
        assertEquals("555-5678", emp.empCnum)
    }

    @Test
    fun `getEmpDescription should return the employee description`() {
        val emp = EmployeeModel(empDescription = "Lorem ipsum")
        assertEquals("Lorem ipsum", emp.empDescription)
    }

    @Test
    fun `setEmpDescription should update the employee description`() {
        val emp = EmployeeModel(empDescription = "Lorem ipsum")
        emp.empDescription = "Dolor sit amet"
        assertEquals("Dolor sit amet", emp.empDescription)
    }
}
