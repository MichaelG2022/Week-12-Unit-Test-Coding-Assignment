import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoTest {
	private TestDemo testDemo;

	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	} // end setUp

	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPositive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, Boolean expectException) {
		// Given: two integers to test (from argumentsForAddPositive Stream)

		// When: the method to test that both integers are positive is called
		// and both are positive
		if (!expectException) {
			
			// Then: the positive integers are added together and match the expected answer
			assertThat(testDemo.addPositive(a, b)).isEqualTo(expected);
			
		} else {
			// When: the method to test that both integers are positive is called
			// and one or both are not
			
			// Then: an exception is thrown
			assertThatThrownBy(() -> testDemo.addPositive(a, b)).isInstanceOf(IllegalArgumentException.class);
		}

	} // end assertThatTwoPositiveNumbersAreAddedCorrectly
	
	static Stream<Arguments> argumentsForAddPositive() {
		return Stream.of(
				arguments(1,5, 6, false),
				arguments(-1, 5, 4, true),
				arguments(1, -5, -4, true),
				arguments(-2, -3, -5, true),
				arguments(0, 0, 0, true),
				arguments(42, 0, 42, true),
				arguments(0, 16, 16, true),
				arguments(-7, 0, -7, true),
				arguments(0, -24, -24, true)
		);
		// @formatter:on
	} // end argumentsForAddPositive
	
	@Test
	public void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);
		
		doReturn(5).when(mockDemo).getRandomInt();
		
		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);
		
	} // end assertThatNumberSquaredIsCorrect

} // end CLASS
