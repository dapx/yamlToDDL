package main;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import exception.LengthDecreaseException;
import exception.MismatchedTypeException;
import model.Table;

public class RunExec {

	public static void main(String[] args) {
		try {
			ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
			Table table = mapper.readValue(new FileReader("/home/dap/workspace/create-dict/create-dict/src/main/resources/data/contact.yml"), Table.class);
			//System.out.println(table.createTable());
			System.out.println(table.alterTable());
			Table table2 = mapper.readValue(new FileReader("/home/dap/workspace/create-dict/create-dict/src/main/resources/data/contact2.yml"), Table.class);
			System.out.println(table.compareWith(table));
		} catch (MismatchedTypeException ex){
			System.out.println(ex.getMessage());
		} catch (LengthDecreaseException ex){
			System.out.println(ex.getMessage());
		} catch (JsonParseException ex) {
			System.out.println(ex.getMessage());
		} catch (JsonMappingException ex) {
			System.out.println(ex.getMessage());
		} catch (FileNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}

}
