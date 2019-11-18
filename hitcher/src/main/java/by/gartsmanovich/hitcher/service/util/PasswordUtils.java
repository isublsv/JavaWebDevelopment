package by.gartsmanovich.hitcher.service.util;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Random;

/**
 * Utility class is designed for password hash generation using
 * PBKDF2WithHmacSHA1 algorithm.
 *
 * @author Dmitry Gartsmanovich
 */
public final class PasswordUtils {

    /**
     * Constructs a secure random number generator (RNG) implementing the
     * default random number algorithm.
     */
    private static final Random RANDOM = new SecureRandom();

    /**
     * Contains characters for password hash generation.
     */
    private static final String ALPHABET =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";

    /**
     * The number of iterations.
     */
    private static final int ITERATIONS = 10000;

    /**
     * The length of the key.
     */
    private static final int KEY_LENGTH = 256;

    /**
     * The length of the salt.
     */
    private static final int SALT_LENGTH = 30;

    /**
     * Default constructor. Forbids class instance creation.
     */
    private PasswordUtils() {
    }

    /**
     * Generate the salt value using {@value ALPHABET} string. The max
     * length of salt equals {@value SALT_LENGTH}
     *
     * @return the salt value
     */
    public static String getSalt() {
        StringBuilder returnValue = new StringBuilder(SALT_LENGTH);
        for (int i = 0; i < SALT_LENGTH; i++) {
            returnValue.append(
                    ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return new String(returnValue);
    }

    /**
     * Encrypt user password using PBKDF2WithHmacSHA1 algorithm.
     *
     * @param password the provided password for encryption.
     * @param salt     element is used to get secure version of provided
     *                 password.
     * @return secure password hash.
     */
    private static byte[] hash(final char[] password, final byte[] salt) {

        PBEKeySpec spec = new PBEKeySpec(password, salt, ITERATIONS,
                                         KEY_LENGTH);
        Arrays.fill(password, Character.MIN_VALUE);
        try {
            SecretKeyFactory skf = SecretKeyFactory.getInstance(
                    "PBKDF2WithHmacSHA1");
            return skf.generateSecret(spec).getEncoded();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new AssertionError(
                    "Error while hashing a password: " + e.getMessage(), e);
        } finally {
            spec.clearPassword();
        }
    }

    /**
     * Encode secure user password into Base64.
     *
     * @param password the provided password for encryption.
     * @param salt     element is used to get secure version of provided
     *                 password
     * @return the string representation of the secured password.
     */
    public static String generateSecurePassword(final String password,
            final String salt) {
        byte[] securePassword = hash(password.toCharArray(), salt.getBytes());

        return Base64.getEncoder().encodeToString(securePassword);
    }

    /**
     * Verify user password using provided password and the values of the
     * password and salt from provided sources.
     *
     * @param providedPassword the provided password for encryption.
     * @param securedPassword  the secure password from provided sources.
     * @param salt             element is used to get secure version of
     *                         provided password
     * @return true if provided and secured passwords are the same
     */
    public static boolean verifyUserPassword(final String providedPassword,
            final String securedPassword, final String salt) {
        // Generate new secure password with the same salt
        String newSecurePassword = generateSecurePassword(providedPassword,
                                                          salt);

        return newSecurePassword.equalsIgnoreCase(securedPassword);
    }
}

