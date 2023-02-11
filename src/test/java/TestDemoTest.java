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

	// Initializes a new testDemo object before each test.
	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	} // end setUp

	/*
	 * Tests the TestDemo class addPositive method with streamed parameters to
	 * assert that 2 positive integers are added together properly or assert that an
	 * exception is thrown if either or both are not positive.
	 * 
	 * The path for the stream parameters doesn't require package name since default
	 * package is used.
	 */
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

	// Arguments to stream for the test method assertThatTwoPositiveNumbersAreAddedCorrectly
	// Tests all possible pairings of positive and negative integers and zero.
	static Stream<Arguments> argumentsForAddPositive() {
		// @formatter:off
		return Stream.of
		(
			arguments(1, 5, 6, false),
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

	// Uses Mockito to Spy on TestDemo method getRandomInt to mock in a 5 and assert that 5 squared (25) is returned.
	@Test
	public void assertThatNumberSquaredIsCorrect() {
		TestDemo mockDemo = spy(testDemo);

		doReturn(5).when(mockDemo).getRandomInt();

		int fiveSquared = mockDemo.randomNumberSquared();
		assertThat(fiveSquared).isEqualTo(25);

	} // end assertThatNumberSquaredIsCorrect

} // end CLASS