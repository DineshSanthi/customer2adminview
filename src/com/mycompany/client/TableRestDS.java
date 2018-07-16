package com.mycompany.client;

import com.smartgwt.client.data.fields.DataSourceTextField;

public class TableRestDS extends AbstractRestDS{
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
	@Override
	protected void setEntityFields() {
        DataSourceTextField appName = new DataSourceTextField("appName",Constants.appNameLabel() );  
        DataSourceTextField tableName = new DataSourceTextField("tableName", Constants.tableNameLabel());  
        DataSourceTextField tableDesc = new DataSourceTextField("tableDescription", Constants.descriptionLabel()); 
        DataSourceTextField descTamil = new DataSourceTextField("descTamil",Constants.descriptonTamilLabel());
        DataSourceTextField descTelugu = new DataSourceTextField("descTelugu", Constants.descriptionTeluguLabel());
        DataSourceTextField category = new DataSourceTextField("category", Constants.categoryLabel());
        DataSourceTextField ttl = new DataSourceTextField("ttl", Constants.ttlLabel());
       
        addField(appName);
        addField(tableName);
        addField(tableDesc);
        addField(descTamil);
        addField(descTelugu);
        addField(category);
        addField(ttl);
        
        
        
	}

	@Override
	protected String getServiceName() {
		return getClass().getSimpleName().replaceAll("RestDS", "").toLowerCase();
	}
	
}