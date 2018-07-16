package com.mycompany.client;

import com.google.gwt.core.client.EntryPoint;

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

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FilterCriteriaFunction;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;
import com.smartgwt.client.widgets.layout.VLayout;
import com.smartgwt.client.widgets.menu.Menu;

public class Application extends VLayout implements EntryPoint {
    private SearchForm searchForm;
    private NavigatorTreeGrid navigatorTree;
    private ApplicationListGrid applicationListGrid;
    private TableListGrid tableListGrid;
    private ApplicationRestDS appRestDS;
    private TableRestDS tableRestDS;
    private TableScriptRestDS tableScriptDS;
    private ColumnRestDS columnDS;
    private ReferenceRestDS referenceRestDS;
    private ReferenceDataRestDS referenceDataRestDS;
    private RelationshipRestDS relationRestDS;
    private Menu itemListMenu;
    private GridEditor editGrid;
    private ResultsLayout resultsLayout;
    private EditLayout editLayout;
    private HLayout mainLayout;
    private MainToolBar mainMenu;
    private MainTabSet mainTabSet;
    
    public void onModuleLoad() {
    	
    	
       	mainLayout = new HLayout(); 
    	mainLayout.setWidth100();
    	mainLayout.setHeight100();
    	mainLayout.setLayoutMargin(10);
/*
        DataSource appRestDS = new ApplicationRestDS();
        DataSource repoRestDS = RepositoryRestDS.getInstance();
        DataSource supplyItemDS = ItemSupplyXmlDS.getInstance();
        DataSource tableDS = new TableRestDS();
        DataSource columnDS = new ColumnRestDS();
        
        
        navigatorTree = new NavigatorTreeGrid(repoRestDS);
        navigatorTree.setAutoFetchData(true);
        
        navigatorTree.addRecordClickHandler(new RecordClickHandler() {
            public void onRecordClick(RecordClickEvent event) {
              String objectType = event.getRecord().getAttribute("objectType");  
              findObjects(objectType);
            }
        });
        
     
        RPCManager.setAllowCrossDomainCalls(true);

        searchForm = new SearchForm(supplyItemDS);

        //when showing options in the combo-box, only show the options from the selected category if appropriate
        final ComboBoxItem itemNameCB = searchForm.getItemNameField();
        itemNameCB.setPickListFilterCriteriaFunction(new FilterCriteriaFunction() {
            public Criteria getCriteria() {
                ListGridRecord record = navigatorTree.getSelectedRecord();
                if ((itemNameCB.getValue() != null) && record != null) {
                    Criteria criteria = new Criteria();
                    criteria.addCriteria("category", record.getAttribute("categoryName"));
                    return criteria;
                }
                return null;
            }
        });

      //  setupContextMenu();

        
        resultsLayout = new ResultsLayout();
        editLayout = new EditLayout();
        //resultsLayout.setWidth100();
        //resultsLayout.setHeight100();
        applicationListGrid = new ApplicationListGrid(appRestDS);
        applicationListGrid.setWidth100();
        applicationListGrid.setHeight100();
        applicationListGrid.setCanEdit(true);
        
        tableListGrid = new TableListGrid(tableDS);
        tableListGrid.setWidth100();
        tableListGrid.setHeight100();
        tableListGrid.setCanEdit(true);
        
         applicationListGrid.addRecordClickHandler(new RecordClickHandler() {
        public void onRecordClick(RecordClickEvent event) {
            
            
            Criteria criteria = new Criteria();
            
            criteria.addCriteria("appName", applicationListGrid.getSelectedRecord().getAttribute("appName"));
            
            //itemDetailTabPane.getRelatedTableGrid().getDataSource().filterData(criteria);
            //itemDetailTabPane.getRelatedTableGrid().filterData(criteria);
        }
    });
        

        applicationListGrid.addCellSavedHandler(new CellSavedHandler() {
            public void onCellSaved(CellSavedEvent event) {
                itemDetailTabPane.updateDetails();
            }
        });

        applicationListGrid.addCellContextClickHandler(new CellContextClickHandler() {
            public void onCellContextClick(CellContextClickEvent event) {
                itemListMenu.showContextMenu();
                event.cancel();
            }
        });


        SectionStack leftSideLayout = new SectionStack();
        leftSideLayout.setWidth(280);
        leftSideLayout.setShowResizeBar(true);
        leftSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
        leftSideLayout.setAnimateSections(true);

        SectionStackSection suppliesCategorySection = new SectionStackSection("Objects");
        suppliesCategorySection.setExpanded(true);
        suppliesCategorySection.setItems(navigatorTree);

        leftSideLayout.setSections(suppliesCategorySection);

        SectionStack rightSideLayout = new SectionStack();
        rightSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
        rightSideLayout.setAnimateSections(true);


        searchForm.setHeight(60);
        searchForm.addFindListener(new com.smartgwt.client.widgets.form.fields.events.ClickHandler() {
            public void onClick(com.smartgwt.client.widgets.form.fields.events.ClickEvent event) {
                findItems(null);
            }
        });

        
        resultsLayout.addChild(applicationListGrid);
        
       
        SectionStackSection supplyItemsSection = new SectionStackSection("Results");
        supplyItemsSection.setItems(resultsLayout);
        supplyItemsSection.setExpanded(true);

        
        SectionStackSection itemDetailsSection = new SectionStackSection("Entity Details");

        
        editLayout.addChild(new ApplicationItemDetailTabPane(appRestDS, tableRestDS, resultsLayout,applicationListGrid));
        
        itemDetailsSection.setItems(editLayout);
        itemDetailsSection.setExpanded(true);
        

        rightSideLayout.setSections(supplyItemsSection, itemDetailsSection);

        mainLayout.addMember(leftSideLayout);
        mainLayout.addMember(rightSideLayout);*/
        
        mainMenu = new MainToolBar();
        
/*        VLayout layout = new VLayout();  
        layout.setWidth100();  
        layout.setHeight100();  
        layout.setMembersMargin(10);  
  
        ApplicationInstance app = new ApplicationInstance();
        mainTabSet = new MainTabSet(app);
        
        
        layout.addMember(mainMenu);
        layout.addMember(mainTabSet);
        
        
        layout.draw();*/
        this.setWidth100();  
        this.setHeight100();  
        this.setMembersMargin(10);  
  
        ApplicationInstance app = new ApplicationInstance();
        mainTabSet = new MainTabSet(app);
        
        
        
        
        this.addMember(mainMenu);
        this.addMember(mainTabSet);
        
        
        this.draw();        
        
    }

/*   private void setupContextMenu() {
        itemListMenu = new Menu();
        itemListMenu.setCellHeight(22);

        MenuItem detailsMenuItem = new MenuItem("Show Details", "silk/application_form.png");
        detailsMenuItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                itemDetailTabPane.selectTab(0);
       //         itemDetailTabPane.updateDetails();
            }
        });

        final MenuItem editMenuItem = new MenuItem("Edit Item", "demoApp/icon_edit.png");
        editMenuItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
                itemDetailTabPane.selectTab(1);
               // itemDetailTabPane.updateDetails();
            }
        });

        final MenuItem deleteMenuItem = new MenuItem("Delete Item", "silk/delete.png");
        deleteMenuItem.addClickHandler(new com.smartgwt.client.widgets.menu.events.ClickHandler() {
            public void onClick(MenuItemClickEvent event) {
            	applicationListGrid.removeSelectedData();
             //   itemDetailTabPane.clearDetails(null);
            }
        });

        itemListMenu.setData(detailsMenuItem, editMenuItem, deleteMenuItem);
    }*/


