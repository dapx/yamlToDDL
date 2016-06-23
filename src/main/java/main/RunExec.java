package main;

import java.io.FileReader;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import model.Table;

public class RunExec {

	public static void main(String[] args) {
		try {
		ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
		Table table = mapper.readValue(new FileReader("/home/dap/workspace/create-dict/create-dict/src/main/java/data/contact.yml"), Table.class);
		 System.out.println(table.toTable());
		} catch (Exception ex){
			System.out.println(ex.getMessage());
		    
		}
	}

}
