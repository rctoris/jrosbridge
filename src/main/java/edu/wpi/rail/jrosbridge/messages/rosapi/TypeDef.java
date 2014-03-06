package edu.wpi.rail.jrosbridge.messages.rosapi;

import java.io.StringReader;
import java.util.Arrays;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The rosapi/TypeDef message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version March 4, 2014
 */
public class TypeDef extends Message {

	/**
	 * The name of the type field for the message.
	 */
	public static final String FIELD_TYPE = "type";

	/**
	 * The name of the field names field for the message.
	 */
	public static final String FIELD_FIELD_NAMES = "fieldnames";

	/**
	 * The name of the field types field for the message.
	 */
	public static final String FIELD_FIELD_TYPES = "fieldtypes";

	/**
	 * The name of the field array length field for the message.
	 */
	public static final String FIELD_FIELD_ARRAY_LEN = "fieldarraylen";

	/**
	 * The name of the examples field for the message.
	 */
	public static final String FIELD_EXAMPLES = "examples";

	/**
	 * The message type.
	 */
	public static final String TYPE = "rosapi/TypeDef";

	private String type;
	private String[] fieldnames, fieldtypes, examples;
	private int[] fieldarraylen;

	/**
	 * Create a new TypeDef with all empty values.
	 */
	public TypeDef() {
		this("", new String[] {}, new String[] {}, new int[] {},
				new String[] {});
	}

	/**
	 * Create a new TypeDef based on the given values. The values of the arrays
	 * will be copied into this object.
	 * 
	 * @param type
	 *            The type field of the message.
	 * @param fieldnames
	 *            The field names field of the message.
	 * @param fieldtypes
	 *            The field types field of the message.
	 * @param fieldarraylen
	 *            The field array length field of the message.
	 * @param examples
	 *            The examples names field of the message.
	 */
	public TypeDef(String type, String[] fieldnames, String[] fieldtypes,
			int[] fieldarraylen, String examples[]) {
		// build the JSON object
		super(
				Json.createObjectBuilder()
						.add(TypeDef.FIELD_TYPE, type)
						.add(TypeDef.FIELD_FIELD_NAMES,
								Json.createReader(
										new StringReader(Arrays
												.deepToString(fieldnames)))
										.readArray())
						.add(TypeDef.FIELD_FIELD_TYPES,
								Json.createReader(
										new StringReader(Arrays
												.deepToString(fieldtypes)))
										.readArray())
						.add(TypeDef.FIELD_FIELD_ARRAY_LEN,
								Json.createReader(
										new StringReader(Arrays
												.toString(fieldarraylen)))
										.readArray())
						.add(TypeDef.FIELD_EXAMPLES,
								Json.createReader(
										new StringReader(Arrays
												.deepToString(examples)))
										.readArray()).build(), TypeDef.TYPE);

		this.type = type;
		// create the arrays
		this.fieldnames = new String[fieldnames.length];
		System.arraycopy(fieldnames, 0, this.fieldnames, 0, fieldnames.length);
		this.fieldtypes = new String[fieldtypes.length];
		System.arraycopy(fieldtypes, 0, this.fieldtypes, 0, fieldtypes.length);
		this.fieldarraylen = new int[fieldarraylen.length];
		System.arraycopy(fieldarraylen, 0, this.fieldarraylen, 0,
				fieldarraylen.length);
		this.examples = new String[examples.length];
		System.arraycopy(examples, 0, this.examples, 0, examples.length);
	}

	/**
	 * Get the type value of this typedef.
	 * 
	 * @return The type value of this typedef.
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Get the field names value of this typedef.
	 * 
	 * @return The field names value of this typedef.
	 */
	public String[] getFieldNames() {
		return this.fieldnames;
	}

	/**
	 * Get the field types value of this typedef.
	 * 
	 * @return The field types value of this typedef.
	 */
	public String[] getFieldTypes() {
		return this.fieldtypes;
	}

	/**
	 * Get the field array length value of this typedef.
	 * 
	 * @return The field array length value of this typedef.
	 */
	public int[] getFieldArrayLen() {
		return this.fieldarraylen;
	}

	/**
	 * Get the examples value of this typedef.
	 * 
	 * @return The examples value of this typedef.
	 */
	public String[] getExamples() {
		return this.examples;
	}

	/**
	 * Create a deep clone of this TypeDef.
	 */
	@Override
	public TypeDef clone() {
		return new TypeDef(this.type, this.fieldnames, this.fieldtypes,
				this.fieldarraylen, this.examples);
	}
}
