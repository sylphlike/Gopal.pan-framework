package com.github.sylphlike.framework.web.exception;

import com.github.sylphlike.framework.web.FReply;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Map;

/**
 * 断言
 * <p>  time 10/12/2020 09:52  星期四 (dd/MM/YYYY HH:mm)
 * <p> email 15923508369@163.com
 * @author Gopal.pan
 * @version 1.0.0
 */
public abstract class AssertUtils {

    /**
     * Assert a boolean expression, throwing an {@code IllegalStateException}
     * if the expression evaluates to {@code false}.
     * <p>Call {@link #isTrue} if you wish to throw an {@code IllegalArgumentException}
     * on an assertion failure.
     * <pre class="code">Assert.state(id == null, "The id property must not already be initialized");</pre>
     * @param expression a boolean expression
     * @param message the exception message to use if the assertion fails
     * @throws IllegalStateException if {@code expression} is {@code false}
     */
    public static void state(boolean expression, String message)  {
        if (!expression) {
            throw new ServiceException(FReply.FW_LOGIC_DATA_ERROR,message);
        }
    }





    /**
     * Assert a boolean expression, throwing an {@code IllegalArgumentException}
     * if the expression evaluates to {@code false}.
     * <pre class="code">Assert.isTrue(i &gt; 0, "The value must be greater than zero");</pre>
     * @param expression a boolean expression
     * @param message the exception message to use if the assertion fails
     * @throws ServiceException if {@code expression} is {@code false}
     */
    public static void isTrue(boolean expression, String message)  {
        if (!expression) {
            throw new ServiceException(FReply.FW_LOGIC_DATA_ERROR,message);
        }
    }




    /**
     * Assert that an object is {@code null}.
     * <pre class="code">Assert.isNull(value, "The value must be null");</pre>
     * @param object the object to check
     * @param message the exception message to use if the assertion fails
     * @ if the object is not {@code null}
     */
    public static void isNull(@Nullable Object object, String message)  {
        if (object != null) {
            throw new ServiceException(FReply.FW_LOGIC_DATA_ERROR,message);
        }
    }




    /**
     * Assert that an object is not {@code null}.
     * <pre class="code">Assert.notNull(clazz, "The class must not be null");</pre>
     * @param object the object to check
     * @param message the exception message to use if the assertion fails
     * @throws ServiceException if the object is {@code null}
     */
    public static void notNull(@Nullable Object object, String message)  {
        if (object == null) {
            throw new ServiceException(FReply.FW_LOGIC_DATA_ERROR,message);
        }
    }


    /**
     * Assert that the given String is not empty; that is,
     * it must not be {@code null} and not the empty String.
     * <pre class="code">Assert.hasLength(name, "Name must not be empty");</pre>
     * @param text the String to check
     * @param message the exception message to use if the assertion fails
     * @throws ServiceException if the text is empty
     * @see StringUtils#hasLength
     */
    public static void hasLength(@Nullable String text, String message)  {
        if (!StringUtils.hasLength(text)) {
            throw new ServiceException(FReply.FW_LOGIC_DATA_ERROR,message);
        }
    }




    /**
     * Assert that the given String contains valid text content; that is, it must not
     * be {@code null} and must contain at least one non-whitespace character.
     * <pre class="code">Assert.hasText(name, "'name' must not be empty");</pre>
     * @param text the String to check
     * @param message the exception message to use if the assertion fails
     * @throws ServiceException if the text does not contain valid text content
     * @see StringUtils#hasText
     */
    public static void hasText(@Nullable String text, String message)  {
        if (!StringUtils.hasText(text)) {
            throw new ServiceException(FReply.FW_LOGIC_DATA_ERROR,message);
        }
    }





    /**
     * Assert that the given text does not contain the given substring.
     * <pre class="code">Assert.doesNotContain(name, "rod", "Name must not contain 'rod'");</pre>
     * @param textToSearch the text to search
     * @param substring the substring to find within the text
     * @param message the exception message to use if the assertion fails
     * @throws ServiceException if the text contains the substring
     */
    public static void doesNotContain(@Nullable String textToSearch, String substring, String message)  {
        if (StringUtils.hasLength(textToSearch) && StringUtils.hasLength(substring) &&
                textToSearch.contains(substring)) {
            throw new ServiceException(FReply.FW_LOGIC_DATA_ERROR,message);
        }
    }




    /**
     * Assert that an array contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">Assert.notEmpty(array, "The array must contain elements");</pre>
     * @param array the array to check
     * @param message the exception message to use if the assertion fails
     * @throws ServiceException if the object array is {@code null} or contains no elements
     */
    public static void notEmpty(@Nullable Object[] array, String message)  {
        if (ObjectUtils.isEmpty(array)) {
            throw new ServiceException(FReply.FW_LOGIC_DATA_ERROR,message);
        }
    }





    /**
     * Assert that an array contains no {@code null} elements.
     * <p>Note: Does not complain if the array is empty!
     * <pre class="code">Assert.noNullElements(array, "The array must contain non-null elements");</pre>
     * @param array the array to check
     * @param message the exception message to use if the assertion fails
     * @throws ServiceException if the object array contains a {@code null} element
     */
    public static void noNullElements(@Nullable Object[] array, String message)  {
        if (array != null) {
            for (Object element : array) {
                if (element == null) {
                    throw new ServiceException(FReply.FW_LOGIC_DATA_ERROR,message);
                }
            }
        }
    }




    /**
     * Assert that a collection contains elements; that is, it must not be
     * {@code null} and must contain at least one element.
     * <pre class="code">Assert.notEmpty(collection, "Collection must contain elements");</pre>
     * @param collection the collection to check
     * @param message the exception message to use if the assertion fails
     * @throws ServiceException if the collection is {@code null} or
     * contains no elements
     */
    public static void notEmpty(@Nullable Collection<?> collection, String message)  {
        if (CollectionUtils.isEmpty(collection)) {
            throw new ServiceException(FReply.FW_LOGIC_DATA_ERROR,message);
        }
    }


    /**
     * Assert that a collection contains no {@code null} elements.
     * <p>Note: Does not complain if the collection is empty!
     * <pre class="code">Assert.noNullElements(collection, "Collection must contain non-null elements");</pre>
     * @param collection the collection to check
     * @param message the exception message to use if the assertion fails
     * @throws ServiceException if the collection contains a {@code null} element
     * @since 5.2
     */
    public static void noNullElements(@Nullable Collection<?> collection, String message)  {
        if (collection != null) {
            for (Object element : collection) {
                if (element == null) {
                    throw new ServiceException(FReply.FW_LOGIC_DATA_ERROR,message);
                }
            }
        }
    }



    /**
     * Assert that a Map contains entries; that is, it must not be {@code null}
     * and must contain at least one entry.
     * <pre class="code">Assert.notEmpty(map, "Map must contain entries");</pre>
     * @param map the map to check
     * @param message the exception message to use if the assertion fails
     * @throws ServiceException if the map is {@code null} or contains no entries
     */
    public static void notEmpty(@Nullable Map<?, ?> map, String message)  {
        if (CollectionUtils.isEmpty(map)) {
            throw new ServiceException(FReply.FW_LOGIC_DATA_ERROR,message);
        }
    }





}
