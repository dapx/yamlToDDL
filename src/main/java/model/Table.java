package model;

import java.util.List;

import exception.MismatchedColumnTypeException;
import exception.LengthColumnDecreaseException;

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
		return "Table [name=" + name + "] Columns " + columns.toString();
	}
	
	public String createTable(){
		StringBuilder sb = new StringBuilder();
		sb.append("CREATE TABLE ").append(name).append(" ( \n");
		for (Column column : columns){
			sb.append(column.getName()).append(" ").append(column.getType()).append("(").append(column.getLength()).append("), \n");
		}
		sb.deleteCharAt(sb.length()-3);
		sb.append(");");
		return sb.toString();
	}
	
	public String alterTable(){
		StringBuilder sb = new StringBuilder();
		for (Column column : columns){
		sb.append("ALTER TABLE ").append(name).append(" add ").append(column.getName()).append(" ").append(column.getType()).append("(").append(column.getLength()).append("); \n");
		}
		return sb.toString();
	}
	
	//VOU REMOVER ESSE METODO HORRIVEL ||| FIQUE TRANQUILO
	public String compareWith(Table table){
		StringBuilder sb = new StringBuilder();
		//this.table é o META-DADOS ORIGINAL
		//table é o META-DADOS obtido de uma tabela do banco
		this.columns.forEach(column -> {
			table.columns.forEach(column2 -> {
				if (column.getName().equals(column2.getName())){
					column.setModify(true);
					if (column.getType().equals(column2.getType())){
						//Na linha abaixo ele verifica se a coluna de mesmo nome e tipo da tabela original(atualizada) é menor que a que está no banco
						if (column.getLength() < column2.getLength()){
							throw new LengthColumnDecreaseException("unable to decrease size of column: " + column.getName());
						} else if (column.getLength() > column2.getLength()) {
							sb.append("ALTER TABLE ").append(this.name).append(" modify ").append(column.getName()).append(" ").append(column.getType()).append("(").append(column.getLength()).append("); \n");
						}
					} else {
						throw new MismatchedColumnTypeException("unable to convert type of column: " + column.getName());
					}
				}
			});
			if (!column.isModify()) {
				sb.append("ALTER TABLE ").append(this.name).append(" add ").append(column.getName()).append(" ").append(column.getType()).append("(").append(column.getLength()).append("); \n");
			}
		});
		return sb.toString();
	}
} 