package de.fhg.iais.roberta.ast.syntax;

import java.util.Locale;

import de.fhg.iais.roberta.dbc.DbcException;

public enum IndexLocation {
    FIRST(), LAST(), FROM_START( "FROMSTART" ), FROM_END( "FROMEND" ), RANDOM();
    private final String[] values;

    private IndexLocation(String... values) {
        this.values = values;
    }

    /**
     * @return valid Java code name of the enumeration
     */
    public String getJavaCode() {
        return this.getClass().getSimpleName() + "." + this;
    }

    /**
     * get function from {@link IndexLocation} from string parameter. It is possible for one function to have multiple string mappings.
     * Throws exception if the operator does not exists.
     *
     * @param functName of the function
     * @return function from the enum {@link FunctionNames}
     */
    public static IndexLocation get(String s) {
        if ( s == null || s.isEmpty() ) {
            throw new DbcException("Invalid function name: " + s);
        }
        String sUpper = s.trim().toUpperCase(Locale.GERMAN);
        for ( IndexLocation funct : IndexLocation.values() ) {
            if ( funct.toString().equals(sUpper) ) {
                return funct;
            }
            for ( String value : funct.values ) {
                if ( sUpper.equals(value) ) {
                    return funct;
                }
            }
        }
        throw new DbcException("Invalid function name: " + s);
    }
}
