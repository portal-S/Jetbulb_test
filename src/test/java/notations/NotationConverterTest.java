package notations;

import com.portal.jetbulb.InvalidValueException;
import com.portal.jetbulb.NotationConverter;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowableOfType;
import static org.junit.jupiter.params.provider.Arguments.arguments;


public class NotationConverterTest {
    private final NotationConverter converter = new NotationConverter();

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"A", "1", "X1", "_", "Vi1"})
    void shouldThrowInvalidValueExceptionIfNonRomanDigitFound(String in) {
        assertThat(
                catchThrowableOfType(() -> converter.toArabic(in), InvalidValueException.class)
        )
                .overridingErrorMessage("InvalidValueException should be thrown if invalid symbols detected")
                .isNotNull()
                .hasMessage("String must contain only valid roman numerals [I, V, X, L, C, D, M]");
    }

    @ParameterizedTest
    @MethodSource("romanAndArabicNotationProvider")
    void shouldSuccessfullyConvertRomanToArabicNotation(String roman, int arabic) throws InvalidValueException {
        assertThat(converter.toArabic(roman))
                .overridingErrorMessage(
                        "Should be arabic value %s after conversion from roman notation %s",
                        arabic,
                        roman
                )
                .isEqualTo(arabic);
    }

    private static Stream<Arguments> romanAndArabicNotationProvider() {
        return Stream.of(
                arguments("III", 3),
                arguments("XII", 12),
                arguments("LIX", 59),
                arguments("MCMXC", 1990)
        );
    }
}
