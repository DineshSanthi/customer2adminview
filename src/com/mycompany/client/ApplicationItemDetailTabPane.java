package com.mycompany.client;

/*
 * SmartGWT (GWT for SmartClient)
 * Copyright 2008 and beyond, Isomorphic Software, Inc.
 *
 * SmartGWT is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License version 3
 * as published by the Free Software Foundation.  SmartGWT is also
 * available under typical commercial license terms - see
 * http://smartclient.com/license
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 */

import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.data.Record;
import com.smartgwt.client.types.Alignment;
import com.smartgwt.client.types.ListGridEditEvent;
import com.smartgwt.client.widgets.Label;
import com.smartgwt.client.widgets.form.DynamicForm;
import com.smartgwt.client.widgets.form.fields.SelectItem;
import com.smartgwt.client.widgets.form.fields.TextItem;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridField;
import com.smartgwt.client.widgets.grid.events.SelectionChangedHandler;
import com.smartgwt.client.widgets.grid.events.SelectionEvent;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.tab.Tab;

public class ApplicationItemDetailTabPane extends AbstractTabPane {

    private ListGrid editGrid;
    private Label editorLabel;
    private ResultsLayout resultsLayout;
    private static ListGrid relatedTableGrid;
    private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
    

    public ApplicationItemDetailTabPane(final DataSource appRestDS, final DataSource tableDS,DataSource defaultDataDS, ResultsLayout resultsLayout, final ListGrid resultsListGrid) {
        this.resultsLayout = resultsLayout;

        editorLabel = new Label();
        editorLabel.setWidth100();
        editorLabel.setHeight100();
        editorLabel.setAlign(Alignment.CENTER);
        editorLabel.setContents("Select a record to edit, or a category to insert a new record into");

        final DynamicForm orderForm = new DynamicForm();  
        orderForm.setDataSource(appRestDS); 
        orderForm.setWidth100();
        
        TextItem appId = new TextItem("id");  
        appId.setTitle("ID");  
        appId.setDisabled(true);  
        appId.setHidden(true);
        appId.setWidth("*");;
       
  
        TextItem appName = new TextItem("appName");  
        appName.setTitle(Constants.appNameLabel());
        appName.setRequired(true);
        
  
        TextItem appDesc = new TextItem("appDescription");  
        appDesc.setTitle(Constants.descriptionLabel()); 
        appDesc.setRequired(true);
        
        TextItem descTamil = new TextItem("descTamil");  
        descTamil.setTitle(Constants.descriptonTamilLabel()); 
        descTamil.setRequired(true);
        
        TextItem descTelugu = new TextItem("descTelugu");  
        descTelugu.setTitle(Constants.descriptionTeluguLabel()); 
        descTelugu.setRequired(true);
        
        SelectItem messagingFeature = new SelectItem("messagingFeature");
        messagingFeature.setTitle(Constants.messageFeatureLabel());  
        messagingFeature.setWidth(240);  
        messagingFeature.setOptionDataSource(defaultDataDS);  
        messagingFeature.setValueField("data");  
        messagingFeature.setDisplayField("data");  
        messagingFeature.setPickListWidth(450); 
        messagingFeature.setRequired(true);     
        
        SelectItem webApplication = new SelectItem("webApplication");
        webApplication.setTitle(Constants.webApplicationLabel());  
        webApplication.setWidth(240);  
        webApplication.setOptionDataSource(defaultDataDS);  
        webApplication.setValueField("data");  
        webApplication.setDisplayField("data");  
        webApplication.setPickListWidth(450); 
        webApplication.setRequired(true);  
        
        SelectItem mobileApplication = new SelectItem("mobileApplication");
        mobileApplication.setTitle(Constants.mobileApplicationLabel());  
        mobileApplication.setWidth(240);  
        mobileApplication.setOptionDataSource(defaultDataDS);  
        mobileApplication.setValueField("data");  
        mobileApplication.setDisplayField("data");  
        mobileApplication.setPickListWidth(450); 
        mobileApplication.setRequired(true);  
        
  
        orderForm.setFields(appId, appName, appDesc,descTamil,descTelugu,messagingFeature,webApplication,mobileApplication); 
        
        
        resultsListGrid.addSelectionChangedHandler(new SelectionChangedHandler() {  
            public void onSelectionChanged(SelectionEvent event) {  
                Record record = event.getRecord();  
                orderForm.editRecord(record);  
            }  
        });  
        
        
        editGrid = new ListGrid();
        editGrid.setWidth(620);  
        editGrid.setHeight(224);  
        editGrid.setShowAllRecords(true);  
        editGrid.setCellHeight(42);  
        editGrid.setWrapCells(true);  
        editGrid.setDataSource(appRestDS); 
        /*editorForm.setWidth(650);
        editorForm.setMargin(25);
        editorForm.setNumCols(4);
        editorForm.setCellPadding(5);
        editorForm.setAutoFocus(false);
        editorForm.setDataSource(supplyItemDS);
        editorForm.setUseAllDataSourceFields(true);*/

       /* TextItem name = new TextItem("appName", "Name");
        TextAreaItem description = new TextAreaItem("appDescription");
        description.setWidth(300);
        description.setRowSpan(3);*/

        /*IPickTreeItem category = new IPickTreeItem("category");
        category.setWidth(140);
        category.setDataSource(supplyCategoryDS);
        category.setEmptyMenuMessage("No Sub Categories");
        category.setCanSelectParentItems(true);

        SpinnerItem unitCost = new SpinnerItem("unitCost");
        unitCost.setStep(0.01);

        CheckboxItem inStock = new CheckboxItem("inStock");

        DateItem nextShipment = new DateItem("nextShipment");
        nextShipment.setUseTextField(true);*/
        

        editGrid.setAutoFetchData(true);  
        editGrid.setCanEdit(true);  
        editGrid.setEditEvent(ListGridEditEvent.CLICK); 
        
    /*    IButton button1 = new IButton("Delete");  
        button1.setTop(250);
        button1.setIconOrientation("right"); 
        button1.addClickHandler(new ClickHandler() {  
        	public void onClick(ClickEvent event) {  
                ListGridRecord record = new ListGridRecord();  
                record.setAttribute("id", editGrid.getSelectedRecord().getAttribute("id"));  
                editGrid.removeData(record);  
                editGrid.fetchData();
        		//editGrid.removeSelectedData();
        		resultsListGrid.removeSelectedData();
            }   
        }); */
      
       /* ToolStripButton alignLeftButton = new ToolStripButton();  
        alignLeftButton.setIcon("[SKIN]/RichTextEditor/text_align_left.png"); 
        alignLeftButton.addClickHandler(new ClickHandler() {  
        	public void onClick(ClickEvent event) {  
                ListGridRecord record = new ListGridRecord();  
                record.setAttribute("id", editGrid.getSelectedRecord().getAttribute("id"));  
                editGrid.removeData(record);  
                editGrid.fetchData();
        		//editGrid.removeSelectedData();
        		resultsListGrid.removeSelectedData();
            }   
        });
        
        ToolStripButton alignRightButton = new ToolStripButton();  
        alignRightButton.setIcon("[SKIN]/RichTextEditor/text_align_right.png"); 
        alignRightButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	//editGrid.saveAllEdits();
            	orderForm.saveData();
            }
        });
        
        IButton button = new IButton( "Save Item");
        button.setTop(250);
        button.setIconOrientation("left"); 
        button.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	//editGrid.saveAllEdits();
            	orderForm.saveData();
            }
        });
        
        
        
        ToolStripButton alignCenterButton = new ToolStripButton();  
        alignCenterButton.setIcon("[SKIN]/RichTextEditor/text_align_center.png");
        alignCenterButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	//resultsListGrid.startEditingNew();
            	final Window winModal = new Window();  
                winModal.setAutoSize(true);  
                winModal.setTitle("New Record");  
                winModal.setShowMinimizeButton(false);  
                winModal.setIsModal(true);  
                winModal.setShowModalMask(true);  
                winModal.centerInPage();  
                winModal.addCloseClickHandler(new CloseClickHandler() {  
                    public void onCloseClick(CloseClickEvent event) {  
                        winModal.destroy();  
                    }
                  });
               final DynamicForm form = new DynamicForm();  
                form.setHeight100();  
                form.setWidth100();  
                form.setPadding(5); 
                form.setDataSource(appRestDS);
                form.setLayoutAlign(VerticalAlignment.BOTTOM);  
          
                TextItem appName = new TextItem("appName");  
                appName.setTitle("Name");
                       
                TextItem appDesc = new TextItem("appDescription");  
                appDesc.setTitle("Description");
               
                IButton button = new IButton( "Save Item");
                button.setTop(250);
                button.setIconOrientation("left"); 
                button.addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                    	//editGrid.saveAllEdits();
                    	form.saveData();
                    	winModal.destroy(); 
                    }
                });
                
                form.setFields(appName,appDesc);  
                winModal.addItem(form);
                winModal.addItem(button);
                winModal.show();
               
            }  
        }); */     
        
       /* IButton button3 = new IButton("Insert");  
        button3.setTop(250);
        button3.setShowDown(true);
       // button1.setIconOrientation("right");
        button3.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
            	//resultsListGrid.startEditingNew();
            	final Window winModal = new Window();  
                winModal.setAutoSize(true);  
                winModal.setTitle("New Record");  
                winModal.setShowMinimizeButton(false);  
                winModal.setIsModal(true);  
                winModal.setShowModalMask(true);  
                winModal.centerInPage();  
                winModal.addCloseClickHandler(new CloseClickHandler() {  
                    public void onCloseClick(CloseClickEvent event) {  
                        winModal.destroy();  
                    }
                  });
               final DynamicForm form = new DynamicForm();  
                form.setHeight100();  
                form.setWidth100();  
                form.setPadding(5); 
                form.setDataSource(appRestDS);
                form.setLayoutAlign(VerticalAlignment.BOTTOM);  
          
                TextItem appName = new TextItem("appName");  
                appName.setTitle("Name");
                       
                TextItem appDesc = new TextItem("appDescription");  
                appDesc.setTitle("Description");
               
                IButton button = new IButton( "Save Item");
                button.setTop(250);
                button.setIconOrientation("left"); 
                button.addClickHandler(new ClickHandler() {
                    public void onClick(ClickEvent event) {
                    	//editGrid.saveAllEdits();
                    	form.saveData();
                    	winModal.destroy(); 
                    }
                });
                
                form.setFields(appName,appDesc);  
                winModal.addItem(form);
                winModal.addItem(button);
                winModal.show();
               
            }  
        });      
         */
        EditPanelToolStrip toolStrip = new EditPanelToolStrip(orderForm);
      
        
      //  editGrid.setFields(nameField, descriptionField,descriptionTamilFiled); 

     //   editGrid.draw();  

       /* ButtonItem saveButton = new ButtonItem("saveItem", "Save Item");
        saveButton.setAlign(Alignment.LEFT);
        saveButton.setWidth(100);
        saveButton.setColSpan(4);
        saveButton.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                editorForm.saveData();
            }
        });

        editorForm.setFields(name, description, saveButton);
        editorForm.setColWidths(100, 200, 100);*/
        
        relatedTableGrid = new ListGrid();
        relatedTableGrid.setWidth100();  
        relatedTableGrid.setHeight100();  
        relatedTableGrid.setShowAllRecords(true);  
        relatedTableGrid.setCellHeight(42);  
        relatedTableGrid.setWrapCells(true);  
        relatedTableGrid.setDataSource(tableDS); 
        

        ListGridField groupField = new ListGridField("appName",Constants.appNameLabel());
        groupField.setCanEdit(false);
        
        ListGridField labelField = new ListGridField("tableName",Constants.tableNameLabel());
        labelField.setCanEdit(false);

        ListGridField labelField1 = new ListGridField("tableDescription",Constants.descriptionLabel());
        labelField1.setCanEdit(false);
        
        relatedTableGrid.setFields(groupField,labelField,labelField1);  
        
        relatedTableGrid.setAutoFetchData(true);  

        Tab editTab = new Tab(Constants.editTabLabel());
        editTab.setIcon("demoApp/icon_edit.png");
        editTab.setWidth(70);
      //  editTab.setPane(editGrid);
        
        VLayout layout = new VLayout();
        //layout.addMember(editGrid);
       layout.addMember(toolStrip);
        layout.addMember(orderForm);
       /* layout.addMember(button);
        layout.addMember(button1);
        layout.addMember(button3);*/
        editTab.setPane(layout);
        
        

        Tab tableTab = new Tab(Constants.relatedTableTabLabel());
        tableTab.setWidth(70);
        tableTab.setPane(relatedTableGrid);
        
        
        setTabs(editTab,tableTab);

       /* addTabSelectedHandler(new TabSelectedHandler() {
            public void onTabSelected(TabSelectedEvent event) {
                updateDetails();
            }
        });*/
    }

	public static ListGrid getRelatedTableGrid() {
		return relatedTableGrid;
	}

	public void setRelatedTableGrid(ListGrid relatedTableGrid) {
		this.relatedTableGrid = relatedTableGrid;
	}

/*
    public void clearDetails(Record selectedCategoryRecord) {
        int selectedTab = getSelectedTabNumber();
        if (selectedTab == 0) {
            //view tab : show empty message
            itemViewer.setData((Record[]) null);
        } else {
            // edit tab : show new record editor, or empty message
            if (selectedCategoryRecord != null) {
                updateTab(1, editorForm);
                Map initialValues = new HashMap();
                initialValues.put("category", selectedCategoryRecord.getAttribute("categoryName"));
                editorForm.editNewRecord(initialValues);
            } else {
                updateTab(1, editorLabel);
            }
        }
    }*/
    

   /* public void updateDetails() {
        Record selectedRecord  = vLayout.getResultGrid().getSelectedRecord();
        int selectedTab = getSelectedTabNumber();
        if (selectedTab == 0) {
            //view tab : show empty message
            itemViewer.setData(new Record[]{selectedRecord});
        } else {
            // edit tab : show record editor
            editorForm.editRecord(selectedRecord);
        }
    }*/
}

