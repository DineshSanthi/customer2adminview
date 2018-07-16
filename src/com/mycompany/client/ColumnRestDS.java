package com.mycompany.client;

import com.smartgwt.client.data.fields.DataSourceTextField;

public class ColumnRestDS extends AbstractRestDS{
	
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	@Override
	protected void setEntityFields() {
        DataSourceTextField tableName = new DataSourceTextField("tableName", Constants.tableNameLabel());  
        DataSourceTextField columnName = new DataSourceTextField("columnName", Constants.columnNameLabel());  
        DataSourceTextField columnDesc = new DataSourceTextField("columnDesc", ""); 
        DataSourceTextField columnType = new DataSourceTextField("columnType", Constants.columnTypeLabel()); 
        DataSourceTextField reference = new DataSourceTextField("reference", ""); 
        DataSourceTextField columnSize = new DataSourceTextField("columnSize", Constants.columnSizeLabel()); 
        DataSourceTextField descTamil = new DataSourceTextField("descTamil", Constants.descriptonTamilLabel());
        DataSourceTextField descTelugu = new DataSourceTextField("descTelugu",Constants.descriptionTeluguLabel());
       
        DataSourceTextField category = new DataSourceTextField("category", Constants.categoryLabel());
        
        addField(tableName);
        addField(columnName);
        addField(columnDesc);
        addField(descTamil);
        addField(descTelugu);
        addField(category);
        addField(columnType);
        addField(reference);
        addField(columnSize);
	}

	@Override
	protected String getServiceName() {
		return getClass().getSimpleName().replaceAll("RestDS", "").toLowerCase();
	}
	
}