    public void findItems(String categoryName) {

        Criteria findValues;

        String formValue = searchForm.getValueAsString("findInCategory");
        ListGridRecord selectedCategory = navigatorTree.getSelectedRecord();
        if (formValue != null && selectedCategory != null) {
            categoryName = selectedCategory.getAttribute("categoryName");
            findValues = searchForm.getValuesAsCriteria();
            findValues.addCriteria("category", categoryName);

        } else if (categoryName == null) {
            findValues = searchForm.getValuesAsCriteria();
        } else {
            findValues = new Criteria();
            findValues.addCriteria("category", categoryName);
        }

        applicationListGrid.filterData(findValues);
      //  itemDetailTabPane.clearDetails(navigatorTree.getSelectedRecord());
    }

    public void findObjects(String objectType) {

/*        Criteria findValues;

            findValues = new Criteria();
            findValues.addCriteria("objectType", objectType);
            applicationListGrid.refreshData();*/
    	
    	if(objectType.equals("application"))
    	{	
            //DataSource tableDS = ApplicationDS.getInstance();
    		DataSource appDS = new ApplicationRestDS();
    		 DataSource tableDS = new TableRestDS();
    		 DataSource defaultDataDS =DefaultDataDS.getInstance();
            ApplicationListGrid listGrid1 = new ApplicationListGrid(appDS);
            listGrid1.setWidth100();
            listGrid1.setHeight100();
            listGrid1.setCanEdit(true);
            resultsLayout.removeChild(resultsLayout.getMember(0));
            resultsLayout.addChild(listGrid1);
            editLayout.removeChild(editLayout.getMember(0));
            editLayout.addChild(new ApplicationItemDetailTabPane(appDS, tableDS,defaultDataDS, resultsLayout,listGrid1));
           
    	}
    	else if(objectType.equals("table"))
    	{	
    		DataSource appRestDS = new ApplicationRestDS();
            DataSource tableDS = new TableRestDS();
            DataSource columnDS = new ColumnRestDS();
            TableScriptRestDS tableScriptDS = new TableScriptRestDS();
            TableListGrid listGrid = new TableListGrid(tableDS);
            listGrid.setWidth100();
            listGrid.setHeight100();
            resultsLayout.removeChild(resultsLayout.getMember(0));
            resultsLayout.addChild(listGrid);
            editLayout.removeChild(editLayout.getMember(0));
            editLayout.addChild(new TableItemDetailTabPane(appRestDS,tableDS,tableScriptDS, columnDS, resultsLayout,listGrid));
    	}
    	else if(objectType.equals("column"))
    	{
    		 DataSource columnDS = new ColumnRestDS();
    		 DataSource tableDS = new TableRestDS();
    		 DataSource referenceDS = new ReferenceRestDS();
    		 DataSource columnTypeDS =ColumnTypeDS.getInstance();
    		 ColumnScriptRestDS columnScriptDS = new ColumnScriptRestDS();
    		 DataSource defaultDataDS =DefaultDataDS.getInstance();
             ColumnListGrid listGrid = new ColumnListGrid(columnDS);
             listGrid.setWidth100();
             listGrid.setHeight100();    
             resultsLayout.removeChild(resultsLayout.getMember(0));
             resultsLayout.addChild(listGrid);
             editLayout.removeChild(editLayout.getMember(0));
             editLayout.addChild(new ColumnItemDetailTabPane(columnDS,tableDS,referenceDS,columnTypeDS,defaultDataDS,columnScriptDS, resultsLayout,listGrid));
     
             
    	}
    	else if(objectType.equals("relation"))
    	{
    		 DataSource relationDS = new RelationshipRestDS();
    		 DataSource tableDS = new TableRestDS();
    		 RelationshipListGrid listGrid = new RelationshipListGrid(relationDS);
             listGrid.setWidth100();
             listGrid.setHeight100(); 
             resultsLayout.removeChild(resultsLayout.getMember(0));
             resultsLayout.addChild(listGrid);
             editLayout.removeChild(editLayout.getMember(0));
             editLayout.addChild(new RelationItemDetailTabPane(relationDS,tableDS, resultsLayout,listGrid));
     
    	}
    	else if(objectType.equals("reference"))
    	{
    		 DataSource referenceDS = new ReferenceRestDS();
    		 DataSource referenceDataDS = new ReferenceDataRestDS();
    		 ReferenceListGrid listGrid = new ReferenceListGrid(referenceDS);
             listGrid.setWidth100();
             listGrid.setHeight100();   
             resultsLayout.removeChild(resultsLayout.getMember(0));
             resultsLayout.addChild(listGrid);
             editLayout.removeChild(editLayout.getMember(0));
             editLayout.addChild(new ReferenceItemDetailTabPane(referenceDS,referenceDataDS, resultsLayout,listGrid));
     
    	}
    	else if(objectType.equals("referencedata"))
    	{
    		 DataSource referenceDataDS = new ReferenceDataRestDS();
    		 ReferenceDataListGrid listGrid = new ReferenceDataListGrid(referenceDataDS);
             listGrid.setWidth100();
             listGrid.setHeight100();        
             resultsLayout.addChild(listGrid);
    	}
    	
    }

    
    
}

