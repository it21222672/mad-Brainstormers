import com.example.firebasekotlin.models.ContactModel
import org.junit.Assert.*
import org.junit.Test

class ContactModelTest {

    @Test
    fun `test getContactId() returns correct value`() {
        // Arrange
        val contactModel = ContactModel(contactId = "123")

        // Act
        val contactId = contactModel.contactId

        // Assert
        assertEquals("123", contactId)
    }

    @Test
    fun `test setContactId() updates contactId correctly`() {
        // Arrange
        val contactModel = ContactModel()

        // Act
        contactModel.contactId = "123"

        // Assert
        assertEquals("123", contactModel.contactId)
    }

    @Test
    fun `test getContactName() returns correct value`() {
        // Arrange
        val contactModel = ContactModel(contactName = "John Doe")

        // Act
        val contactName = contactModel.contactName

        // Assert
        assertEquals("John Doe", contactName)
    }

    @Test
    fun `test setContactName() updates contactName correctly`() {
        // Arrange
        val contactModel = ContactModel()

        // Act
        contactModel.contactName = "John Doe"

        // Assert
        assertEquals("John Doe", contactModel.contactName)
    }

    @Test
    fun `test getContactEmail() returns correct value`() {
        // Arrange
        val contactModel = ContactModel(contactEmail = "john.doe@example.com")

        // Act
        val contactEmail = contactModel.contactEmail

        // Assert
        assertEquals("john.doe@example.com", contactEmail)
    }

    @Test
    fun `test setContactEmail() updates contactEmail correctly`() {
        // Arrange
        val contactModel = ContactModel()

        // Act
        contactModel.contactEmail = "john.doe@example.com"

        // Assert
        assertEquals("john.doe@example.com", contactModel.contactEmail)
    }

    @Test
    fun `test getContactDescription() returns correct value`() {
        // Arrange
        val contactModel = ContactModel(contactDescription = "Hello, I would like to inquire about your services.")

        // Act
        val contactDescription = contactModel.contactDescription

        // Assert
        assertEquals("Hello, I would like to inquire about your services.", contactDescription)
    }

    @Test
    fun `test setContactDescription() updates contactDescription correctly`() {
        // Arrange
        val contactModel = ContactModel()

        // Act
        contactModel.contactDescription = "Hello, I would like to inquire about your services."

        // Assert
        assertEquals("Hello, I would like to inquire about your services.", contactModel.contactDescription)
    }
}
