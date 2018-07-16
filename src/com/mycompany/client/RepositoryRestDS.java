package com.mycompany.client;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.i18n.client.Constants;
import com.smartgwt.client.data.DSRequest;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.DataSourceField;
import com.smartgwt.client.data.OperationBinding;
import com.smartgwt.client.data.RestDataSource;
import com.smartgwt.client.data.fields.DataSourceDateField;
import com.smartgwt.client.data.fields.DataSourceIntegerField;
import com.smartgwt.client.data.fields.DataSourceTextField;
import com.smartgwt.client.types.DSDataFormat;
import com.smartgwt.client.types.DSOperationType;
import com.smartgwt.client.types.DSProtocol;
import com.smartgwt.client.types.FieldType;
import com.smartgwt.client.util.JSON;
import com.smartgwt.client.widgets.grid.ListGridRecord;

public class RepositoryRestDS extends DataSource {
	
	private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
    private static RepositoryRestDS instance = null;
    public RepositoryRestDS()
    {
    	setDataFormat(DSDataFormat.JSON);
    //	setDataProtocol(DSProtocol.POSTMESSAGE);
    	setJsonPrefix(null);
    	setJsonSuffix(null);
    }
    
    public static RepositoryRestDS getInstance() {
        if (instance == null) {
            instance = new RepositoryRestDS("RestDS");
            
        }
        return instance;
    }

    public RepositoryRestDS(String id) {

        setID(id);
        setClientOnly(true);
      /*  OperationBinding fetchOperationBinding = new OperationBinding();
        fetchOperationBinding.setOperationType(DSOperationType.FETCH);
     //   fetchOperationBinding.setDataProtocol(DSProtocol.POSTMESSAGE);
        fetchOperationBinding.setDataURL("http://127.0.0.1:8080/application/all");

        this.setOperationBindings(fetchOperationBinding);*/

/*        DataSourceField propertyField = new DataSourceField("id", FieldType.TEXT);
        propertyField.setPrimaryKey(true);
        //propertyField.setHidden(true);

        DataSourceField labelField = new DataSourceField("appName", FieldType.TEXT, "Application Name");
        labelField.setCanEdit(false);
        // labelField.setPrimaryKey(true);

        DataSourceField groupField = new DataSourceField("tableName", FieldType.TEXT, "Description");
        groupField.setCanEdit(false);
        groupField.setHidden(true);
        
        this.setFields(propertyField,labelField,groupField);
        
        this.setDataURL("http://127.0.0.1:8080/table/all");*/
        
        
        DataSourceField typeField = new DataSourceField("objectType", FieldType.TEXT, "Object Type");
        typeField.setPrimaryKey(true);
        typeField.setHidden(true);
        
        DataSourceField nameField = new DataSourceField("objectName", FieldType.TEXT, "Object Name");
        nameField.setPrimaryKey(true);
        
        this.setFields(typeField,nameField);
        
       // this.addData(createRecord("customer", "Customer"));
        this.addData(createRecord("application", Constants.applicationEntityLabel()));
        this.addData(createRecord("table", Constants.tableEntityLabel()));
        this.addData(createRecord("column", Constants.columnEntityLabel()));
        this.addData(createRecord("reference", Constants.referenceTableEntityLabel()));
        this.addData(createRecord("relation", Constants.relationshipEntityLabel()));

    } 
    
    
    public ListGridRecord createRecord(String objectType, String objectName) {  
        ListGridRecord record = new ListGridRecord();  
        record.setAttribute("objectType", objectType);  
        record.setAttribute("objectName", objectName);  
        return record;  
    } 

}
