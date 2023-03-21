/*
 * Ariela Mishaan (22052)
 * Algoritmos y Estructuras de Datos Sección 40
 * Hoja de Trabajo 7
 * 20-03-2023
 * Clase Failed Postcondition: tomada de la base de datos del libro Java Structures (Duane A. Bailey)
 */

/**
 * A class of error indicating failed postcondition.
 *
 * @version $Id: FailedPostcondition.java 23 2006-08-21 20:50:47Z bailey $
 * @author, 2001 duane a. bailey
 */
class FailedPostcondition extends FailedAssertion
{
    final static long serialVersionUID = 0;
    /**
     * Constructs an error indicating failure to meet a postcondition.
     *
     * @post Constructs a new failed postcondition
     * 
     * @param reason String describing postcondition.
     */
    public FailedPostcondition(String reason)
    {
        super("\nA postcondition: " + reason);
    }
}
