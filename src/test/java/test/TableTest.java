package test;

import static org.junit.Assert.*;
import java.io.FileReader;
import org.junit.Before;
import org.junit.Test;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import exception.LengthColumnDecreaseException;
import exception.MismatchedColumnTypeException;
import model.Table;

public class TableTest {

	ObjectMapper mapper;
	Table table, table2, table3;
	
	@Before
	public void setUp() throws Exception {
		mapper = new ObjectMapper(new YAMLFactory());
		table = mapper.readValue(new FileReader("src/test/resources/data/contact.yml"), Table.class);
		table2 = mapper.readValue(new FileReader("src/test/resources/data/contact2.yml"), Table.class);
		table3 = mapper.readValue(new FileReader("src/test/resources/data/contact3.yml"), Table.class);
	}
	
	@Test
	public void testTableCompareHerself(){
		assertEquals(table.compareWith(table), "");
	}
	
	@Test(expected=MismatchedColumnTypeException.class)
	public void testTableCompareWithMismatchedTypeException(){
		table.compareWith(table2);
	}
	
	@Test(expected=LengthColumnDecreaseException.class)
	public void testTableCompareWithLengthColumnDecreaseException(){
		table.compareWith(table3);
	}

}
