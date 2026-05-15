import com.example.Feline;
import com.example.Lion;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LionParametrizedTest {

    private String sex;
    private boolean expectedMane;
    private boolean shouldThrow;

    public LionParametrizedTest(String sex, boolean expectedMane, boolean shouldThrow) {
        this.sex = sex;
        this.expectedMane = expectedMane;
        this.shouldThrow = shouldThrow;
    }

    @Parameters(name = "{index}: пол={0}, ожидаемая грива={1}, исключение={2}")
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][] {
                {"Самец", true, false},   // Самец - должен быть с гривой, исключений нет
                {"Самка", false, false},  // Самка - без гривы, исключений нет
                {"Неизвестно", false, true}, // Неизвестный пол - ожидаем исключение
                {"", false, true},        // Пустая строка - ожидаем исключение
                {null, false, true}       // null - ожидаем исключение
        });
    }

    @Test
    // Параметризованный тест конструктора Lion и метода doesHaveMane()
    public void testLionCreationAndMane() throws Exception {
        Feline mockFeline = new Feline();
        if (shouldThrow) {
            // Если ожидается исключение, проверяем, что оно выбрасывается
            try {
                new Lion(sex);
                fail("Должно было быть выброшено исключение для пола: " + sex);
            } catch (Exception e) {
                // Проверяем сообщение исключения
                assertEquals("Используйте допустимые значения пола животного - самей или самка", e.getMessage());
            }
        } else {
            // Если исключения не ожидается, создаем льва и проверяем гриву
            Lion lion = new Lion(sex);
            assertEquals("Проверка наличия гривы для пола: " + sex, expectedMane, lion.doesHaveMane());
        }
    }
}