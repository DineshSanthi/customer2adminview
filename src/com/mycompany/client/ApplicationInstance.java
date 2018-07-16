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

import com.smartgwt.client.data.Criteria;
import com.smartgwt.client.data.DataSource;
import com.smartgwt.client.rpc.RPCManager;
import com.smartgwt.client.types.VisibilityMode;
import com.smartgwt.client.widgets.form.fields.ComboBoxItem;
import com.smartgwt.client.widgets.form.fields.FilterCriteriaFunction;
import com.smartgwt.client.widgets.grid.ListGrid;
import com.smartgwt.client.widgets.grid.ListGridRecord;
import com.smartgwt.client.widgets.grid.events.RecordClickEvent;
import com.smartgwt.client.widgets.grid.events.RecordClickHandler;
import com.smartgwt.client.widgets.layout.HLayout;
import com.smartgwt.client.widgets.layout.SectionStack;
import com.smartgwt.client.widgets.layout.SectionStackSection;

public class ApplicationInstance extends HLayout{
    private SearchForm searchForm;
    private NavigatorTreeGrid navigatorTree;
    private ListGrid resultsListGrid;
	private TableListGrid tableListGrid;
    private TableRestDS tableRestDS;
    private TableScriptRestDS tableScriptDS;
    private ResultsLayout resultsLayout;
    private EditLayout editLayout;
    private ResultsPanelToolStrip resultsPanelToolStrip;
    private static final ApplicationMessages Constants = ApplicationMessages.INSTANCE;
    
    public ApplicationInstance() {
    	 
    	this.setWidth100();
    	this.setHeight100();
    	this.setLayoutMargin(10);

        DataSource appRestDS = new ApplicationRestDS();
        DataSource repoRestDS = RepositoryRestDS.getInstance();
        DataSource supplyItemDS = ItemSupplyXmlDS.getInstance();
        DataSource tableDS = new TableRestDS();
        DataSource defaultDataDS = DefaultDataDS.getInstance();
        
        
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
        resultsListGrid = new ApplicationListGrid(appRestDS);
        resultsListGrid.setWidth100();
        resultsListGrid.setHeight100();
        resultsListGrid.setCanEdit(true);
        
        tableListGrid = new TableListGrid(tableDS);
        tableListGrid.setWidth100();
        tableListGrid.setHeight100();
        tableListGrid.setCanEdit(true);
        
       resultsListGrid.addRecordClickHandler(new RecordClickHandler() {
        public void onRecordClick(RecordClickEvent event) {
            Criteria criteria = new Criteria();
            criteria.addCriteria("appName", resultsListGrid.getSelectedRecord().getAttribute("appName"));
            //itemDetailTabPane.getRelatedTableGrid().getDataSource().filterData(criteria);
            ApplicationItemDetailTabPane.getRelatedTableGrid().filterData(criteria);
        }
    });
        

/*        applicationListGrid.addCellSavedHandler(new CellSavedHandler() {
            public void onCellSaved(CellSavedEvent event) {
                itemDetailTabPane.updateDetails();
            }
        });

        applicationListGrid.addCellContextClickHandler(new CellContextClickHandler() {
            public void onCellContextClick(CellContextClickEvent event) {
                itemListMenu.showContextMenu();
                event.cancel();
            }
        });*/


        SectionStack leftSideLayout = new SectionStack();
        leftSideLayout.setWidth(280);
        leftSideLayout.setShowResizeBar(true);
        leftSideLayout.setVisibilityMode(VisibilityMode.MULTIPLE);
        leftSideLayout.setAnimateSections(true);

        SectionStackSection suppliesCategorySection = new SectionStackSection(Constants.categorySectionTitle());
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

        resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
        
        resultsLayout.addMember(resultsPanelToolStrip);
        resultsLayout.addMember(resultsListGrid);
        
       
        SectionStackSection supplyItemsSection = new SectionStackSection(Constants.resultSectionTitle());
        supplyItemsSection.setItems(resultsLayout);
        supplyItemsSection.setExpanded(true);

        
        SectionStackSection itemDetailsSection = new SectionStackSection(Constants.detailSectionTitle());

        
        editLayout.addMember(new ApplicationItemDetailTabPane(appRestDS, tableDS,defaultDataDS, resultsLayout,resultsListGrid));
        
        itemDetailsSection.setItems(editLayout);
        itemDetailsSection.setExpanded(true);
        

        rightSideLayout.setSections(supplyItemsSection, itemDetailsSection);

        this.addMember(leftSideLayout);
        this.addMember(rightSideLayout);        
        
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

        resultsListGrid.filterData(findValues);
      //  itemDetailTabPane.clearDetails(navigatorTree.getSelectedRecord());
    }

