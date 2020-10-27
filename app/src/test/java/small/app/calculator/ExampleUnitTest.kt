package small.app.calculator

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testInputInt() {
        val model: ViewModel = ViewModel()
        model.addDigit('1')
        model.addDigit('0')
        assertEquals(10.0, model.getDisplay().toDouble(), 0.0)

    }

    @Test
    fun testInputDouble() {
        val model: ViewModel = ViewModel()
        model.addDigit('1')
        model.addDigit('0')
        model.addDigit('.')
        model.addDigit('6')
        assertEquals(10.6, model.getDisplay().toDouble(), 0.0)

    }

    @Test
    fun testAddition2PositiveNumbers() {
        val model: ViewModel = ViewModel()
        model.addDigit('1')
        model.addDigit('0')
        model.addDigit('.')
        model.addDigit('6')
        model.changeOp('+')
        model.addDigit('9')
        model.addDigit('.')
        model.addDigit('5')

        assertEquals(9.5, model.getDisplay().toDouble(), 0.0)
        assertEquals(10.6, model.getResult().toString().toDouble(), 0.0)
        model.changeOp('E')

        assertEquals(20.1, model.getDisplay().toDouble(), 0.0)
    }

    @Test
    fun testSubstract2PositiveNumbers() {
        val model: ViewModel = ViewModel()
        model.addDigit('1')
        model.addDigit('0')
        model.addDigit('.')
        model.addDigit('6')
        model.changeOp('-')
        model.addDigit('9')
        model.addDigit('.')
        model.addDigit('5')

        assertEquals(9.5, model.getDisplay().toDouble(), 0.0)
        assertEquals(10.6, model.getResult().toDouble(), 0.0)
        model.changeOp('E')

        val strB = model.display.value!!
        val dispStr = strB.toString()
        assertEquals(1.1, dispStr.toDouble(), 0.0)
    }

    @Test
    fun testMultiply2PositiveNumbers() {
        val model: ViewModel = ViewModel()
        model.addDigit('1')
        model.addDigit('.')
        model.addDigit('5')
        model.changeOp('*')
        model.addDigit('1')
        model.addDigit('0')
        model.changeOp('E')
        assertEquals(15.0, model.getDisplay().toDouble(), 0.0)
    }

    @Test
    fun testNegativeNumberDisplay() {
        val model: ViewModel = ViewModel()
        model.changeOp('-')
        model.addDigit('1')

        assertEquals(-1.0, model.getDisplay().toDouble(), 0.0)
    }

    @Test
    fun testCancelNegativeNumberDisplay() {
        val model: ViewModel = ViewModel()
        model.changeOp('-')
        model.changeOp('+')
        model.addDigit('1')
        assertEquals(1.0, model.getDisplay().toDouble(), 0.0)
    }


    @Test
    fun testAddTwoNegativeNumber() {
        val model: ViewModel = ViewModel()
        model.changeOp('-')
        model.addDigit('1')
        model.addDigit('2')
        model.addDigit('.')
        model.addDigit('1')

        model.changeOp('+')

        model.changeOp('-')
        model.addDigit('7')
        model.addDigit('.')
        model.addDigit('1')

        model.changeOp('E')

        assertEquals(-19.2, model.getDisplay().toDouble(), 0.0)

    }

    @Test
    fun testMultiply_1Positive_1Negative_Number() {
        val model: ViewModel = ViewModel()
        model.addDigit('1')
        model.addDigit('2')
        model.addDigit('.')
        model.addDigit('1')

        model.changeOp('*')

        model.changeOp('-')
        model.addDigit('7')
        model.addDigit('.')
        model.addDigit('1')

        model.changeOp('E')

        assertEquals(-85.91, model.getDisplay().toDouble(), 0.0)

    }

    @Test
    fun testCombine4Op() {
        val model: ViewModel = ViewModel()
        // 12.1*-7.1/-1.1+15.1

        model.addDigit('1')
        model.addDigit('2')
        model.addDigit('.')
        model.addDigit('1')

        model.changeOp('*')

        assertEquals(12.1, model.getResult().toDouble(), 0.0)

        model.changeOp('-')
        model.addDigit('7')
        model.addDigit('.')
        model.addDigit('1')

        model.changeOp('/')

        assertEquals(-85.91, model.getResult().toDouble(), 0.0)

        model.changeOp('-')
        model.addDigit('1')
        model.addDigit('.')
        model.addDigit('1')

        model.changeOp('+')
        assertEquals(78.1, model.getResult().toDouble(), 0.0)

        model.addDigit('1')
        model.addDigit('5')
        model.addDigit('.')
        model.addDigit('1')

        model.changeOp('E')

        assertEquals(93.2, model.getDisplay().toDouble(), 0.0)


    }

}