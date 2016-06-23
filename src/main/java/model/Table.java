package model;

import java.util.List;

public class Table {
	private String name;
	private List<Column> columns;
	
	public List<Column> getColumns() {
		return columns;
	}
	
	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Table() {
		super();
	}

	@Override
	public String toString() {
		return "Table [name=" + name + "]";
	}
	
	public String toTable(){
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ").append(name).append(" ( \n");
		for (Column column : columns){
			sb.append(column.getName()).append(" ").append(column.getType()).append("(").append(column.getLength()).append("), \n");
		}
		sb.deleteCharAt(sb.length()-3);
		sb.append(");");
		return sb.toString();
	}
}