    public void findObjects(String objectType) {

        resultsLayout.removeMember(resultsPanelToolStrip);
        resultsLayout.removeMember(resultsListGrid);
    	
    	if(objectType.equals("application"))
    	{	
            //DataSource tableDS = ApplicationDS.getInstance();
    		DataSource appDS = new ApplicationRestDS();
    		DataSource tableDS = new TableRestDS();
    		DataSource defaultDataDS = DefaultDataDS.getInstance();
    		resultsListGrid = new ApplicationListGrid(appDS);
    		resultsListGrid.addRecordClickHandler(new RecordClickHandler() {
    			public void onRecordClick(RecordClickEvent event) {
    		            Criteria criteria = new Criteria();
    		            criteria.addCriteria("appName", resultsListGrid.getSelectedRecord().getAttribute("appName"));
    		            //itemDetailTabPane.getRelatedTableGrid().getDataSource().filterData(criteria);
    		            ApplicationItemDetailTabPane.getRelatedTableGrid().filterData(criteria);
    		        }
    		    });
            resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
            editLayout.removeMember(editLayout.getMember(0));
            editLayout.addMember(new ApplicationItemDetailTabPane(appDS, tableDS,defaultDataDS, resultsLayout,resultsListGrid));
    	}
    	else if(objectType.equals("table"))
    	{	
    		DataSource appRestDS = new ApplicationRestDS();
            DataSource tableDS = new TableRestDS();
            DataSource columnDS = new ColumnRestDS();
            TableScriptRestDS tableScriptDS1 =new TableScriptRestDS();
            resultsListGrid = new TableListGrid(tableDS);
            resultsListGrid.addRecordClickHandler(new RecordClickHandler() {
    			public void onRecordClick(RecordClickEvent event) {
    		            Criteria criteria = new Criteria();
    		            criteria.addCriteria("tableName", resultsListGrid.getSelectedRecord().getAttribute("id"));
    		            //itemDetailTabPane.getRelatedTableGrid().getDataSource().filterData(criteria);
    		            TableItemDetailTabPane.getRelatedColumnGrid().filterData(criteria);
    		        }
    		    });
            resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
            editLayout.removeMember(editLayout.getMember(0));
            editLayout.addMember(new TableItemDetailTabPane(appRestDS,tableDS,tableScriptDS1, columnDS, resultsLayout,resultsListGrid));
    	}
    	else if(objectType.equals("column"))
    	{
    		 DataSource columnDS = new ColumnRestDS();
    		 DataSource tableDS = new TableRestDS();
    		 DataSource referenceDS = new ReferenceRestDS();
    		 DataSource columnTypeDS =ColumnTypeDS.getInstance();
    		 ColumnScriptRestDS columnScriptDS = new ColumnScriptRestDS();
    		 DataSource defaultDataDS =DefaultDataDS.getInstance();
    		 resultsListGrid = new ColumnListGrid(columnDS);
    		 resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
             editLayout.removeMember(editLayout.getMember(0));
             editLayout.addMember(new ColumnItemDetailTabPane(columnDS,tableDS,referenceDS,columnTypeDS,defaultDataDS,columnScriptDS, resultsLayout,resultsListGrid));
    	}
    	else if(objectType.equals("relation"))
    	{
    		 DataSource relationDS = new RelationshipRestDS();
    		 DataSource tableDS = new TableRestDS();
    		 resultsListGrid = new RelationshipListGrid(relationDS);
    		 resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
             editLayout.removeMember(editLayout.getMember(0));
             editLayout.addMember(new RelationItemDetailTabPane(relationDS,tableDS, resultsLayout,resultsListGrid));
    	}
    	else if(objectType.equals("reference"))
    	{
    		 DataSource referenceDS = new ReferenceRestDS();
    		 DataSource referenceDataDS = new ReferenceDataRestDS();
    		 resultsListGrid= new ReferenceListGrid(referenceDS);
    		 resultsListGrid.addRecordClickHandler(new RecordClickHandler() {
        			public void onRecordClick(RecordClickEvent event) {
        		            Criteria criteria = new Criteria();
        		            criteria.addCriteria("refId", resultsListGrid.getSelectedRecord().getAttribute("id"));
        		            //itemDetailTabPane.getRelatedTableGrid().getDataSource().filterData(criteria);
        		            ReferenceItemDetailTabPane.getRelatedDataGrid().filterData(criteria);
        		        }
        		    });
    		 resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
             editLayout.removeMember(editLayout.getMember(0));
             editLayout.addMember(new ReferenceItemDetailTabPane(referenceDS,referenceDataDS, resultsLayout,resultsListGrid));
    	}
    	else if(objectType.equals("tableScript"))
    	{
    		 DataSource tableScriptDS = new TableScriptRestDS();
    		 DataSource tableDS = new TableRestDS();
    		 resultsListGrid = new TableScriptListGrid(tableScriptDS);
    		 resultsPanelToolStrip = new ResultsPanelToolStrip(resultsListGrid);
             editLayout.removeMember(editLayout.getMember(0));
             editLayout.addMember(new TableScriptItemDetailTabPane(tableScriptDS,tableDS,resultsLayout,resultsListGrid));
    	}
    	else if(objectType.equals("referencedata"))
    	{
    		 DataSource referenceDataDS = new ReferenceDataRestDS();
    		 ReferenceDataListGrid listGrid = new ReferenceDataListGrid(referenceDataDS);
             listGrid.setWidth100();
             listGrid.setHeight100();        
             resultsLayout.removeMember(listGrid);
             
    	}
    	
        resultsLayout.addMember(resultsPanelToolStrip);
        resultsLayout.addMember(resultsListGrid);

    	
    }

    
    
}

