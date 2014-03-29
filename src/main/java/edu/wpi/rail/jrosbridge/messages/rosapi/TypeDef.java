package edu.wpi.rail.jrosbridge.messages.rosapi;

import java.io.StringReader;
import java.util.Arrays;

import javax.json.Json;

import edu.wpi.rail.jrosbridge.messages.Message;

/**
 * The rosapi/TypeDef message.
 * 
 * @author Russell Toris -- rctoris@wpi.edu
 * @version March 29, 2014
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
	private String[] fieldNames, fieldTypes, examples;
	private int[] fieldArrayLen;

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
	 * @param fieldNames
	 *            The field names field of the message.
	 * @param fieldTypes
	 *            The field types field of the message.
	 * @param fieldArrayLen
	 *            The field array length field of the message.
	 * @param examples
	 *            The examples names field of the message.
	 */
	public TypeDef(String type, String[] fieldNames, String[] fieldTypes,
			int[] fieldArrayLen, String examples[]) {
		// build the JSON object
		super(
				Json.createObjectBuilder()
						.add(TypeDef.FIELD_TYPE, type)
						.add(TypeDef.FIELD_FIELD_NAMES,
								Json.createReader(
										new StringReader(Arrays
												.deepToString(fieldNames)))
										.readArray())
						.add(TypeDef.FIELD_FIELD_TYPES,
								Json.createReader(
										new StringReader(Arrays
												.deepToString(fieldTypes)))
										.readArray())
						.add(TypeDef.FIELD_FIELD_ARRAY_LEN,
								Json.createReader(
										new StringReader(Arrays
												.toString(fieldArrayLen)))
										.readArray())
						.add(TypeDef.FIELD_EXAMPLES,
								Json.createReader(
										new StringReader(Arrays
												.deepToString(examples)))
										.readArray()).build(), TypeDef.TYPE);

		this.type = type;
		// create the arrays
		this.fieldNames = new String[fieldNames.length];
		System.arraycopy(fieldNames, 0, this.fieldNames, 0, fieldNames.length);
		this.fieldTypes = new String[fieldTypes.length];
		System.arraycopy(fieldTypes, 0, this.fieldTypes, 0, fieldTypes.length);
		this.fieldArrayLen = new int[fieldArrayLen.length];
		System.arraycopy(fieldArrayLen, 0, this.fieldArrayLen, 0,
				fieldArrayLen.length);
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
		return this.fieldNames;
	}

	/**
	 * Get the field types value of this typedef.
	 * 
	 * @return The field types value of this typedef.
	 */
	public String[] getFieldTypes() {
		return this.fieldTypes;
	}

	/**
	 * Get the field array length value of this typedef.
	 * 
	 * @return The field array length value of this typedef.
	 */
	public int[] getFieldArrayLen() {
		return this.fieldArrayLen;
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
	 * Create a clone of this TypeDef.
	 */
	@Override
	public TypeDef clone() {
		return new TypeDef(this.type, this.fieldNames, this.fieldTypes,
				this.fieldArrayLen, this.examples);
	}
}
