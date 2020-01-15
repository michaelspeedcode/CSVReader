import com.speed.mvvm.DateUtils.Companion.formatDate
import org.junit.Assert.assertEquals
import org.junit.Test

class DateUtilsTest {

    @Throws(Exception::class)
    @Test
    fun testDateFormatter() {
        assertEquals("12 Nov 1950 - 08:00AM", formatDate("1950-11-12T08:00:00"))
    }

  @Throws(Exception::class)
    @Test
    fun testDateFormatterNegativeNumbersMalformed() {
        assertEquals("-1950--11--12T08:00:00", formatDate("-1950--11--12T08:00:00"))
    }

    @Throws(Exception::class)
    @Test
    fun testDateFormatterMalformed() {
        assertEquals("195011-12T08:00:00", formatDate("195011-12T08:00:00"))
    }

    @Throws(Exception::class)
    @Test
    fun testDateFormatterEmptyDate() {
        assertEquals("", formatDate(""))
    }

    @Throws(Exception::class)
    @Test
    fun testDateFormatterNotADate() {
        assertEquals("Hello not a date", formatDate("Hello not a date"))
    }
}